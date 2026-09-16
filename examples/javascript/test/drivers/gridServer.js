const { spawn } = require('node:child_process')
const http = require('node:http')
const path = require('node:path')
const portprober = require('selenium-webdriver/net/portprober')

const SERVER_JAR = path.join(__dirname, '..', '..', '..', 'selenium-server-4.46.0.jar')

function waitForServer(url, timeout) {
  const deadline = Date.now() + timeout

  return new Promise((resolve, reject) => {
    function attempt() {
      http
        .get(url, (res) => {
          res.resume()
          resolve()
        })
        .on('error', () => {
          if (Date.now() > deadline) {
            reject(new Error(`Selenium server did not start within ${timeout}ms`))
            return
          }
          setTimeout(attempt, 200)
        })
    }
    attempt()
  })
}

async function startGrid() {
  const port = await portprober.findFreePort()
  const child = spawn('java', [
    '-jar',
    SERVER_JAR,
    'standalone',
    '--port',
    String(port),
    '--selenium-manager',
    'true',
    '--enable-managed-downloads',
    'true',
    '--log-level',
    'WARNING',
  ])

  const url = `http://localhost:${port}`
  await waitForServer(`${url}/status`, 60000)

  return { url, process: child }
}

function stopGrid(child) {
  if (child) {
    child.kill()
  }
}

module.exports = { startGrid, stopGrid }

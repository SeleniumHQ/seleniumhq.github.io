const { spawn } = require('node:child_process')
const http = require('node:http')
const path = require('node:path')
const portprober = require('selenium-webdriver/net/portprober')

const SERVER_JAR = path.join(__dirname, '..', '..', '..', 'selenium-server-4.49.0.jar')

function waitForServer(url, timeout) {
  const deadline = Date.now() + timeout

  return new Promise((resolve, reject) => {
    function retryOrFail() {
      if (Date.now() > deadline) {
        reject(new Error(`Selenium server did not start within ${timeout}ms`))
        return
      }
      setTimeout(attempt, 200)
    }

    function attempt() {
      const req = http.get(url, { timeout: 2000 }, (res) => {
        let body = ''
        res.on('data', (chunk) => (body += chunk))
        res.on('end', () => {
          try {
            const { value } = JSON.parse(body)
            if (res.statusCode === 200 && value && value.ready) {
              resolve()
              return
            }
          } catch (err) {
            // Not valid JSON yet (e.g. server still booting) - fall through to retry.
          }
          retryOrFail()
        })
        res.on('aborted', retryOrFail)
      })
      req.on('timeout', () => {
        req.destroy()
        retryOrFail()
      })
      req.on('error', retryOrFail)
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
  try {
    await waitForServer(`${url}/status`, 60000)
  } catch (err) {
    child.kill()
    throw err
  }

  return { url, process: child }
}

function stopGrid(child) {
  if (child) {
    child.kill()
  }
}

module.exports = { startGrid, stopGrid }

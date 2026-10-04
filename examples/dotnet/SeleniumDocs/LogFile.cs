using System;
using System.Collections.Generic;
using System.IO;
using System.Threading;

namespace SeleniumDocs
{
    // Driver log files can still be open by the driver process after Quit(),
    // especially on Windows. Share the file instead of requesting exclusive access.
    public static class LogFile
    {
        public static string[] ReadLines(string path)
        {
            using var stream = new FileStream(path, FileMode.Open, FileAccess.Read,
                FileShare.ReadWrite | FileShare.Delete);
            using var reader = new StreamReader(stream);
            var lines = new List<string>();
            string line;
            while ((line = reader.ReadLine()) != null)
            {
                lines.Add(line);
            }
            return lines.ToArray();
        }

        public static void TryDelete(string path)
        {
            const int maxAttempts = 10;
            for (var attempt = 1; attempt <= maxAttempts; attempt++)
            {
                try
                {
                    File.Delete(path);
                    return;
                }
                catch (IOException) when (attempt < maxAttempts)
                {
                    // Still held by the driver process; give it time to exit.
                    Thread.Sleep(200);
                }
                catch (IOException e)
                {
                    Console.Error.WriteLine($"Could not delete driver log {path}: {e.Message}");
                }
            }
        }
    }
}

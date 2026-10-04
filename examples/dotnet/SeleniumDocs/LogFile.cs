using System.Collections.Generic;
using System.IO;

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
            try
            {
                File.Delete(path);
            }
            catch (IOException)
            {
                // Still held by the driver process; the OS temp directory cleans it up.
            }
        }
    }
}

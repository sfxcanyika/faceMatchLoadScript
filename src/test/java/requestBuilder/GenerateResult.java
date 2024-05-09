package requestBuilder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class GenerateResult {
    private static final int MAX_RETRIES = 3;
    private static final long RETRY_INTERVAL_MS = 1000;
    public static void writeToCSVFile(String pathname, String[] values) {
        int retryCount = 0;
        boolean fileWritten = false;
        while (!fileWritten && retryCount < MAX_RETRIES) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathname, true))) {
                for (int i = 0; i < values.length; i++) {
                    writer.write(values[i]);
                    if (i != values.length - 1) {
                        writer.write(","); // Add comma except for the last element
                    }
                }
                writer.newLine();
                fileWritten = true;
            } catch (IOException e) {
                retryCount++;
                try {
                    TimeUnit.MILLISECONDS.sleep(RETRY_INTERVAL_MS);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                e.printStackTrace();
            }
        }

    }
}

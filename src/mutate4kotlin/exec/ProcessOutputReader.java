package mutate4kotlin.exec;

import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

final class ProcessOutputReader {

    private final InputStream inputStream;
    private final Thread readerThread;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    private ProcessOutputReader(Process process) {
        this.inputStream = process.getInputStream();
        this.readerThread = new Thread(this::readOutput, "mutate4kotlin-process-output-reader");
        this.readerThread.setDaemon(true);
        this.readerThread.start();
    }

    static ProcessOutputReader start(Process process) {
        return new ProcessOutputReader(process);
    }

    String await(long timeoutMillis) throws InterruptedException {
        if (timeoutMillis <= 0) {
            readerThread.join();
        } else {
            readerThread.join(timeoutMillis);
        }
        synchronized (output) {
            return output.toString(Charset.defaultCharset());
        }
    }

    void close() {
        try {
            inputStream.close();
        } catch (IOException ignored) {
        }
    }

    private void readOutput() {
        try {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) >= 0) {
                synchronized (output) {
                    output.write(buffer, 0, bytesRead);
                }
            }
        } catch (IOException ignored) {
        }
    }
}

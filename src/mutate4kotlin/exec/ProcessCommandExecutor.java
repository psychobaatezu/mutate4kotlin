package mutate4kotlin.exec;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class ProcessCommandExecutor {

    private final ProcessRunnerSupport support = new ProcessRunnerSupport();
    public CommandResult run(List<String> command, Path workingDirectory) throws IOException, InterruptedException {
        return run(command, workingDirectory, 0);
    }

    public CommandResult run(List<String> command, Path workingDirectory, long timeoutMillis) throws IOException, InterruptedException {
        long start = System.nanoTime();
        Process process = startProcess(command, workingDirectory);
        boolean timedOut = !support.waitFor(process, timeoutMillis);
        int exitCode = support.exitCode(process, timedOut);
        String output = support.readOutput(process);
        long durationMillis = (System.nanoTime() - start) / 1_000_000L;
        return new CommandResult(exitCode, output, durationMillis, timedOut);
    }

    private Process startProcess(List<String> command, Path workingDirectory) throws IOException {
        return new ProcessBuilder(command)
                .directory(workingDirectory.toFile())
                .redirectErrorStream(true)
                .start();
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=c2fc085afd863bb258b9478ee8d41e5678bc6c6713f1c0188601a1371cec936f
scope.0.id=Y2xhc3M6UHJvY2Vzc0NvbW1hbmRFeGVjdXRvciNQcm9jZXNzQ29tbWFuZEV4ZWN1dG9yOjE1
scope.0.kind=class
scope.0.startLine=15
scope.0.endLine=38
scope.0.semanticHash=a456b476b1e8c7dcd54f79d9d854538774aba24101b7729ee3e915bc03df1c14
scope.1.id=ZmllbGQ6UHJvY2Vzc0NvbW1hbmRFeGVjdXRvciNzdXBwb3J0OjE3
scope.1.kind=field
scope.1.startLine=17
scope.1.endLine=17
scope.1.semanticHash=a4f5b31be2e3da953e96338818db19f8a3e0bc792967a04343ddfff56409f7ce
scope.2.id=bWV0aG9kOlByb2Nlc3NDb21tYW5kRXhlY3V0b3IjY3RvcigwKToxNQ
scope.2.kind=method
scope.2.startLine=1
scope.2.endLine=38
scope.2.semanticHash=c0484d49175ea421d6faccf6ff5082e4c0566bf5b9f6e34fc64d16f3fba20d3e
scope.3.id=bWV0aG9kOlByb2Nlc3NDb21tYW5kRXhlY3V0b3IjcnVuKDIpOjE4
scope.3.kind=method
scope.3.startLine=18
scope.3.endLine=20
scope.3.semanticHash=df72bc6487a3227361c5adc9248fe3f97f0387e11538aaf30e8505fc5bab6c77
scope.4.id=bWV0aG9kOlByb2Nlc3NDb21tYW5kRXhlY3V0b3IjcnVuKDMpOjIy
scope.4.kind=method
scope.4.startLine=22
scope.4.endLine=30
scope.4.semanticHash=c44e53b66bc9ae6699013f80d2fef21dceb951065141aa7163d0f5f80a18644e
scope.5.id=bWV0aG9kOlByb2Nlc3NDb21tYW5kRXhlY3V0b3Ijc3RhcnRQcm9jZXNzKDIpOjMy
scope.5.kind=method
scope.5.startLine=32
scope.5.endLine=37
scope.5.semanticHash=5360185079dacc7d29473b03f8faa1d9e4421c8aaf9426ca4cd9b997228a5a9a
*/

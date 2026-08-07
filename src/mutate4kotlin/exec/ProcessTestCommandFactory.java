package mutate4kotlin.exec;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

final class ProcessTestCommandFactory {

    private ProcessTestCommandFactory() {
    }

    static Process startProcess(Path projectRoot, List<String> command) throws IOException {
        return new ProcessBuilder(command)
                .directory(projectRoot.toFile())
                .redirectErrorStream(true)
                .start();
    }

    static Process startShellProcess(Path projectRoot, String commandText) throws IOException {
        return new ProcessBuilder("/bin/sh", "-lc", commandText)
                .directory(projectRoot.toFile())
                .redirectErrorStream(true)
                .start();
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=28a2631e7ef921c26874643940f6277b012baf2b302970110b4d754837610b45
scope.0.id=Y2xhc3M6UHJvY2Vzc1Rlc3RDb21tYW5kRmFjdG9yeSNQcm9jZXNzVGVzdENvbW1hbmRGYWN0b3J5OjE0
scope.0.kind=class
scope.0.startLine=14
scope.0.endLine=32
scope.0.semanticHash=d8a8580c7fa863e564237d10975ca83eb3255e747167bfb557f85b428b0fbdac
scope.1.id=bWV0aG9kOlByb2Nlc3NUZXN0Q29tbWFuZEZhY3RvcnkjY3RvcigwKToxNg
scope.1.kind=method
scope.1.startLine=16
scope.1.endLine=17
scope.1.semanticHash=b297879dffebffd5b2b9429512507714cce0ffde65f5c97df0dc5c2318ea0559
scope.2.id=bWV0aG9kOlByb2Nlc3NUZXN0Q29tbWFuZEZhY3Rvcnkjc3RhcnRQcm9jZXNzKDIpOjE5
scope.2.kind=method
scope.2.startLine=19
scope.2.endLine=24
scope.2.semanticHash=1f718134946bdb1af0c0e4ea6073612640f9d23c38bcec2296698297e4be779a
scope.3.id=bWV0aG9kOlByb2Nlc3NUZXN0Q29tbWFuZEZhY3Rvcnkjc3RhcnRTaGVsbFByb2Nlc3MoMik6MjY
scope.3.kind=method
scope.3.startLine=26
scope.3.endLine=31
scope.3.semanticHash=289ce0316004a2eb69c2f9cf6649902a5d2293e94003477a493ad16e86368296
*/

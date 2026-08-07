package mutate4kotlin.exec;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.*;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

final class ModuleTreeCopier {

    void copy(Path moduleRoot, Path workerRoot) throws IOException {
        Path excludedRoot = moduleRoot.resolve("target").normalize();
        Files.walkFileTree(moduleRoot, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                if (dir.normalize().startsWith(excludedRoot)) {
                    return FileVisitResult.SKIP_SUBTREE;
                }
                Files.createDirectories(workerRoot.resolve(moduleRoot.relativize(dir)));
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (Files.isSymbolicLink(file)) {
                    return FileVisitResult.CONTINUE;
                }
                Files.copy(file, workerRoot.resolve(moduleRoot.relativize(file)));
                return FileVisitResult.CONTINUE;
            }
        });
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=101fc385299b8e9c16c6bd1d41e273ff59f9d7f466e0a49cf918c08ddf3ffbc2
scope.0.id=Y2xhc3M6TW9kdWxlVHJlZUNvcGllciNNb2R1bGVUcmVlQ29waWVyOjE3
scope.0.kind=class
scope.0.startLine=17
scope.0.endLine=38
scope.0.semanticHash=51d6519591e7d363d41f1dcab7cd9a55fe5ca7ca5e8f786a2e38e822b57c2771
scope.1.id=Y2xhc3M6TW9kdWxlVHJlZUNvcGllci4jOjIx
scope.1.kind=class
scope.1.startLine=21
scope.1.endLine=36
scope.1.semanticHash=92697868bce8abb3f2255e917da6c6bccb5e3335af290dbfed8de9547912d4fb
scope.2.id=bWV0aG9kOk1vZHVsZVRyZWVDb3BpZXIjY29weSgyKToxOQ
scope.2.kind=method
scope.2.startLine=19
scope.2.endLine=37
scope.2.semanticHash=190a47be585fcf47ecc25c42d3b3588e3e36b2d6d74f8eed45305a995d6b9f21
scope.3.id=bWV0aG9kOk1vZHVsZVRyZWVDb3BpZXIjY3RvcigwKToxNw
scope.3.kind=method
scope.3.startLine=1
scope.3.endLine=38
scope.3.semanticHash=5a7ccd4bdfb0be525fcb95a4f98248721e635908a902630b4234d86e37d0abcf
scope.4.id=bWV0aG9kOk1vZHVsZVRyZWVDb3BpZXIuI2N0b3IoMCk6MjE
scope.4.kind=method
scope.4.startLine=1
scope.4.endLine=38
scope.4.semanticHash=5a7ccd4bdfb0be525fcb95a4f98248721e635908a902630b4234d86e37d0abcf
scope.5.id=bWV0aG9kOk1vZHVsZVRyZWVDb3BpZXIuI3ByZVZpc2l0RGlyZWN0b3J5KDIpOjIy
scope.5.kind=method
scope.5.startLine=22
scope.5.endLine=29
scope.5.semanticHash=bdbb4a3307b6d1611ff460e81b61801b801b0c2f00e70c7b0d48f0ca9f0ecbdb
scope.6.id=bWV0aG9kOk1vZHVsZVRyZWVDb3BpZXIuI3Zpc2l0RmlsZSgyKTozMQ
scope.6.kind=method
scope.6.startLine=31
scope.6.endLine=35
scope.6.semanticHash=d31e954a79de121f4c4311a74d0fea0d5a5f205a88cbddc8853822b414687af4
*/

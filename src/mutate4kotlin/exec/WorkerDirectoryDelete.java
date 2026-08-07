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

final class WorkerDirectoryDelete {

    void delete(Path root) throws IOException {
        Files.walkFileTree(root, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.deleteIfExists(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                if (exc != null) {
                    throw exc;
                }
                Files.deleteIfExists(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=adc14605d957354f620158bb3fcac73fc72f9bd2b8eb9cbd061058e6d8e23115
scope.0.id=Y2xhc3M6V29ya2VyRGlyZWN0b3J5RGVsZXRlI1dvcmtlckRpcmVjdG9yeURlbGV0ZToxNw
scope.0.kind=class
scope.0.startLine=17
scope.0.endLine=37
scope.0.semanticHash=d3b1b86fe5a1422c4a961f697844b4d786af80874cfff890fbc2849331883352
scope.1.id=Y2xhc3M6V29ya2VyRGlyZWN0b3J5RGVsZXRlLiM6MjA
scope.1.kind=class
scope.1.startLine=20
scope.1.endLine=35
scope.1.semanticHash=01ae5ee52027d495d1cce1a718945374f9d113ed853526df34bb75e064ca8425
scope.2.id=bWV0aG9kOldvcmtlckRpcmVjdG9yeURlbGV0ZSNjdG9yKDApOjE3
scope.2.kind=method
scope.2.startLine=1
scope.2.endLine=37
scope.2.semanticHash=f490008a96830815dcb20a40963cd754024d9eb130c203ec08f24fd9f9033086
scope.3.id=bWV0aG9kOldvcmtlckRpcmVjdG9yeURlbGV0ZSNkZWxldGUoMSk6MTk
scope.3.kind=method
scope.3.startLine=19
scope.3.endLine=36
scope.3.semanticHash=e7edcf6fdc9e84f68f0e7a087e6311ddebffd4e783b2fa0068158fb2a1d60232
scope.4.id=bWV0aG9kOldvcmtlckRpcmVjdG9yeURlbGV0ZS4jY3RvcigwKToyMA
scope.4.kind=method
scope.4.startLine=1
scope.4.endLine=37
scope.4.semanticHash=f490008a96830815dcb20a40963cd754024d9eb130c203ec08f24fd9f9033086
scope.5.id=bWV0aG9kOldvcmtlckRpcmVjdG9yeURlbGV0ZS4jcG9zdFZpc2l0RGlyZWN0b3J5KDIpOjI3
scope.5.kind=method
scope.5.startLine=27
scope.5.endLine=34
scope.5.semanticHash=fb68e34f6f18c956d92efa99468c5ab8eb62d7183245faad6311f71d07c8f657
scope.6.id=bWV0aG9kOldvcmtlckRpcmVjdG9yeURlbGV0ZS4jdmlzaXRGaWxlKDIpOjIx
scope.6.kind=method
scope.6.startLine=21
scope.6.endLine=25
scope.6.semanticHash=6b7d951116b035044f9421108e96f12e9c9ffa5a96d765fc2c07cd9f088a3f0c
*/

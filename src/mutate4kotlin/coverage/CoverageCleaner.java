package mutate4kotlin.coverage;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.*;
import mutate4kotlin.exec.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

final class CoverageCleaner {

    void deleteStaleCoverage(Path directory, Path execFile) throws IOException {
        deleteTreeIfPresent(directory);
        deleteFileIfPresent(execFile);
    }

    private void deleteTreeIfPresent(Path path) throws IOException {
        if (!Files.isDirectory(path)) {
            return;
        }
        try (var walk = Files.walk(path)) {
            walk.sorted(java.util.Comparator.reverseOrder()).forEach(this::deletePath);
        }
    }

    private void deleteFileIfPresent(Path path) throws IOException {
        if (Files.isRegularFile(path)) {
            Files.deleteIfExists(path);
        }
    }

    private void deletePath(Path path) {
        try {
            Files.deleteIfExists(path);
        } catch (IOException ex) {
            throw new IllegalStateException("Failed deleting stale coverage: " + path, ex);
        }
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=98b728c3833cf4814aa2f6e6ecf81d69540f195201b307d0f2c53763a84da56a
scope.0.id=Y2xhc3M6Q292ZXJhZ2VDbGVhbmVyI0NvdmVyYWdlQ2xlYW5lcjoxNQ
scope.0.kind=class
scope.0.startLine=15
scope.0.endLine=44
scope.0.semanticHash=b353ed78f3649be0269937d18f8661d59b89508787f4605f5c71b227ed6b60c5
scope.1.id=bWV0aG9kOkNvdmVyYWdlQ2xlYW5lciNjdG9yKDApOjE1
scope.1.kind=method
scope.1.startLine=1
scope.1.endLine=44
scope.1.semanticHash=0c5af300481c19e8df10a9010386c594de2955fc117dbf1b6489707e776ed249
scope.2.id=bWV0aG9kOkNvdmVyYWdlQ2xlYW5lciNkZWxldGVGaWxlSWZQcmVzZW50KDEpOjMx
scope.2.kind=method
scope.2.startLine=31
scope.2.endLine=35
scope.2.semanticHash=4610b1471cffb8895c594675821a9cbae86d5611ff859491b2511e8500eda2f4
scope.3.id=bWV0aG9kOkNvdmVyYWdlQ2xlYW5lciNkZWxldGVQYXRoKDEpOjM3
scope.3.kind=method
scope.3.startLine=37
scope.3.endLine=43
scope.3.semanticHash=907226ed8dd0286e6134bbf058f6b85a047dcfee3309c3f0af710781696beae0
scope.4.id=bWV0aG9kOkNvdmVyYWdlQ2xlYW5lciNkZWxldGVTdGFsZUNvdmVyYWdlKDIpOjE3
scope.4.kind=method
scope.4.startLine=17
scope.4.endLine=20
scope.4.semanticHash=fe5d623f07a24a0f9faa8b869136a588a9d034029e7e4137b73ea1b03c9cf65d
scope.5.id=bWV0aG9kOkNvdmVyYWdlQ2xlYW5lciNkZWxldGVUcmVlSWZQcmVzZW50KDEpOjIy
scope.5.kind=method
scope.5.startLine=22
scope.5.endLine=29
scope.5.semanticHash=dbf5f1aecc3c6eb4a9a5a1e152b4d9e180fafd475dceccb63fb035c1b61f067d
*/

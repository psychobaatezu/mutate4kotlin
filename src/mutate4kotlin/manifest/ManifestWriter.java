package mutate4kotlin.manifest;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.*;

import java.nio.file.Path;

public final class ManifestWriter {

    private final ManifestSupport manifestSupport;
    public ManifestWriter(ManifestSupport manifestSupport) {
        this.manifestSupport = manifestSupport;
    }
    public void write(Path sourceFile, SourceAnalysis analysis) throws Exception {
        manifestSupport.write(sourceFile, analysis.sourceWithoutManifest(),
                new DifferentialManifest(1, analysis.moduleHash(), analysis.scopes()));
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=b64985a813d498f49a438c397b943039258146284a6bfb910d93ce20ac87c6d6
scope.0.id=Y2xhc3M6TWFuaWZlc3RXcml0ZXIjTWFuaWZlc3RXcml0ZXI6MTI
scope.0.kind=class
scope.0.startLine=12
scope.0.endLine=22
scope.0.semanticHash=066519bf4f3ec4395f3236660adf01033d79b2b5245744b2cc4dc4c781d9d3f0
scope.1.id=ZmllbGQ6TWFuaWZlc3RXcml0ZXIjbWFuaWZlc3RTdXBwb3J0OjE0
scope.1.kind=field
scope.1.startLine=14
scope.1.endLine=14
scope.1.semanticHash=3b18e7680a38456f29abcc80d1cf6ae7426fd744949d701cb129be20a8759a6a
scope.2.id=bWV0aG9kOk1hbmlmZXN0V3JpdGVyI2N0b3IoMSk6MTU
scope.2.kind=method
scope.2.startLine=15
scope.2.endLine=17
scope.2.semanticHash=3cdcdd1e5bb4fb32e720594db5d496c43507c9bc6cbc4278bcdad30434f49436
scope.3.id=bWV0aG9kOk1hbmlmZXN0V3JpdGVyI3dyaXRlKDIpOjE4
scope.3.kind=method
scope.3.startLine=18
scope.3.endLine=21
scope.3.semanticHash=1491eca4d28b8a5960af2b6667b1412ad99a892f21c16c9d3649bc356081ac6b
*/

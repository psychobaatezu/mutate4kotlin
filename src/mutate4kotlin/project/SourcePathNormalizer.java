package mutate4kotlin.project;

import mutate4kotlin.model.*;

import mutate4kotlin.cli.*;
import mutate4kotlin.engine.*;

import mutate4kotlin.selection.*;

import mutate4kotlin.analysis.*;
import mutate4kotlin.coverage.*;
import mutate4kotlin.exec.*;
import mutate4kotlin.manifest.*;

import java.nio.file.Path;

final class SourcePathNormalizer {

    String normalize(Path moduleRoot, Path file) {
        String relative = moduleRoot.relativize(file).toString().replace('\\', '/');
        if (relative.startsWith("src/main/kotlin/")) {
            return relative.substring("src/main/kotlin/".length());
        }
        if (relative.startsWith("src/test/kotlin/")) {
            return relative.substring("src/test/kotlin/".length());
        }
        if (relative.startsWith("src/main/java/")) {
            return relative.substring("src/main/java/".length());
        }
        if (relative.startsWith("src/test/java/")) {
            return relative.substring("src/test/java/".length());
        }
        if (relative.startsWith("src/")) {
            return relative.substring("src/".length());
        }
        return relative;
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=d82fc8b5a2faa51f8b173a4c57f84596fad5379a6c18d3123a2dc1b40f15a22e
scope.0.id=Y2xhc3M6U291cmNlUGF0aE5vcm1hbGl6ZXIjU291cmNlUGF0aE5vcm1hbGl6ZXI6MTc
scope.0.kind=class
scope.0.startLine=17
scope.0.endLine=32
scope.0.semanticHash=17fa5e5d1329f8937b6c6555b3857eb2c2225f67347a445b928eef8cf8170bc4
scope.1.id=bWV0aG9kOlNvdXJjZVBhdGhOb3JtYWxpemVyI2N0b3IoMCk6MTc
scope.1.kind=method
scope.1.startLine=1
scope.1.endLine=32
scope.1.semanticHash=f14f0b3c6c01fc499e85abb57b395ad165a08bb3fb159ab6ac8a6bc7cc76e76a
scope.2.id=bWV0aG9kOlNvdXJjZVBhdGhOb3JtYWxpemVyI25vcm1hbGl6ZSgyKToxOQ
scope.2.kind=method
scope.2.startLine=19
scope.2.endLine=31
scope.2.semanticHash=9aae2c1d3ac27d5909b9453b254282d60db9e8eaf4ea3a43ca2c0778a4d3b5c4
*/

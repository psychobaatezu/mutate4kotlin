package mutate4kotlin.project;

import mutate4kotlin.model.*;

import mutate4kotlin.cli.*;
import mutate4kotlin.engine.*;

import mutate4kotlin.selection.*;

import mutate4kotlin.analysis.*;
import mutate4kotlin.coverage.*;
import mutate4kotlin.exec.*;
import mutate4kotlin.manifest.*;

import java.nio.file.Files;
import java.nio.file.Path;

final class ModuleRootFinder {

    private final Path workspaceRoot;

    ModuleRootFinder(Path workspaceRoot) {
        this.workspaceRoot = workspaceRoot;
    }

    Path find(Path file) {
        Path current = Files.isDirectory(file) ? file : file.getParent();
        while (current != null) {
            if (Files.exists(current.resolve("pom.xml"))) {
                return current;
            }
            current = current.getParent();
        }
        return null;
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=c2416e71fe5d130b6b69254f47cf7fb42ad51ef0a6422f459816f7bf4d25af91
scope.0.id=Y2xhc3M6TW9kdWxlUm9vdEZpbmRlciNNb2R1bGVSb290RmluZGVyOjE4
scope.0.kind=class
scope.0.startLine=18
scope.0.endLine=36
scope.0.semanticHash=eed57b4754a554224ee94f1c94fd95ed31fdbac2cb0610f9999031679902f7f5
scope.1.id=ZmllbGQ6TW9kdWxlUm9vdEZpbmRlciN3b3Jrc3BhY2VSb290OjIw
scope.1.kind=field
scope.1.startLine=20
scope.1.endLine=20
scope.1.semanticHash=ea8f3cda099696b9fbb3052dac911c417ff5d1db470f4d0f8f5ec10ce687102c
scope.2.id=bWV0aG9kOk1vZHVsZVJvb3RGaW5kZXIjY3RvcigxKToyMg
scope.2.kind=method
scope.2.startLine=22
scope.2.endLine=24
scope.2.semanticHash=63557660dbf0cab8499e68a2b7cb9ac3d9124d573259d21bedb90b9e24024f79
scope.3.id=bWV0aG9kOk1vZHVsZVJvb3RGaW5kZXIjZmluZCgxKToyNg
scope.3.kind=method
scope.3.startLine=26
scope.3.endLine=35
scope.3.semanticHash=d410982bc2ea52e04457348cd80097e30cefe2c9d1bb58efd3af40845b9709d5
*/

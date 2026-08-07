package mutate4kotlin.project;

import mutate4kotlin.model.*;

import mutate4kotlin.cli.*;
import mutate4kotlin.engine.*;

import mutate4kotlin.selection.*;

import mutate4kotlin.analysis.*;
import mutate4kotlin.coverage.*;
import mutate4kotlin.exec.*;
import mutate4kotlin.manifest.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public final class SourceFileFinder {

    private SourceFileFinder() {
    }

    public static List<Path> findAllJavaFilesUnderSrc(Path root) throws IOException {
        Path src = root.resolve("src");
        if (!Files.isDirectory(src)) {
            return List.of();
        }
        try (var stream = Files.walk(src)) {
            return stream.filter(Files::isRegularFile)
                    .filter(path -> path.getFileName().toString().endsWith(".kt"))
                    .sorted()
                    .toList();
        }
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=daf9af4970f6b192cf23a20ff190d59736e5bde69e269da73699aaaef1dfa00e
scope.0.id=Y2xhc3M6U291cmNlRmlsZUZpbmRlciNTb3VyY2VGaWxlRmluZGVyOjIw
scope.0.kind=class
scope.0.startLine=20
scope.0.endLine=37
scope.0.semanticHash=fda89f55b2dc5f0ce1eefe25f1abd5818c219ad644ae66cab9d094dcaf6f1e6e
scope.1.id=bWV0aG9kOlNvdXJjZUZpbGVGaW5kZXIjY3RvcigwKToyMg
scope.1.kind=method
scope.1.startLine=22
scope.1.endLine=23
scope.1.semanticHash=952989561249035658f6719d569bc70bd2b9d10da263124c47f965e77064bb82
scope.2.id=bWV0aG9kOlNvdXJjZUZpbGVGaW5kZXIjZmluZEFsbEphdmFGaWxlc1VuZGVyU3JjKDEpOjI1
scope.2.kind=method
scope.2.startLine=25
scope.2.endLine=36
scope.2.semanticHash=31299cf8fb60ba6376aaec90619d4820aaa62669530f81e5fbb2855afa36c5bc
*/

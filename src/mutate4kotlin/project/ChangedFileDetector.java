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
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class ChangedFileDetector {

    private ChangedFileDetector() {
    }

    public static List<Path> changedJavaFilesUnderSrc(Path projectRoot) throws IOException, InterruptedException {
        Process process = new ProcessBuilder("git", "-C", projectRoot.toString(), "status", "--porcelain")
                .redirectErrorStream(true)
                .start();

        int exit = process.waitFor();
        String output = new String(process.getInputStream().readAllBytes());
        if (exit != 0) {
            throw new IllegalStateException("git status failed: " + output);
        }

        List<Path> files = new ArrayList<>();
        for (String line : output.split("\\R")) {
            Path path = parseLine(projectRoot, line);
            if (path != null && path.startsWith(projectRoot.resolve("src").normalize())) {
                files.add(path);
            }
        }
        files.sort(Path::compareTo);
        return files;
    }

    private static Path parseLine(Path root, String line) {
        if (line == null || line.isBlank() || line.length() < 4) {
            return null;
        }
        String pathText = line.substring(3).trim();
        int renameMarker = pathText.indexOf(" -> ");
        String finalPath = renameMarker >= 0 ? pathText.substring(renameMarker + 4) : pathText;
        if (!finalPath.endsWith(".kt")) {
            return null;
        }
        return root.resolve(finalPath).normalize();
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=17614ba0f6ceae4b5b939349ba0749de8065e5858d98b3649b7237dd82f30705
scope.0.id=Y2xhc3M6Q2hhbmdlZEZpbGVEZXRlY3RvciNDaGFuZ2VkRmlsZURldGVjdG9yOjIw
scope.0.kind=class
scope.0.startLine=20
scope.0.endLine=59
scope.0.semanticHash=bfe6680db2a847c48ac0d912b28bc6e21c1f4a9f297217b8ac0a3cde3e71238b
scope.1.id=bWV0aG9kOkNoYW5nZWRGaWxlRGV0ZWN0b3IjY2hhbmdlZEphdmFGaWxlc1VuZGVyU3JjKDEpOjI1
scope.1.kind=method
scope.1.startLine=25
scope.1.endLine=45
scope.1.semanticHash=4c0d2f2b99affa9cd4f56645fe8213fe7bbc1260a4568740d147737146188b07
scope.2.id=bWV0aG9kOkNoYW5nZWRGaWxlRGV0ZWN0b3IjY3RvcigwKToyMg
scope.2.kind=method
scope.2.startLine=22
scope.2.endLine=23
scope.2.semanticHash=8fe3abf26e99f2bd7186b30f9fe28659467f583d27b11adf97718417babfbefb
scope.3.id=bWV0aG9kOkNoYW5nZWRGaWxlRGV0ZWN0b3IjcGFyc2VMaW5lKDIpOjQ3
scope.3.kind=method
scope.3.startLine=47
scope.3.endLine=58
scope.3.semanticHash=11d9fe38a2a1286243a4b838539aa4865cb0aa01fa48667d0a25740a505725a3
*/

package mutate4kotlin;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.cli.*;
import mutate4kotlin.engine.*;

import mutate4kotlin.selection.*;

import mutate4kotlin.analysis.*;
import mutate4kotlin.coverage.*;
import mutate4kotlin.exec.*;
import mutate4kotlin.manifest.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChangedFileDetectorTest {

    @TempDir
    Path tempDir;

    @Test
    void findsModifiedAndUntrackedKotlinFilesUnderSrc() throws Exception {
        run("git init", tempDir);
        run("git config user.email test@example.com", tempDir);
        run("git config user.name test", tempDir);

        Path src = tempDir.resolve("src/main/kotlin/demo");
        Files.createDirectories(src);
        Path tracked = src.resolve("Tracked.kt");
        Files.writeString(tracked, "class Tracked {}\n");

        run("git add .", tempDir);
        run("git commit -m init", tempDir);

        Files.writeString(tracked, "class Tracked { int x = 1; }\n");
        Path untracked = src.resolve("NewFile.kt");
        Files.writeString(untracked, "class NewFile {}\n");
        Files.writeString(tempDir.resolve("README.md"), "ignore me\n");

        List<Path> changed = ChangedFileDetector.changedJavaFilesUnderSrc(tempDir);

        assertEquals(List.of(
                tempDir.resolve("src/main/kotlin/demo/NewFile.kt"),
                tempDir.resolve("src/main/kotlin/demo/Tracked.kt")
        ), changed);
    }

    private static void run(String command, Path dir) throws IOException, InterruptedException {
        Process process = new ProcessBuilder("sh", "-c", command)
                .directory(dir.toFile())
                .redirectErrorStream(true)
                .start();
        if (process.waitFor() != 0) {
            throw new IllegalStateException(new String(process.getInputStream().readAllBytes()));
        }
    }
}

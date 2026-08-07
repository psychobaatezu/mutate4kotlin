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

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SourceFileFinderTest {

    @TempDir
    Path tempDir;

    @Test
    void returnsEmptyListWhenSrcDirectoryIsMissing() throws Exception {
        assertEquals(List.of(), SourceFileFinder.findAllJavaFilesUnderSrc(tempDir));
    }

    @Test
    void findsAndSortsKotlinFilesUnderSrcOnly() throws Exception {
        Path src = tempDir.resolve("src/mutate4kotlin");
        Files.createDirectories(src);
        Path first = src.resolve("A.kt");
        Path second = src.resolve("nested/B.kt");
        Path ignored = tempDir.resolve("test/mutate4kotlin/C.kt");
        Path notKotlin = src.resolve("notes.txt");

        Files.createDirectories(second.getParent());
        Files.createDirectories(ignored.getParent());
        Files.writeString(second, "class B {}");
        Files.writeString(first, "class A {}");
        Files.writeString(ignored, "class C {}");
        Files.writeString(notKotlin, "ignore");

        assertEquals(List.of(first, second), SourceFileFinder.findAllJavaFilesUnderSrc(tempDir));
    }
}

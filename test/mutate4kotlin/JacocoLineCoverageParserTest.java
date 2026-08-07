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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JacocoLineCoverageParserTest {

    @TempDir
    Path tempDir;

    @Test
    void parsesCoveredLinesByPackageAndSourceFile() throws Exception {
        Path xml = tempDir.resolve("jacoco.xml");
        Files.writeString(xml, """
                <report name="demo">
                  <package name="demo">
                    <sourcefile name="Sample.java">
                      <line nr="5" mi="0" ci="3"/>
                      <line nr="9" mi="1" ci="0"/>
                    </sourcefile>
                  </package>
                </report>
                """);

        CoverageReport report = JacocoLineCoverageParser.parse(xml);

        assertTrue(report.covers("demo/Sample.java", 5));
        assertFalse(report.covers("demo/Sample.java", 9));
    }

    @Test
    void ignoresLinesWithMalformedCoverageNumbers() throws Exception {
        Path xml = tempDir.resolve("jacoco-invalid.xml");
        Files.writeString(xml, """
                <report name="demo">
                  <package name="demo">
                    <sourcefile name="Sample.java">
                      <line nr="oops" mi="0" ci="nan"/>
                    </sourcefile>
                  </package>
                </report>
                """);

        CoverageReport report = JacocoLineCoverageParser.parse(xml);

        assertFalse(report.covers("demo/Sample.java", 0));
    }
}

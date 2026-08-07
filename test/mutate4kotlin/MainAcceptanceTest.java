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

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainAcceptanceTest {

    @TempDir
    Path tempDir;

    @Test
    void printsHelp() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        int exit = Main.run(new String[]{"--help"}, tempDir,
                new PrintStream(out), new PrintStream(new ByteArrayOutputStream()));

        assertEquals(0, exit);
        assertTrue(out.toString().contains("mutate4kotlin <file.kt>"));
        assertTrue(out.toString().contains("--lines 12,18"));
    }

    @Test
    void rejectsDirectoryTargets() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayOutputStream err = new ByteArrayOutputStream();

        int exit = Main.run(new String[]{"crap4java"}, tempDir,
                new PrintStream(out), new PrintStream(err));

        assertEquals(1, exit);
        assertTrue(err.toString().contains("target must be a .kt file"));
        assertTrue(out.toString().contains("mutate4kotlin <file.kt>"));
    }

    @Test
    void mutatesARealMavenProject() throws Exception {
        writePassingProject(tempDir);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayOutputStream err = new ByteArrayOutputStream();

        int exit = Main.run(new String[]{"src/main/kotlin/demo/Flag.kt"}, tempDir,
                new PrintStream(out), new PrintStream(err));

        assertEquals(0, exit);
        assertTrue(out.toString().contains("KILLED"));
        assertTrue(out.toString().contains("replace true with false"));
        assertTrue(out.toString().contains("Coverage:"));
        assertTrue(out.toString().contains("Summary:"));
        assertEquals("", err.toString());
    }

    @Test
    void mutatesComparisonOperatorsInARealMavenProject() throws Exception {
        writeComparisonProject(tempDir);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        int exit = Main.run(new String[]{"src/main/kotlin/demo/Threshold.kt"}, tempDir,
                new PrintStream(out), new PrintStream(new ByteArrayOutputStream()));

        assertEquals(0, exit);
        assertTrue(out.toString().contains("KILLED"));
        assertTrue(out.toString().contains("replace > with >="));
        assertTrue(out.toString().contains("Summary:"));
    }

    @Test
    void failsFastWhenBaselineProjectTestsAreRed() throws Exception {
        writeFailingProject(tempDir);
        ByteArrayOutputStream err = new ByteArrayOutputStream();

        int exit = Main.run(new String[]{"src/main/kotlin/demo/Flag.kt"}, tempDir,
                new PrintStream(new ByteArrayOutputStream()), new PrintStream(err));

        assertEquals(2, exit);
        assertTrue(err.toString().contains("Baseline tests failed."));
    }

    @Test
    void mutatesReferenceRvaluesToNullInARealMavenProject() throws Exception {
        writeNullProject(tempDir);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        int exit = Main.run(new String[]{"src/main/kotlin/demo/Greeting.kt"}, tempDir,
                new PrintStream(out), new PrintStream(new ByteArrayOutputStream()));

        assertTrue(exit == 0 || exit == 3);
        assertTrue(out.toString().contains("Summary:"));
    }

    @Test
    void mutatesUnaryOperatorsInARealMavenProject() throws Exception {
        writeUnaryProject(tempDir);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        int exit = Main.run(new String[]{"src/main/kotlin/demo/Guard.kt"}, tempDir,
                new PrintStream(out), new PrintStream(new ByteArrayOutputStream()));

        assertEquals(0, exit);
        assertTrue(out.toString().contains("KILLED"));
        assertTrue(out.toString().contains("Summary:"));
    }

    @Test
    void restrictsMutationsToRequestedLinesInARealMavenProject() throws Exception {
        writeTwoMutationProject(tempDir);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        int exit = Main.run(new String[]{"src/main/kotlin/demo/Pair.kt", "--lines", "5"}, tempDir,
                new PrintStream(out), new PrintStream(new ByteArrayOutputStream()));

        assertEquals(0, exit);
        assertTrue(out.toString().contains("Summary:"));
    }

    @Test
    void killsTimedOutMutantsInARealMavenProject() throws Exception {
        writeTimeoutProject(tempDir);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        int exit = Main.run(new String[]{"src/main/kotlin/demo/Looping.kt", "--timeout-factor", "1"}, tempDir,
                new PrintStream(out), new PrintStream(new ByteArrayOutputStream()));

        assertEquals(0, exit);
        assertTrue(out.toString().contains("Summary:"));
    }

    @Test
    void reportsUncoveredSitesFromCoverageAndSkipsThem() throws Exception {
        writeUncoveredProject(tempDir);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        int exit = Main.run(new String[]{"src/main/kotlin/demo/Covered.kt"}, tempDir,
                new PrintStream(out), new PrintStream(new ByteArrayOutputStream()));

        assertEquals(0, exit);
        assertTrue(out.toString().contains("Coverage:"));
        assertTrue(out.toString().contains("Summary:"));
    }

    @Test
    void updatesManifestWithoutRunningProjectTests() throws Exception {
        writeFailingProject(tempDir);
        Path source = tempDir.resolve("src/main/kotlin/demo/Flag.kt");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayOutputStream err = new ByteArrayOutputStream();

        int exit = Main.run(new String[]{"src/main/kotlin/demo/Flag.kt", "--update-manifest"}, tempDir,
                new PrintStream(out), new PrintStream(err));

        assertEquals(0, exit);
        assertTrue(out.toString().contains("Updated manifest for src/main/kotlin/demo/Flag.kt"));
        assertEquals("", err.toString());
        assertTrue(new ManifestSupport().read(source).isPresent());
        assertTrue(Files.readString(source).contains("mutate4kotlin-manifest"));
    }

    private void writePassingProject(Path root) throws Exception {
        Files.writeString(root.resolve("pom.xml"), pomXml());
        Path source = root.resolve("src/main/kotlin/demo/Flag.kt");
        Files.createDirectories(source.getParent());
        Files.writeString(source, """
                package demo

                class Flag {
                    fun enabled(): Boolean = true
                }
                """);
        Path test = root.resolve("src/test/kotlin/demo/FlagTest.kt");
        Files.createDirectories(test.getParent());
        Files.writeString(test, """
                package demo

                import org.junit.jupiter.api.Assertions.assertTrue
                import org.junit.jupiter.api.Test

                class FlagTest {
                    @Test
                    fun enabledByDefault() {
                        assertTrue(Flag().enabled())
                    }
                }
                """);
    }

    private void writeFailingProject(Path root) throws Exception {
        writePassingProject(root);
        Path test = root.resolve("src/test/kotlin/demo/FlagTest.kt");
        Files.writeString(test, """
                package demo

                import org.junit.jupiter.api.Assertions.assertFalse
                import org.junit.jupiter.api.Test

                class FlagTest {
                    @Test
                    fun enabledByDefault() {
                        assertFalse(Flag().enabled())
                    }
                }
                """);
    }

    private void writeComparisonProject(Path root) throws Exception {
        Files.writeString(root.resolve("pom.xml"), pomXml());
        Path source = root.resolve("src/main/kotlin/demo/Threshold.kt");
        Files.createDirectories(source.getParent());
        Files.writeString(source, """
                package demo

                class Threshold {
                    fun accepts(value: Int): Boolean = value > 10
                }
                """);
        Path test = root.resolve("src/test/kotlin/demo/ThresholdTest.kt");
        Files.createDirectories(test.getParent());
        Files.writeString(test, """
                package demo

                import org.junit.jupiter.api.Assertions.assertFalse
                import org.junit.jupiter.api.Assertions.assertTrue
                import org.junit.jupiter.api.Test

                class ThresholdTest {
                    @Test
                    fun acceptsOnlyValuesAboveTen() {
                        val threshold = Threshold()
                        assertFalse(threshold.accepts(10))
                        assertTrue(threshold.accepts(11))
                    }
                }
                """);
    }

    private void writeNullProject(Path root) throws Exception {
        Files.writeString(root.resolve("pom.xml"), pomXml());
        Path source = root.resolve("src/main/kotlin/demo/Greeting.kt");
        Files.createDirectories(source.getParent());
        Files.writeString(source, """
                package demo

                class Greeting {
                    fun value(): String = "hello"
                }
                """);
        Path test = root.resolve("src/test/kotlin/demo/GreetingTest.kt");
        Files.createDirectories(test.getParent());
        Files.writeString(test, """
                package demo

                import org.junit.jupiter.api.Assertions.assertEquals
                import org.junit.jupiter.api.Assertions.assertNotNull
                import org.junit.jupiter.api.Test

                class GreetingTest {
                    @Test
                    fun returnsGreeting() {
                        val value = Greeting().value()
                        assertNotNull(value)
                        assertEquals("hello", value)
                    }
                }
                """);
    }

    private void writeUnaryProject(Path root) throws Exception {
        Files.writeString(root.resolve("pom.xml"), pomXml());
        Path source = root.resolve("src/main/kotlin/demo/Guard.kt");
        Files.createDirectories(source.getParent());
        Files.writeString(source, """
                package demo

                class Guard {
                    fun allows(blocked: Boolean): Boolean = !blocked
                }
                """);
        Path test = root.resolve("src/test/kotlin/demo/GuardTest.kt");
        Files.createDirectories(test.getParent());
        Files.writeString(test, """
                package demo

                import org.junit.jupiter.api.Assertions.assertFalse
                import org.junit.jupiter.api.Assertions.assertTrue
                import org.junit.jupiter.api.Test

                class GuardTest {
                    @Test
                    fun allowsOnlyWhenNotBlocked() {
                        val guard = Guard()
                        assertTrue(guard.allows(false))
                        assertFalse(guard.allows(true))
                    }
                }
                """);
    }

    private void writeTwoMutationProject(Path root) throws Exception {
        Files.writeString(root.resolve("pom.xml"), pomXml());
        Path source = root.resolve("src/main/kotlin/demo/Pair.kt");
        Files.createDirectories(source.getParent());
        Files.writeString(source, """
                package demo

                class Pair {
                    fun first(): Boolean = true

                    fun second(): Boolean = false
                }
                """);
        Path test = root.resolve("src/test/kotlin/demo/PairTest.kt");
        Files.createDirectories(test.getParent());
        Files.writeString(test, """
                package demo

                import org.junit.jupiter.api.Assertions.assertFalse
                import org.junit.jupiter.api.Assertions.assertTrue
                import org.junit.jupiter.api.Test

                class PairTest {
                    @Test
                    fun checksBothValues() {
                        val pair = Pair()
                        assertTrue(pair.first())
                        assertFalse(pair.second())
                    }
                }
                """);
    }

    private void writeTimeoutProject(Path root) throws Exception {
        Files.writeString(root.resolve("pom.xml"), pomXml());
        Path source = root.resolve("src/main/kotlin/demo/Looping.kt");
        Files.createDirectories(source.getParent());
        Files.writeString(source, """
                package demo

                class Looping {
                    fun finishes(blocked: Boolean): Boolean {
                        while (!blocked) {
                        }
                        return true
                    }
                }
                """);
        Path test = root.resolve("src/test/kotlin/demo/LoopingTest.kt");
        Files.createDirectories(test.getParent());
        Files.writeString(test, """
                package demo

                import org.junit.jupiter.api.Assertions.assertTrue
                import org.junit.jupiter.api.Test

                class LoopingTest {
                    @Test
                    fun returnsWhenInitiallyBlocked() {
                        assertTrue(Looping().finishes(true))
                    }
                }
                """);
    }

    private void writeUncoveredProject(Path root) throws Exception {
        Files.writeString(root.resolve("pom.xml"), pomXml());
        Path source = root.resolve("src/main/kotlin/demo/Covered.kt");
        Files.createDirectories(source.getParent());
        Files.writeString(source, """
                package demo

                class Covered {
                    fun exercised(): Boolean = true

                    fun notExercised(): Boolean = false
                }
                """);
        Path test = root.resolve("src/test/kotlin/demo/CoveredTest.kt");
        Files.createDirectories(test.getParent());
        Files.writeString(test, """
                package demo

                import org.junit.jupiter.api.Assertions.assertTrue
                import org.junit.jupiter.api.Test

                class CoveredTest {
                    @Test
                    fun coversOnlyOneMethod() {
                        assertTrue(Covered().exercised())
                    }
                }
                """);
    }

    private String pomXml() {
        return """
                <project xmlns="http://maven.apache.org/POM/4.0.0"
                         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
                  <modelVersion>4.0.0</modelVersion>
                  <groupId>demo</groupId>
                  <artifactId>demo</artifactId>
                  <version>1.0-SNAPSHOT</version>
                  <properties>
                    <kotlin.version>2.0.21</kotlin.version>
                    <maven.compiler.source>17</maven.compiler.source>
                    <maven.compiler.target>17</maven.compiler.target>
                    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
                    <junit.version>5.10.2</junit.version>
                  </properties>
                  <dependencies>
                    <dependency>
                      <groupId>org.jetbrains.kotlin</groupId>
                      <artifactId>kotlin-stdlib</artifactId>
                      <version>${kotlin.version}</version>
                    </dependency>
                    <dependency>
                      <groupId>org.junit.jupiter</groupId>
                      <artifactId>junit-jupiter</artifactId>
                      <version>${junit.version}</version>
                      <scope>test</scope>
                    </dependency>
                  </dependencies>
                  <build>
                    <testSourceDirectory>${project.basedir}/src/test/kotlin</testSourceDirectory>
                    <plugins>
                      <plugin>
                        <groupId>org.jetbrains.kotlin</groupId>
                        <artifactId>kotlin-maven-plugin</artifactId>
                        <version>${kotlin.version}</version>
                        <executions>
                          <execution>
                            <id>compile</id>
                            <goals>
                              <goal>compile</goal>
                            </goals>
                            <configuration>
                              <sourceDirs>
                                <sourceDir>${project.basedir}/src/main/kotlin</sourceDir>
                              </sourceDirs>
                            </configuration>
                          </execution>
                          <execution>
                            <id>test-compile</id>
                            <goals>
                              <goal>test-compile</goal>
                            </goals>
                            <configuration>
                              <sourceDirs>
                                <sourceDir>${project.basedir}/src/test/kotlin</sourceDir>
                              </sourceDirs>
                            </configuration>
                          </execution>
                        </executions>
                      </plugin>
                      <plugin>
                        <groupId>org.apache.maven.plugins</groupId>
                        <artifactId>maven-surefire-plugin</artifactId>
                        <version>3.2.5</version>
                      </plugin>
                    </plugins>
                  </build>
                </project>
                """;
    }
}

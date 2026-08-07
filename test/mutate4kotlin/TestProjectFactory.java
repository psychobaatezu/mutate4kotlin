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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

final class TestProjectFactory {

    private TestProjectFactory() {
    }

    static Path createProject(String name) throws IOException {
        Path projectRoot = Files.createTempDirectory(name);
        Files.writeString(projectRoot.resolve("pom.xml"), """
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
                      <version>5.10.2</version>
                      <scope>test</scope>
                    </dependency>
                  </dependencies>
                  <build>
                    <sourceDirectory>${project.basedir}/src/main/kotlin</sourceDirectory>
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
                """);
        Path sourceRoot = projectRoot.resolve("src/main/kotlin/mutate4kotlin");
        Path testRoot = projectRoot.resolve("src/test/kotlin/mutate4kotlin");
        Files.createDirectories(sourceRoot);
        Files.createDirectories(testRoot);
        Files.writeString(sourceRoot.resolve("Sample.kt"), """
                package mutate4kotlin

                class Sample {
                    fun truth(): Boolean {
                        return true
                    }
                }
                """);
        Files.writeString(testRoot.resolve("SampleTest.kt"), """
                package mutate4kotlin

                import org.junit.jupiter.api.Assertions.assertTrue
                import org.junit.jupiter.api.Test

                class SampleTest {
                    @Test
                    fun truthIsTrue() {
                        assertTrue(Sample().truth())
                    }
                }
                """);
        return projectRoot;
    }
}

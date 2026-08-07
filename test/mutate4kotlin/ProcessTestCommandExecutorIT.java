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

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProcessTestCommandExecutorIT {

    @Test
    void runsMavenTestsInProject() throws Exception {
        Path projectRoot = TestProjectFactory.createProject("process-test-executor");

        TestRun result = new ProcessTestCommandExecutor().runTests(projectRoot, 0);

        assertEquals(0, result.exitCode());
        assertFalse(result.timedOut());
        assertTrue(Files.exists(projectRoot.resolve("target/surefire-reports")));
    }

    @Test
    void timesOutLongRunningMavenTests() throws Exception {
        Path projectRoot = TestProjectFactory.createProject("process-test-timeout");
        Files.writeString(projectRoot.resolve("src/test/kotlin/mutate4kotlin/SampleTest.kt"), """
                package mutate4kotlin

                import org.junit.jupiter.api.Test

                class SampleTest {
                    @Test
                    fun waitsTooLong() {
                        Thread.sleep(5000)
                    }
                }
                """);

        TestRun result = new ProcessTestCommandExecutor().runTests(projectRoot, 50);

        assertEquals(124, result.exitCode());
        assertTrue(result.timedOut());
    }
}

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

class CoverageRunnerIT {

    @Test
    void deletesStaleCoverageArtifactsBeforeRunningCoverage() throws Exception {
        Path projectRoot = TestProjectFactory.createProject("coverage-runner-stale");
        Path jacocoDir = projectRoot.resolve("target/site/jacoco");
        Path jacocoExec = projectRoot.resolve("target/jacoco.exec");
        Files.createDirectories(jacocoDir);
        Files.writeString(jacocoDir.resolve("old.xml"), "stale");
        Files.createDirectories(jacocoExec.getParent());
        Files.writeString(jacocoExec, "stale");

        CoverageRun run = new CoverageRunner(new ProcessCommandExecutor()).generateCoverage(projectRoot);

        assertEquals(0, run.baseline().exitCode());
        assertTrue(Files.exists(jacocoDir.resolve("jacoco.xml")));
        assertTrue(Files.exists(jacocoExec));
        assertFalse(Files.exists(jacocoDir.resolve("old.xml")));
    }

    @Test
    void parsesJacocoXmlWhenCoverageRunSucceeds() throws Exception {
        Path projectRoot = TestProjectFactory.createProject("coverage-runner-parse");

        CoverageRun run = new CoverageRunner(new ProcessCommandExecutor()).generateCoverage(projectRoot);

        assertTrue(run.report().covers("mutate4kotlin/Sample.java", 5));
    }

    @Test
    void reusesExistingJacocoReportWithoutRunningCoverageCommand() throws Exception {
        Path projectRoot = TestProjectFactory.createProject("coverage-runner-reuse");
        CoverageRunner runner = new CoverageRunner(new ProcessCommandExecutor());
        runner.generateCoverage(projectRoot);

        CoverageRun reused = runner.generateCoverage(projectRoot, true);

        assertTrue(reused.reused());
        assertTrue(reused.reportAvailable());
        assertEquals(null, reused.baseline());
        assertTrue(reused.report().covers("mutate4kotlin/Sample.java", 5));
    }

    @Test
    void reportsMissingCoverageWhenReuseIsRequestedWithoutExistingReport() throws Exception {
        Path projectRoot = TestProjectFactory.createProject("coverage-runner-missing-reuse");

        CoverageRun reused = new CoverageRunner(new ProcessCommandExecutor()).generateCoverage(projectRoot, true);

        assertTrue(reused.reused());
        assertFalse(reused.reportAvailable());
        assertEquals(null, reused.baseline());
        assertFalse(reused.report().covers("mutate4kotlin/Sample.java", 5));
    }
}

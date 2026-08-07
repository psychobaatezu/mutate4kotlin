package mutate4kotlin;

import mutate4kotlin.analysis.*;
import mutate4kotlin.cli.*;
import mutate4kotlin.coverage.*;
import mutate4kotlin.engine.*;
import mutate4kotlin.exec.*;
import mutate4kotlin.manifest.*;
import mutate4kotlin.model.*;
import mutate4kotlin.project.*;
import mutate4kotlin.report.*;
import mutate4kotlin.selection.*;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CoverageRunnerTest {

    @Test
    void defaultGenerateCoverageRefreshesCoverage() throws Exception {
        Path projectRoot = TestProjectFactory.createProject("coverage-runner-default");

        CoverageRun run = new CoverageRunner(new ProcessCommandExecutor()).generateCoverage(projectRoot);

        assertEquals(0, run.baseline().exitCode());
        assertFalse(run.reused());
        assertTrue(run.reportAvailable());
    }

    @Test
    void generatesCoverageByRunningMavenAndParsingJacocoXml() throws Exception {
        Path projectRoot = TestProjectFactory.createProject("coverage-runner-unit");

        CoverageRun run = new CoverageRunner(new ProcessCommandExecutor()).generateCoverage(projectRoot, false);

        assertEquals(0, run.baseline().exitCode());
        assertFalse(run.reused());
        assertTrue(run.reportAvailable());
        assertTrue(run.report().covers("mutate4kotlin/Sample.java", 5));
    }

    @Test
    void reusesExistingJacocoXmlWhenRequested() throws Exception {
        Path projectRoot = TestProjectFactory.createProject("coverage-runner-reuse-unit");
        CoverageRunner runner = new CoverageRunner(new ProcessCommandExecutor());
        runner.generateCoverage(projectRoot, false);

        CoverageRun run = runner.generateCoverage(projectRoot, true);

        assertEquals(null, run.baseline());
        assertTrue(run.reused());
        assertTrue(run.reportAvailable());
        assertTrue(run.report().covers("mutate4kotlin/Sample.java", 5));
    }

    @Test
    void reportsMissingCoverageWhenReuseRequestedWithoutJacocoXml() throws Exception {
        Path projectRoot = Files.createTempDirectory("coverage-runner-missing-unit");
        Files.createDirectories(projectRoot);

        CoverageRun run = new CoverageRunner(new ProcessCommandExecutor()).generateCoverage(projectRoot, true);

        assertEquals(null, run.baseline());
        assertTrue(run.reused());
        assertFalse(run.reportAvailable());
        assertFalse(run.report().covers("mutate4kotlin/Sample.java", 5));
    }
}

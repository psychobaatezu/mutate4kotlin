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

import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProcessCommandExecutorTest {

    private static final String LARGE_OUTPUT_COMMAND = "awk 'BEGIN { for (i = 0; i < 200000; i++) printf \"Tatooine\" }'";

    @TempDir
    Path tempDir;

    @Test
    void capturesSuccessfulCommandOutput() throws Exception {
        CommandResult result = new ProcessCommandExecutor().run(
                List.of("sh", "-c", "printf 'ok'"),
                tempDir
        );

        assertEquals(0, result.exitCode());
        assertEquals("ok", result.output());
        assertFalse(result.timedOut());
    }

    @Test
    void returnsTimeoutExitCodeWhenCommandTakesTooLong() throws Exception {
        CommandResult result = new ProcessCommandExecutor().run(
                List.of("sh", "-c", "sleep 1"),
                tempDir,
                10
        );

        assertEquals(124, result.exitCode());
        assertTrue(result.timedOut());
    }

    @Test
    void capturesOutputLargerThanTheProcessPipeBuffer() throws Exception {
        CommandResult result = new ProcessCommandExecutor().run(
                List.of("sh", "-c", LARGE_OUTPUT_COMMAND),
                tempDir
        );

        assertEquals(0, result.exitCode());
        assertEquals(1_600_000, result.output().length());
        assertFalse(result.timedOut());
    }
}

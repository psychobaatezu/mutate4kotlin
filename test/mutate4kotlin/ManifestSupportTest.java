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
import static org.junit.jupiter.api.Assertions.assertTrue;

class ManifestSupportTest {

    private final ManifestSupport manifestSupport = new ManifestSupport();

    @TempDir
    Path tempDir;

    @Test
    void writesReadsAndStripsEmbeddedManifest() throws Exception {
        Path sourceFile = tempDir.resolve("Sample.java");
        String source = """
                package demo;

                class Sample {
                    boolean truthy() {
                        return true;
                    }
                }
                """;
        Files.writeString(sourceFile, source);
        DifferentialManifest manifest = new DifferentialManifest(
                1,
                "module-hash",
                List.of(new MutationScope("method:demo.Sample#truthy():4", "method", 4, 6, "scope-hash"))
        );

        manifestSupport.write(sourceFile, source, manifest);

        String withManifest = Files.readString(sourceFile);
        assertTrue(withManifest.contains("mutate4kotlin-manifest"));
        assertEquals(source.stripTrailing() + "\n", manifestSupport.stripManifest(withManifest));

        DifferentialManifest parsed = manifestSupport.read(sourceFile).orElseThrow();
        assertEquals(1, parsed.version());
        assertEquals("module-hash", parsed.moduleHash());
        assertEquals(1, parsed.scopes().size());
        assertEquals("method:demo.Sample#truthy():4", parsed.scopes().get(0).id());
        assertEquals("method", parsed.scopes().get(0).kind());
        assertEquals(4, parsed.scopes().get(0).startLine());
        assertEquals(6, parsed.scopes().get(0).endLine());
        assertEquals("scope-hash", parsed.scopes().get(0).semanticHash());
    }
}

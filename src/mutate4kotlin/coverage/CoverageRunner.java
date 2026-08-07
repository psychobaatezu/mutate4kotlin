package mutate4kotlin.coverage;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.*;
import mutate4kotlin.exec.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CoverageRunner {

    static final long COVERAGE_TIMEOUT_MILLIS = 300_000L;

    private final ProcessCommandExecutor executor;
    private final CoverageCleaner cleaner = new CoverageCleaner();
    public CoverageRunner(ProcessCommandExecutor executor) {
        this.executor = executor;
    }
    public CoverageRun generateCoverage(Path projectRoot) throws Exception {
        return generateCoverage(projectRoot, false);
    }

    public CoverageRun generateCoverage(Path projectRoot, boolean reuseCoverage) throws Exception {
        Path jacocoDir = projectRoot.resolve("target/site/jacoco");
        Path jacocoExec = projectRoot.resolve("target/jacoco.exec");
        Path jacocoXml = jacocoDir.resolve("jacoco.xml");
        if (reuseCoverage) {
            if (Files.exists(jacocoXml)) {
                return new CoverageRun(null,
                        JacocoLineCoverageParser.parse(jacocoXml),
                        true,
                        true);
            }
            return new CoverageRun(null, new CoverageReport(Set.of()), true, false);
        }
        cleaner.deleteStaleCoverage(jacocoDir, jacocoExec);

        List<String> command = new ArrayList<>();
        command.add("mvn");
        command.add("-q");
        if (hasProjectJacocoPlugin(projectRoot)) {
            command.add("org.jacoco:jacoco-maven-plugin:prepare-agent");
            command.add("test");
            command.add("org.jacoco:jacoco-maven-plugin:report");
        } else {
            command.add("org.jacoco:jacoco-maven-plugin:0.8.12:prepare-agent");
            command.add("test");
            command.add("org.jacoco:jacoco-maven-plugin:0.8.12:report");
        }
        CommandResult result = executor.run(command, projectRoot, COVERAGE_TIMEOUT_MILLIS);
        CoverageReport report = result.exitCode() == 0
                ? JacocoLineCoverageParser.parse(jacocoXml)
                : new CoverageReport(Set.of());
        return new CoverageRun(
                new TestRun(result.exitCode(), result.output(), result.durationMillis(), result.timedOut()),
                report,
                false,
                result.exitCode() == 0 && Files.exists(jacocoXml)
        );
    }

    private boolean hasProjectJacocoPlugin(Path projectRoot) throws Exception {
        Path pomFile = projectRoot.resolve("pom.xml");
        if (!Files.exists(pomFile)) {
            return false;
        }
        String pom = Files.readString(pomFile);
        return pom.contains("<artifactId>jacoco-maven-plugin</artifactId>");
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=8d418d6acc907f8fe1b8ffa78808f83ae9e564f6dbc1044793d6b688fa4f3b4e
scope.0.id=Y2xhc3M6Q292ZXJhZ2VSdW5uZXIjQ292ZXJhZ2VSdW5uZXI6MTY
scope.0.kind=class
scope.0.startLine=16
scope.0.endLine=60
scope.0.semanticHash=3c65865b0ce02efb4fcaad8f44c81ddbab680e898931a861f3967c6b09ff08b8
scope.1.id=ZmllbGQ6Q292ZXJhZ2VSdW5uZXIjQ09WRVJBR0VfVElNRU9VVF9NSUxMSVM6MTg
scope.1.kind=field
scope.1.startLine=18
scope.1.endLine=18
scope.1.semanticHash=6e5c508ee1d9fb99aaf963644e6d2fc5ce1cd711d34b57d45d09baba6d3eec9d
scope.2.id=ZmllbGQ6Q292ZXJhZ2VSdW5uZXIjY2xlYW5lcjoyMQ
scope.2.kind=field
scope.2.startLine=21
scope.2.endLine=21
scope.2.semanticHash=e054311929a09448bb9f5fa070ebf5cd46aee39e391c54a1ae6ea4f6d91c1f70
scope.3.id=ZmllbGQ6Q292ZXJhZ2VSdW5uZXIjZXhlY3V0b3I6MjA
scope.3.kind=field
scope.3.startLine=20
scope.3.endLine=20
scope.3.semanticHash=36f4c7e4d9eee22de597df01707b2a19afbccc80cd17a6814874870e814db1e2
scope.4.id=bWV0aG9kOkNvdmVyYWdlUnVubmVyI2N0b3IoMSk6MjI
scope.4.kind=method
scope.4.startLine=22
scope.4.endLine=24
scope.4.semanticHash=0bfa41f3538d9de31a3184d53c3350206581fc85892b3a0727e1404fc9a4bff8
scope.5.id=bWV0aG9kOkNvdmVyYWdlUnVubmVyI2dlbmVyYXRlQ292ZXJhZ2UoMSk6MjU
scope.5.kind=method
scope.5.startLine=25
scope.5.endLine=27
scope.5.semanticHash=d4e2b3e49e789a9287e2f419d23a5bc61b4e848e0b9a7f504a7b2aaede5cf7ff
scope.6.id=bWV0aG9kOkNvdmVyYWdlUnVubmVyI2dlbmVyYXRlQ292ZXJhZ2UoMik6Mjk
scope.6.kind=method
scope.6.startLine=29
scope.6.endLine=59
scope.6.semanticHash=54b5c5f8e4167abc51f4487b6164899fc93fc2e9f1e49fb300125a55d0b87cae
*/

package mutate4kotlin.engine;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.*;
import mutate4kotlin.analysis.*;
import mutate4kotlin.cli.*;
import mutate4kotlin.coverage.*;
import mutate4kotlin.exec.*;
import mutate4kotlin.manifest.*;
import mutate4kotlin.selection.*;

import mutate4kotlin.selection.*;

import mutate4kotlin.analysis.*;
import mutate4kotlin.coverage.*;
import mutate4kotlin.exec.*;
import mutate4kotlin.manifest.*;

import java.nio.file.Path;
import java.util.List;

public record ExecutionContext(Path sourceFile,
                        Path moduleRoot,
                        TestCommandExecutor executor,
                        ProgressReporter progressReporter,
                        SourceAnalysis analysis) {

    public static ExecutionContext create(CliArguments parsed,
                                   TestCommandExecutor executor,
                                   ProgressReporter verboseProgressReporter,
                                   ProjectLayout layout,
                                   MutationCatalog catalog) throws Exception {
        TestCommandExecutor selectedExecutor = parsed.testCommand() == null ? executor : executor.withCommand(parsed.testCommand());
        List<Path> files = List.of(layout.explicitFile(parsed.fileArgs().get(0)));
        Path sourceFile = files.get(0);
        return new ExecutionContext(
                sourceFile,
                layout.moduleRootFor(files),
                selectedExecutor,
                parsed.verbose() ? verboseProgressReporter : new NoOpProgressReporter(),
                catalog.analyze(sourceFile)
        );
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=1ff38a6ee25d76ccb41119126d18c0f8008f6d1ce22c8b209efae90b7d5b9648
scope.0.id=Y2xhc3M6RXhlY3V0aW9uQ29udGV4dCNFeGVjdXRpb25Db250ZXh0OjI2
scope.0.kind=class
scope.0.startLine=26
scope.0.endLine=48
scope.0.semanticHash=98c7a171845b19eb5431285b254cbf217f2e1ea6ed5e05fd9641febfcbd0661e
scope.1.id=ZmllbGQ6RXhlY3V0aW9uQ29udGV4dCNhbmFseXNpczozMA
scope.1.kind=field
scope.1.startLine=30
scope.1.endLine=30
scope.1.semanticHash=2a66085e22cc6ff200bf45635992b4e6a85606688405bc7deb0749b12ea52c87
scope.2.id=ZmllbGQ6RXhlY3V0aW9uQ29udGV4dCNleGVjdXRvcjoyOA
scope.2.kind=field
scope.2.startLine=28
scope.2.endLine=28
scope.2.semanticHash=dd087a71a0cf1d0127618691f228b3d1c40515f5baff3eaaaeba748ba29275af
scope.3.id=ZmllbGQ6RXhlY3V0aW9uQ29udGV4dCNtb2R1bGVSb290OjI3
scope.3.kind=field
scope.3.startLine=27
scope.3.endLine=27
scope.3.semanticHash=3bb7421cad62b59b77b2c0c509f54aad78fc679c8051750f61dcb249f6eaf98b
scope.4.id=ZmllbGQ6RXhlY3V0aW9uQ29udGV4dCNwcm9ncmVzc1JlcG9ydGVyOjI5
scope.4.kind=field
scope.4.startLine=29
scope.4.endLine=29
scope.4.semanticHash=3cf8a010ce7148930f65d3d2b2317bd58108d5ba039ff944c2de2384aefef9b6
scope.5.id=ZmllbGQ6RXhlY3V0aW9uQ29udGV4dCNzb3VyY2VGaWxlOjI2
scope.5.kind=field
scope.5.startLine=26
scope.5.endLine=26
scope.5.semanticHash=61d34f23b74db96ae1f61e0adfe26948af29a75d59162ddf69fbf897c404026e
scope.6.id=bWV0aG9kOkV4ZWN1dGlvbkNvbnRleHQjY3JlYXRlKDUpOjMy
scope.6.kind=method
scope.6.startLine=32
scope.6.endLine=47
scope.6.semanticHash=4d48fe9ebb96d0908425b0a11e662ed9793cb3b0efbc97e516868342f1874d96
scope.7.id=bWV0aG9kOkV4ZWN1dGlvbkNvbnRleHQjY3Rvcig1KToyNg
scope.7.kind=method
scope.7.startLine=1
scope.7.endLine=48
scope.7.semanticHash=79de81de44d4754768db95c62e75bad8c567a000a0aea3134c9c3946c83bff02
*/

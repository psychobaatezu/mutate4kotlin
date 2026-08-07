package mutate4kotlin.analysis;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.*;
import mutate4kotlin.manifest.*;

import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.util.JavacTask;
import com.sun.source.util.Trees;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static javax.tools.JavaCompiler.CompilationTask;
import static javax.tools.StandardLocation.CLASS_OUTPUT;
import static javax.tools.StandardLocation.CLASS_PATH;

final class JavaSourceCompiler {

    CompiledSource compile(Path file) throws IOException {
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        try (StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, Locale.ROOT, null)) {
            Path outputDir = Files.createTempDirectory("mutate4kotlin-classes");
            Path javaFile = file;
            boolean copiedFile = false;
            if (!file.getFileName().toString().endsWith(".java")) {
                javaFile = outputDir.resolve(file.getFileName().toString().replaceFirst("\\.[^.]+$", "") + ".java");
                Files.copy(file, javaFile, StandardCopyOption.REPLACE_EXISTING);
                copiedFile = true;
            }
            fileManager.setLocationFromPaths(CLASS_OUTPUT, List.of(outputDir));
            fileManager.setLocationFromPaths(CLASS_PATH, List.of());
            Iterable<? extends JavaFileObject> javaFiles = fileManager.getJavaFileObjects(javaFile.toFile());
            CompilationTask task = compiler.getTask(null, fileManager, diagnostics,
                    List.of("-proc:none", "-implicit:none"), null, javaFiles);
            JavacTask javacTask = (JavacTask) task;
            List<CompilationUnitTree> units = new ArrayList<>();
            for (CompilationUnitTree unit : javacTask.parse()) {
                units.add(unit);
            }
            javacTask.analyze();
            if (copiedFile) {
                Files.deleteIfExists(javaFile);
            }
            return new CompiledSource(units, Trees.instance(javacTask));
        }
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=82f762e0acce8ae81a9c877609e03f1bac3ad289646f639d4e093c172c134cc7
scope.0.id=Y2xhc3M6SmF2YVNvdXJjZUNvbXBpbGVyI0phdmFTb3VyY2VDb21waWxlcjozMQ
scope.0.kind=class
scope.0.startLine=31
scope.0.endLine=52
scope.0.semanticHash=fe6623ba6417dc69ea43ce8eb841ccf892355cd74f1885bb0216310d18f998ed
scope.1.id=bWV0aG9kOkphdmFTb3VyY2VDb21waWxlciNjb21waWxlKDEpOjMz
scope.1.kind=method
scope.1.startLine=33
scope.1.endLine=51
scope.1.semanticHash=598111f1ff2bac39f06dd8417ebee2a89fe579f0b653761b9c439d93b95d9309
scope.2.id=bWV0aG9kOkphdmFTb3VyY2VDb21waWxlciNjdG9yKDApOjMx
scope.2.kind=method
scope.2.startLine=1
scope.2.endLine=52
scope.2.semanticHash=d32b1ae80b5f3a37baaa8fe5645f7d026edeef96fca0881bd7dbb35d7fac5748
*/

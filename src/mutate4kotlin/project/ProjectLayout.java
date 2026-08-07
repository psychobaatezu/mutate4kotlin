package mutate4kotlin.project;

import mutate4kotlin.model.*;

import mutate4kotlin.cli.*;
import mutate4kotlin.engine.*;

import mutate4kotlin.selection.*;

import mutate4kotlin.analysis.*;
import mutate4kotlin.coverage.*;
import mutate4kotlin.exec.*;
import mutate4kotlin.manifest.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public final class ProjectLayout {

    private final Path workspaceRoot;
    private final ModuleRootFinder moduleRootFinder;
    private final SourcePathNormalizer sourcePathNormalizer;

    public ProjectLayout(Path workspaceRoot) {
        this.workspaceRoot = workspaceRoot;
        this.moduleRootFinder = new ModuleRootFinder(workspaceRoot);
        this.sourcePathNormalizer = new SourcePathNormalizer();
    }

    public Path explicitFile(String arg) {
        Path path = workspaceRoot.resolve(arg).normalize();
        if (Files.isDirectory(path)) {
            throw new IllegalArgumentException("mutate4kotlin target must be a .kt file");
        }
        return path;
    }

    public Path moduleRootFor(List<Path> files) {
        Path moduleRoot = moduleRootFinder.find(files.get(0));
        return moduleRoot == null ? workspaceRoot : moduleRoot;
    }

    public String sourceSuffix(Path moduleRoot, Path file) {
        return sourcePathNormalizer.normalize(moduleRoot, file);
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=143cef0f491ca1eb4640a72fd5842858d64878b03fb4f35e85ff9fcd444e15d2
scope.0.id=Y2xhc3M6UHJvamVjdExheW91dCNQcm9qZWN0TGF5b3V0OjE5
scope.0.kind=class
scope.0.startLine=19
scope.0.endLine=47
scope.0.semanticHash=7b2560215d6ccfc13724b048a5a5353ee73789aa177d3a3b65022002317a5fdf
scope.1.id=ZmllbGQ6UHJvamVjdExheW91dCNtb2R1bGVSb290RmluZGVyOjIy
scope.1.kind=field
scope.1.startLine=22
scope.1.endLine=22
scope.1.semanticHash=98bdbe4fe7e7a2bb5969a691cc9d3db94a8eeb3bf14ca7a5a601bf793aec94d5
scope.2.id=ZmllbGQ6UHJvamVjdExheW91dCNzb3VyY2VQYXRoTm9ybWFsaXplcjoyMw
scope.2.kind=field
scope.2.startLine=23
scope.2.endLine=23
scope.2.semanticHash=51641c83494122338cf134693dae91b3d4199f2febb2d6ef87ea4be21114c2e1
scope.3.id=ZmllbGQ6UHJvamVjdExheW91dCN3b3Jrc3BhY2VSb290OjIx
scope.3.kind=field
scope.3.startLine=21
scope.3.endLine=21
scope.3.semanticHash=ea8f3cda099696b9fbb3052dac911c417ff5d1db470f4d0f8f5ec10ce687102c
scope.4.id=bWV0aG9kOlByb2plY3RMYXlvdXQjY3RvcigxKToyNQ
scope.4.kind=method
scope.4.startLine=25
scope.4.endLine=29
scope.4.semanticHash=d6c2fbd70871b8e30a6909dc40a40a39040b40d4d1f0642556584a2fad028397
scope.5.id=bWV0aG9kOlByb2plY3RMYXlvdXQjZXhwbGljaXRGaWxlKDEpOjMx
scope.5.kind=method
scope.5.startLine=31
scope.5.endLine=37
scope.5.semanticHash=515fd55250341915660bd81fb05357435183db2b197af802658988a0c972f443
scope.6.id=bWV0aG9kOlByb2plY3RMYXlvdXQjbW9kdWxlUm9vdEZvcigxKTozOQ
scope.6.kind=method
scope.6.startLine=39
scope.6.endLine=42
scope.6.semanticHash=10106185550f22f55bab0775274b439a1cc3d16ccd145795adc19142b895f06a
scope.7.id=bWV0aG9kOlByb2plY3RMYXlvdXQjc291cmNlU3VmZml4KDIpOjQ0
scope.7.kind=method
scope.7.startLine=44
scope.7.endLine=46
scope.7.semanticHash=235b33d73506bb666846b1c1e0bbd50712034dd9aea37e8a2278b826c02662d1
*/

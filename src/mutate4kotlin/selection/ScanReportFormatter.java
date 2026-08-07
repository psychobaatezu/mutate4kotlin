package mutate4kotlin.selection;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.*;

import java.nio.file.Path;
import java.util.List;
import java.util.Set;

public final class ScanReportFormatter {

    private final Path workspaceRoot;

    public ScanReportFormatter(Path workspaceRoot) {
        this.workspaceRoot = workspaceRoot;
    }

    public String format(Path sourceFile, List<MutationSite> sites, Set<String> changedScopes) {
        StringBuilder report = new StringBuilder();
        report.append("Scan: ").append(sites.size()).append(" mutation sites in ")
                .append(relative(sourceFile)).append('\n');
        for (MutationSite site : sites) {
            report.append(changedScopes.contains(site.scopeId()) ? "* " : "  ");
            report.append(relative(site.file())).append(':').append(site.lineNumber())
                    .append(' ').append(site.description()).append('\n');
        }
        if (!changedScopes.isEmpty()) {
            report.append("* indicates a scope that differs from the embedded manifest.\n");
        }
        return report.toString();
    }

    private String relative(Path file) {
        return workspaceRoot.relativize(file).toString().replace('\\', '/');
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=bb562aee2018cc1f1197fbeba85779f8aa190169bd70aaece847f474900380b1
scope.0.id=Y2xhc3M6U2NhblJlcG9ydEZvcm1hdHRlciNTY2FuUmVwb3J0Rm9ybWF0dGVyOjE0
scope.0.kind=class
scope.0.startLine=14
scope.0.endLine=40
scope.0.semanticHash=99e2d9d50e626349584f65a8f81373a7be43060b2e06556610cb129921de9e91
scope.1.id=ZmllbGQ6U2NhblJlcG9ydEZvcm1hdHRlciN3b3Jrc3BhY2VSb290OjE2
scope.1.kind=field
scope.1.startLine=16
scope.1.endLine=16
scope.1.semanticHash=ea8f3cda099696b9fbb3052dac911c417ff5d1db470f4d0f8f5ec10ce687102c
scope.2.id=bWV0aG9kOlNjYW5SZXBvcnRGb3JtYXR0ZXIjY3RvcigxKToxOA
scope.2.kind=method
scope.2.startLine=18
scope.2.endLine=20
scope.2.semanticHash=b2b39fd6bc56539bc6963dd0480007fd8ba812d9016d5c29323b95a7ce3002f2
scope.3.id=bWV0aG9kOlNjYW5SZXBvcnRGb3JtYXR0ZXIjZm9ybWF0KDMpOjIy
scope.3.kind=method
scope.3.startLine=22
scope.3.endLine=35
scope.3.semanticHash=d1066f0a2cd3a4b6ba4b70468000e65359fc500c6e1e14adbae32f5f3ca05410
scope.4.id=bWV0aG9kOlNjYW5SZXBvcnRGb3JtYXR0ZXIjcmVsYXRpdmUoMSk6Mzc
scope.4.kind=method
scope.4.startLine=37
scope.4.endLine=39
scope.4.semanticHash=ef9d92fb4a17b0e30f164e8287a61d51bd89c07153c185d56f4ce0abf43a73b3
*/

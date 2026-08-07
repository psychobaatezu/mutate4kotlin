package mutate4kotlin.cli;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.*;
import mutate4kotlin.analysis.*;
import mutate4kotlin.coverage.*;
import mutate4kotlin.engine.*;
import mutate4kotlin.exec.*;
import mutate4kotlin.manifest.*;
import mutate4kotlin.selection.*;

import mutate4kotlin.selection.*;

import mutate4kotlin.analysis.*;
import mutate4kotlin.coverage.*;
import mutate4kotlin.exec.*;
import mutate4kotlin.manifest.*;

final class SelectionFlagValidator {

    void validate(CliArgumentParseState state) {
        validateScanConflicts(state);
        validateUpdateManifestConflicts(state);
        validateLineConflicts(state);
        validateDifferentialConflicts(state);
    }

    private void validateScanConflicts(CliArgumentParseState state) {
        reject(state.scan && state.sinceLastRun, "--scan may not be combined with --since-last-run");
        reject(state.scan && state.mutateAll, "--scan may not be combined with --mutate-all");
        reject(state.scan && state.updateManifest, "--scan may not be combined with --update-manifest");
        reject(state.scan && state.reuseCoverage, "--scan may not be combined with --reuse-coverage");
    }

    private void validateUpdateManifestConflicts(CliArgumentParseState state) {
        reject(state.updateManifest && state.sinceLastRun, "--update-manifest may not be combined with --since-last-run");
        reject(state.updateManifest && state.mutateAll, "--update-manifest may not be combined with --mutate-all");
        reject(state.updateManifest && !state.lines.isEmpty(), "--update-manifest may not be combined with --lines");
        reject(state.updateManifest && state.reuseCoverage, "--update-manifest may not be combined with --reuse-coverage");
    }

    private void validateLineConflicts(CliArgumentParseState state) {
        reject(!state.lines.isEmpty() && state.sinceLastRun, "--lines may not be combined with --since-last-run");
        reject(!state.lines.isEmpty() && state.mutateAll, "--lines may not be combined with --mutate-all");
    }

    private void validateDifferentialConflicts(CliArgumentParseState state) {
        reject(state.sinceLastRun && state.mutateAll, "--since-last-run may not be combined with --mutate-all");
    }

    private void reject(boolean invalid, String message) {
        if (invalid) {
            throw new IllegalArgumentException(message);
        }
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=dd5ad19ac599c0d3bcb2af5fe1e03eb4fcc3a3008bd3b65c684221848a819ca0
scope.0.id=Y2xhc3M6U2VsZWN0aW9uRmxhZ1ZhbGlkYXRvciNTZWxlY3Rpb25GbGFnVmFsaWRhdG9yOjIz
scope.0.kind=class
scope.0.startLine=23
scope.0.endLine=60
scope.0.semanticHash=64e5d39ef37fdd6271dc6646d935ce8039420aff413d0d34b8250daa1d23d101
scope.1.id=bWV0aG9kOlNlbGVjdGlvbkZsYWdWYWxpZGF0b3IjY3RvcigwKToyMw
scope.1.kind=method
scope.1.startLine=1
scope.1.endLine=60
scope.1.semanticHash=87dca9da3a2e8a3ce10a3ce8b113d9be08d8a8a79b4d0153050ee7756722cc98
scope.2.id=bWV0aG9kOlNlbGVjdGlvbkZsYWdWYWxpZGF0b3IjcmVqZWN0KDIpOjU1
scope.2.kind=method
scope.2.startLine=55
scope.2.endLine=59
scope.2.semanticHash=beb57718ee98cd2e44786f9f7764444772cd20995646adb1e5db64f62ba5b5c1
scope.3.id=bWV0aG9kOlNlbGVjdGlvbkZsYWdWYWxpZGF0b3IjdmFsaWRhdGUoMSk6MjU
scope.3.kind=method
scope.3.startLine=25
scope.3.endLine=30
scope.3.semanticHash=448299af2d6633bd5069d5506965d6a2d9698272fec3c3e65a022419a4792b28
scope.4.id=bWV0aG9kOlNlbGVjdGlvbkZsYWdWYWxpZGF0b3IjdmFsaWRhdGVEaWZmZXJlbnRpYWxDb25mbGljdHMoMSk6NTE
scope.4.kind=method
scope.4.startLine=51
scope.4.endLine=53
scope.4.semanticHash=2556eb12f3c09c56dc5bc50e4490d9cbd79b624e511a337ac3be878a70a282f9
scope.5.id=bWV0aG9kOlNlbGVjdGlvbkZsYWdWYWxpZGF0b3IjdmFsaWRhdGVMaW5lQ29uZmxpY3RzKDEpOjQ2
scope.5.kind=method
scope.5.startLine=46
scope.5.endLine=49
scope.5.semanticHash=1362d0e99f0b95042cd22c8527a30f07743a33a67c96a6e0ee75fbf4c7d840c6
scope.6.id=bWV0aG9kOlNlbGVjdGlvbkZsYWdWYWxpZGF0b3IjdmFsaWRhdGVTY2FuQ29uZmxpY3RzKDEpOjMy
scope.6.kind=method
scope.6.startLine=32
scope.6.endLine=37
scope.6.semanticHash=f77126e07625337ff98bdbefe4cb15513f5f6402ed2337e95e216c2a4ae17cc1
scope.7.id=bWV0aG9kOlNlbGVjdGlvbkZsYWdWYWxpZGF0b3IjdmFsaWRhdGVVcGRhdGVNYW5pZmVzdENvbmZsaWN0cygxKTozOQ
scope.7.kind=method
scope.7.startLine=39
scope.7.endLine=44
scope.7.semanticHash=30a68e990fb1dd8f9975f9e18fd4680e7f63aa5f62b15542bbf2cc66ca78fce0
*/

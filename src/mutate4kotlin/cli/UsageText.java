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

final class UsageText {

    private UsageText() {
    }

    static String text() {
        return """
                Usage:
                  mutate4kotlin <file.kt>                        Mutate one Kotlin source file
                  mutate4kotlin <file.kt> --scan                Print mutation-site scan without running tests
                  mutate4kotlin <file.kt> --update-manifest     Write embedded manifest without running tests
                  mutate4kotlin <file.kt> --reuse-coverage      Reuse existing JaCoCo coverage without refreshing it
                  mutate4kotlin <file.kt> --lines 12,18         Restrict mutations to specific source lines
                  mutate4kotlin <file.kt> --since-last-run      Mutate only scopes changed since embedded manifest
                  mutate4kotlin <file.kt> --mutate-all          Ignore embedded manifest and mutate all covered sites
                  mutate4kotlin <file.kt> --mutation-warning 50 Warn when selected mutation count exceeds threshold
                  mutate4kotlin <file.kt> --max-workers 4       Limit parallel worker count
                  mutate4kotlin <file.kt> --timeout-factor 15   Set mutant timeout as baseline multiplier
                  mutate4kotlin <file.kt> --test-command CMD    Override the test command used for baseline and mutants
                  mutate4kotlin <file.kt> --verbose             Print live worker progress
                  mutate4kotlin --help                          Print this help message
                """;
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=55bbffb4c3c91924fab8ebabeac41166c1708b3686a27377b36164119bb13856
scope.0.id=Y2xhc3M6VXNhZ2VUZXh0I1VzYWdlVGV4dDoyMw
scope.0.kind=class
scope.0.startLine=23
scope.0.endLine=46
scope.0.semanticHash=44539d3a88c9778a1f652ad49543fa01f3e63fad59bdf7c5b28d03a416de5df1
scope.1.id=bWV0aG9kOlVzYWdlVGV4dCNjdG9yKDApOjI1
scope.1.kind=method
scope.1.startLine=25
scope.1.endLine=26
scope.1.semanticHash=2c5260a62764098ff916549096f15e840ae635710b3d87f6432fe9b9f3353d2b
scope.2.id=bWV0aG9kOlVzYWdlVGV4dCN0ZXh0KDApOjI4
scope.2.kind=method
scope.2.startLine=28
scope.2.endLine=45
scope.2.semanticHash=e43ae401b77c782b013331342a5e818f0946632f6f23a29b10b378da5dd47fb7
*/

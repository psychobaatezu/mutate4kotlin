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

final class CliArgumentValidators {

    private final SelectionFlagValidator selection = new SelectionFlagValidator();
    private final JavaFileArgumentValidator javaFile = new JavaFileArgumentValidator();
    private final LineSelectionParser lines = new LineSelectionParser();
    private final IntegerArgumentParser integers = new IntegerArgumentParser();

    void validateSelectionFlags(CliArgumentParseState state) {
        selection.validate(state);
    }

    void ensureExactlyOneJavaFile(java.util.List<String> values) {
        javaFile.validate(values);
    }

    java.util.Set<Integer> parseLines(String text) {
        return lines.parse(text);
    }

    int parsePositiveInt(String text, String flag) {
        return integers.parsePositiveInt(text, flag);
    }

    void ensureHasValue(String[] args, int index, String flag) {
        if (index >= args.length || args[index].startsWith("--")) {
            throw new IllegalArgumentException(flag + " requires a value");
        }
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=479fc7d7d1f45c4251a18376d704539a56ce8869a7f1dd1ded8afd262944ffb1
scope.0.id=Y2xhc3M6Q2xpQXJndW1lbnRWYWxpZGF0b3JzI0NsaUFyZ3VtZW50VmFsaWRhdG9yczoyMw
scope.0.kind=class
scope.0.startLine=23
scope.0.endLine=51
scope.0.semanticHash=5c6e3327215ad3749511cb9e2e3b744d764d05abcc6825adc3b09421a463af9d
scope.1.id=ZmllbGQ6Q2xpQXJndW1lbnRWYWxpZGF0b3JzI2ludGVnZXJzOjI4
scope.1.kind=field
scope.1.startLine=28
scope.1.endLine=28
scope.1.semanticHash=062a16f8af0e1767ed813374fc22e50ee4184e83a3ace5cdd70702abccd7203b
scope.2.id=ZmllbGQ6Q2xpQXJndW1lbnRWYWxpZGF0b3JzI2phdmFGaWxlOjI2
scope.2.kind=field
scope.2.startLine=26
scope.2.endLine=26
scope.2.semanticHash=17d35563085dffd53e8a88e5e7e0b09ac730c32b16012fec84ec6e88f80ebc45
scope.3.id=ZmllbGQ6Q2xpQXJndW1lbnRWYWxpZGF0b3JzI2xpbmVzOjI3
scope.3.kind=field
scope.3.startLine=27
scope.3.endLine=27
scope.3.semanticHash=4c5705ced46a6c749111cd3eee0911b2e0f8a2b39e5b00e96fef3bd0bd5d4a32
scope.4.id=ZmllbGQ6Q2xpQXJndW1lbnRWYWxpZGF0b3JzI3NlbGVjdGlvbjoyNQ
scope.4.kind=field
scope.4.startLine=25
scope.4.endLine=25
scope.4.semanticHash=61d552e2e746ff54c04ec3bc2f1240eb79bfca78507465bec0afc1b52ee51458
scope.5.id=bWV0aG9kOkNsaUFyZ3VtZW50VmFsaWRhdG9ycyNjdG9yKDApOjIz
scope.5.kind=method
scope.5.startLine=1
scope.5.endLine=51
scope.5.semanticHash=5673c55b5e9323b6347a47393b1c13ed2ecaedbcad17260f835d2cc32a480c9a
scope.6.id=bWV0aG9kOkNsaUFyZ3VtZW50VmFsaWRhdG9ycyNlbnN1cmVFeGFjdGx5T25lSmF2YUZpbGUoMSk6MzQ
scope.6.kind=method
scope.6.startLine=34
scope.6.endLine=36
scope.6.semanticHash=7b368edc13fa9b16ac6feac5a07b71a3fd1719f9ace742f98717f3e5fb06074a
scope.7.id=bWV0aG9kOkNsaUFyZ3VtZW50VmFsaWRhdG9ycyNlbnN1cmVIYXNWYWx1ZSgzKTo0Ng
scope.7.kind=method
scope.7.startLine=46
scope.7.endLine=50
scope.7.semanticHash=54e819d14f6d4d09a2499b09b6fb6b1b0b39e61aaa80309ca0f3ce0b357e3eb0
scope.8.id=bWV0aG9kOkNsaUFyZ3VtZW50VmFsaWRhdG9ycyNwYXJzZUxpbmVzKDEpOjM4
scope.8.kind=method
scope.8.startLine=38
scope.8.endLine=40
scope.8.semanticHash=9c710005981712d0f441e044025c1442956957e4f7171f031efa8a688c582678
scope.9.id=bWV0aG9kOkNsaUFyZ3VtZW50VmFsaWRhdG9ycyNwYXJzZVBvc2l0aXZlSW50KDIpOjQy
scope.9.kind=method
scope.9.startLine=42
scope.9.endLine=44
scope.9.semanticHash=b1eb6f7f6c6f6e4c91ebab9722352c7f912d143dbaadfbe11147caedb4e9f35c
scope.10.id=bWV0aG9kOkNsaUFyZ3VtZW50VmFsaWRhdG9ycyN2YWxpZGF0ZVNlbGVjdGlvbkZsYWdzKDEpOjMw
scope.10.kind=method
scope.10.startLine=30
scope.10.endLine=32
scope.10.semanticHash=6c83de6c0f8c0d9339c0ad65707dd16b27eb2e56e60444392c2929199234756c
*/

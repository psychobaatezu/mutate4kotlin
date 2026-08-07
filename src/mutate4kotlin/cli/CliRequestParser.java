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

import java.io.PrintStream;

final class CliRequestParser {

    private final PrintStream out;
    private final PrintStream err;

    CliRequestParser(PrintStream out, PrintStream err) {
        this.out = out;
        this.err = err;
    }

    ParseOutcome parse(String[] args) {
        try {
            CliArguments parsed = CliArgumentsParser.parse(args);
            if (parsed.mode() == CliMode.HELP) {
                out.println(Main.usage());
                return ParseOutcome.exit(0);
            }
            return ParseOutcome.ok(parsed);
        } catch (IllegalArgumentException ex) {
            err.println(ex.getMessage());
            out.println(Main.usage());
            return ParseOutcome.exit(1);
        }
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=3753648042006519df8157791b46ac2177760d8818aef28f7837d1e635f431ab
scope.0.id=Y2xhc3M6Q2xpUmVxdWVzdFBhcnNlciNDbGlSZXF1ZXN0UGFyc2VyOjI1
scope.0.kind=class
scope.0.startLine=25
scope.0.endLine=49
scope.0.semanticHash=ea45c7b994c4718a59c440c90e9a873ad6e3989f3ebf62c2f43cd9629c04fe49
scope.1.id=ZmllbGQ6Q2xpUmVxdWVzdFBhcnNlciNlcnI6Mjg
scope.1.kind=field
scope.1.startLine=28
scope.1.endLine=28
scope.1.semanticHash=0f12a462a677e93faaa05787bc46db2cace278490b3a801f721c167aedea712a
scope.2.id=ZmllbGQ6Q2xpUmVxdWVzdFBhcnNlciNvdXQ6Mjc
scope.2.kind=field
scope.2.startLine=27
scope.2.endLine=27
scope.2.semanticHash=b98df4fbf291f7cd01ba32f6b30c169fc64c08011a73e48a271561a4fcdd0a52
scope.3.id=bWV0aG9kOkNsaVJlcXVlc3RQYXJzZXIjY3RvcigyKTozMA
scope.3.kind=method
scope.3.startLine=30
scope.3.endLine=33
scope.3.semanticHash=c743d4cc4dc3e4cf1a58bcb3e0dbbe1e70db93b988301a3dc8d3af850a7034e2
scope.4.id=bWV0aG9kOkNsaVJlcXVlc3RQYXJzZXIjcGFyc2UoMSk6MzU
scope.4.kind=method
scope.4.startLine=35
scope.4.endLine=48
scope.4.semanticHash=8f3d8eef5a9cffe806c759fa222d83bfab6a74b9493ccb17b38d66a8b3b9d707
*/

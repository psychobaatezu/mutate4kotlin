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

public final class CliArgumentsParser {

    private static final CliArgumentParserEngine ENGINE = new CliArgumentParserEngine(new CliArgumentValidators());

    private CliArgumentsParser() {
    }

    public static CliArguments parse(String[] args) {
        return ENGINE.parse(args);
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=5f98cd08c0dd206e55551b52f157dce37be06f5271f128ffc5a07a18940395ff
scope.0.id=Y2xhc3M6Q2xpQXJndW1lbnRzUGFyc2VyI0NsaUFyZ3VtZW50c1BhcnNlcjoyMw
scope.0.kind=class
scope.0.startLine=23
scope.0.endLine=33
scope.0.semanticHash=06ddb673da43a37153b9a5c0e280e315aff8c5584e13e73db89b156eda3d4389
scope.1.id=ZmllbGQ6Q2xpQXJndW1lbnRzUGFyc2VyI0VOR0lORToyNQ
scope.1.kind=field
scope.1.startLine=25
scope.1.endLine=25
scope.1.semanticHash=4ee2c14dbe6607ced9f13819e8d4091c5c772636b8debf755711c59d418d274a
scope.2.id=bWV0aG9kOkNsaUFyZ3VtZW50c1BhcnNlciNjdG9yKDApOjI3
scope.2.kind=method
scope.2.startLine=27
scope.2.endLine=28
scope.2.semanticHash=c9502e5b2d38c24ae05d67ebe8ddde01d9ecd2bf449a91ac80aa7ba16421ee7d
scope.3.id=bWV0aG9kOkNsaUFyZ3VtZW50c1BhcnNlciNwYXJzZSgxKTozMA
scope.3.kind=method
scope.3.startLine=30
scope.3.endLine=32
scope.3.semanticHash=bb6b47f1fede8d9249df46caf1399a21654359f80c489ff4cecd06c498f4369d
*/

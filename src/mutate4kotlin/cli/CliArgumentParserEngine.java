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

final class CliArgumentParserEngine {

    private final CliArgumentValidators validators;

    CliArgumentParserEngine(CliArgumentValidators validators) {
        this.validators = validators;
    }

    CliArguments parse(String[] args) {
        CliArgumentParseState state = new CliArgumentParseState();
        for (int i = 0; i < args.length; i++) {
            i = CliArgumentSwitch.parse(args, i, state, validators);
        }
        if (state.help) {
            return state.helpArguments();
        }
        validators.validateSelectionFlags(state);
        validators.ensureExactlyOneJavaFile(state.values);
        return state.toCliArguments();
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=b286a6cf31faa70534ec3dcb23ce97a14056bea73c44ab5501de20943bd0bf3f
scope.0.id=Y2xhc3M6Q2xpQXJndW1lbnRQYXJzZXJFbmdpbmUjQ2xpQXJndW1lbnRQYXJzZXJFbmdpbmU6MjM
scope.0.kind=class
scope.0.startLine=23
scope.0.endLine=43
scope.0.semanticHash=b78127738019f27d988d63e8ff15d20233ececaedd01fc0dc8a282897bd2d57f
scope.1.id=ZmllbGQ6Q2xpQXJndW1lbnRQYXJzZXJFbmdpbmUjdmFsaWRhdG9yczoyNQ
scope.1.kind=field
scope.1.startLine=25
scope.1.endLine=25
scope.1.semanticHash=e6d33a8a571391f797ec9482f684ba1c6075a91de0d0fbecf48e240698b39570
scope.2.id=bWV0aG9kOkNsaUFyZ3VtZW50UGFyc2VyRW5naW5lI2N0b3IoMSk6Mjc
scope.2.kind=method
scope.2.startLine=27
scope.2.endLine=29
scope.2.semanticHash=41078c47845bac2ea199e3b6b0a5c5824c187108a30b65d31eb77dc1829eaa00
scope.3.id=bWV0aG9kOkNsaUFyZ3VtZW50UGFyc2VyRW5naW5lI3BhcnNlKDEpOjMx
scope.3.kind=method
scope.3.startLine=31
scope.3.endLine=42
scope.3.semanticHash=d12bb16d5b61222ebda05a7b8a181104c915282e891cdcae7c329582856c0ea5
*/

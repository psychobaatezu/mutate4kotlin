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

record ParseOutcome(CliArguments arguments, int exitCode) {

    static ParseOutcome ok(CliArguments arguments) {
        return new ParseOutcome(arguments, -1);
    }

    static ParseOutcome exit(int code) {
        return new ParseOutcome(null, code);
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=8dd084180da468b4ce06a56542c41edbcdfbab0d3e850f618ca60e100c08faea
scope.0.id=Y2xhc3M6UGFyc2VPdXRjb21lI1BhcnNlT3V0Y29tZToyMw
scope.0.kind=class
scope.0.startLine=23
scope.0.endLine=32
scope.0.semanticHash=2063f77bb922d74f7fddd05bcc7b37f8350a3fe0d64ce40105cb43cce6b13dac
scope.1.id=ZmllbGQ6UGFyc2VPdXRjb21lI2FyZ3VtZW50czoyMw
scope.1.kind=field
scope.1.startLine=23
scope.1.endLine=23
scope.1.semanticHash=cd0aa9cda8be3d77fb8220803123993e2f047089a17517566fa55bcece43809b
scope.2.id=ZmllbGQ6UGFyc2VPdXRjb21lI2V4aXRDb2RlOjIz
scope.2.kind=field
scope.2.startLine=23
scope.2.endLine=23
scope.2.semanticHash=22d20a5f7c9173958dfb701f79fb99a4bb0b0451e48a6d3c48c78a1f0d2ef019
scope.3.id=bWV0aG9kOlBhcnNlT3V0Y29tZSNjdG9yKDIpOjIz
scope.3.kind=method
scope.3.startLine=1
scope.3.endLine=32
scope.3.semanticHash=3c813f808697cb1003cafad7272449b41b3616ed24d6f629d1839274f8d0518d
scope.4.id=bWV0aG9kOlBhcnNlT3V0Y29tZSNleGl0KDEpOjI5
scope.4.kind=method
scope.4.startLine=29
scope.4.endLine=31
scope.4.semanticHash=d5b262476ef695a84ad06b4343f8a84f94ad4c1a2d5eefb2d5e12a978263d51b
scope.5.id=bWV0aG9kOlBhcnNlT3V0Y29tZSNvaygxKToyNQ
scope.5.kind=method
scope.5.startLine=25
scope.5.endLine=27
scope.5.semanticHash=4c212688567c1b87ca6554c506979b987eecbffb566d0e23ea976080a63f02c2
*/

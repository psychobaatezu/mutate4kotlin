package mutate4kotlin.model;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.cli.*;
import mutate4kotlin.engine.*;

import mutate4kotlin.selection.*;

import mutate4kotlin.analysis.*;
import mutate4kotlin.coverage.*;
import mutate4kotlin.exec.*;
import mutate4kotlin.manifest.*;

public record ScopeRef(String id, String kind, int startLine, int endLine) {

    public static ScopeRef from(MutationScope scope) {
        return new ScopeRef(scope.id(), scope.kind(), scope.startLine(), scope.endLine());
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=9e4440a5249ce9dc8221f0ffe189a137ef89d2a4a8685ffa4236b460b0a73d45
scope.0.id=Y2xhc3M6U2NvcGVSZWYjU2NvcGVSZWY6MTY
scope.0.kind=class
scope.0.startLine=16
scope.0.endLine=21
scope.0.semanticHash=17cf6d81ef4a368d9ab0c27c9a44128c3ad43ffb223d0c9a2a1176d93321779e
scope.1.id=ZmllbGQ6U2NvcGVSZWYjZW5kTGluZToxNg
scope.1.kind=field
scope.1.startLine=16
scope.1.endLine=16
scope.1.semanticHash=6a317d712b10858871edee8b33ee58b8a682069bbab781a96d93cf4abe131abe
scope.2.id=ZmllbGQ6U2NvcGVSZWYjaWQ6MTY
scope.2.kind=field
scope.2.startLine=16
scope.2.endLine=16
scope.2.semanticHash=e8fa4cb36294bbb4bd5d903d949ffd11d1cecb67601bc0971c86bcf7b9f75249
scope.3.id=ZmllbGQ6U2NvcGVSZWYja2luZDoxNg
scope.3.kind=field
scope.3.startLine=16
scope.3.endLine=16
scope.3.semanticHash=409014eefe90e3ecd3219c367bb4cae049ec0a555945b9fcb4539bc28c2cd25e
scope.4.id=ZmllbGQ6U2NvcGVSZWYjc3RhcnRMaW5lOjE2
scope.4.kind=field
scope.4.startLine=16
scope.4.endLine=16
scope.4.semanticHash=1c3439a97a0bcc4e6560a484ca45e0482b4b1010943d82fa235aa4a63d21ffb1
scope.5.id=bWV0aG9kOlNjb3BlUmVmI2N0b3IoNCk6MTY
scope.5.kind=method
scope.5.startLine=1
scope.5.endLine=21
scope.5.semanticHash=3ad01ba81163ec13b10b394286284fdc2ddf713c19c91b38f10301fb037f1dac
scope.6.id=bWV0aG9kOlNjb3BlUmVmI2Zyb20oMSk6MTg
scope.6.kind=method
scope.6.startLine=18
scope.6.endLine=20
scope.6.semanticHash=8f327c9ef005f1e4a736e8377428fdd1b13a841c298fbda7dd838474d68341e7
*/

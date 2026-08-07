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

public record TestRun(int exitCode, String output, long durationMillis, boolean timedOut) {

    public boolean passed() {
        return exitCode == 0 && !timedOut;
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=a1200c7c8594f640a57e739bfe7b153df24c5646ce825f35d16a65a70d70c2fe
scope.0.id=Y2xhc3M6VGVzdFJ1biNUZXN0UnVuOjE2
scope.0.kind=class
scope.0.startLine=16
scope.0.endLine=21
scope.0.semanticHash=a8319cdc2497ef28fc609875b51d04b84139383a3723e7e7ef3ba8d6dd0edc81
scope.1.id=ZmllbGQ6VGVzdFJ1biNkdXJhdGlvbk1pbGxpczoxNg
scope.1.kind=field
scope.1.startLine=16
scope.1.endLine=16
scope.1.semanticHash=aa9aa63ad847ef3f927bafd418f3e392b391e4c0075d48a91d10d27edf303bc3
scope.2.id=ZmllbGQ6VGVzdFJ1biNleGl0Q29kZToxNg
scope.2.kind=field
scope.2.startLine=16
scope.2.endLine=16
scope.2.semanticHash=22d20a5f7c9173958dfb701f79fb99a4bb0b0451e48a6d3c48c78a1f0d2ef019
scope.3.id=ZmllbGQ6VGVzdFJ1biNvdXRwdXQ6MTY
scope.3.kind=field
scope.3.startLine=16
scope.3.endLine=16
scope.3.semanticHash=a7385d49a8e8309a95b92e10b7c0f0563448f907f09582aa98f5020ce07e6008
scope.4.id=ZmllbGQ6VGVzdFJ1biN0aW1lZE91dDoxNg
scope.4.kind=field
scope.4.startLine=16
scope.4.endLine=16
scope.4.semanticHash=15d6b8ef15a135d7a7402858996de81a1a61f74338bfc1158a2c3dd58a852ac8
scope.5.id=bWV0aG9kOlRlc3RSdW4jY3Rvcig0KToxNg
scope.5.kind=method
scope.5.startLine=1
scope.5.endLine=21
scope.5.semanticHash=9e2ec17d5a82c6ba8db791a1c9dba16ff33e7637713f0c5aa560811d9d9b8971
scope.6.id=bWV0aG9kOlRlc3RSdW4jcGFzc2VkKDApOjE4
scope.6.kind=method
scope.6.startLine=18
scope.6.endLine=20
scope.6.semanticHash=80bacb5150f79299f101b8301c40780c11f66a0f0b604116c97ad06892121daf
*/

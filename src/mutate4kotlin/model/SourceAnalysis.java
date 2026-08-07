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

import java.util.List;

public record SourceAnalysis(String sourceWithoutManifest, List<MutationSite> sites, List<MutationScope> scopes, String moduleHash) {
}

/* mutate4kotlin-manifest
version=1
moduleHash=e25171ef1d335e824ee56f92a1b02c850287c4b08c0ff8d31dae356bb2e5493f
scope.0.id=Y2xhc3M6U291cmNlQW5hbHlzaXMjU291cmNlQW5hbHlzaXM6MTg
scope.0.kind=class
scope.0.startLine=18
scope.0.endLine=19
scope.0.semanticHash=2b36764fa6c31c97b375463d0496fd3b95371cdb8a425ce226aa3491bb9c4129
scope.1.id=ZmllbGQ6U291cmNlQW5hbHlzaXMjbW9kdWxlSGFzaDoxOA
scope.1.kind=field
scope.1.startLine=18
scope.1.endLine=18
scope.1.semanticHash=053274f7b8645a3d89c728e10be06932de6b8480352e50cfec8e178920e832d9
scope.2.id=ZmllbGQ6U291cmNlQW5hbHlzaXMjc2NvcGVzOjE4
scope.2.kind=field
scope.2.startLine=18
scope.2.endLine=18
scope.2.semanticHash=4a9e24cf881dede690f21f7e430f391a556e5ec7db5855c8bd55305b01e6a8ef
scope.3.id=ZmllbGQ6U291cmNlQW5hbHlzaXMjc2l0ZXM6MTg
scope.3.kind=field
scope.3.startLine=18
scope.3.endLine=18
scope.3.semanticHash=b65130d404b08b000d3afbdc60171fe0abb6d6a71372fe67bd3835195bbe0741
scope.4.id=ZmllbGQ6U291cmNlQW5hbHlzaXMjc291cmNlV2l0aG91dE1hbmlmZXN0OjE4
scope.4.kind=field
scope.4.startLine=18
scope.4.endLine=18
scope.4.semanticHash=32ae1e36c543cbdec279edcee6040bf4edd06323692af4adc7123e31e9651c92
scope.5.id=bWV0aG9kOlNvdXJjZUFuYWx5c2lzI2N0b3IoNCk6MTg
scope.5.kind=method
scope.5.startLine=1
scope.5.endLine=19
scope.5.semanticHash=49cee33aadc8f42ebf591bf91c057243756bfebb61f3b7e94fa0dfbab854fa00
*/

package mutate4kotlin;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.cli.*;
import mutate4kotlin.engine.*;

import mutate4kotlin.selection.*;

import mutate4kotlin.analysis.*;
import mutate4kotlin.coverage.*;
import mutate4kotlin.exec.*;
import mutate4kotlin.manifest.*;

import java.util.List;
import java.util.Set;

public record CliArguments(CliMode mode,
                    List<String> fileArgs,
                    Set<Integer> lines,
                    boolean scan,
                    boolean updateManifest,
                    boolean reuseCoverage,
                    boolean sinceLastRun,
                    boolean mutateAll,
                    int timeoutFactor,
                    int mutationWarning,
                    int maxWorkers,
                    String testCommand,
                    boolean verbose) {
}

/* mutate4kotlin-manifest
version=1
moduleHash=3fbc118c46773248b423a6a4d5f47aa079f7fbfc7a163a78e80701ad57c8969d
scope.0.id=Y2xhc3M6Q2xpQXJndW1lbnRzI0NsaUFyZ3VtZW50czoyMQ
scope.0.kind=class
scope.0.startLine=21
scope.0.endLine=34
scope.0.semanticHash=43a95039c81874abfb9aee23f206a1cf25510b9325835b0182ccedbee902bbae
scope.1.id=ZmllbGQ6Q2xpQXJndW1lbnRzI2ZpbGVBcmdzOjIy
scope.1.kind=field
scope.1.startLine=22
scope.1.endLine=22
scope.1.semanticHash=f80baf7e7953a4656e562aaf16e4f07371e3273850635d8563b47631b1dad17c
scope.2.id=ZmllbGQ6Q2xpQXJndW1lbnRzI2xpbmVzOjIz
scope.2.kind=field
scope.2.startLine=23
scope.2.endLine=23
scope.2.semanticHash=8a75ee2e82541226ee127d34811baf42045b1c69d8b37903c1fb78cde148348a
scope.3.id=ZmllbGQ6Q2xpQXJndW1lbnRzI21heFdvcmtlcnM6MzE
scope.3.kind=field
scope.3.startLine=31
scope.3.endLine=31
scope.3.semanticHash=cb9e580b0dbcba22d95de3dcbc46151b4a0fd0354ec55d5550643f4bd515f69a
scope.4.id=ZmllbGQ6Q2xpQXJndW1lbnRzI21vZGU6MjE
scope.4.kind=field
scope.4.startLine=21
scope.4.endLine=21
scope.4.semanticHash=b09c0d200fb0d0f48cf18d6f308f4a995a0bdc16401be97c6b5ac3be74abcc23
scope.5.id=ZmllbGQ6Q2xpQXJndW1lbnRzI211dGF0ZUFsbDoyOA
scope.5.kind=field
scope.5.startLine=28
scope.5.endLine=28
scope.5.semanticHash=45b9ddd73abb90f7661c5660a536201133ef36f9d37780c314976b8369329893
scope.6.id=ZmllbGQ6Q2xpQXJndW1lbnRzI211dGF0aW9uV2FybmluZzozMA
scope.6.kind=field
scope.6.startLine=30
scope.6.endLine=30
scope.6.semanticHash=db70f05471a31f019eeea08e8c09a2a723520a01342f16ffb145bae63dd1da10
scope.7.id=ZmllbGQ6Q2xpQXJndW1lbnRzI3JldXNlQ292ZXJhZ2U6MjY
scope.7.kind=field
scope.7.startLine=26
scope.7.endLine=26
scope.7.semanticHash=bd02e245f365b15e84d7abb7a0644d0a27b11d7c9df78a5395c46d2d423b0990
scope.8.id=ZmllbGQ6Q2xpQXJndW1lbnRzI3NjYW46MjQ
scope.8.kind=field
scope.8.startLine=24
scope.8.endLine=24
scope.8.semanticHash=7f10f199c6d010c2ad9bfc4c060bbff97d152c95e333e09f9e5f223e7657e8dc
scope.9.id=ZmllbGQ6Q2xpQXJndW1lbnRzI3NpbmNlTGFzdFJ1bjoyNw
scope.9.kind=field
scope.9.startLine=27
scope.9.endLine=27
scope.9.semanticHash=e3aab04e984210af337a062a2e746b648c5424dd0129379132745921c8fbc330
scope.10.id=ZmllbGQ6Q2xpQXJndW1lbnRzI3Rlc3RDb21tYW5kOjMy
scope.10.kind=field
scope.10.startLine=32
scope.10.endLine=32
scope.10.semanticHash=fcaba91778b2be1d513e1fa0c2dfed255038dc22dc4a56902203442ff3a99cbb
scope.11.id=ZmllbGQ6Q2xpQXJndW1lbnRzI3RpbWVvdXRGYWN0b3I6Mjk
scope.11.kind=field
scope.11.startLine=29
scope.11.endLine=29
scope.11.semanticHash=1b3ea5376e0c3c20dc9146685258db39ce3876efb7e4fa354f84d6a18bd61237
scope.12.id=ZmllbGQ6Q2xpQXJndW1lbnRzI3VwZGF0ZU1hbmlmZXN0OjI1
scope.12.kind=field
scope.12.startLine=25
scope.12.endLine=25
scope.12.semanticHash=7ffdcb49b2b92703aeea63659102c2dd1bb4465c7da15555e74cef6ff4e51a22
scope.13.id=ZmllbGQ6Q2xpQXJndW1lbnRzI3ZlcmJvc2U6MzM
scope.13.kind=field
scope.13.startLine=33
scope.13.endLine=33
scope.13.semanticHash=bb1902dc1650551d06c41dfc0f5c22f3860a3d2377acd2928894bea2b86ccfb6
scope.14.id=bWV0aG9kOkNsaUFyZ3VtZW50cyNjdG9yKDEzKToyMQ
scope.14.kind=method
scope.14.startLine=1
scope.14.endLine=34
scope.14.semanticHash=988930c4a6d6946b8a07ada148d3bcbafc9c30f71b141abb305e1e5c55235069
*/

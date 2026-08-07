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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

final class CliArgumentParseState {

    boolean help;
    boolean verbose;
    Set<Integer> lines = Set.of();
    boolean scan;
    boolean updateManifest;
    boolean reuseCoverage;
    boolean sinceLastRun;
    boolean mutateAll;
    int timeoutFactor = CliArgumentsParserDefaults.DEFAULT_TIMEOUT_FACTOR;
    int mutationWarning = CliArgumentsParserDefaults.DEFAULT_MUTATION_WARNING;
    int maxWorkers = CliArgumentsParserDefaults.DEFAULT_MAX_WORKERS;
    String testCommand;
    final List<String> values = new ArrayList<>();

    CliArguments helpArguments() {
        return new CliArguments(CliMode.HELP, List.of(), Set.of(), false, false, false, false, false,
                timeoutFactor, mutationWarning, maxWorkers, null, verbose);
    }

    CliArguments toCliArguments() {
        return new CliArguments(
                CliMode.EXPLICIT_FILES,
                List.copyOf(values),
                lines,
                scan,
                updateManifest,
                reuseCoverage,
                sinceLastRun,
                mutateAll,
                timeoutFactor,
                mutationWarning,
                maxWorkers,
                testCommand,
                verbose
        );
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=87a090d350617ab1f797c3a7fa2e638d21426e20e8a923560b48aea6d853dc2b
scope.0.id=Y2xhc3M6Q2xpQXJndW1lbnRQYXJzZVN0YXRlI0NsaUFyZ3VtZW50UGFyc2VTdGF0ZToyNw
scope.0.kind=class
scope.0.startLine=27
scope.0.endLine=65
scope.0.semanticHash=af9ec4f9b21651f47f50a1c4069d1e671cd7bcf835c929e64ec2579c3202cb6a
scope.1.id=ZmllbGQ6Q2xpQXJndW1lbnRQYXJzZVN0YXRlI2hlbHA6Mjk
scope.1.kind=field
scope.1.startLine=29
scope.1.endLine=29
scope.1.semanticHash=35690be3e31a7c92ccb81daa48fc0f4ac93fa04005d069bcc023686b0d10f7fd
scope.2.id=ZmllbGQ6Q2xpQXJndW1lbnRQYXJzZVN0YXRlI2xpbmVzOjMx
scope.2.kind=field
scope.2.startLine=31
scope.2.endLine=31
scope.2.semanticHash=51bcc7b5a5573eac9b86a4d0bb0af46236536f2e49ab3f6f9afc803848529193
scope.3.id=ZmllbGQ6Q2xpQXJndW1lbnRQYXJzZVN0YXRlI21heFdvcmtlcnM6Mzk
scope.3.kind=field
scope.3.startLine=39
scope.3.endLine=39
scope.3.semanticHash=0f5577370cea62f6ae143cc63050873157c1d682394b264e8d85282f4477bc2c
scope.4.id=ZmllbGQ6Q2xpQXJndW1lbnRQYXJzZVN0YXRlI211dGF0ZUFsbDozNg
scope.4.kind=field
scope.4.startLine=36
scope.4.endLine=36
scope.4.semanticHash=301f08316018fb615f6f3d7413ea5d1472ef9f164f50f1d8db0720da4607d2db
scope.5.id=ZmllbGQ6Q2xpQXJndW1lbnRQYXJzZVN0YXRlI211dGF0aW9uV2FybmluZzozOA
scope.5.kind=field
scope.5.startLine=38
scope.5.endLine=38
scope.5.semanticHash=c05340e1234b2aa2077b929d78d71d59a4271367f1c1fc9d3bf1744ef9d1609b
scope.6.id=ZmllbGQ6Q2xpQXJndW1lbnRQYXJzZVN0YXRlI3JldXNlQ292ZXJhZ2U6MzQ
scope.6.kind=field
scope.6.startLine=34
scope.6.endLine=34
scope.6.semanticHash=a030f55219eb0e114824e366218720d4e4c279f8e9356d78c2737f268ab787f7
scope.7.id=ZmllbGQ6Q2xpQXJndW1lbnRQYXJzZVN0YXRlI3NjYW46MzI
scope.7.kind=field
scope.7.startLine=32
scope.7.endLine=32
scope.7.semanticHash=996f1e8e0bbfab797a7897f07d2236945659723362fb03e6ba8e702f11c7526e
scope.8.id=ZmllbGQ6Q2xpQXJndW1lbnRQYXJzZVN0YXRlI3NpbmNlTGFzdFJ1bjozNQ
scope.8.kind=field
scope.8.startLine=35
scope.8.endLine=35
scope.8.semanticHash=e5d2024b253e415abe50124d8b3f92018df8bbb4efdafad4b40881b30b44b5b2
scope.9.id=ZmllbGQ6Q2xpQXJndW1lbnRQYXJzZVN0YXRlI3Rlc3RDb21tYW5kOjQw
scope.9.kind=field
scope.9.startLine=40
scope.9.endLine=40
scope.9.semanticHash=3d3aa181c72cfe9c957932f7666554a01446f8dd58c192f43605e78f6491b7a5
scope.10.id=ZmllbGQ6Q2xpQXJndW1lbnRQYXJzZVN0YXRlI3RpbWVvdXRGYWN0b3I6Mzc
scope.10.kind=field
scope.10.startLine=37
scope.10.endLine=37
scope.10.semanticHash=f1b2d467846a92ed76456958bbc911723128834d07fab35730d228883ed4ccaf
scope.11.id=ZmllbGQ6Q2xpQXJndW1lbnRQYXJzZVN0YXRlI3VwZGF0ZU1hbmlmZXN0OjMz
scope.11.kind=field
scope.11.startLine=33
scope.11.endLine=33
scope.11.semanticHash=1f702c8ab025d5c71925c52d1e2bc9f67068fde0613d7546e6a2a38d457cf6a1
scope.12.id=ZmllbGQ6Q2xpQXJndW1lbnRQYXJzZVN0YXRlI3ZhbHVlczo0MQ
scope.12.kind=field
scope.12.startLine=41
scope.12.endLine=41
scope.12.semanticHash=b5d1c7a4e0b651e4d120fa7ed33f622ebb8894bbb99e195d970909759a3e000b
scope.13.id=ZmllbGQ6Q2xpQXJndW1lbnRQYXJzZVN0YXRlI3ZlcmJvc2U6MzA
scope.13.kind=field
scope.13.startLine=30
scope.13.endLine=30
scope.13.semanticHash=166515b0f52e3ce745db1f16f2bf6848bc4584b19174b2f481ab37f53505256c
scope.14.id=bWV0aG9kOkNsaUFyZ3VtZW50UGFyc2VTdGF0ZSNjdG9yKDApOjI3
scope.14.kind=method
scope.14.startLine=1
scope.14.endLine=65
scope.14.semanticHash=961f87bf2d9fa7e37d9f344dbdd42cb4f47c31713b7c9f158190d71354258389
scope.15.id=bWV0aG9kOkNsaUFyZ3VtZW50UGFyc2VTdGF0ZSNoZWxwQXJndW1lbnRzKDApOjQz
scope.15.kind=method
scope.15.startLine=43
scope.15.endLine=46
scope.15.semanticHash=22c4b967067420e5654ca0e32a879fc2d2d0e5adeafa34aab089b2035310be4d
scope.16.id=bWV0aG9kOkNsaUFyZ3VtZW50UGFyc2VTdGF0ZSN0b0NsaUFyZ3VtZW50cygwKTo0OA
scope.16.kind=method
scope.16.startLine=48
scope.16.endLine=64
scope.16.semanticHash=28b5d885153afb5e2a02dbe108dad6b842c23780d8c3ca73ff84170a40ba7f6c
*/

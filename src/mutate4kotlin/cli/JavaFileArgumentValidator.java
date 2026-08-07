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

import java.util.List;

final class JavaFileArgumentValidator {

    void validate(List<String> values) {
        if (values.isEmpty()) {
            throw new IllegalArgumentException("mutate4kotlin requires exactly one Kotlin file");
        }
        if (values.size() != 1) {
            throw new IllegalArgumentException("mutate4kotlin accepts exactly one Kotlin file");
        }
        if (!values.get(0).endsWith(".kt")) {
            throw new IllegalArgumentException("mutate4kotlin target must be a .kt file");
        }
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=1655a87438042b533b6615cbda57205578bffc6981ce75789035589167b6d767
scope.0.id=Y2xhc3M6SmF2YUZpbGVBcmd1bWVudFZhbGlkYXRvciNKYXZhRmlsZUFyZ3VtZW50VmFsaWRhdG9yOjI1
scope.0.kind=class
scope.0.startLine=25
scope.0.endLine=38
scope.0.semanticHash=78c9af4c0f81a53014e086a7c08cdee76a1dd0949d4bfa51cc42c495a7bb6f28
scope.1.id=bWV0aG9kOkphdmFGaWxlQXJndW1lbnRWYWxpZGF0b3IjY3RvcigwKToyNQ
scope.1.kind=method
scope.1.startLine=1
scope.1.endLine=38
scope.1.semanticHash=d223f0307d9a8eb61580e84ba81468927cb68823f2019f3d3a8371c5c7dac653
scope.2.id=bWV0aG9kOkphdmFGaWxlQXJndW1lbnRWYWxpZGF0b3IjdmFsaWRhdGUoMSk6Mjc
scope.2.kind=method
scope.2.startLine=27
scope.2.endLine=37
scope.2.semanticHash=87e59d537eab8243507503c0e187a8b9c2851a58aaf57dc83a69d58553d78266
*/

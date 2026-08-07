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

import java.util.Set;

public final class CoverageReport {

    private final Set<CoverageSite> coveredLines;
    private final boolean treatAllAsCovered;

    public CoverageReport(Set<CoverageSite> coveredLines) {
        this(coveredLines, false);
    }

    private CoverageReport(Set<CoverageSite> coveredLines, boolean treatAllAsCovered) {
        this.coveredLines = coveredLines;
        this.treatAllAsCovered = treatAllAsCovered;
    }

    public static CoverageReport allCovered() {
        return new CoverageReport(Set.of(), true);
    }

    public boolean covers(String sourcePath, int lineNumber) {
        return treatAllAsCovered || coveredLines.contains(new CoverageSite(sourcePath, lineNumber));
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=86ff060ca10a8668a402fc8c5a3ba5e206fb2349a9d757872041e6769902844a
scope.0.id=Y2xhc3M6Q292ZXJhZ2VSZXBvcnQjQ292ZXJhZ2VSZXBvcnQ6MTg
scope.0.kind=class
scope.0.startLine=18
scope.0.endLine=39
scope.0.semanticHash=5bb450c3865483fb31a985d6dc195f756e4d319a4c2d87387326839ad1fcee80
scope.1.id=ZmllbGQ6Q292ZXJhZ2VSZXBvcnQjY292ZXJlZExpbmVzOjIw
scope.1.kind=field
scope.1.startLine=20
scope.1.endLine=20
scope.1.semanticHash=4f7a88fd3dc1fcfec51c707be9032883b91d6c11f3db85363f6c010f9482846c
scope.2.id=ZmllbGQ6Q292ZXJhZ2VSZXBvcnQjdHJlYXRBbGxBc0NvdmVyZWQ6MjE
scope.2.kind=field
scope.2.startLine=21
scope.2.endLine=21
scope.2.semanticHash=a5c8870fff04bd311508d42c8d1f24a7f828c96442818a906ad573d83cff8a6e
scope.3.id=bWV0aG9kOkNvdmVyYWdlUmVwb3J0I2FsbENvdmVyZWQoMCk6MzI
scope.3.kind=method
scope.3.startLine=32
scope.3.endLine=34
scope.3.semanticHash=3f41d8efce05472b2d211082e3f2c9c5b903409b206a54a7a613b8c883d1e631
scope.4.id=bWV0aG9kOkNvdmVyYWdlUmVwb3J0I2NvdmVycygyKTozNg
scope.4.kind=method
scope.4.startLine=36
scope.4.endLine=38
scope.4.semanticHash=8665da6a7a59a85c55671ecd967860d5bd3b43015a6d8cdfc6e3d4b87beaaf7b
scope.5.id=bWV0aG9kOkNvdmVyYWdlUmVwb3J0I2N0b3IoMSk6MjM
scope.5.kind=method
scope.5.startLine=23
scope.5.endLine=25
scope.5.semanticHash=6a5a59a60cc72cd041b044dbc5a0119df18f1c08c64411247ac335231497b6a5
scope.6.id=bWV0aG9kOkNvdmVyYWdlUmVwb3J0I2N0b3IoMik6Mjc
scope.6.kind=method
scope.6.startLine=27
scope.6.endLine=30
scope.6.semanticHash=20777abf11d1446bd2ea870682d12bd8dbc69e6a97c9d1fdb56e1391b3fcaf2a
*/

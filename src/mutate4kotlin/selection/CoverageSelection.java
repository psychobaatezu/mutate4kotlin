package mutate4kotlin.selection;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.*;

import java.util.List;

public record CoverageSelection(List<MutationSite> covered, List<MutationSite> uncovered) {
}

/* mutate4kotlin-manifest
version=1
moduleHash=bfa68e917e4357fc0969cd341346ec7846532fd0c9cc192689fe063cc076476a
scope.0.id=Y2xhc3M6Q292ZXJhZ2VTZWxlY3Rpb24jQ292ZXJhZ2VTZWxlY3Rpb246MTI
scope.0.kind=class
scope.0.startLine=12
scope.0.endLine=13
scope.0.semanticHash=81c68d73512defd6dbfa0b534c854afaffa4e851baf6f143e40ac5be64a9a59d
scope.1.id=ZmllbGQ6Q292ZXJhZ2VTZWxlY3Rpb24jY292ZXJlZDoxMg
scope.1.kind=field
scope.1.startLine=12
scope.1.endLine=12
scope.1.semanticHash=1ba30c841a8d27c4fa2069a50194d49b2a874213f60a521e96b20f85e76a8b91
scope.2.id=ZmllbGQ6Q292ZXJhZ2VTZWxlY3Rpb24jdW5jb3ZlcmVkOjEy
scope.2.kind=field
scope.2.startLine=12
scope.2.endLine=12
scope.2.semanticHash=7dd9888fa1be867590905516bd26210d3cc14c762db8e7367dadd2c067373983
scope.3.id=bWV0aG9kOkNvdmVyYWdlU2VsZWN0aW9uI2N0b3IoMik6MTI
scope.3.kind=method
scope.3.startLine=1
scope.3.endLine=13
scope.3.semanticHash=ecee709e0d648d9cbafb3519a9e113eb8504f2179d1fe151710eefb572574298
*/

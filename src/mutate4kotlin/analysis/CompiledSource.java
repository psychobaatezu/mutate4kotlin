package mutate4kotlin.analysis;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.*;
import mutate4kotlin.manifest.*;

import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.util.Trees;

import java.util.List;

record CompiledSource(List<CompilationUnitTree> units, Trees trees) {
}

/* mutate4kotlin-manifest
version=1
moduleHash=904693fe7a95a7e9e03fd47e8c4e06cf7195fc84b7f9cba7b48b563d2f1fbf19
scope.0.id=Y2xhc3M6Q29tcGlsZWRTb3VyY2UjQ29tcGlsZWRTb3VyY2U6MTY
scope.0.kind=class
scope.0.startLine=16
scope.0.endLine=17
scope.0.semanticHash=4a664fd4c78cd3f1ec06fb97bde5f4515edf894734af915aa352cbf3e08aa2b6
scope.1.id=ZmllbGQ6Q29tcGlsZWRTb3VyY2UjdHJlZXM6MTY
scope.1.kind=field
scope.1.startLine=16
scope.1.endLine=16
scope.1.semanticHash=9ac453f0f04ab2a20a41554f878679e248af7a2f4aa033e6ba8460dc7f752a99
scope.2.id=ZmllbGQ6Q29tcGlsZWRTb3VyY2UjdW5pdHM6MTY
scope.2.kind=field
scope.2.startLine=16
scope.2.endLine=16
scope.2.semanticHash=f96398ef4cb3b2a614cb095026672bc6594e5dd6714fa47e8f18b73a5e5c7175
scope.3.id=bWV0aG9kOkNvbXBpbGVkU291cmNlI2N0b3IoMik6MTY
scope.3.kind=method
scope.3.startLine=1
scope.3.endLine=17
scope.3.semanticHash=bee0711e055ab52df9ab75b9b31a5d0d008539c98bd2670624e588e40803bcbc
*/

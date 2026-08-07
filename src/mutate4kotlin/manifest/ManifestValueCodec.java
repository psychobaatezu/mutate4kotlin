package mutate4kotlin.manifest;

import mutate4kotlin.project.*;
import mutate4kotlin.report.*;

import mutate4kotlin.model.*;

import mutate4kotlin.*;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

final class ManifestValueCodec {

    private ManifestValueCodec() {
    }

    static String encode(String value) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(value.getBytes(StandardCharsets.UTF_8));
    }

    static String decode(String value) {
        return new String(Base64.getUrlDecoder().decode(value), StandardCharsets.UTF_8);
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=87dc4b507bf0d6775dda6e8a258bad0ad99fa1a2f10b9ad839f89a7537c7c2f8
scope.0.id=Y2xhc3M6TWFuaWZlc3RWYWx1ZUNvZGVjI01hbmlmZXN0VmFsdWVDb2RlYzoxMw
scope.0.kind=class
scope.0.startLine=13
scope.0.endLine=25
scope.0.semanticHash=00537745b43d2da156ab5dc29c4521ec76c6aab56b0365127668c46c1512e445
scope.1.id=bWV0aG9kOk1hbmlmZXN0VmFsdWVDb2RlYyNjdG9yKDApOjE1
scope.1.kind=method
scope.1.startLine=15
scope.1.endLine=16
scope.1.semanticHash=1cd022e779ecfb03073acf14a67dff57e96f3b8d608871a5081021df71230f46
scope.2.id=bWV0aG9kOk1hbmlmZXN0VmFsdWVDb2RlYyNkZWNvZGUoMSk6MjI
scope.2.kind=method
scope.2.startLine=22
scope.2.endLine=24
scope.2.semanticHash=97255e54358f9104ac29459e6bcb4a97267481e4a48acfe9203317c7b24e7900
scope.3.id=bWV0aG9kOk1hbmlmZXN0VmFsdWVDb2RlYyNlbmNvZGUoMSk6MTg
scope.3.kind=method
scope.3.startLine=18
scope.3.endLine=20
scope.3.semanticHash=51425ac14868211c47a0d053d73e0a5ff274da87048ee4fcf90f67477856621e
*/

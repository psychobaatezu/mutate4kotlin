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

import java.io.PrintStream;
import java.nio.file.Path;
import java.util.function.IntConsumer;

public final class Main {

    private Main() {
    }

    public static void main(String[] args) throws Exception {
        int exit = run(args, Path.of(".").toAbsolutePath().normalize(), System.out, System.err);
        exitIfNeeded(exit, System::exit);
    }

    public static int run(String[] args, Path projectRoot, PrintStream out, PrintStream err) throws Exception {
        return run(args, projectRoot, out, err, new ProcessTestCommandExecutor());
    }

    public static int run(String[] args,
                          Path projectRoot,
                          PrintStream out,
                          PrintStream err,
                          TestCommandExecutor executor) throws Exception {
        return new CliApplication(projectRoot, out, err, executor).execute(args);
    }

    public static String usage() {
        return UsageText.text();
    }

    public static void exitIfNeeded(int exit, IntConsumer exiter) {
        if (exit != 0) {
            exiter.accept(exit);
        }
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=d88c55e08c5cf5102d7c8568505b0fc23e67f3f8850e176595fdec592f75e380
scope.0.id=Y2xhc3M6TWFpbiNNYWluOjI3
scope.0.kind=class
scope.0.startLine=27
scope.0.endLine=58
scope.0.semanticHash=8999fbaf437784649c22d17fb08aa11ac38ce123be1d5730a2b959331d2f32bd
scope.1.id=bWV0aG9kOk1haW4jY3RvcigwKToyOQ
scope.1.kind=method
scope.1.startLine=29
scope.1.endLine=30
scope.1.semanticHash=2a7894b82bf05c8917e82420e502ab2cc96fef2366eeef066911514202ec3bd1
scope.2.id=bWV0aG9kOk1haW4jZXhpdElmTmVlZGVkKDIpOjUz
scope.2.kind=method
scope.2.startLine=53
scope.2.endLine=57
scope.2.semanticHash=c1357c50609b91a3f0d8be9fd25ef1151af31ddebc7f09d7f8068e86746eace2
scope.3.id=bWV0aG9kOk1haW4jbWFpbigxKTozMg
scope.3.kind=method
scope.3.startLine=32
scope.3.endLine=35
scope.3.semanticHash=cd648688400cd5285495030b3a9f8bbd556cba11d92a5489b6bc89af08225c37
scope.4.id=bWV0aG9kOk1haW4jcnVuKDQpOjM3
scope.4.kind=method
scope.4.startLine=37
scope.4.endLine=39
scope.4.semanticHash=3941de7180f99c576b359edafc19a8d0acc2a5547b7eeec5d9cdcc5f2f1226f4
scope.5.id=bWV0aG9kOk1haW4jcnVuKDUpOjQx
scope.5.kind=method
scope.5.startLine=41
scope.5.endLine=47
scope.5.semanticHash=0b8b7a39043d720b345b0f29cc810943c5724668a40af958c678c9394832f710
scope.6.id=bWV0aG9kOk1haW4jdXNhZ2UoMCk6NDk
scope.6.kind=method
scope.6.startLine=49
scope.6.endLine=51
scope.6.semanticHash=53ea21662cb174fdefe781402bbea538cf79867496de9c8f2542fab96c63e73e
*/

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

import java.util.function.Consumer;

final class CliArgumentSwitch {

    private CliArgumentSwitch() {
    }

    static int parse(String[] args, int index, CliArgumentParseState state, CliArgumentValidators validators) {
        String arg = args[index];
        Integer parsedIndex = parseModeFlag(arg, index, state);
        if (parsedIndex != null) {
            return parsedIndex;
        }
        parsedIndex = parseSelectionFlag(arg, index, state);
        if (parsedIndex != null) {
            return parsedIndex;
        }
        parsedIndex = parseValueFlag(args, index, state, validators, arg);
        if (parsedIndex != null) {
            return parsedIndex;
        }
        return addFileArgument(arg, index, state);
    }

    private static Integer parseModeFlag(String arg, int index, CliArgumentParseState state) {
        return switch (arg) {
            case "--help" -> set(index, () -> state.help = true);
            case "--verbose" -> set(index, () -> state.verbose = true);
            default -> null;
        };
    }

    private static Integer parseSelectionFlag(String arg, int index, CliArgumentParseState state) {
        return switch (arg) {
            case "--scan" -> set(index, () -> state.scan = true);
            case "--update-manifest" -> set(index, () -> state.updateManifest = true);
            case "--reuse-coverage" -> set(index, () -> state.reuseCoverage = true);
            case "--since-last-run" -> set(index, () -> state.sinceLastRun = true);
            case "--mutate-all" -> set(index, () -> state.mutateAll = true);
            default -> null;
        };
    }

    private static Integer parseValueFlag(String[] args,
                                          int index,
                                          CliArgumentParseState state,
                                          CliArgumentValidators validators,
                                          String arg) {
        return switch (arg) {
            case "--lines" -> parseFlagValue(args, index, "--lines",
                    value -> state.lines = validators.parseLines(value), validators);
            case "--timeout-factor" -> parseFlagValue(args, index, "--timeout-factor",
                    value -> state.timeoutFactor = validators.parsePositiveInt(value, "--timeout-factor"), validators);
            case "--mutation-warning" -> parseFlagValue(args, index, "--mutation-warning",
                    value -> state.mutationWarning = validators.parsePositiveInt(value, "--mutation-warning"), validators);
            case "--max-workers" -> parseFlagValue(args, index, "--max-workers",
                    value -> state.maxWorkers = validators.parsePositiveInt(value, "--max-workers"), validators);
            case "--test-command" -> parseFlagValue(args, index, "--test-command",
                    value -> setTestCommand(state, value), validators);
            default -> null;
        };
    }

    private static int parseFlagValue(String[] args, int index, String flag, Consumer<String> consumer,
                                      CliArgumentValidators validators) {
        int valueIndex = index + 1;
        validators.ensureHasValue(args, valueIndex, flag);
        consumer.accept(args[valueIndex]);
        return valueIndex;
    }

    private static int addFileArgument(String arg, int index, CliArgumentParseState state) {
        if (arg.startsWith("--")) {
            throw new IllegalArgumentException("Unknown option: " + arg);
        }
        state.values.add(arg);
        return index;
    }

    private static int set(int index, Runnable action) {
        action.run();
        return index;
    }

    private static void setTestCommand(CliArgumentParseState state, String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("--test-command must not be blank");
        }
        state.testCommand = value;
    }
}

/* mutate4kotlin-manifest
version=1
moduleHash=058794755ac82936270cb7ee500356a016f4234c77db9584c8cf559f5081e499
scope.0.id=Y2xhc3M6Q2xpQXJndW1lbnRTd2l0Y2gjQ2xpQXJndW1lbnRTd2l0Y2g6MjU
scope.0.kind=class
scope.0.startLine=25
scope.0.endLine=113
scope.0.semanticHash=9ac58c845204a791f1d7bf42f5c29a26336c06ff6d50cf5b16aa24283b3b03b6
scope.1.id=bWV0aG9kOkNsaUFyZ3VtZW50U3dpdGNoI2FkZEZpbGVBcmd1bWVudCgzKTo5NA
scope.1.kind=method
scope.1.startLine=94
scope.1.endLine=100
scope.1.semanticHash=e79ce1b21e269131df4c9168472b66e9b3defd525be0b9b91fa339d9db4c6c90
scope.2.id=bWV0aG9kOkNsaUFyZ3VtZW50U3dpdGNoI2N0b3IoMCk6Mjc
scope.2.kind=method
scope.2.startLine=27
scope.2.endLine=28
scope.2.semanticHash=e19a94c888b1e756b5303a85f722863e6573d6277effd5693354a7996df33155
scope.3.id=bWV0aG9kOkNsaUFyZ3VtZW50U3dpdGNoI3BhcnNlKDQpOjMw
scope.3.kind=method
scope.3.startLine=30
scope.3.endLine=45
scope.3.semanticHash=c7c16aac86ce987b141609695c59add0bf8641f5b2df7848597b430cb58ef9e1
scope.4.id=bWV0aG9kOkNsaUFyZ3VtZW50U3dpdGNoI3BhcnNlRmxhZ1ZhbHVlKDUpOjg2
scope.4.kind=method
scope.4.startLine=86
scope.4.endLine=92
scope.4.semanticHash=771578bf467e1e7729242c1810a84d9b46fc6fd13b216428c6ad0ad52ece0181
scope.5.id=bWV0aG9kOkNsaUFyZ3VtZW50U3dpdGNoI3BhcnNlTW9kZUZsYWcoMyk6NDc
scope.5.kind=method
scope.5.startLine=47
scope.5.endLine=53
scope.5.semanticHash=34d1a9a33454c159c17bfc42fe75a50b18f34cd01d16e50bb4d194e9b2e4b3a0
scope.6.id=bWV0aG9kOkNsaUFyZ3VtZW50U3dpdGNoI3BhcnNlU2VsZWN0aW9uRmxhZygzKTo1NQ
scope.6.kind=method
scope.6.startLine=55
scope.6.endLine=64
scope.6.semanticHash=b92dc4a5f62ed77308e4577d9012cd2c7518dfb1c32327b8b88f804dc0a82bce
scope.7.id=bWV0aG9kOkNsaUFyZ3VtZW50U3dpdGNoI3BhcnNlVmFsdWVGbGFnKDUpOjY2
scope.7.kind=method
scope.7.startLine=66
scope.7.endLine=84
scope.7.semanticHash=4e081f989582eb2c0769a76e01ec1cdc6e2ef88a635fa6804724672573a86d3f
scope.8.id=bWV0aG9kOkNsaUFyZ3VtZW50U3dpdGNoI3NldCgyKToxMDI
scope.8.kind=method
scope.8.startLine=102
scope.8.endLine=105
scope.8.semanticHash=d70bd0cb67b6cd33ec25c3c7e3f6a954965cce5f69e7aa2ceb83c159e151bde4
scope.9.id=bWV0aG9kOkNsaUFyZ3VtZW50U3dpdGNoI3NldFRlc3RDb21tYW5kKDIpOjEwNw
scope.9.kind=method
scope.9.startLine=107
scope.9.endLine=112
scope.9.semanticHash=c1b09b560d2cee69a1d8f2c8239c8659b6f4e50b628e77be881c996f4a2f5cb8
*/

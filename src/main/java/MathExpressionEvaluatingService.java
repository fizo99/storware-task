import java.util.List;
import java.util.function.Function;

public class MathExpressionEvaluatingService {

    public long getResult(ParsedFileData parsedFileData) {
        return evaluateCommands(parsedFileData.getCommands(), parsedFileData.getApplyCommandValue());
    }

    private Long evaluateCommands(List<Command> commands, Long applyCommandValue) {
        return commands.stream()
                .map(Command::getFunction)
                .reduce((startingValue) -> startingValue, Function::andThen)
                .apply(applyCommandValue);
    }
}

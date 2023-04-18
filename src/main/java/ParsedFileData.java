import java.util.List;

public class ParsedFileData {
    private final List<Command> commands;
    private final Long applyCommandValue;

    public ParsedFileData(List<Command> commands, Long applyCommandValue) {
        this.commands = commands;
        this.applyCommandValue = applyCommandValue;
    }

    public List<Command> getCommands() {
        return this.commands;
    }

    public Long getApplyCommandValue() {
        return applyCommandValue;
    }
}

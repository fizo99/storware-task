import java.util.List;
import java.util.stream.Collectors;

public class FileParser {
    public ParsedFileData parse(List<String> fileContent) {
        validateFileContent(fileContent);

        var applyCommandValue = extractApplyCommandValue(fileContent);
        var commands = extractCommands(fileContent);

        return new ParsedFileData(commands, applyCommandValue);
    }

    private void validateFileContent(List<String> fileContent) {
        if (fileContent.size() == 0) {
            throw new FileParsingException("File is empty.");
        }

        var lastLineCommand = getCommand(
                splitLine(fileContent.get(fileContent.size() - 1))
        );

        if (!"apply".equals(lastLineCommand)) {
            throw new FileParsingException("Unable to find 'apply' command on last line in file.");
        }
    }

    private Long extractApplyCommandValue(List<String> lines) {
        var applyCommandLine = lines.get(lines.size() - 1);
        var splittedLine = splitLine(applyCommandLine);
        return convertToLong(getValue(splittedLine));
    }

    private List<Command> extractCommands(List<String> lines) {
        return lines.stream()
                .limit(lines.size() - 1)
                .map(this::splitLine)
                .map(this::toCommand)
                .collect(Collectors.toList());
    }

    private Command toCommand(String[] splitted) {
        var commandType = convertToCommandType(getCommand(splitted));
        var value = convertToLong(getValue(splitted));
        return new Command(commandType, value);
    }

    private CommandType convertToCommandType(String command) {
        return CommandType.getInstance(command)
                .orElseThrow(
                        () -> new FileParsingException(String.format("Command '%s' not found.", command))
                );
    }

    private Long convertToLong(String value) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException numberFormatException) {
            throw new FileParsingException(
                    String.format("Unable to convert '%s' to numeric value.", value)
            );
        }
    }

    private String[] splitLine(String line) {
        var splitted = line.split(" ");
        if (splitted.length != 2) {
            throw new FileParsingException(
                    String.format("Unable to parse line '%s'. Expected command and value separated by space.", line)
            );
        }
        return splitted;
    }

    private String getCommand(String[] splitted) {
        return splitted[0];
    }

    private String getValue(String[] splitted) {
        return splitted[1];
    }

}

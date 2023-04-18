import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

public class FileParserTest {
    private final FileParser fileParser = new FileParser();

    static Stream<Arguments> generateInvalidFileContents() {
        return Stream.of(
                Arguments.of(List.of()),
                Arguments.of(List.of(
                        "add 10"
                )),
                Arguments.of(List.of(
                        "invalidCommand 10",
                        "apply 10"
                )),
                Arguments.of(List.of(
                        "add invalidValue",
                        "apply 10"
                )),
                Arguments.of(List.of(
                        "too much values",
                        "apply 10"
                ))
        );
    }

    @Test
    public void shouldCorrectlyParseData() {
        var fileContent = List.of(
                "add 2",
                "apply 10"
        );

        var parsedData = fileParser.parse(fileContent);

        Assertions.assertAll(
                () -> Assertions.assertEquals(10, parsedData.getApplyCommandValue()),
                () -> Assertions.assertEquals(1, parsedData.getCommands().size()),
                () -> Assertions.assertEquals(new Command(CommandType.ADD, 2), parsedData.getCommands().get(0))
        );
    }

    @ParameterizedTest
    @MethodSource("generateInvalidFileContents")
    public void shouldThrowFileParsingExceptionWhenFileContentIsInvalid(List<String> fileContent) {
        Assertions.assertThrows(FileParsingException.class, () -> {
            fileParser.parse(fileContent);
        });
    }

}

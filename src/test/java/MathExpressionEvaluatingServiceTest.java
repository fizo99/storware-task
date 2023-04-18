import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

public class MathExpressionEvaluatingServiceTest {
    private final MathExpressionEvaluatingService mathExpressionEvaluatingService
            = new MathExpressionEvaluatingService();

    static Stream<Arguments> generateInvalidFileContents() {
        return Stream.of(
                Arguments.of(new ParsedFileData(
                        List.of(
                                new Command(CommandType.ADD, 2L),
                                new Command(CommandType.MULTIPLY, 3L)
                        ), 10L
                ), 36L),
                Arguments.of(new ParsedFileData(
                        List.of(
                                new Command(CommandType.MULTIPLY, 3L),
                                new Command(CommandType.ADD, 2L)
                        ), 10L
                ), 32L),
                Arguments.of(new ParsedFileData(
                        List.of(
                                new Command(CommandType.MULTIPLY, 3L),
                                new Command(CommandType.SUBTRACT, 10L),
                                new Command(CommandType.ADD, 8L),
                                new Command(CommandType.DIVIDE, 2L)
                        ), 10L
                ), 14L)
        );
    }

    @ParameterizedTest
    @MethodSource("generateInvalidFileContents")
    public void shouldThrowFileParsingExceptionWhenFileContentIsInvalid(ParsedFileData parsedFileData, Long expectedResult) {
        var result = mathExpressionEvaluatingService.getResult(parsedFileData);
        Assertions.assertEquals(expectedResult, result);
    }

}

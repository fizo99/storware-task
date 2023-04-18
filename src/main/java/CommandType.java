import java.util.Arrays;
import java.util.Optional;
import java.util.function.BiFunction;

public enum CommandType {
    ADD(
            "add",
            (a, b) -> a + b
    ),
    SUBTRACT(
            "subtract",
            (a, b) -> a - b
    ),
    MULTIPLY(
            "multiply",
            (a, b) -> a * b
    ),
    DIVIDE(
            "divide",
            (a, b) -> a / b
    );
    private final String commandName;
    private final BiFunction<Long, Long, Long> arithmeticFunction;

    CommandType(String commandName, BiFunction<Long, Long, Long> arithmeticFunction) {
        this.commandName = commandName;
        this.arithmeticFunction = arithmeticFunction;
    }

    public static Optional<CommandType> getInstance(String command) {
        return Arrays.stream(CommandType.values())
                .filter(enumValue -> enumValue.commandName.equals(command))
                .findFirst();
    }

    public BiFunction<Long, Long, Long> getArithmeticFunction() {
        return arithmeticFunction;
    }
}

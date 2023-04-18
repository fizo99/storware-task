import java.util.Objects;
import java.util.function.Function;

public class Command {
    private final CommandType commandType;
    private final long value;

    public Command(CommandType commandType, long value) {
        this.commandType = commandType;
        this.value = value;
    }

    public Function<Long, Long> getFunction() {
        return (x) -> commandType.getArithmeticFunction().apply(x, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Command command = (Command) o;
        return value == command.value && commandType == command.commandType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(commandType, value);
    }
}

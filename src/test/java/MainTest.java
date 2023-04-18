import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MainTest {

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenProvidedArgumentsQuantityIsNotEqualToOne() {
        var args = new String[]{"/dev/null", "/"};

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Main.main(args);
        });
    }

    @Test
    public void shouldPrintCorrectResult() {
        var outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        var args = new String[]{TestUtils.getTestFilePath().toString()};

        Main.main(args);

        Assertions.assertEquals(String.valueOf(TestUtils.TEST_FILE_RESULT), outputStreamCaptor.toString());
    }
}

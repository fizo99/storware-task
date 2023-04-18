import java.io.File;
import java.nio.file.Path;
import java.util.Objects;

public class TestUtils {
    static final String TEST_FILE_NAME = "testfile";
    static final Long TEST_FILE_RESULT = 3L;

    static Path getTestFilePath() {
        return new File(
                Objects
                        .requireNonNull(TestUtils.class.getResource(TEST_FILE_NAME))
                        .getFile()
        ).toPath();
    }
}

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class IOUtilTest {
    private final IOUtil ioUtil = new IOUtil();

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"invalidPath", "C:\\/anotherInvalidPath", "/dev//anotherInvalidPath"})
    public void shouldThrowFileLoadingExceptionWhenPathIsInvalid(String path) {
        Assertions.assertThrows(FileLoadingException.class, () -> {
            ioUtil.readLines(path);
        });
    }

    @Test
    public void shouldThrowFileLoadingExceptionWhenFileIsAbsent() {
        var path = TestUtils.getTestFilePath().toString().replace(TestUtils.TEST_FILE_NAME, "absentFile");
        Assertions.assertThrows(FileLoadingException.class, () -> {
            ioUtil.readLines(path);
        });
    }

    @Test
    public void shouldReadExistingFileContent() {
        var path = TestUtils.getTestFilePath().toString();

        var content = ioUtil.readLines(path);

        Assertions.assertEquals(5, content.size());
    }

}

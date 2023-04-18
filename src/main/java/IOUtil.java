import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class IOUtil {
    public List<String> readLines(String filePath) {
        var path = convertToPathObject(filePath);
        try (var linesStream = Files.lines(path)) {
            return linesStream.collect(Collectors.toList());
        } catch (NoSuchFileException e) {
            throw new FileLoadingException(String.format("Unable to read data from file '%s. File not found.", filePath));
        } catch (IOException e) {
            throw new FileLoadingException("Unable to read data from file.", e);
        }
    }

    private Path convertToPathObject(String filePath) {
        if (filePath == null) {
            throw new FileLoadingException("Invalid file path. File path is null.");
        }

        try {
            return Paths.get(filePath);
        } catch (InvalidPathException invalidPathException) {
            throw new FileLoadingException(
                    String.format("Invalid file path '%s'. %s", invalidPathException.getInput(), invalidPathException.getReason())
            );
        }
    }
}

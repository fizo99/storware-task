public class Main {
    private static final IOUtil ioUtil = new IOUtil();
    private static final FileParser fileParser = new FileParser();
    private static final MathExpressionEvaluatingService mathExpressionEvaluatingService
            = new MathExpressionEvaluatingService();

    public static void main(String[] args) {
        var filePath = extractFilePath(args);

        var fileContent = ioUtil.readLines(filePath);
        var parsedFileData = fileParser.parse(fileContent);
        var result = mathExpressionEvaluatingService.getResult(parsedFileData);

        System.out.print(result);
    }

    private static String extractFilePath(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException(String.format(
                    "Expected 1 argument. Got %s.", args.length
            ));
        }
        return args[0];
    }

}

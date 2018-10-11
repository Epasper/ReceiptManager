package bootcamp;

public class RequestBuilderFactory {

    public static RequestBuilderInterface getBuilder (String fileExtension) {
        switch (fileExtension.toLowerCase()) {
            case "xml": return new RequestBuilderXml();
            case "csv": return new RequestBuilderCsv();
            default: throw new RuntimeException("Unsupported File Extension");
        }
    }

}

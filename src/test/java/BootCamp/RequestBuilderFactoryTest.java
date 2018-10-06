package BootCamp;

import org.junit.Test;

import static org.junit.Assert.*;

public class RequestBuilderFactoryTest {

    @Test
    public void FactorySupportsKnownExtensions() {
        assertTrue((RequestBuilderFactory.getBuilder("xml") instanceof RequestBuilderXml));
        assertTrue((RequestBuilderFactory.getBuilder("csv") instanceof RequestBuilderCsv));
    }

    @Test
    public void FactorySupportsKnownExtensionsMixedCase() {
        assertTrue((RequestBuilderFactory.getBuilder("XML") instanceof RequestBuilderXml));
        assertTrue((RequestBuilderFactory.getBuilder("xMl") instanceof RequestBuilderXml));
        assertTrue((RequestBuilderFactory.getBuilder("XmL") instanceof RequestBuilderXml));


        assertTrue((RequestBuilderFactory.getBuilder("CSV") instanceof RequestBuilderCsv));
        assertTrue((RequestBuilderFactory.getBuilder("cSv") instanceof RequestBuilderCsv));
        assertTrue((RequestBuilderFactory.getBuilder("CsV") instanceof RequestBuilderCsv));
    }

    @Test(expected = RuntimeException.class)
    public void FactoryShouldThrowUnsupportedFiletypeOnTxt()
    {
        RequestBuilderFactory.getBuilder("txt");
    }

    @Test(expected = RuntimeException.class)
    public void FactoryShouldThrowUnsupportedFiletypeOnUnicode()
    {
        RequestBuilderFactory.getBuilder("대박");
    }

    @Test(expected = RuntimeException.class)
    public void FactoryShouldThrowExceptionOnNull()
    {
        RequestBuilderFactory.getBuilder(null);
    }

    @Test(expected = RuntimeException.class)
    public void FactoryShouldThrowUnsupportedFiletypeOnEmptyString()
    {
        RequestBuilderFactory.getBuilder("");
    }

    @Test(expected = RuntimeException.class)
    public void FactoryShouldThrowUnsupportedFiletypeOnDot()
    {
        RequestBuilderFactory.getBuilder(".");
    }
}
package moe.yo3explorer.peparser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PeParserTest {
    private static final String DEMO_BINARY_NAME = "demoBinary/a.exe";
    @Before
    public void checkDemoBinaryExists()
    {
        File exeFile = new File(DEMO_BINARY_NAME);
        if (!exeFile.isFile())
            org.junit.Assume.assumeTrue("The demo binary was not yet compiled!",exeFile.isFile());
    }

    @Test
    public void testPeParsing() throws IOException {
        File file = new File(DEMO_BINARY_NAME);
        byte[] buffer = new byte[(int)file.length()];
        FileInputStream fis = new FileInputStream(file);
        fis.read(buffer,0,buffer.length);

        PeParser peParser = new PeParser(buffer);
        System.out.println("Machine Type: " + peParser.getPeHeader().getMachine());

        Assert.assertNotNull(peParser);
    }
}

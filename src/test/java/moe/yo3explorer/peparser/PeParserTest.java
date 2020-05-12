package moe.yo3explorer.peparser;

import moe.yo3explorer.peparser.rsrcModel.ImageResourceRepresentation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.List;

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

        List<ImageResourceRepresentation> resources = peParser.getResources();
        for (ImageResourceRepresentation resource : resources) {
            System.out.println(resource.toString());
        }

        Assert.assertNotNull(peParser);
    }

    private byte[] patchExfont(byte[] exfont)
    {
        byte rawData[] =
                {
                        (byte)0x42, (byte)0x4D, (byte)0x76, (byte)0x21, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x36, (byte)0x04, (byte)0x00, (byte)0x00, (byte)0x28, (byte)0x00,
                        (byte)0x00, (byte)0x00, (byte)0x9C, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x30, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x01, (byte)0x00, (byte)0x08, (byte)0x00, (byte)0x00, (byte)0x00,
                        (byte)0x00, (byte)0x00,
                } ;

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos.write(rawData);
            baos.write(exfont,20,exfont.length - 20);
            baos.flush();
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

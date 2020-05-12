package moe.yo3explorer.peparser.peModel;

import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class PeSection
{
    public PeSection(@NotNull ByteBuffer byteBuffer)
    {
        byte[] nameBuffer = new byte[8];
        byteBuffer.get(nameBuffer);
        for (int i = 0; i < nameBuffer.length; i++)
            if (nameBuffer[i] == 0)
                nameBuffer[i] = 0x20;

        Name = new String(nameBuffer, StandardCharsets.US_ASCII).trim();
        VirtualSize = byteBuffer.getInt();
        VirtualAddress = byteBuffer.getInt();
        SizeOfRawData = byteBuffer.getInt();
        PointerToRawData = byteBuffer.getInt();
        PointerToRealocations = byteBuffer.getInt();
        PointerToLinenumbers = byteBuffer.getInt();
        NumberOfRealocations = byteBuffer.getShort();
        NumberOfLinenumbers = byteBuffer.getShort();
        Characteristics = byteBuffer.getInt();
    }

    private String Name;
    private int VirtualSize;
    private int VirtualAddress;
    private int SizeOfRawData;
    private int PointerToRawData;
    private int PointerToRealocations;
    private int PointerToLinenumbers;
    private short NumberOfRealocations;
    private short NumberOfLinenumbers;
    private int Characteristics;

    public String getName() {
        return Name;
    }

    public int getVirtualSize() {
        return VirtualSize;
    }

    /**
     * RVA
     * @return RVA
     */
    public int getVirtualAddress() {
        return VirtualAddress;
    }

    public int getSizeOfRawData() {
        return SizeOfRawData;
    }

    public int getPointerToRawData() {
        return PointerToRawData;
    }

    public int getPointerToRealocations() {
        return PointerToRealocations;
    }

    public int getPointerToLinenumbers() {
        return PointerToLinenumbers;
    }

    public short getNumberOfRealocations() {
        return NumberOfRealocations;
    }

    public short getNumberOfLinenumbers() {
        return NumberOfLinenumbers;
    }

    public int getCharacteristics() {
        return Characteristics;
    }
}

package moe.yo3explorer.peparser.peModel;

import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class PeOptionalHeader
{
    public PeOptionalHeader(@NotNull ByteBuffer buffer)
    {
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        Magic = buffer.getShort();
        MajorLinkerVersion = buffer.get();
        MinorLinkerVersion = buffer.get();
        SizeOfCode = buffer.getInt();
        SizeOfInitializedData = buffer.getInt();
        SizeOfUninitializedData = buffer.getInt();
        AddressOfEntryPoint = buffer.getInt();
        BaseOfCode = buffer.getInt();
        BaseOfData = buffer.getInt();
        ImageBase = buffer.getInt();
        SectionAlignment = buffer.getInt();
        FileAlignment = buffer.getInt();
        MajorOperatingSystemVersion = buffer.getShort();
        MinorOperatingSystemVersion = buffer.getShort();
        MajorImageVersion = buffer.getShort();
        MinorImageVersion = buffer.getShort();
        MajorSubsystemVersion = buffer.getShort();
        MinorSubsystemVersion = buffer.getShort();
        Win32VersionValue = buffer.getInt();
        SizeOfImage = buffer.getInt();
        SizeOfHeaders = buffer.getInt();
        CheckSum = buffer.getInt();
        Subsystem = buffer.getShort();
        DllCharacteristics = buffer.getShort();
        SizeOfStackReserve = buffer.getInt();
        SizeOfStackCommit = buffer.getInt();
        SizeOfHeapReserve = buffer.getInt();
        SizeOfHeapCommit = buffer.getInt();
        LoaderFlags = buffer.getInt();
        NumberOfRvaAndSizes = buffer.getInt();
        HeaderSize = buffer.position();
    }
    //<editor-fold>A
    private short Magic; // 0x010b - PE32, 0x020b - PE32+ (64 bit)
    private byte  MajorLinkerVersion;
    private byte  MinorLinkerVersion;
    private int SizeOfCode;
    private int SizeOfInitializedData;
    private int SizeOfUninitializedData;
    private int AddressOfEntryPoint;
    private int BaseOfCode;
    private int BaseOfData;
    private int ImageBase;
    private int SectionAlignment;
    private int FileAlignment;
    //</editor-fold>B
    //<editor-fold>B
    private short MajorOperatingSystemVersion;
    private short MinorOperatingSystemVersion;
    private short MajorImageVersion;
    private short MinorImageVersion;
    private short MajorSubsystemVersion;
    private short MinorSubsystemVersion;
    private int Win32VersionValue;
    private int SizeOfImage;
    private int SizeOfHeaders;
    private int CheckSum;
    private short Subsystem;
    //</editor-fold>B
    private short DllCharacteristics;
    private int SizeOfStackReserve;
    private int SizeOfStackCommit;
    private int SizeOfHeapReserve;
    private int SizeOfHeapCommit;
    private int LoaderFlags;
    private int NumberOfRvaAndSizes;
    private int HeaderSize;

    public byte getMajorLinkerVersion() {
        return MajorLinkerVersion;
    }

    public byte getMinorLinkerVersion() {
        return MinorLinkerVersion;
    }

    public int getSizeOfCode() {
        return SizeOfCode;
    }

    public int getSizeOfInitializedData() {
        return SizeOfInitializedData;
    }

    public int getSizeOfUninitializedData() {
        return SizeOfUninitializedData;
    }

    public int getAddressOfEntryPoint() {
        return AddressOfEntryPoint;
    }

    public int getBaseOfCode() {
        return BaseOfCode;
    }

    public int getBaseOfData() {
        return BaseOfData;
    }

    public int getImageBase() {
        return ImageBase;
    }

    public int getSectionAlignment() {
        return SectionAlignment;
    }

    public int getFileAlignment() {
        return FileAlignment;
    }

    public short getMajorOperatingSystemVersion() {
        return MajorOperatingSystemVersion;
    }

    public short getMinorOperatingSystemVersion() {
        return MinorOperatingSystemVersion;
    }

    public short getMajorImageVersion() {
        return MajorImageVersion;
    }

    public short getMinorImageVersion() {
        return MinorImageVersion;
    }

    public short getMajorSubsystemVersion() {
        return MajorSubsystemVersion;
    }

    public short getMinorSubsystemVersion() {
        return MinorSubsystemVersion;
    }

    public int getWin32VersionValue() {
        return Win32VersionValue;
    }

    public int getSizeOfImage() {
        return SizeOfImage;
    }

    public int getSizeOfHeaders() {
        return SizeOfHeaders;
    }

    public int getCheckSum() {
        return CheckSum;
    }

    public short getSubsystem() {
        return Subsystem;
    }

    public short getDllCharacteristics() {
        return DllCharacteristics;
    }

    public int getSizeOfStackReserve() {
        return SizeOfStackReserve;
    }

    public int getSizeOfStackCommit() {
        return SizeOfStackCommit;
    }

    public int getSizeOfHeapReserve() {
        return SizeOfHeapReserve;
    }

    public int getSizeOfHeapCommit() {
        return SizeOfHeapCommit;
    }

    public int getLoaderFlags() {
        return LoaderFlags;
    }

    public int getNumberOfRvaAndSizes() {
        return NumberOfRvaAndSizes;
    }

    public int getHeaderSize() {
        return HeaderSize;
    }
}

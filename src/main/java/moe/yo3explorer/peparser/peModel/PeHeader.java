package moe.yo3explorer.peparser.peModel;

import moe.yo3explorer.peparser.PeParserException;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Optional;

public class PeHeader
{
    public static final int MAGIC_NUMBER = 17744;
    public PeHeader(@NotNull ByteBuffer byteBuffer)
    {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        mMagic = byteBuffer.getInt();
        if (mMagic != MAGIC_NUMBER)
            throw new PeParserException("invalid PE file");

        mMachine = byteBuffer.getShort();
        mNumberOfSections = byteBuffer.getShort();
        mTimeDateStamp = byteBuffer.getInt();
        mPointerToSymbolTable = byteBuffer.getInt();
        mNumberOfSymbols = byteBuffer.getInt();
        mSizeOfOptionalHeader = byteBuffer.getShort();
        mCharacteristics = byteBuffer.getShort();
    }

    private int mMagic; // PE\0\0 or
    private short mMachine;
    private short mNumberOfSections;
    private int mTimeDateStamp;
    private int mPointerToSymbolTable;
    private int mNumberOfSymbols;
    private short mSizeOfOptionalHeader;
    private short mCharacteristics;

    public MachineType getMachine() {
        MachineType[] values = MachineType.values();
        Optional<MachineType> first = Arrays.stream(values).filter(x -> x.getId() == mMachine).findFirst();
        return first.orElse(MachineType.UNKNOWN);
    }

    public short getNumberOfSections() {
        return mNumberOfSections;
    }

    public int getTimeDateStamp() {
        return mTimeDateStamp;
    }

    public int getPointerToSymbolTable() {
        return mPointerToSymbolTable;
    }

    public int getNumberOfSymbols() {
        return mNumberOfSymbols;
    }

    public short getSizeOfOptionalHeader() {
        return mSizeOfOptionalHeader;
    }

    public short getCharacteristics() {
        return mCharacteristics;
    }
}

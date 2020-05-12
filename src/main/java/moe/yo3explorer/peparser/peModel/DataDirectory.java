package moe.yo3explorer.peparser.peModel;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class DataDirectory
{
    public DataDirectory(ByteBuffer byteBuffer)
    {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        rva = byteBuffer.getInt();
        size = byteBuffer.getInt();
    }

    private int rva, size;

    public int getRva() {
        return rva;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "DataDirectory{" +
                "rva=" + rva +
                ", size=" + size +
                '}';
    }
}

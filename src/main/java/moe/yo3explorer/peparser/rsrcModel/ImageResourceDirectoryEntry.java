package moe.yo3explorer.peparser.rsrcModel;

public class ImageResourceDirectoryEntry
{
    public int NameId;
    public int Data;

    @Override
    public String toString() {
        return "ImageResourceDirectoryEntry{" +
                "NameId=" + NameId +
                ", Data=" + Data +
                '}';
    }

    public boolean getSignBitClear()
    {
            return (NameId >> 31) != -1;
    }

    public int getLower31bitOfNameId()
    {
        return (NameId & 0x7FFFFFFF);
    }

    public boolean getDataMostSignificantBitSet()
    {
        return (Data >> 31) == -1;
    }

    public int getNextDirOffset()
    {
            return (Data & 0x7FFFFFFF);
    }

    public String Name;

}

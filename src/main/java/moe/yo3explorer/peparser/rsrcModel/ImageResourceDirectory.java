package moe.yo3explorer.peparser.rsrcModel;

public class ImageResourceDirectory
{
    public int Characteristics;
    public int TimeDateStamp;
    public short MajorVersion;
    public short MinorVersion;
    public short NumberOfNamedEntries;
    public short NumberOfIdEntries;

    public int getNumberOfEntries()
    {
        return NumberOfIdEntries + NumberOfNamedEntries;
    }
}

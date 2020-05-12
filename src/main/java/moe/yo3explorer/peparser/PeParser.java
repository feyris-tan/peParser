package moe.yo3explorer.peparser;

import moe.yo3explorer.peparser.peModel.*;
import moe.yo3explorer.peparser.rsrcModel.ImageResourceDirectory;
import moe.yo3explorer.peparser.rsrcModel.ImageResourceDataEntry;
import moe.yo3explorer.peparser.rsrcModel.ImageResourceDirectoryEntry;
import moe.yo3explorer.peparser.rsrcModel.ImageResourceRepresentation;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PeParser {
    public PeParser(byte[] buffer)
    {
        this(ByteBuffer.wrap(buffer));
    }

    public PeParser(@NotNull ByteBuffer buffer)
    {
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        short aShort = buffer.getShort();
        buffer.position(0);
        if (aShort == ZbykowskiHeader.MAGIC_NUMBER)
        {
            zbykowskiHeader = new ZbykowskiHeader(buffer);
            buffer.position(zbykowskiHeader.getE_lfanew());
        }

        peHeader = new PeHeader(buffer);
        if (peHeader.getSizeOfOptionalHeader() > 0)
        {
            optionalHeader = new PeOptionalHeader(buffer.slice());
            buffer.position(buffer.position() + optionalHeader.getHeaderSize());
            int headerRemain = peHeader.getSizeOfOptionalHeader() - optionalHeader.getHeaderSize();
            while (headerRemain > 0)
            {
                DataDirectory dataDirectoryCandidate = new DataDirectory(buffer);
                headerRemain -= 8;
                if (dataDirectoryCandidate.getSize() != 0)
                {
                    if (dataDirectories == null)
                        dataDirectories = new ArrayList<>();
                    dataDirectories.add(dataDirectoryCandidate);
                }
            }
        }

        short numberOfSections = peHeader.getNumberOfSections();
        peSections = new PeSection[numberOfSections];
        for (short i = 0; i < numberOfSections; i++)
        {
            peSections[i] = new PeSection(buffer);
        }

        Optional<PeSection> first = Arrays.stream(peSections).filter(x -> x.getName().equals(".rsrc")).findFirst();
        if (first.isPresent())
        {
            PeSection rsrcSection = first.get();
            rsrcRva = rsrcSection.getVirtualAddress();
            rsrcOffset = rsrcSection.getPointerToRawData();
            buffer.position(rsrcOffset);
            readRsrcSection(buffer);
        }
    }

    private void readRsrcSection(@NotNull ByteBuffer buffer)
    {
        ImageResourceDirectory resourceDirectory = new ImageResourceDirectory();
        resourceDirectory.Characteristics = buffer.getInt();
        resourceDirectory.TimeDateStamp = buffer.getInt();
        resourceDirectory.MajorVersion = buffer.getShort();
        resourceDirectory.MinorVersion = buffer.getShort();
        resourceDirectory.NumberOfNamedEntries = buffer.getShort();
        resourceDirectory.NumberOfIdEntries = buffer.getShort();

        ImageResourceDirectoryEntry[] entries = new ImageResourceDirectoryEntry[resourceDirectory.getNumberOfEntries()];
        for (int i = 0; i < entries.length; i++)
        {
            entries[i] = new ImageResourceDirectoryEntry();
            entries[i].NameId = buffer.getInt();
            entries[i].Data = buffer.getInt();

            if (entries[i].getSignBitClear())
            {
                entries[i].Name = Integer.toString(entries[i].NameId);
            }
            else
            {
                entries[i].Name = readUnicodeString(buffer,entries[i].getLower31bitOfNameId());
            }

            if (entries[i].getDataMostSignificantBitSet())
            {
                int currentOffset = buffer.position();
                buffer.position(rsrcOffset + entries[i].getNextDirOffset());
                readRsrcSection(buffer);
                buffer.position(currentOffset);
            }
            else
            {
                int currentOffset = buffer.position();
                buffer.position(rsrcOffset + entries[i].getNextDirOffset());

                ImageResourceDataEntry info = new ImageResourceDataEntry();
                info.Data = rsrcOffset + (buffer.getInt() - rsrcRva);
                info.Size = buffer.getInt();
                info.CodePage = buffer.getInt();
                info.Reserved = buffer.getInt();

                ImageResourceRepresentation resourceRepresentation = new ImageResourceRepresentation();
                resourceRepresentation.setBuffer(buffer,info.Data,info.Size);
                resourceRepresentation.setCodePage(info.Reserved);
                resourceRepresentation.setCategoryName(entries[i].Name);
                if (resources == null)
                    resources = new ArrayList<>();
                resources.add(resourceRepresentation);

                buffer.position(currentOffset);
            }
        }
    }

    @NotNull
    private String readUnicodeString(@NotNull ByteBuffer buffer, int offset)
    {
        int oldOffset = buffer.position();
        buffer.position(offset);

        int len = buffer.getShort() * 2;
        byte[] outBuffer = new byte[len];
        buffer.get(outBuffer);

        String result = new String(outBuffer, StandardCharsets.UTF_8);

        buffer.position(oldOffset);
        return result;
    }

    private ZbykowskiHeader zbykowskiHeader;
    private PeHeader peHeader;
    private PeOptionalHeader optionalHeader;
    private List<DataDirectory> dataDirectories;
    private PeSection[] peSections;
    private int rsrcRva;
    private int rsrcOffset;
    private List<ImageResourceRepresentation> resources;

    public boolean isZbykowskiHeaderPresent()
    {
        return zbykowskiHeader != null;
    }

    public ZbykowskiHeader getZbykowskiHeader() {
        return zbykowskiHeader;
    }

    public PeHeader getPeHeader() {
        return peHeader;
    }

    public boolean isOptionalHeaderPresent()
    {
        return optionalHeader != null;
    }

    public PeOptionalHeader getOptionalHeader() {
        return optionalHeader;
    }

    public List<DataDirectory> getDataDirectories() {
        if (dataDirectories == null)
            return new ArrayList<>();
        return dataDirectories;
    }

    public List<ImageResourceRepresentation> getResources() {
        if (resources == null)
            return new ArrayList<>();
        return resources;
    }

    public PeSection[] getPeSections() {
        return peSections;
    }

    public int getRsrcRva() {
        return rsrcRva;
    }
}

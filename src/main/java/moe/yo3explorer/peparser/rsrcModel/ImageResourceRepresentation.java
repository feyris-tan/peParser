package moe.yo3explorer.peparser.rsrcModel;

import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;

public class ImageResourceRepresentation {
    private byte data[];
    private int codePage;
    private String categoryName;

    public void setBuffer(@NotNull ByteBuffer buffer, int offset, int length) {
        if (data != null)
        {
            throw new IllegalStateException("Buffer already set!");
        }
        data = new byte[length];
        buffer.position(offset);
        buffer.get(data,0,length);
    }

    public void setCodePage(int codePage) {
        this.codePage = codePage;
    }

    public int getCodePage() {
        return codePage;
    }

    public void setCategoryName(String categoryName) {
        categoryName = categoryName.replace("\0","");
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    @Override
    public String toString() {
        return "ImageResourceRepresentation{" +
                "codePage=" + codePage +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }

    public byte[] getData() {
        return data;
    }
}

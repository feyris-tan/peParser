package moe.yo3explorer.peparser.peModel;

import moe.yo3explorer.peparser.PeParserException;

import java.nio.ByteBuffer;

/**
 * The information in this was gathered from
 * http://www.woodmann.com/krobar/other/other98.html
 *
 * This represents the optional DOS stub before a PE file.
 */
public class ZbykowskiHeader
{
    public final static short MAGIC_NUMBER = 23117;

    public ZbykowskiHeader(ByteBuffer byteBuffer)
    {
        e_magic = byteBuffer.getShort();
        if (e_magic != MAGIC_NUMBER)
        {
            throw new PeParserException("Invalid magic number!");
        }
        e_cblp = byteBuffer.getShort();
        e_cp = byteBuffer.getShort();
        e_crlc = byteBuffer.getShort();
        e_cparhdr = byteBuffer.getShort();
        e_minalloc = byteBuffer.getShort();
        e_maxalloc = byteBuffer.getShort();
        e_ss = byteBuffer.getShort();
        e_sp = byteBuffer.getShort();
        e_csum = byteBuffer.getShort();
        e_ip = byteBuffer.getShort();
        e_cs = byteBuffer.getShort();
        e_lfarc = byteBuffer.getShort();
        e_ovno = byteBuffer.getShort();
        e_res = new short[4];
        for (int i = 0; i < e_res.length; i++)
            e_res[i] = byteBuffer.getShort();
        e_oemid = byteBuffer.getShort();
        e_oeminfo = byteBuffer.getShort();
        e_res2 = new short[10];
        for (int i = 0; i < e_res2.length; i++)
            e_res2[i] = byteBuffer.getShort();
        e_lfanew = byteBuffer.getInt();
    }

    private short e_magic, e_cblp, e_cp, e_crlc;
    private short e_cparhdr, e_minalloc, e_maxalloc;
    private short e_ss, e_sp, e_csum, e_ip, e_cs;
    private short e_lfarc, e_ovno;
    private short[] e_res;
    private short e_oemid, e_oeminfo;
    private short[] e_res2;
    private int e_lfanew;

    /**
     *
     * @return Bytes on last page of file
     */
    public short getE_cblp() {
        return e_cblp;
    }

    /**
     *
     * @return Pages in file
     */
    public short getE_cp() {
        return e_cp;
    }

    /**
     *
     * @return Relocations
     */
    public short getE_crlc() {
        return e_crlc;
    }

    /**
     *
     * @return Size of header in paragraphs
     */
    public short getE_cparhdr() {
        return e_cparhdr;
    }

    /**
     *
     * @return Minimum extra paragraphs needed
     */
    public short getE_minalloc() {
        return e_minalloc;
    }

    /**
     *
     * @return Maximum extra paragraphs needed
     */
    public short getE_maxalloc() {
        return e_maxalloc;
    }

    /**
     *
     * @return Initial (relative) SS value
     */
    public short getE_ss() {
        return e_ss;
    }

    /**
     *
     * @return Initial SP value
     */
    public short getE_sp() {
        return e_sp;
    }

    /**
     *
     * @return Checksum
     */
    public short getE_csum() {
        return e_csum;
    }

    /**
     *
     * @return Initial IP value
     */
    public short getE_ip() {
        return e_ip;
    }

    /**
     *
     * @return Initial (relative) CS value
     */
    public short getE_cs() {
        return e_cs;
    }

    /**
     *
     * @return File address of relocation table
     */
    public short getE_lfarc() {
        return e_lfarc;
    }

    /**
     *
     * @return Overlay number
     */
    public short getE_ovno() {
        return e_ovno;
    }

    /**
     *
     * @return OEM identifier (for e_oeminfo)
     */
    public short getE_oemid() {
        return e_oemid;
    }

    /**
     *
     * @return OEM information; e_oemid specific
     */
    public short getE_oeminfo() {
        return e_oeminfo;
    }

    /**
     *
     * @return File address of new exe header.
     */
    public int getE_lfanew() {
        return e_lfanew;
    }
}

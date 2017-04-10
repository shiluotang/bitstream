package org.sqg;

import java.io.IOException;
import java.math.BigInteger;

public interface BitOutput {

    void writeBit(int bit) throws IOException;

    void writeByte(byte value, int bits) throws IOException;

    void writeShort(short value, int bits) throws IOException;

    void writeInt(int value, int bits) throws IOException;

    void writeLong(long value, int bits) throws IOException;

    void writeBigInteger(BigInteger value, int bits) throws IOException;

    void writeUtf8(String value, int bits) throws IOException;

    void writeUByte(short value, int bits) throws IOException;

    void writeUShort(int value, int bits) throws IOException;

    void writeUInt(long value, int bits) throws IOException;

    /**
     * FIXME This may not be practical?
     */
    void writeULong(long value, int bits) throws IOException;

    void writeUBigInteger(BigInteger value, int bits) throws IOException;

    void writeSByte(byte value, int bits) throws IOException;

    void writeSShort(short value, int bits) throws IOException;

    void writeSInt(int value, int bits) throws IOException;

    void writeSLong(long value, int bits) throws IOException;

    void writeSBigInteger(BigInteger value, int bits) throws IOException;
}

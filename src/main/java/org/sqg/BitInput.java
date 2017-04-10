package org.sqg;

import java.io.IOException;
import java.math.BigInteger;

/**
 * For reading raw bit as numbers and string.
 *
 * <p>
 * There're three types of number.
 * <ol>
 * <li>2 's complement number.(补码)  use readXXX(int) methods</li>
 * <li>unsigned number. use readUXXX(int) methods</li>
 * <li>sign magnitude number. it equals a sign bit following unsigned number
 * bits. use readSXXX(int) methods</li>
 * </ol>
 * </p>
 *
 * @author <a href="mailto:shengquangang2015@163.com">Quangang Sheng</a>
 */
public interface BitInput {

    /**
     * Read a single bit value.
     *
     * <p>
     * Only 1 or 0 are valid return value.
     * </p>
     *
     * @return 1 or 0.
     * @exception IOException If I/O error happens.
     */
    int readBit() throws IOException;

    /**
     * Read the two's complement number.
     *
     * @param bits The bits to read.
     * @return The two's complement number.
     * @exception IOException If I/O error happens.
     */
    byte readByte(int bits) throws IOException;

    /**
     * Read the two's complement number.
     *
     * @param bits The bits to read.
     * @return The two's complement number.
     * @exception IOException If I/O error happens.
     */
    short readShort(int bits) throws IOException;

    /**
     * Read the two's complement number.
     *
     * @param bits The bits to read.
     * @return The two's complement number.
     * @exception IOException If I/O error happens.
     */
    int readInt(int bits) throws IOException;

    /**
     * Read the two's complement number.
     *
     * @param bits The bits to read.
     * @return The two's complement number.
     * @exception IOException If I/O error happens.
     */
    long readLong(int bits) throws IOException;

    /**
     * Read the sign-magnitude number.
     *
     * @param bits The bits to read.
     * @return The sign bit and magnitude bits number.
     * @exception IOException If I/O error happens.
     */
    BigInteger readBigInteger(int bits) throws IOException;

    float readFloat() throws IOException;

    double readDouble() throws IOException;

    /**
     * Read unsigned number.
     *
     * @param bits The bits to read.
     * @return The unsigned number represent by all the bits.
     * @exception IOException If I/O error happens.
     */
    short readUByte(int bits) throws IOException;

    /**
     * Read unsigned number.
     *
     * @param bits The bits to read.
     * @return The unsigned number represent by all the bits.
     * @exception IOException If I/O error happens.
     */
    int readUShort(int bits) throws IOException;

    /**
     * Read unsigned number.
     *
     * @param bits The bits to read.
     * @return The unsigned number represent by all the bits.
     * @exception IOException If I/O error happens.
     */
    long readUInt(int bits) throws IOException;

    /**
     * Read unsigned number.
     *
     * @param bits The bits to read.
     * @return The unsigned number represent by all the bits.
     * @exception IOException If I/O error happens.
     */
    BigInteger readUBigInteger(int bits) throws IOException;

    /**
     * Read the sign-magnitude number.
     *
     * @param bits The bits to read.
     * @return The sign bit and magnitude bits number.
     * @exception IOException If I/O error happens.
     */
    byte readSByte(int bits) throws IOException;

    /**
     * Read the sign-magnitude number.
     *
     * @param bits The bits to read.
     * @return The sign bit and magnitude bits number.
     * @exception IOException If I/O error happens.
     */
    short readSShort(int bits) throws IOException;

    /**
     * Read the sign-magnitude number.
     *
     * @param bits The bits to read.
     * @return The sign bit and magnitude bits number.
     * @exception IOException If I/O error happens.
     */
    int readSInt(int bits) throws IOException;

    /**
     * Read the sign-magnitude number.
     *
     * @param bits The bits to read.
     * @return The sign bit and magnitude bits number.
     * @exception IOException If I/O error happens.
     */
    long readSLong(int bits) throws IOException;

    /**
     * Read the sign-magnitude number.
     *
     * @param bits The bits to read.
     * @return The sign bit and magnitude bits number.
     * @exception IOException If I/O error happens.
     */
    BigInteger readSBigInteger(int bits) throws IOException;

    /**
     * Read UTF8 String content.
     *
     * @param bits The bits to read.
     * @return The String content of specified bits.
     * @exception IOException If I/O error happens.
     */
    String readUtf8(int bits) throws IOException;

    /**
     * Skip some following bits.
     *
     * @param bits The number of bits to ignore.
     * @exception IOException If I/O error happens.
     */
    void skip(int bits) throws IOException;
}

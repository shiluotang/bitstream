package org.sqg;

import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;


/**
 * Read raw bits as stream.
 *
 * @author samuel
 * @version Thu Mar 30 22:02:11 CST 2017
 * @since 1.0
 */
public class BitInputStream extends FilterInputStream implements BitInput {

    private static final int[] SHIFTS = {
            7, 6, 5, 4, 3, 2, 1, 0
    };

    private final class ByteFrame {

        private int _M_frame;

        private int _M_size;

        private int _M_pos;

        private int _M_markedFrame;

        private int _M_markedSize;

        private int _M_markedPos;

        public ByteFrame() {
            _M_frame = 0;
            _M_pos = 0;
            _M_size = 0;
        }

        public int peekBit() throws IOException {
            if (empty())
                fill();
            return (_M_frame >>> SHIFTS[_M_pos]) & 0x1;
        }

        public int readBit() throws IOException {
            int bit = peekBit();
            ++_M_pos;
            return bit;
        }

        public void fill() throws IOException {
            int b = in.read();
            if (b == -1)
                throw new EOFException();
            _M_frame = b;
            if (_M_pos > 7)
                _M_pos = 0;
            _M_size = 8;
        }

        public boolean empty() {
            return _M_pos >= _M_size;
        }

        public void mark() {
            _M_markedPos = _M_pos;
            _M_markedSize = _M_size;
            _M_markedFrame = _M_frame;
        }

        public void reset() {
            _M_pos = _M_markedPos;
            _M_size = _M_markedSize;
            _M_frame = _M_markedFrame;
        }

        public int available() {
            return availableBits() / 8;
        }

        public int availableBits() {
            return empty() ? 0 : _M_size - _M_pos;
        }
    }

    private ByteFrame _M_buffer;

    public BitInputStream(final InputStream in) {
        super(in);
        _M_buffer = new ByteFrame();
    }

    @Override
    public int readBit() throws IOException {
        return _M_buffer.readBit();
    }

    @Override
    public byte readByte(final int bits) throws IOException {
        if (bits > Byte.SIZE || bits < 2)
            throw new IllegalArgumentException();
        final int shift = 32 - bits;
        byte value = 0;
        for (int i = 1; i <= bits; ++i)
            value |= readBit() << (bits - i);
        return (byte) (value << shift >> shift);
    }

    @Override
    public short readShort(final int bits) throws IOException {
        if (bits > Short.SIZE || bits < 2)
            throw new IllegalArgumentException();
        final int shift = 32 - bits;
        short value = 0;
        for (int i = 1; i <= bits; ++i)
            value |= readBit() << (bits - i);
        return (short) (value << shift >> shift);
    }

    @Override
    public int readInt(final int bits) throws IOException {
        if (bits > Integer.SIZE || bits < 2)
            throw new IllegalArgumentException();
        final int shift = 32 - bits;
        int value = 0;
        for (int i = 1; i <= bits; ++i)
            value |= readBit() << (bits - i);
        return value << shift >> shift;
    }

    @Override
    public long readLong(final int bits) throws IOException {
        if (bits > Long.SIZE || bits < 2)
            throw new IllegalArgumentException();
        final int shift = 64 - bits;
        long value = 0;
        for (int i = 1; i <= bits; ++i)
            value |= readBit() << (bits - i);
        return value << shift >> shift;
    }

    @Override
    public float readFloat() throws IOException {
        return Float.intBitsToFloat(readInt(Integer.SIZE));
    }

    @Override
    public double readDouble() throws IOException {
        return Double.longBitsToDouble(readLong(Double.SIZE));
    }

    @Override
    public BigInteger readBigInteger(final int bits) throws IOException {
        int numOfBytes = (bits + Byte.SIZE - 1) / Byte.SIZE;
        int remainingBits = bits % Byte.SIZE;
        byte[] bytes = new byte[numOfBytes];
        if (remainingBits > 0) {
            bytes[0] = readByte(remainingBits);
            for (int i = 1; i < numOfBytes; ++i)
                bytes[i] = readByte(Byte.SIZE);
        } else {
            for (int i = 0; i < numOfBytes; ++i)
                bytes[i] = readByte(Byte.SIZE);
        }
        return new BigInteger(bytes);
    }

    @Override
    public short readUByte(final int bits) throws IOException {
        short value = 0;
        for (int i = 1; i <= bits; ++i)
            value |= readBit() << (bits - i);
        return value;
    }

    @Override
    public int readUShort(final int bits) throws IOException {
        int value = 0;
        for (int i = 1; i <= bits; ++i)
            value |= readBit() << (bits - i);
        return value;
    }

    @Override
    public long readUInt(final int bits) throws IOException {
        long value = 0;
        for (int i = 1; i <= bits; ++i)
            value |= readBit() << (bits - i);
        return value;
    }

    @Override
    public BigInteger readUBigInteger(final int bits) throws IOException {
        if (bits < 1)
            throw new IllegalArgumentException();
        int numOfBytes = (bits + Byte.SIZE - 1) / Byte.SIZE;
        int leadingBits = bits % Byte.SIZE;
        if (leadingBits == 0)
            leadingBits = Byte.SIZE;
        byte[] bytes = new byte[numOfBytes + 1];
        short first2Bytes = readUByte(leadingBits);
        bytes[0] = (byte) ((first2Bytes >>> 8) & 0xff);
        bytes[1] = (byte) ((first2Bytes >>> 0) & 0xff);
        for (int i = 1; i < numOfBytes; ++i)
            bytes[i + 1] = readByte(Byte.SIZE);
        return new BigInteger(bytes);
    }

    @Override
    public String readUtf8(final int bits) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void skipBits(final long bits) throws IOException {
        int n = 0;
        for (long i = 0; i < bits && n != -1; ++i)
            n = readBit();
    }

    @Override
    public byte readSByte(final int bits) throws IOException {
        if (bits > Byte.SIZE || bits < 2)
            throw new IllegalArgumentException();
        int sign = readBit();
        byte value = (byte) readUByte(bits - 1);
        return (byte) (sign == 1 ? -value : value);
    }

    @Override
    public short readSShort(final int bits) throws IOException {
        if (bits > Short.SIZE || bits < 2)
            throw new IllegalArgumentException();
        int sign = readBit();
        short value = (short) readUShort(bits - 1);
        return (short) (sign == 1 ? -value : value);
    }

    @Override
    public int readSInt(final int bits) throws IOException {
        if (bits > Integer.SIZE || bits < 2)
            throw new IllegalArgumentException();
        int sign = readBit();
        int value = (int) readUInt(bits - 1);
        return (int) (sign == 1 ? -value : value);
    }

    @Override
    public long readSLong(final int bits) throws IOException {
        if (bits > Long.SIZE || bits < 2)
            throw new IllegalArgumentException();
        int sign = readBit();
        long value = 0;
        for (int i = 1, n = bits - 1; i <= n; ++i)
            value |= readBit() << (n - i);
        return (long) (sign == 1 ? -value : value);
    }

    @Override
    public BigInteger readSBigInteger(final int bits) throws IOException {
        if (bits < 2)
            throw new IllegalArgumentException();
        int sign = readBit();
        BigInteger integer = readUBigInteger(bits - 1);
        return sign == 1 ? integer.negate() : integer;
    }

    @Override
    public void mark(final int readlimit) {
        super.mark(readlimit + 1);
        _M_buffer.mark();
    }

    @Override
    public void reset() throws IOException {
        super.reset();
        _M_buffer.reset();
    }

    @Override
    public int available() throws IOException {
        return super.available() + _M_buffer.available();
    }

    public int availableBits() throws IOException {
        return super.available() * 8 + _M_buffer.availableBits();
    }

    public void markBits(final int readlimitbits) {
        mark((readlimitbits + 8 - 1) / 8);
    }
}

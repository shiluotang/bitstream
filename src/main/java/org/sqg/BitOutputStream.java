package org.sqg;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;

public class BitOutputStream extends FilterOutputStream implements BitOutput {

    private static final int[] SHIFTS = { 7, 6, 5, 4, 3, 2, 1, 0 };

    private static final int[] NOT_ONE_MASKS = {
        (~(0x1 << 7)) & 0xff, (-(0x1 << 6)) & 0xff,
        (-(0x1 << 5)) & 0xff, (-(0x1 << 4)) & 0xff,
        (-(0x1 << 3)) & 0xff, (-(0x1 << 2)) & 0xff,
        (-(0x1 << 1)) & 0xff, (-(0x1 << 0)) & 0xff
    };

    private final class ByteFrame {

        /**
         * As the OutputStream.write(int) actually take a "int" parameter, we
         * decide to use the same type here.
         */
        private int _M_frame;

        private int _M_pos;

        public ByteFrame() {
            // make sure padding bits be 0.
            _M_frame = 0;
            _M_pos = 0;
        }

        public void writeBit(final int bit) throws IOException {
            touchBit(bit);
            ++_M_pos;
        }

        public void touchBit(final int bit) throws IOException {
            if (full())
                flush();
            _M_frame = (_M_frame & NOT_ONE_MASKS[_M_pos])
                | ((bit & 0x1) << SHIFTS[_M_pos]);
        }

        public boolean full() {
            return Byte.SIZE <= _M_pos;
        }

        public void flush() throws IOException {
            if (_M_pos > 0) {
                // only write frame on data exists.
                out.write(_M_frame);
                _M_pos = 0;
                // make sure padding bits to byte boundary is bit 0.
                _M_frame = 0;
            }
        }
    }

    private final ByteFrame _M_buffer;

    public BitOutputStream(OutputStream out) {
        super(out);
        _M_buffer = new ByteFrame();
    }

    @Override
    public void writeBit(final int bit) throws IOException {
        _M_buffer.writeBit(bit);
    }

    @Override
    public void writeByte(final byte value, final int bits) throws IOException {
        if (bits > Byte.SIZE)
            throw new IllegalArgumentException();
        // As integral data (including byte, short, int, long, BigInteger) all
        // have fixed bit size. But data may not fill all the bits, typically
        // the small positive numbers are the good examples.
        //
        // So, we are going to write only some bits into storage.
        // Say there is a number 3 of JAVA primitive type byte, which is 11 in
        // binary form, we need to store. As it's very small only two bits are
        // occupied, the target storage can be just 2 bits!
        //
        // Now we have the api to specify how many bits to be written.
        // As we all know, that the full format of byte in binary is
        // 0000 0000
        // And this number in the full format of binary is
        // 0000 0011
        // So write two bits means the last two bits. from the least significant
        // bit to most significant bit.(Something like the LSB and MSB which
        // actually refer to bytes, but not bits)
        //
        // So remember this when implementing our writeXXX functions.
        // The parameter "bits" always refer to the last n bits of the parameter
        // "value". These n bits always end at the least significant bit.

        for (int i = 1; i <= bits; ++i)
            _M_buffer.writeBit(value >> (bits - i));
    }

    @Override
    public void writeShort(final short value, final int bits) throws IOException {
        if (bits > Short.SIZE)
            throw new IllegalArgumentException();
        for (int i = 1; i <= bits; ++i)
            _M_buffer.writeBit(value >> (bits - i));
    }

    @Override
    public void writeInt(final int value, final int bits) throws IOException {
        if (bits > Integer.SIZE)
            throw new IllegalArgumentException();
        for (int i = 1; i <= bits; ++i)
            _M_buffer.writeBit(value >> (bits - i));
    }

    @Override
    public void writeLong(final long value, final int bits) throws IOException {
        if (bits > Long.SIZE)
            throw new IllegalArgumentException();
        for (int i = 1; i <= bits; ++i)
            _M_buffer.writeBit((int) (value >> (bits - i)));
    }

    @Override
    public void writeBigInteger(BigInteger value, int bits) throws IOException {
        for (int i = 0; i < bits; ++i)
            _M_buffer.writeBit(value.testBit(bits - i - 1) ? 1 : 0);
    }

    @Override
    public void writeUtf8(String value, int bits) throws IOException {
        // TODO Auto-generated method stub
    }

    @Override
    public void writeUByte(final short value, final int bits)
        throws IOException {
        writeByte((byte) (value & 0xff), bits);
    }

    @Override
    public void writeUShort(final int value, final int bits)
        throws IOException {
        writeShort((short) (value & 0xffff), bits);
    }

    @Override
    public void writeUInt(final long value, final int bits) throws IOException {
        writeInt((int) (value & 0xffffffffL), bits);
    }

    @Override
    public void writeULong(final long value, final int bits)
        throws IOException {
        writeLong(value, bits);
    }

    @Override
    public void writeUBigInteger(BigInteger value, int bits)
        throws IOException {
        writeBigInteger(value, bits);
    }

    @Override
    public void flush() throws IOException {
        _M_buffer.flush();
        super.flush();
    }

    @Override
    public void writeSByte(byte value, int bits) throws IOException {
        if (bits > Byte.SIZE)
            throw new IllegalArgumentException();
        if (value > 0) {
            writeByte(value, bits);
            return;
        }
        _M_buffer.writeBit(1);
        writeByte((byte) -value, bits - 1);
    }

    @Override
    public void writeSShort(short value, int bits) throws IOException {
        if (bits > Short.SIZE)
            throw new IllegalArgumentException();
        if (value > 0) {
            writeShort(value, bits);
            return;
        }
        _M_buffer.writeBit(1);
        writeShort((short) -value, bits - 1);
    }

    @Override
    public void writeSInt(int value, int bits) throws IOException {
        if (bits > Integer.SIZE)
            throw new IllegalArgumentException();
        if (value > 0) {
            writeInt(value, bits);
            return;
        }
        _M_buffer.writeBit(1);
        writeInt(-value, bits - 1);
    }

    @Override
    public void writeSLong(long value, int bits) throws IOException {
        if (bits > Long.SIZE)
            throw new IllegalArgumentException();
        if (value > 0) {
            writeLong(value, bits);
            return;
        }
        _M_buffer.writeBit(1);
        writeLong(-value, bits - 1);
    }

    @Override
    public void writeSBigInteger(final BigInteger value, final int bits) throws IOException {
        if (value.signum() != -1)
            writeBigInteger(value, bits);
        else {
            writeBit(1);
            writeBigInteger(value.negate(), bits - 1);
        }
    }

    public void writeByte(final byte value) throws IOException {
        writeByte(value, Byte.SIZE);
    }

    public void writeShort(final short value) throws IOException {
        writeShort(value, Short.SIZE);
    }

    public void writeInt(final int value) throws IOException {
        writeInt(value, Integer.SIZE);
    }

    public void writeLong(final long value) throws IOException {
        writeLong(value, Long.SIZE);
    }

    public void writeFloat(final float value) throws IOException {
        writeInt(Float.floatToRawIntBits(value), Float.SIZE);
    }

    public void writeDouble(final double value) throws IOException {
        writeLong(Double.doubleToRawLongBits(value), Double.SIZE);
    }

    public void writeBigInteger(final BigInteger value) throws IOException {
        // TODO not implemented!
        throw new UnsupportedOperationException();
    }
}

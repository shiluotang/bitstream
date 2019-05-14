package org.sqg;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.xml.bind.DatatypeConverter;

import org.junit.Assert;
import org.junit.Test;

public class BitInputStreamTest {

    @Test
    public void testByte() throws IOException {
        // 0b10001010
        final byte VALUE = (byte) (138 & 0xff);
        ByteBuffer buffer = ByteBuffer.allocate(Byte.SIZE / 8);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.put(VALUE);
        buffer.flip();
        byte[] data = buffer.array();
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            Assert.assertEquals(VALUE, is.readByte(8));
        }
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            is.skipBits(1);
            Assert.assertEquals((byte) (VALUE & 0xf), is.readByte(7));
        }
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            is.skipBits(4);
            Assert.assertEquals((byte) (VALUE | 0xf0), is.readByte(4));
        }
    }

    @Test
    public void testSByte() throws IOException {
        // 0b10001010
        final byte VALUE = (byte) (138 & 0xff);
        ByteBuffer buffer = ByteBuffer.allocate(Byte.SIZE / 8);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.put(VALUE);
        buffer.flip();
        byte[] data = buffer.array();
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            Assert.assertEquals((byte) -(VALUE & 0x7f), is.readSByte(8));
        }
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            is.skipBits(1);
            Assert.assertEquals((byte) (VALUE & 0x7f), is.readSByte(7));
        }
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            is.skipBits(4);
            Assert.assertEquals((byte) -(VALUE & 0x7), is.readSByte(4));
        }
    }

    @Test
    public void testUByte() throws IOException {
        // 0b10001010
        final byte VALUE = (byte) (138 & 0xff);
        ByteBuffer buffer = ByteBuffer.allocate(Byte.SIZE / 8);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.put(VALUE);
        buffer.flip();
        byte[] data = buffer.array();
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            Assert.assertEquals((int) (VALUE & 0xff), is.readUByte(8));
        }
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            is.skipBits(1);
            Assert.assertEquals((int) (VALUE & 0x7f), is.readUByte(7));
        }
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            is.skipBits(4);
            Assert.assertEquals((int) (VALUE & 0xf), is.readUByte(4));
        }
    }

    @Test
    public void testShort() throws IOException {
        // 0b10001010
        final byte VALUE = (byte) (138 & 0xff);
        ByteBuffer buffer = ByteBuffer.allocate(Byte.SIZE / 8);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.put(VALUE);
        buffer.flip();
        byte[] data = buffer.array();
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            Assert.assertEquals((short) (VALUE & 0xffff), is.readShort(8));
        }
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            is.skipBits(1);
            Assert.assertEquals((short) (((byte) (VALUE & 0xf)) & 0xffff), is.readShort(7));
        }
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            is.skipBits(4);
            Assert.assertEquals((short) (((byte) (VALUE | 0xf0)) & 0xffff), is.readShort(4));
        }
    }

    @Test
    public void testSShort() throws IOException {
        // 0b10001010
        final byte VALUE = (byte) (138 & 0xff);
        ByteBuffer buffer = ByteBuffer.allocate(Byte.SIZE / 8);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.put(VALUE);
        buffer.flip();
        byte[] data = buffer.array();
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            Assert.assertEquals((short) (((byte) -(VALUE & 0x7f)) &0xffff), is.readSShort(8));
        }
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            is.skipBits(1);
            Assert.assertEquals((short) (((byte) (VALUE & 0x7f)) & 0xffff), is.readSShort(7));
        }
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            is.skipBits(4);
            Assert.assertEquals((short) (((byte) -(VALUE & 0x7)) & 0xffff), is.readSShort(4));
        }
    }

    @Test
    public void testUShort() throws IOException {
        // 0b10001010
        final byte VALUE = (byte) (138 & 0xff);
        ByteBuffer buffer = ByteBuffer.allocate(Byte.SIZE / 8);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.put(VALUE);
        buffer.flip();
        byte[] data = buffer.array();
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            Assert.assertEquals((short) (VALUE & 0xff), is.readUShort(8));
        }
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            is.skipBits(1);
            Assert.assertEquals((short) (VALUE & 0x7f), is.readUShort(7));
        }
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            is.skipBits(4);
            Assert.assertEquals((short) (VALUE & 0xf), is.readUShort(4));
        }
    }

    @Test
    public void testInt() throws IOException {
        // 0b10001010
        final byte VALUE = (byte) (138 & 0xff);
        ByteBuffer buffer = ByteBuffer.allocate(Byte.SIZE / 8);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.put(VALUE);
        buffer.flip();
        byte[] data = buffer.array();
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            Assert.assertEquals((int) (VALUE & 0xff | 0xffffff00), is.readInt(8));
        }
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            is.skipBits(1);
            Assert.assertEquals(VALUE & 0x7f, is.readInt(7));
        }
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            is.skipBits(4);
            Assert.assertEquals(VALUE & 0xf | 0xfffffff0, is.readShort(4));
        }
    }

    @Test
    public void testSInt() throws IOException {
        // 0b10001010
        final byte VALUE = (byte) (138 & 0xff);
        ByteBuffer buffer = ByteBuffer.allocate(Byte.SIZE / 8);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.put(VALUE);
        buffer.flip();
        byte[] data = buffer.array();
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            Assert.assertEquals(-(VALUE & 0x7f), is.readSInt(8));
        }
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            is.skipBits(1);
            Assert.assertEquals(VALUE & 0x3f, is.readSInt(7));
        }
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            is.skipBits(4);
            Assert.assertEquals(-(VALUE & 0x7), is.readSShort(4));
        }
    }

    @Test
    public void testUInt() throws IOException {
        // 0b10001010
        final byte VALUE = (byte) (138 & 0xff);
        ByteBuffer buffer = ByteBuffer.allocate(Integer.SIZE / 8);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.putInt(VALUE & 0xff);
        buffer.flip();
        byte[] data = buffer.array();
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            is.skipBits(32 - 8);
            Assert.assertEquals(VALUE & 0xff, is.readUInt(8));
        }
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            is.skipBits(32 - 8);
            is.skipBits(1);
            Assert.assertEquals(VALUE & 0x7f, is.readUInt(7));
        }
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            is.skipBits(32 - 8);
            is.skipBits(4);
            Assert.assertEquals(VALUE & 0xf, is.readUInt(4));
        }
        buffer.rewind();
        buffer.putInt(-1);
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            Assert.assertEquals(-1 & 0xffffffffL, is.readUInt(32));
        }
    }

    @Test
    public void testLong() throws IOException {
        // 0b10001010
        final byte VALUE = (byte) (138 & 0xff);
        ByteBuffer buffer = ByteBuffer.allocate(Byte.SIZE / 8);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.put(VALUE);
        buffer.flip();
        byte[] data = buffer.array();
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            Assert.assertEquals((((long) VALUE) & 0xff) << 56 >> 56, is.readLong(8));
        }
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            is.skipBits(1);
            Assert.assertEquals((((long) VALUE) & 0x7f) << 57 >> 57, is.readLong(7));
        }
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            is.skipBits(4);
            Assert.assertEquals((((long) VALUE) & 0xf) << 60 >> 60, is.readLong(4));
        }
    }

    @Test
    public void testSLong() throws IOException {
        // 0b10001010
        final byte VALUE = (byte) (138 & 0xff);
        ByteBuffer buffer = ByteBuffer.allocate(Byte.SIZE / 8);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.put(VALUE);
        buffer.flip();
        byte[] data = buffer.array();
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            Assert.assertEquals(-(VALUE & 0x7f), is.readSLong(8));
        }
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            is.skipBits(1);
            Assert.assertEquals(VALUE & 0x3f, is.readSLong(7));
        }
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            is.skipBits(4);
            Assert.assertEquals(-(VALUE & 0x7), is.readSLong(4));
        }
    }

    @Test
    public void testBigInteger() throws IOException {
        // 0b10001010
        final byte VALUE = (byte) (138 & 0xff);
        ByteBuffer buffer = ByteBuffer.allocate(Byte.SIZE / 8);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.put(VALUE);
        buffer.flip();
        byte[] data = buffer.array();
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            Assert.assertEquals(BigInteger.valueOf(VALUE), is.readBigInteger(8));
        }
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            is.skipBits(1);
            Assert.assertEquals(BigInteger.valueOf(VALUE & 0x7f), is.readBigInteger(7));
        }
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            is.skipBits(4);
            Assert.assertEquals(BigInteger.valueOf((VALUE & 0xf) << 28 >> 28), is.readBigInteger(4));
        }
    }

    @Test
    public void testSBigInteger() throws IOException {
        // 0b10001010
        final byte VALUE = (byte) (138 & 0xff);
        ByteBuffer buffer = ByteBuffer.allocate(Byte.SIZE / 8);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.put(VALUE);
        buffer.flip();
        byte[] data = buffer.array();
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            Assert.assertEquals(BigInteger.valueOf(-(VALUE & 0x7f)), is.readSBigInteger(8));
        }
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            is.skipBits(1);
            Assert.assertEquals(BigInteger.valueOf(VALUE & 0x3f), is.readSBigInteger(7));
        }
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            is.skipBits(4);
            Assert.assertEquals(BigInteger.valueOf(-(VALUE & 0x7)), is.readSBigInteger(4));
        }
    }

    @Test
    public void testUBigInteger() throws IOException {
        // 0b10001010
        final byte VALUE = (byte) (138 & 0xff);
        ByteBuffer buffer = ByteBuffer.allocate(Byte.SIZE / 8);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.put(VALUE);
        buffer.flip();
        byte[] data = buffer.array();
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            Assert.assertEquals(BigInteger.valueOf(VALUE & 0xff), is.readUBigInteger(8));
        }
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            is.skipBits(1);
            Assert.assertEquals(BigInteger.valueOf(VALUE & 0x7f), is.readUBigInteger(7));
        }
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            is.skipBits(4);
            Assert.assertEquals(BigInteger.valueOf(VALUE & 0xf), is.readUBigInteger(4));
        }
    }

    @Test
    public void testSkipBits() throws IOException {
        // 0b10001010
        final byte VALUE = (byte) (138 & 0xff);
        ByteBuffer buffer = ByteBuffer.allocate(Byte.SIZE / 8);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.put(VALUE);
        buffer.flip();
        byte[] data = buffer.array();
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            is.skipBits(1);
            Assert.assertEquals(VALUE & 0x7f, is.readByte(7));
        }
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            is.skipBits(4);
            Assert.assertEquals(VALUE | 0xf0, is.readByte(4));
        }
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            is.skipBits(5);
            Assert.assertEquals(VALUE & 0x7, is.readByte(3));
        }
    }

    @Test
    public void testMark() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(Byte.SIZE / 8 * 8);
        buffer.order(ByteOrder.BIG_ENDIAN);
        for (int i = 0; i < 8; ++i)
            buffer.put((byte) i);
        buffer.flip();
        byte[] data = buffer.array();
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            is.markBits(8);
            Assert.assertEquals(0, is.readByte(8));
            is.reset();
            Assert.assertEquals(0, is.readByte(8));
            Assert.assertEquals(1, is.readByte(8));
            is.mark(1);
            Assert.assertEquals(2, is.readByte(8));
            is.reset();
            Assert.assertEquals(2, is.readByte(8));
            Assert.assertEquals(3, is.readByte(8));
        }
        try (BitInputStream is = new BitInputStream(new ByteArrayInputStream(data))) {
            is.skipBits(8);
            is.markBits(9);
            Assert.assertEquals(1, is.readByte(8));
            is.reset();
            Assert.assertEquals(1, is.readByte(8));
            Assert.assertEquals(2, is.readByte(8));
            is.mark(1);
            Assert.assertEquals(3, is.readByte(8));
            is.reset();
            Assert.assertEquals(3, is.readByte(8));
            Assert.assertEquals(4, is.readByte(8));
        }
    }
}

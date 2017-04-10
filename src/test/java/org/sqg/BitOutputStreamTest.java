package org.sqg;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Test;

public class BitOutputStreamTest {

    @Test
    public void testBit() throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            try (BitOutputStream os = new BitOutputStream(NoClose.wrap(bos))) {
                os.writeBit(1);
                os.writeBit(0);
                os.writeBit(1);
                os.writeBit(0);
                os.writeBit(0);
                os.writeBit(0);
            }
            Assert.assertEquals("10100000", new BitStringBuilder()
                    .append(bos.toByteArray()).toString());
        }
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            try (BitOutputStream os = new BitOutputStream(NoClose.wrap(bos))) {
                os.writeBit(1);
                os.writeBit(0);
                os.writeBit(1);
                os.writeBit(1);
                os.writeBit(1);
                os.writeBit(0);
                os.writeBit(0);
                os.writeBit(0);
            }
            Assert.assertEquals("10111000", new BitStringBuilder()
                    .append(bos.toByteArray()).toString());
        }
    }

    @Test
    public void testByte() throws IOException {
        final byte VALUE = (byte) 0b10000101;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            try (BitOutputStream os = new BitOutputStream(NoClose.wrap(bos))) {
                os.writeByte(VALUE, 4);
                os.writeByte(VALUE, 8);
            }
            Assert.assertEquals("0101100001010000", new BitStringBuilder()
                    .append(bos.toByteArray()).toString());
        }
    }

    @Test
    public void testSByte() throws IOException {
        final byte VALUE = (byte) 0b00000101;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            try (BitOutputStream os = new BitOutputStream(NoClose.wrap(bos))) {
                os.writeSByte((byte)-VALUE, 6);
            }
            Assert.assertEquals("10010100", new BitStringBuilder()
                    .append(bos.toByteArray()).toString());
        }
    }

    @Test
    public void testUByte() throws IOException {
        final byte VALUE = (byte) 0b10000101;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            try (BitOutputStream os = new BitOutputStream(NoClose.wrap(bos))) {
                os.writeUByte(VALUE, 6);
            }
            Assert.assertEquals("00010100", new BitStringBuilder()
                    .append(bos.toByteArray()).toString());
        }
    }

    @Test
    public void testShort() throws IOException {
        final byte VALUE = (byte) 0b10000101;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            try (BitOutputStream os = new BitOutputStream(NoClose.wrap(bos))) {
                os.writeShort(VALUE, 8);
            }
            Assert.assertEquals("10000101", new BitStringBuilder()
                    .append(bos.toByteArray()).toString());
        }
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            try (BitOutputStream os = new BitOutputStream(NoClose.wrap(bos))) {
                os.writeShort(VALUE, 7);
            }
            Assert.assertEquals("00001010", new BitStringBuilder()
                    .append(bos.toByteArray()).toString());
        }
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            try (BitOutputStream os = new BitOutputStream(NoClose.wrap(bos))) {
                os.writeShort(VALUE, 4);
            }
            Assert.assertEquals("01010000", new BitStringBuilder()
                    .append(bos.toByteArray()).toString());
        }
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            try (BitOutputStream os = new BitOutputStream(NoClose.wrap(bos))) {
                os.writeShort(VALUE, 3);
            }
            Assert.assertEquals("10100000", new BitStringBuilder()
                    .append(bos.toByteArray()).toString());
        }
    }

    @Test
    public void testSShort() throws IOException {
        final byte VALUE = (byte) 0b10000101;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            try (BitOutputStream os = new BitOutputStream(NoClose.wrap(bos))) {
                os.writeSShort(VALUE, 8);
            }
            Assert.assertEquals("11111011", new BitStringBuilder()
                    .append(bos.toByteArray()).toString());
        }
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            try (BitOutputStream os = new BitOutputStream(NoClose.wrap(bos))) {
                os.writeSShort(VALUE, 3);
            }
            Assert.assertEquals("11100000", new BitStringBuilder()
                    .append(bos.toByteArray()).toString());
        }
    }

    @Test
    public void testUShort() throws IOException {
        final byte VALUE = (byte) 0b10000101;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            try (BitOutputStream os = new BitOutputStream(NoClose.wrap(bos))) {
                os.writeUShort(VALUE, 8);
            }
            Assert.assertEquals("10000101", new BitStringBuilder()
                    .append(bos.toByteArray()).toString());
        }
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            try (BitOutputStream os = new BitOutputStream(NoClose.wrap(bos))) {
                os.writeUShort(VALUE, 3);
            }
            Assert.assertEquals("10100000", new BitStringBuilder()
                    .append(bos.toByteArray()).toString());
        }
    }

    @Test
    public void testInt() throws IOException {
        final byte VALUE = (byte) 0b10000101;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            try (BitOutputStream os = new BitOutputStream(NoClose.wrap(bos))) {
                os.writeInt(VALUE, 8);
            }
            Assert.assertEquals("10000101", new BitStringBuilder()
                    .append(bos.toByteArray()).toString());
        }
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            try (BitOutputStream os = new BitOutputStream(NoClose.wrap(bos))) {
                os.writeInt(VALUE, 3);
            }
            Assert.assertEquals("10100000", new BitStringBuilder()
                    .append(bos.toByteArray()).toString());
        }
    }

    @Test
    public void testSInt() throws IOException {
        final byte VALUE = (byte) 0b10000101;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            try (BitOutputStream os = new BitOutputStream(NoClose.wrap(bos))) {
                os.writeSInt(VALUE, 8);
            }
            Assert.assertEquals("11111011", new BitStringBuilder()
                    .append(bos.toByteArray()).toString());
        }
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            try (BitOutputStream os = new BitOutputStream(NoClose.wrap(bos))) {
                os.writeSInt(VALUE, 3);
            }
            Assert.assertEquals("11100000", new BitStringBuilder()
                    .append(bos.toByteArray()).toString());
        }
    }

    @Test
    public void testUInt() throws IOException {
        final byte VALUE = (byte) 0b10000101;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            try (BitOutputStream os = new BitOutputStream(NoClose.wrap(bos))) {
                os.writeUInt(VALUE, 8);
            }
            Assert.assertEquals("10000101", new BitStringBuilder()
                    .append(bos.toByteArray()).toString());
        }
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            try (BitOutputStream os = new BitOutputStream(NoClose.wrap(bos))) {
                os.writeUInt(VALUE, 3);
            }
            Assert.assertEquals("10100000", new BitStringBuilder()
                    .append(bos.toByteArray()).toString());
        }
    }

    @Test
    public void testLong() throws IOException {
        final byte VALUE = (byte) 0b10000101;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            try (BitOutputStream os = new BitOutputStream(NoClose.wrap(bos))) {
                os.writeLong(VALUE, 8);
            }
            Assert.assertEquals("10000101", new BitStringBuilder()
                    .append(bos.toByteArray()).toString());
        }
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            try (BitOutputStream os = new BitOutputStream(NoClose.wrap(bos))) {
                os.writeLong(VALUE, 3);
            }
            Assert.assertEquals("10100000", new BitStringBuilder()
                    .append(bos.toByteArray()).toString());
        }
    }

    @Test
    public void testSLong() throws IOException {
        final byte VALUE = (byte) 0b10000101;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            try (BitOutputStream os = new BitOutputStream(NoClose.wrap(bos))) {
                os.writeSLong(VALUE, 8);
            }
            Assert.assertEquals("11111011", new BitStringBuilder()
                    .append(bos.toByteArray()).toString());
        }
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            try (BitOutputStream os = new BitOutputStream(NoClose.wrap(bos))) {
                os.writeSLong(VALUE, 3);
            }
            Assert.assertEquals("11100000", new BitStringBuilder()
                    .append(bos.toByteArray()).toString());
        }
    }

    @Test
    public void testULong() throws IOException {
        final byte VALUE = (byte) 0b10000101;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            try (BitOutputStream os = new BitOutputStream(NoClose.wrap(bos))) {
                os.writeULong(VALUE, 8);
            }
            Assert.assertEquals("10000101", new BitStringBuilder()
                    .append(bos.toByteArray()).toString());
        }
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            try (BitOutputStream os = new BitOutputStream(NoClose.wrap(bos))) {
                os.writeULong(VALUE, 3);
            }
            Assert.assertEquals("10100000", new BitStringBuilder()
                    .append(bos.toByteArray()).toString());
        }
    }

    @Test
    public void testBigInteger() throws IOException {
        final byte VALUE = (byte) 0b10000101;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            try (BitOutputStream os = new BitOutputStream(NoClose.wrap(bos))) {
                os.writeBigInteger(BigInteger.valueOf(VALUE), 8);
            }
            Assert.assertEquals("10000101", new BitStringBuilder()
                    .append(bos.toByteArray()).toString());
        }
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            try (BitOutputStream os = new BitOutputStream(NoClose.wrap(bos))) {
                os.writeBigInteger(BigInteger.valueOf(VALUE), 3);
            }
            Assert.assertEquals("10100000", new BitStringBuilder()
                    .append(bos.toByteArray()).toString());
        }
    }

    @Test
    public void testSBigInteger() throws IOException {
        final byte VALUE = (byte) 0b10000101;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            try (BitOutputStream os = new BitOutputStream(NoClose.wrap(bos))) {
                os.writeSBigInteger(BigInteger.valueOf(VALUE), 8);
            }
            Assert.assertEquals("11111011", new BitStringBuilder()
                    .append(bos.toByteArray()).toString());
        }
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            try (BitOutputStream os = new BitOutputStream(NoClose.wrap(bos))) {
                os.writeSBigInteger(BigInteger.valueOf(VALUE), 3);
            }
            Assert.assertEquals("11100000", new BitStringBuilder()
                    .append(bos.toByteArray()).toString());
        }
    }

    @Test
    public void testUBigInteger() throws IOException {
        final byte VALUE = (byte) 0b10000101;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            try (BitOutputStream os = new BitOutputStream(NoClose.wrap(bos))) {
                os.writeUBigInteger(BigInteger.valueOf(VALUE), 8);
            }
            Assert.assertEquals("10000101", new BitStringBuilder()
                    .append(bos.toByteArray()).toString());
        }
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            try (BitOutputStream os = new BitOutputStream(NoClose.wrap(bos))) {
                os.writeUBigInteger(BigInteger.valueOf(VALUE), 3);
            }
            Assert.assertEquals("10100000", new BitStringBuilder()
                    .append(bos.toByteArray()).toString());
        }
    }
}

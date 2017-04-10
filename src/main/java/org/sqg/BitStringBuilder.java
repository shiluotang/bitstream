package org.sqg;

public class BitStringBuilder {

    private StringBuilder _M_builder;

    public BitStringBuilder() {
        _M_builder = new StringBuilder();
    }

    public BitStringBuilder append(final byte value) {
        _M_builder.append(Integer.toBinaryString(value & 0xff));
        return this;
    }

    public BitStringBuilder append(final short value) {
        _M_builder.append(Integer.toBinaryString(value & 0xffff));
        return this;
    }

    public BitStringBuilder append(final int value) {
        _M_builder.append(Integer.toBinaryString(value));
        return this;
    }

    public BitStringBuilder append(final long value) {
        _M_builder.append(Long.toBinaryString(value));
        return this;
    }

    public BitStringBuilder append(final float value) {
        _M_builder.append(Integer.toBinaryString(Float.floatToRawIntBits(value)));
        return this;
    }

    public BitStringBuilder append(final double value) {
        _M_builder.append(Long.toBinaryString(Double.doubleToRawLongBits(value)));
        return this;
    }

    public BitStringBuilder append(byte[] data, int offset, int n) {
        for (int i = 0; i < n; ++i)
            _M_builder.append(String.format("%8s", Integer.toBinaryString(data[i + offset] & 0xff)).replace(' ', '0'));
        return this;
    }

    public BitStringBuilder append(short[] data, int offset, int n) {
        for (int i = 0; i < n; ++i)
            _M_builder.append(String.format("%16s", Integer.toBinaryString(data[i + offset] & 0xffff)).replace(' ', '0'));
        return this;
    }

    public BitStringBuilder append(int[] data, int offset, int n) {
        for (int i = 0; i < n; ++i)
            _M_builder.append(String.format("%32s", Integer.toBinaryString(data[i + offset])).replace(' ', '0'));
        return this;
    }

    public BitStringBuilder append(long[] data, int offset, int n) {
        for (int i = 0; i < n; ++i)
            _M_builder.append(String.format("%32s", Long.toBinaryString(data[i + offset])).replace(' ', '0'));
        return this;
    }

    public BitStringBuilder append(float[] data, int offset, int n) {
        for (int i = 0; i < n; ++i)
            append(data[i + offset]);
        return this;
    }

    public BitStringBuilder append(double[] data, int offset, int n) {
        for (int i = 0; i < n; ++i)
            append(data[i + offset]);
        return this;
    }

    public BitStringBuilder append(byte[] data) {
        return append(data, 0, data.length);
    }

    public BitStringBuilder append(short[] data) {
        return append(data, 0, data.length);
    }

    public BitStringBuilder append(int[] data) {
        return append(data, 0, data.length);
    }

    public BitStringBuilder append(long[] data) {
        return append(data, 0, data.length);
    }

    public BitStringBuilder append(float[] data) {
        return append(data, 0, data.length);
    }

    public BitStringBuilder append(double[] data) {
        return append(data, 0, data.length);
    }

    @Override
    public String toString() {
        return _M_builder.toString();
    }
}

package org.sqg;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

final class NoClose {

    private static final class Input extends InputStream {

        private InputStream _M_in;

        public Input(InputStream in) {
            _M_in = in;
        }

        @Override
        public final int read() throws IOException {
            return _M_in.read();
        }

        public void close() throws IOException {
        }

    }

    static final class Output extends OutputStream {

        private OutputStream _M_out;

        public Output(OutputStream out) {
            _M_out = out;
        }

        @Override
        public void write(int b) throws IOException {
            _M_out.write(b);
        }

        public void close() throws IOException {
        }
    }

    public static InputStream wrap(InputStream in) {
        if (in instanceof Input)
            return in;
        return new Input(in);
    }

    public static OutputStream wrap(OutputStream out) {
        if (out instanceof Output)
            return out;
        return new Output(out);
    }
}

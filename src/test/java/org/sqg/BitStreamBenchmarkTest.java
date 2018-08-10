package org.sqg;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class BitStreamBenchmarkTest {

    @Test
    public void test1() throws IOException {
        final int N = 0xffffff;
        long t1, t2 = 0;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            t1 = System.nanoTime();
            for (int i = 0; i < N; ++i) {
                baos.reset();
                try (BitOutputStream bos = new BitOutputStream(NoClose.wrap(baos))) {
                    bos.writeUInt(1L, 5);
                    bos.writeUInt(-1L, 5);
                    bos.writeUInt(2L, 5);
                    bos.writeUInt(-2L, 5);
                    bos.writeUInt(3L, 5);
                    bos.writeUInt(-3L, 5);
                }
                Assert.assertEquals((6 * 5 + 7) / 8, baos.size());
            }
            t2 = System.nanoTime();
            System.out.printf("N = %d, Total = %g s, Average = %g s, QPS = %g\n",
                    N, (t2 - t1) * 1e-9, 1e-9 * (t2 - t1) / N, 1e9 * N / (t2 - t1));
        }
    }
}

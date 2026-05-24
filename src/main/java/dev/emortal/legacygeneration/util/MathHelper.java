package dev.emortal.legacygeneration.util;

public class MathHelper {
    private static final float[] a = new float[65536];

    public static float sin(float f) {
        return a[(int) (f * 10430.378F) & '\uffff'];
    }

    public static float cos(float f) {
        return a[(int) (f * 10430.378F + 16384.0F) & '\uffff'];
    }

    public static int floor(double d0) {
        int i = (int) d0;
        return d0 < (double) i ? i - 1 : i;
    }

    static {
        for (int i = 0; i < 65536; ++i) {
            a[i] = (float) Math.sin((double) i * 3.141592653589793D * 2.0D / 65536.0D);
        }
    }
}

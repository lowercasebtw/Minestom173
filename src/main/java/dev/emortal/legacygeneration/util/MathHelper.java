package dev.emortal.legacygeneration.util;

public final class MathHelper {
    private static final int MAX_SIN_SIZE = Character.MAX_VALUE + 1;
    private static final float[] SIN_TABLE = new float[MAX_SIN_SIZE];

    static {
        for (int i = 0; i < MAX_SIN_SIZE; ++i) {
            SIN_TABLE[i] = (float) Math.sin((double) i * 3.141592653589793D * 2.0D / (double) MAX_SIN_SIZE);
        }
    }

    public static float sin(final float value) {
        return SIN_TABLE[(int) (value * 10430.378F) & '\uffff'];
    }

    public static float cos(final float value) {
        return SIN_TABLE[(int) (value * 10430.378F + 16384.0F) & '\uffff'];
    }

    public static int floor(final double value) {
        final int floored = (int) value;
        return value < (double) floored ? floored - 1 : floored;
    }

    public static long floorLong(final double value) {
        final long floored = (long) value;
        return value < floored ? floored - 1L : floored;
    }

    public static double lerp(double var1, double var3, double var5) {
        return var3 + var1 * (var5 - var3);
    }
}

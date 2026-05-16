package dev.emortal.minestom173.generator.populator;

import dev.emortal.minestom173.generator.WorldContext;
import dev.emortal.minestom173.util.BlockConstants;
import dev.emortal.minestom173.util.MathHelper;
import net.minestom.server.instance.block.Block;

import java.util.Random;

public class WorldGenBigTree implements WorldGenerator {
    static final byte[] a = new byte[]{(byte) 2, (byte) 0, (byte) 0, (byte) 1, (byte) 2, (byte) 1};
    private final Random random = new Random();
    private final int[] pos = new int[]{0, 0, 0};
    private int e = 0;
    private int f;
    private double j = 1.0D;
    private double k = 1.0D;
    private int m = 12;
    private int n = 4;
    private int[][] o;

    public WorldGenBigTree() {
    }

    private void a(final WorldContext worldContext) {
        final double g = 0.618D;
        this.f = (int) ((double) this.e * g);
        if (this.f >= this.e) {
            this.f = this.e - 1;
        }

        int i = (int) (1.382D + Math.pow(this.k * (double) this.e / 13.0D, 2.0D));
        if (i < 1) {
            i = 1;
        }

        int[][] aint = new int[i * this.e][4];
        int j = this.pos[1] + this.e - this.n;
        int k = 1;
        int l = this.pos[1] + this.f;
        int i1 = j - this.pos[1];

        aint[0][0] = this.pos[0];
        aint[0][1] = j;
        aint[0][2] = this.pos[2];
        aint[0][3] = l;
        --j;
        while (i1 >= 0) {
            int j1 = 0;
            float f = this.a(i1);
            if (!(f < 0.0F)) {
                for (double d0 = 0.5D; j1 < i; ++j1) {
                    double d1 = this.j * (double) f * ((double) this.random.nextFloat() + 0.328D);
                    double d2 = (double) this.random.nextFloat() * 2.0D * 3.14159D;
                    int k1 = MathHelper.floor(d1 * Math.sin(d2) + (double) this.pos[0] + d0);
                    int l1 = MathHelper.floor(d1 * Math.cos(d2) + (double) this.pos[2] + d0);
                    int[] aint1 = new int[]{k1, j, l1};
                    int[] aint2 = new int[]{k1, j + this.n, l1};

                    if (this.a(worldContext, aint1, aint2) == -1) {
                        int[] aint3 = new int[]{this.pos[0], this.pos[1], this.pos[2]};
                        double d3 = Math.sqrt(Math.pow(Math.abs(this.pos[0] - aint1[0]), 2.0D) + Math.pow(Math.abs(this.pos[2] - aint1[2]), 2.0D));
                        final double i2 = 0.381D;
                        double d4 = d3 * i2;
                        if ((double) aint1[1] - d4 > (double) l) {
                            aint3[1] = l;
                        } else {
                            aint3[1] = (int) ((double) aint1[1] - d4);
                        }

                        if (this.a(worldContext, aint3, aint1) == -1) {
                            aint[k][0] = k1;
                            aint[k][1] = j;
                            aint[k][2] = l1;
                            aint[k][3] = aint3[1];
                            ++k;
                        }
                    }
                }
            }

            --j;
            --i1;
        }

        this.o = new int[k][4];
        System.arraycopy(aint, 0, this.o, 0, k);
    }

    private void a(final WorldContext worldContext, int i, int j, int k, float f, byte b0, Block l) {
        int i1 = (int) ((double) f + 0.618D);
        byte b1 = a[b0];
        byte b2 = a[b0 + 3];
        int[] aint = new int[]{i, j, k};
        int[] aint1 = new int[]{0, 0, 0};
        int j1 = -i1;
        int k1 = -i1;

        for (aint1[b0] = aint[b0]; j1 <= i1; ++j1) {
            aint1[b1] = aint[b1] + j1;
            k1 = -i1;

            while (k1 <= i1) {
                double d0 = Math.sqrt(Math.pow((double) Math.abs(j1) + 0.5D, 2.0D) + Math.pow((double) Math.abs(k1) + 0.5D, 2.0D));

                if (d0 > (double) f) {
                    ++k1;
                } else {
                    aint1[b2] = aint[b2] + k1;
                    Block l1 = worldContext.getBlock(aint1[0], aint1[1], aint1[2]);
                    if (l1.isAir() || BlockConstants.isLeaves(l1)) {
                        worldContext.setBlock(aint1[0], aint1[1], aint1[2], l);
                    }

                    ++k1;
                }
            }
        }
    }

    private float a(int i) {
        if ((double) i < (double) ((float) this.e) * 0.3D) {
            return -1.618F;
        } else {
            float f = (float) this.e / 2.0F;
            float f1 = (float) this.e / 2.0F - (float) i;
            float f2;

            if (f1 == 0.0F) {
                f2 = f;
            } else if (Math.abs(f1) >= f) {
                f2 = 0.0F;
            } else {
                f2 = (float) Math.sqrt(Math.pow(Math.abs(f), 2.0D) - Math.pow(Math.abs(f1), 2.0D));
            }

            f2 *= 0.5F;
            return f2;
        }
    }

    private float b(int i) {
        return i >= 0 && i < this.n ? (i != 0 && i != this.n - 1 ? 3.0F : 2.0F) : -1.0F;
    }

    private void a(final WorldContext worldContext, final int x, final int y, final int z) {
        int l = y;
        for (int i1 = y + this.n; l < i1; ++l) {
            float f = this.b(l - y);
            this.a(worldContext, x, l, z, f, (byte) 1, Block.OAK_LEAVES);
        }
    }

    private void a(final WorldContext worldContext, final int[] aint, final int[] aint1, final Block block) {
        int[] aint2 = new int[]{0, 0, 0};
        byte b0 = 0;

        byte b1;
        for (b1 = 0; b0 < 3; ++b0) {
            aint2[b0] = aint1[b0] - aint[b0];
            if (Math.abs(aint2[b0]) > Math.abs(aint2[b1])) {
                b1 = b0;
            }
        }

        if (aint2[b1] != 0) {
            byte b2 = a[b1];
            byte b3 = a[b1 + 3];
            byte b4;

            if (aint2[b1] > 0) {
                b4 = 1;
            } else {
                b4 = -1;
            }

            double d0 = (double) aint2[b2] / (double) aint2[b1];
            double d1 = (double) aint2[b3] / (double) aint2[b1];
            int[] aint3 = new int[]{0, 0, 0};
            int j = 0;
            for (int k = aint2[b1] + b4; j != k; j += b4) {
                aint3[b1] = MathHelper.floor((double) (aint[b1] + j) + 0.5D);
                aint3[b2] = MathHelper.floor((double) aint[b2] + (double) j * d0 + 0.5D);
                aint3[b3] = MathHelper.floor((double) aint[b3] + (double) j * d1 + 0.5D);
                worldContext.setBlock(aint3[0], aint3[1], aint3[2], block);
            }
        }
    }

    private boolean c(int i) {
        return (double) i >= (double) this.e * 0.2D;
    }

    private int a(final WorldContext worldContext, final int[] aint, final int[] aint1) {
        int[] aint2 = new int[]{0, 0, 0};
        byte b0 = 0;
        byte b1;
        for (b1 = 0; b0 < 3; ++b0) {
            aint2[b0] = aint1[b0] - aint[b0];
            if (Math.abs(aint2[b0]) > Math.abs(aint2[b1])) {
                b1 = b0;
            }
        }

        if (aint2[b1] == 0) {
            return -1;
        } else {
            byte b2 = a[b1];
            byte b3 = a[b1 + 3];
            byte b4;

            if (aint2[b1] > 0) {
                b4 = 1;
            } else {
                b4 = -1;
            }

            double d0 = (double) aint2[b2] / (double) aint2[b1];
            double d1 = (double) aint2[b3] / (double) aint2[b1];
            int[] aint3 = new int[]{0, 0, 0};
            int i = 0;
            int j;
            for (j = aint2[b1] + b4; i != j; i += b4) {
                aint3[b1] = aint[b1] + i;
                aint3[b2] = MathHelper.floor((double) aint[b2] + (double) i * d0);
                aint3[b3] = MathHelper.floor((double) aint[b3] + (double) i * d1);
                Block k = worldContext.getBlock(aint3[0], aint3[1], aint3[2]);
                if (!k.isAir() && !BlockConstants.isLeaves(k)) {
                    break;
                }
            }

            return i == j ? -1 : Math.abs(i);
        }
    }

    private boolean d(final WorldContext worldContext) {
        final int[] aint = new int[]{this.pos[0], this.pos[1], this.pos[2]};
        final int[] aint1 = new int[]{this.pos[0], this.pos[1] + this.e - 1, this.pos[2]};
        final Block block = worldContext.getBlock(this.pos[0], this.pos[1] - 1, this.pos[2]);
        if (block != Block.GRASS_BLOCK && block != Block.DIRT) {
            return false;
        } else {
            int j = this.a(worldContext, aint, aint1);
            if (j == -1) {
                return true;
            } else if (j < 6) {
                return false;
            } else {
                this.e = j;
                return true;
            }
        }
    }

    @Override
    public void scale(double scaleX, double scaleY, double scaleZ) {
        this.m = (int) (scaleX * 12.0D);
        if (scaleX > 0.5D) {
            this.n = 5;
        }

        this.j = scaleY;
        this.k = scaleZ;
    }

    @Override
    public boolean populate(final WorldContext worldContext, final int centerX, final int centerY, final int centerZ) {
        this.random.setSeed(this.random.nextLong());
        this.pos[0] = centerX;
        this.pos[1] = centerY;
        this.pos[2] = centerZ;
        if (this.e == 0) {
            this.e = 5 + this.random.nextInt(this.m);
        }

        if (!this.d(worldContext)) {
            return false;
        } else {
            this.a(worldContext);

            int s = 0;
            for (int j = this.o.length; s < j; ++s) {
                int k = this.o[s][0];
                int l = this.o[s][1];
                int i1 = this.o[s][2];
                this.a(worldContext, k, l, i1);
            }

            int v = this.pos[0];
            int a = this.pos[1];
            int o = this.pos[1] + this.f;
            int e = this.pos[2];
            int[] aints = new int[]{v, a, e};
            int[] aints1 = new int[]{v, o, e};
            this.a(worldContext, aints, aints1, Block.OAK_LOG);

            int i = 0;
            int j = this.o.length;
            for (int[] aint = new int[]{this.pos[0], this.pos[1], this.pos[2]}; i < j; ++i) {
                int[] aint1 = this.o[i];
                int[] aint2 = new int[]{aint1[0], aint1[1], aint1[2]};
                aint[1] = aint1[3];
                int k = aint[1] - this.pos[1];
                if (this.c(k)) {
                    this.a(worldContext, aint, aint2, Block.OAK_LOG);
                }
            }

            return true;
        }
    }
}

package dev.emortal.legacygeneration.b1_8_1.populator;

import dev.emortal.legacygeneration.util.MathHelper;
import dev.emortal.legacygeneration.util.WorldContext;
import dev.emortal.legacygeneration.util.WorldGenerator;
import net.minestom.server.instance.block.Block;

import java.util.Random;

public class BigTreeFeature implements WorldGenerator {
    static final byte[] axisConversionArray = new byte[]{2, 0, 0, 1, 2, 1};
    Random rnd = new Random();
    WorldContext thisLevel;
    int[] origin = new int[]{0, 0, 0};
    int height = 0;
    int trunkHeight;
    double trunkHeightScale = 0.618;
    double branchSlope = 0.381;
    double widthScale = 1.0;
    double foliageDensity = 1.0;
    int trunkWidth = 1;
    int heightVariance = 12;
    int foliageHeight = 4;
    int[][] foliageCoords;

    void doPlace() {
        this.trunkHeight = (int) (this.height * this.trunkHeightScale);
        if (this.trunkHeight >= this.height) {
            this.trunkHeight = this.height - 1;
        }

        int var1 = (int) (1.382 + Math.pow(this.foliageDensity * this.height / 13.0, 2.0));
        if (var1 < 1) {
            var1 = 1;
        }

        int[][] var2 = new int[var1 * this.height][4];
        int var3 = this.origin[1] + this.height - this.foliageHeight;
        int var4 = 1;
        int var5 = this.origin[1] + this.trunkHeight;
        int var6 = var3 - this.origin[1];
        var2[0][0] = this.origin[0];
        var2[0][1] = var3;
        var2[0][2] = this.origin[2];
        var2[0][3] = var5;
        var3--;

        while (var6 >= 0) {
            int var7 = 0;
            float var8 = this.crossSection(var6);
            if (var8 < 0.0F) {
                var3--;
                var6--;
            } else {
                for (double var9 = 0.5; var7 < var1; var7++) {
                    double var11 = this.widthScale * (var8 * (this.rnd.nextFloat() + 0.328));
                    double var13 = this.rnd.nextFloat() * 2.0 * 3.14159;
                    int var15 = MathHelper.floor(var11 * Math.sin(var13) + this.origin[0] + var9);
                    int var16 = MathHelper.floor(var11 * Math.cos(var13) + this.origin[2] + var9);
                    int[] var17 = new int[]{var15, var3, var16};
                    int[] var18 = new int[]{var15, var3 + this.foliageHeight, var16};
                    if (this.m_89118737(var17, var18) == -1) {
                        int[] var19 = new int[]{this.origin[0], this.origin[1], this.origin[2]};
                        double var20 = Math.sqrt(Math.pow(Math.abs(this.origin[0] - var17[0]), 2.0) + Math.pow(Math.abs(this.origin[2] - var17[2]), 2.0));
                        double var22 = var20 * this.branchSlope;
                        if (var17[1] - var22 > var5) {
                            var19[1] = var5;
                        } else {
                            var19[1] = (int) (var17[1] - var22);
                        }

                        if (this.m_89118737(var19, var17) == -1) {
                            var2[var4][0] = var15;
                            var2[var4][1] = var3;
                            var2[var4][2] = var16;
                            var2[var4][3] = var19[1];
                            var4++;
                        }
                    }
                }

                var3--;
                var6--;
            }
        }

        this.foliageCoords = new int[var4][4];
        System.arraycopy(var2, 0, this.foliageCoords, 0, var4);
    }

    void m_10273293(int i, int i2, int i3, float f, byte b, int i4) {
        int var7 = (int) (f + 0.618);
        byte var8 = axisConversionArray[b];
        byte var9 = axisConversionArray[b + 3];
        int[] var10 = new int[]{i, i2, i3};
        int[] var11 = new int[]{0, 0, 0};
        int var12 = -var7;
        int var13 = -var7;

        for (var11[b] = var10[b]; var12 <= var7; var12++) {
            var11[var8] = var10[var8] + var12;
            var13 = -var7;

            while (var13 <= var7) {
                double var15 = Math.sqrt(Math.pow(Math.abs(var12) + 0.5, 2.0) + Math.pow(Math.abs(var13) + 0.5, 2.0));
                if (var15 > f) {
                    var13++;
                } else {
                    var11[var9] = var10[var9] + var13;
                    int var14 = this.thisLevel.getBlock(var11[0], var11[1], var11[2]).id();
                    if (var14 != 0 && var14 != 18) {
                        var13++;
                    } else {
                        this.thisLevel.setBlock(var11[0], var11[1], var11[2], Block.fromBlockId(i4));
                        var13++;
                    }
                }
            }
        }
    }

    float crossSection(int i) {
        if (i < this.height * 0.3) {
            return -1.618F;
        } else {
            float var2 = this.height / 2.0F;
            float var3 = this.height / 2.0F - i;
            float var4;
            if (var3 == 0.0F) {
                var4 = var2;
            } else if (Math.abs(var3) >= var2) {
                var4 = 0.0F;
            } else {
                var4 = (float) Math.sqrt(Math.pow(Math.abs(var2), 2.0) - Math.pow(Math.abs(var3), 2.0));
            }

            return var4 * 0.5F;
        }
    }

    float m_98677669(int i) {
        if (i < 0 || i >= this.foliageHeight) {
            return -1.0F;
        } else {
            return i != 0 && i != this.foliageHeight - 1 ? 3.0F : 2.0F;
        }
    }

    void m_72179436(int i, int i2, int i3) {
        int var4 = i2;

        for (int var5 = i2 + this.foliageHeight; var4 < var5; var4++) {
            float var6 = this.m_98677669(var4 - i2);
            this.m_10273293(i, var4, i3, var6, (byte) 1, 18);
        }
    }

    void makeLimb(int[] coords, int[] steps, Block block) {
        int[] var4 = new int[]{0, 0, 0};
        byte var5 = 0;

        byte var6;
        for (var6 = 0; var5 < 3; var5++) {
            var4[var5] = steps[var5] - coords[var5];
            if (Math.abs(var4[var5]) > Math.abs(var4[var6])) {
                var6 = var5;
            }
        }

        if (var4[var6] != 0) {
            byte var7 = axisConversionArray[var6];
            byte var8 = axisConversionArray[var6 + 3];
            byte var9;
            if (var4[var6] > 0) {
                var9 = 1;
            } else {
                var9 = -1;
            }

            double var10 = (double) var4[var7] / var4[var6];
            double var12 = (double) var4[var8] / var4[var6];
            int[] var14 = new int[]{0, 0, 0};
            byte var15 = 0;

            for (int var16 = var4[var6] + var9; var15 != var16; var15 += var9) {
                var14[var6] = MathHelper.floor(coords[var6] + var15 + 0.5);
                var14[var7] = MathHelper.floor(coords[var7] + var15 * var10 + 0.5);
                var14[var8] = MathHelper.floor(coords[var8] + var15 * var12 + 0.5);
                this.thisLevel.setBlock(var14[0], var14[1], var14[2], block);
            }
        }
    }

    void makeFoliage() {
        int var1 = 0;
        for (int var2 = this.foliageCoords.length; var1 < var2; var1++) {
            int var3 = this.foliageCoords[var1][0];
            int var4 = this.foliageCoords[var1][1];
            int var5 = this.foliageCoords[var1][2];
            this.m_72179436(var3, var4, var5);
        }
    }

    boolean trimBranches(int i) {
        return !(i < this.height * 0.2);
    }

    void makeTrunk() {
        int var1 = this.origin[0];
        int var2 = this.origin[1];
        int var3 = this.origin[1] + this.trunkHeight;
        int var4 = this.origin[2];
        int[] var5 = new int[]{var1, var2, var4};
        int[] var6 = new int[]{var1, var3, var4};
        this.makeLimb(var5, var6, Block.OAK_LOG);
        if (this.trunkWidth == 2) {
            var5[0]++;
            var6[0]++;
            this.makeLimb(var5, var6, Block.OAK_LOG);
            var5[2]++;
            var6[2]++;
            this.makeLimb(var5, var6, Block.OAK_LOG);
            var5[0] += -1;
            var6[0] += -1;
            this.makeLimb(var5, var6, Block.OAK_LOG);
        }
    }

    void makeBranches() {
        int var1 = 0;
        int var2 = this.foliageCoords.length;
        for (int[] var3 = new int[]{this.origin[0], this.origin[1], this.origin[2]}; var1 < var2; var1++) {
            int[] var4 = this.foliageCoords[var1];
            int[] var5 = new int[]{var4[0], var4[1], var4[2]};
            var3[1] = var4[3];
            int var6 = var3[1] - this.origin[1];
            if (this.trimBranches(var6)) {
                this.makeLimb(var3, var5, Block.OAK_LOG);
            }
        }
    }

    int m_89118737(int[] intArray, int[] intArray2) {
        int[] var3 = new int[]{0, 0, 0};
        byte var4 = 0;

        byte var5;
        for (var5 = 0; var4 < 3; var4++) {
            var3[var4] = intArray2[var4] - intArray[var4];
            if (Math.abs(var3[var4]) > Math.abs(var3[var5])) {
                var5 = var4;
            }
        }

        if (var3[var5] == 0) {
            return -1;
        } else {
            byte var6 = axisConversionArray[var5];
            byte var7 = axisConversionArray[var5 + 3];
            byte var8;
            if (var3[var5] > 0) {
                var8 = 1;
            } else {
                var8 = -1;
            }

            double var9 = (double) var3[var6] / var3[var5];
            double var11 = (double) var3[var7] / var3[var5];
            int[] var13 = new int[]{0, 0, 0};
            byte var14 = 0;

            int var15;
            for (var15 = var3[var5] + var8; var14 != var15; var14 += var8) {
                var13[var5] = intArray[var5] + var14;
                var13[var6] = MathHelper.floor(intArray[var6] + var14 * var9);
                var13[var7] = MathHelper.floor(intArray[var7] + var14 * var11);
                Block var16 = this.thisLevel.getBlock(var13[0], var13[1], var13[2]);
                if (!var16.isAir() && var16 != Block.OAK_LEAVES) {
                    break;
                }
            }

            return var14 == var15 ? -1 : Math.abs(var14);
        }
    }

    boolean m_40147907() {
        int[] var1 = new int[]{this.origin[0], this.origin[1], this.origin[2]};
        int[] var2 = new int[]{this.origin[0], this.origin[1] + this.height - 1, this.origin[2]};
        Block var3 = this.thisLevel.getBlock(this.origin[0], this.origin[1] - 1, this.origin[2]);
        if (var3 != Block.GRASS_BLOCK && var3 != Block.PODZOL) {
            return false;
        } else {
            int var4 = this.m_89118737(var1, var2);
            if (var4 == -1) {
                return true;
            } else if (var4 < 6) {
                return false;
            } else {
                this.height = var4;
                return true;
            }
        }
    }

    @Override
    public void scale(final double scaleX, final double scaleY, final double scaleZ) {
        this.heightVariance = (int) (scaleX * 12.0);
        if (scaleX > 0.5) {
            this.foliageHeight = 5;
        }

        this.widthScale = scaleY;
        this.foliageDensity = scaleZ;
    }

    @Override
    public boolean populate(final WorldContext worldContext, final int centerX, final int centerY, final int centerZ) {
        this.thisLevel = worldContext;
        long var6 = worldContext.random().nextLong();
        this.rnd.setSeed(var6);
        this.origin[0] = centerX;
        this.origin[1] = centerY;
        this.origin[2] = centerZ;
        if (this.height == 0) {
            this.height = 5 + this.rnd.nextInt(this.heightVariance);
        }

        if (!this.m_40147907()) {
            return false;
        } else {
            this.doPlace();
            this.makeFoliage();
            this.makeTrunk();
            this.makeBranches();
            return true;
        }
    }
}

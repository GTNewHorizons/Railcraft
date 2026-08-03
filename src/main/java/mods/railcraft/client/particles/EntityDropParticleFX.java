/*
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info This code is the property of CovertJaguar and may only be used
 * with explicit written permission unless otherwise specified on the license page at
 * http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.client.particles;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityDropParticleFX extends EntityFX {

    /**
     * The height of the current bob
     */
    private int bobTimer;

    public EntityDropParticleFX(World world, double x, double y, double z, float particleRed, float particleGreen,
            float particleBlue) {
        super(world, x, y, z, 0.0D, 0.0D, 0.0D);
        this.motionX = this.motionY = this.motionZ = 0.0D;

        this.particleRed = particleRed;
        this.particleGreen = particleGreen;
        this.particleBlue = particleBlue;

        this.setParticleTextureIndex(113);
        this.setSize(0.01F, 0.01F);
        this.particleGravity = 0.06F;
        this.bobTimer = 40;
        this.particleMaxAge = (int) (64.0D / (Math.random() * 0.8D + 0.2D));
        this.motionX = this.motionY = this.motionZ = 0.0D;
    }

    /**
     * Cheap ground emulation that avoids the block-by-block collision resolution of Entity.moveEntity and its
     * associated AxisAlignedBB allocations. The drop's terminal velocity can exceed one block per tick, so the
     * landing surface is detected by sweeping the vertical path.
     */
    @Override
    public void moveEntity(double x, double y, double z) {
        int prevFeetY = MathHelper.floor_double(this.posY - 0.005D);
        this.posX += x;
        this.posY += y;
        this.posZ += z;

        if (y < 0.0D) {
            int feetY = MathHelper.floor_double(this.posY - 0.005D);
            int bx = MathHelper.floor_double(this.posX);
            int bz = MathHelper.floor_double(this.posZ);
            for (int by = prevFeetY; by >= feetY; --by) {
                if (this.worldObj.getBlock(bx, by, bz).getMaterial().blocksMovement()) {
                    this.posY = (double) by + 1.005D;
                    this.motionY = 0.0D;
                    this.onGround = true;
                    return;
                }
            }
        }

        this.onGround = false;
    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        this.motionY -= (double) this.particleGravity;

        if (this.bobTimer-- > 0) {
            this.motionX *= 0.02D;
            this.motionY *= 0.02D;
            this.motionZ *= 0.02D;
            this.setParticleTextureIndex(113);
        } else this.setParticleTextureIndex(112);

        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.9800000190734863D;
        this.motionY *= 0.9800000190734863D;
        this.motionZ *= 0.9800000190734863D;

        if (this.particleMaxAge-- <= 0) this.setDead();

        if (this.onGround) {
            this.setParticleTextureIndex(114);

            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
        }

        Material material = this.worldObj.getBlock(
                MathHelper.floor_double(this.posX),
                MathHelper.floor_double(this.posY),
                MathHelper.floor_double(this.posZ)).getMaterial();

        if (material.isLiquid() || material.isSolid()) {
            double d0 = (double) ((float) (MathHelper.floor_double(this.posY) + 1) - BlockLiquid.getLiquidHeightPercent(
                    this.worldObj.getBlockMetadata(
                            MathHelper.floor_double(this.posX),
                            MathHelper.floor_double(this.posY),
                            MathHelper.floor_double(this.posZ))));

            if (this.posY < d0) this.setDead();
        }
    }
}

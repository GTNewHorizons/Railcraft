/*
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info This code is the property of CovertJaguar and may only be used
 * with explicit written permission unless otherwise specified on the license page at
 * http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.client.particles;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
@SideOnly(Side.CLIENT)
public class EntityFireSparkFX extends EntityFX {

    private final float lavaParticleScale;
    private final double endX, endY, endZ;
    private final double maxDist;
    private final double dirX, dirY, dirZ;

    public EntityFireSparkFX(World world, double x, double y, double z, double endX, double endY, double endZ) {
        super(world, x, y, z, 0, 0, 0);
        this.endX = endX;
        this.endY = endY;
        this.endZ = endZ;

        maxDist = getDistanceSq(endX, endY, endZ);
        double dx = x - endX;
        double dy = y - endY;
        double dz = z - endZ;
        double length = MathHelper.sqrt_double(dx * dx + dy * dy + dz * dz);
        if (length > 0.0D) {
            this.dirX = dx / length;
            this.dirY = dy / length;
            this.dirZ = dz / length;
        } else {
            this.dirX = this.dirY = this.dirZ = 0.0D;
        }

        multipleParticleScaleBy(0.5f);

        this.particleRed = this.particleGreen = this.particleBlue = 1.0F;
        this.particleScale *= this.rand.nextFloat() * 2.0F + 0.2F;
        this.lavaParticleScale = this.particleScale;
        this.particleMaxAge = 2000;
        this.noClip = true;
        this.setParticleTextureIndex(49);
    }

    private void calculateVector(double dist) {
        this.motionX = this.dirX * 0.1f;
        this.motionY = this.dirY * 0.1f + 0.2 * (dist / maxDist);
        this.motionZ = this.dirZ * 0.1f;
    }

    @Override
    public int getBrightnessForRender(float par1) {
        int brightness = super.getBrightnessForRender(par1);
        short short1 = 240;
        int j = brightness >> 16 & 255;
        return short1 | j << 16;
    }

    /**
     * Gets how bright this entity is.
     */
    @Override
    public float getBrightness(float par1) {
        return 1.0F;
    }

    @Override
    public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6,
            float par7) {
        float f6 = ((float) this.particleAge + par2) / (float) this.particleMaxAge;
        this.particleScale = this.lavaParticleScale * (1.0F - f6 * f6);
        super.renderParticle(par1Tessellator, par2, par3, par4, par5, par6, par7);
    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.particleAge++ >= this.particleMaxAge) {
            this.setDead();
            return;
        }

        double dist = getDistanceSq(endX, endY, endZ);
        if (dist <= 0.1) {
            this.setDead();
            return;
        }

        calculateVector(dist);

        this.moveEntity(this.motionX, this.motionY, this.motionZ);
    }
}

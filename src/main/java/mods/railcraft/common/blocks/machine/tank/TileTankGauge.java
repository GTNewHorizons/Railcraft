/*
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info This code is the property of CovertJaguar and may only be used
 * with explicit written permission unless otherwise specified on the license page at
 * http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.common.blocks.machine.tank;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.IIcon;
import net.minecraft.world.EnumSkyBlock;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.util.misc.MiscTools;
import mods.railcraft.common.util.misc.Timer;

/**
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public class TileTankGauge extends TileTankBase {

    private int lightValue = 0;
    private final Timer timer = new Timer();

    public TileTankGauge() {
        super();
    }

    public TileTankGauge(TankMaterial material) {
        super(material);
    }

    @Override
    public Machine getMachineType() {
        return Tanks.getGauge(material);
    }

    @Override
    public IIcon getIcon(int side) {
        if (!isStructureValid() || getPattern() == null) {
            return getTextureFromMachine(side);
        }

        int px = getPatternPositionX();
        int py = getPatternPositionY();
        int pz = getPatternPositionZ();

        ForgeDirection s = ForgeDirection.getOrientation(side);
        char markerSide = getPattern().getPatternMarkerChecked(
                MiscTools.getXOnSide(px, s),
                MiscTools.getYOnSide(py, s),
                MiscTools.getZOnSide(pz, s));

        if (!isMapPositionOtherBlock(markerSide)) {
            return getTextureFromMachine(9);
        }

        if (s == ForgeDirection.UP || s == ForgeDirection.DOWN) {
            int markerTop = getPattern().getPatternMarkerChecked(px, py + 1, pz);
            if (markerTop == 'A' || markerTop == 'O') {
                Block up = worldObj.getBlock(xCoord, yCoord, zCoord - 1);
                Block down = worldObj.getBlock(xCoord, yCoord, zCoord + 1);
                return getTextureBasedOnNeighbors(up, down);
            }
            return getTextureFromMachine(0);
        }

        Block up = worldObj.getBlock(xCoord, yCoord + 1, zCoord);
        Block down = worldObj.getBlock(xCoord, yCoord - 1, zCoord);
        return getTextureBasedOnNeighbors(up, down);
    }

    private IIcon getTextureBasedOnNeighbors(Block up, Block down) {
        if (up == getBlockType() && down == getBlockType()) {
            return getTextureFromMachine(7);
        } else if (up == getBlockType()) {
            return getTextureFromMachine(8);
        } else if (down == getBlockType()) {
            return getTextureFromMachine(6);
        }
        return getTextureFromMachine(0);
    }

    private IIcon getTextureFromMachine(int index) {
        return getMachineType().getTexture(index);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(Random rand) {
        int oldLightValue = lightValue;
        if (timer.hasTriggered(worldObj, 80) && isStructureValid()) updateLightValue();
        if (oldLightValue != lightValue) worldObj.updateLightByType(EnumSkyBlock.Block, xCoord, yCoord, zCoord);
    }

    @Override
    public int getLightValue() {
        return lightValue;
    }

    private void updateLightValue() {
        Fluid fluid = getTank().getFluidType();
        lightValue = fluid != null ? fluid.getLuminosity() : 0;
    }
}

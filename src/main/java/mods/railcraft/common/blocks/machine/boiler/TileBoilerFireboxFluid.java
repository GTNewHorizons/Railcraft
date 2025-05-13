/*
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info This code is the property of CovertJaguar and may only be used
 * with explicit written permission unless otherwise specified on the license page at
 * http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.common.blocks.machine.boiler;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import mods.railcraft.api.fuel.FuelManager;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.Machines;
import mods.railcraft.common.blocks.machine.MultiBlockPattern;
import mods.railcraft.common.blocks.machine.TileMultiBlock;
import mods.railcraft.common.fluids.FluidHelper;
import mods.railcraft.common.fluids.FluidItemHelper;
import mods.railcraft.common.fluids.Fluids;
import mods.railcraft.common.fluids.tanks.BoilerFuelTank;
import mods.railcraft.common.gui.EnumGui;
import mods.railcraft.common.gui.GuiHandler;
import mods.railcraft.common.util.inventory.InvTools;
import mods.railcraft.common.util.steam.FluidFuelProvider;

/**
 * @author CovertJaguar <http://www.railcraft.info>
 */
public class TileBoilerFireboxFluid extends TileBoilerFirebox {

    private static final int TANK_FUEL = 2;
    private static final int[] SLOTS = InvTools.buildSlotArray(0, 2);
    protected final BoilerFuelTank tankFuel = new BoilerFuelTank(FluidHelper.BUCKET_VOLUME * 16, this);

    public TileBoilerFireboxFluid() {
        super(2);
        tankManager.add(tankFuel);
        boiler.setFuelProvider(new FluidFuelProvider(tankFuel));
    }

    public static void placeFluidBoiler(World world, int x, int y, int z, int width, int height, boolean highPressure,
            int water, FluidStack fuel) {
        for (MultiBlockPattern pattern : TileBoiler.patterns) {
            if (pattern.getPatternHeight() - 3 == height && pattern.getPatternWidthX() - 2 == width) {
                Map<Character, Integer> blockMapping = new HashMap<Character, Integer>();
                blockMapping.put('F', Block.getIdFromBlock(Machines.BOILER_FIREBOX_LIQUID.getBlock()));
                blockMapping.put(
                        'H',
                        highPressure ? Block.getIdFromBlock(Machines.BOILER_TANK_HIGH_PRESSURE.getBlock())
                                : Block.getIdFromBlock(Machines.BOILER_TANK_LOW_PRESSURE.getBlock()));
                TileEntity tile = pattern.placeStructureIds(world, x, y, z, blockMapping);
                if (tile instanceof TileBoilerFireboxFluid) {
                    TileBoilerFireboxFluid master = (TileBoilerFireboxFluid) tile;
                    master.tankWater.setFluid(Fluids.WATER.get(water));
                    master.tankFuel.setFluid(fuel);
                }
                return;
            }
        }
    }

    @Override
    public Machine getMachineType() {
        return Machines.BOILER_FIREBOX_LIQUID;
    }

    @Override
    public boolean openGui(EntityPlayer player) {
        // ItemStack current = player.getCurrentEquippedItem();
        // if (current != null && current.itemID == getBlockId()) {
        // return false;
        // }
        TileMultiBlock mBlock = getMasterBlock();
        if (mBlock != null) {
            GuiHandler.openGui(EnumGui.BOILER_LIQUID, player, worldObj, mBlock.xCoord, mBlock.yCoord, mBlock.zCoord);
            return true;
        }
        return false;
    }

    @Override
    protected boolean handleClick(EntityPlayer player, int side) {
        if (FluidHelper.handleRightClick(this, ForgeDirection.getOrientation(side), player, true, false)) return true;
        return super.handleClick(player, side);
    }

    @Override
    protected void process() {}

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        if (fluid == null) return false;
        if (FuelManager.getBoilerFuelValue(fluid) > 0) return true;
        return super.canFill(from, fluid);
    }

    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        if (Fluids.WATER.is(resource)) return fill(TANK_WATER, resource, doFill);
        return fill(TANK_FUEL, resource, doFill);
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int var1) {
        return SLOTS;
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack stack, int side) {
        return isItemValidForSlot(slot, stack);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack stack, int side) {
        return slot == SLOT_LIQUID_OUTPUT;
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        if (!isStructureValid()) return false;
        if (slot == SLOT_LIQUID_INPUT) {
            Fluid fluid = FluidItemHelper.getFluidInContainer(stack);
            if (fluid == null) return false;
            return Fluids.WATER.is(fluid) || FuelManager.getBoilerFuelValue(fluid) > 0;
        }
        return false;
    }
}

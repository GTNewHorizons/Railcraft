/*
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info This code is the property of CovertJaguar and may only be used
 * with explicit written permission unless otherwise specified on the license page at
 * http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.common.blocks.machine.alpha;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;

import mods.railcraft.common.blocks.RailcraftBlocks;
import mods.railcraft.common.blocks.machine.IEnumMachine;
import mods.railcraft.common.blocks.machine.MultiBlockPattern;
import mods.railcraft.common.blocks.machine.TileMultiBlock;
import mods.railcraft.common.blocks.machine.TileTank;
import mods.railcraft.common.fluids.FluidHelper;
import mods.railcraft.common.fluids.FluidItemHelper;
import mods.railcraft.common.fluids.Fluids;
import mods.railcraft.common.fluids.TankManager;
import mods.railcraft.common.fluids.tanks.FilteredTank;
import mods.railcraft.common.gui.EnumGui;
import mods.railcraft.common.gui.GuiHandler;
import mods.railcraft.common.gui.slots.SlotWaterOrEmpty;
import mods.railcraft.common.plugins.forge.LocalizationPlugin;
import mods.railcraft.common.util.inventory.InvTools;
import mods.railcraft.common.util.inventory.wrappers.InventoryMapper;
import mods.railcraft.common.util.misc.Game;
import mods.railcraft.common.util.misc.ITileFilter;

/**
 * @author CovertJaguar <http://www.railcraft.info>
 */
public class TileTankWater extends TileTank implements ISidedInventory {

    private static final int OUTPUT_RATE = 40;
    private static final int TANK_CAPACITY = FluidHelper.BUCKET_VOLUME * 400;
    private static final int REFILL_INTERVAL = 8;
    private static final float REFILL_RATE = 10f;
    private static final float REFILL_PENALTY_INSIDE = 0.5f;
    private static final float REFILL_PENALTY_SNOW = 0.5f;
    private static final float REFILL_BOOST_RAIN = 3.0f;
    private static final byte REFILL_RATE_MIN = 1;
    private static final int SLOT_INPUT = 0;
    private static final int SLOT_OUTPUT = 1;
    private static final int[] SLOTS = InvTools.buildSlotArray(0, 2);

    private static final Map<Integer, ForgeDirection[]> LIQUID_OUTPUTS = new HashMap<>();
    private static final byte X_SHIFT = 4;
    private static final byte Y_SHIFT = 2;

    private static final ITileFilter LIQUID_OUTPUT_FILTER = new ITileFilter() {

        @Override
        public boolean matches(TileEntity tile) {
            if (tile instanceof TileTank) return false;
            else if (tile instanceof IFluidHandler) return true;
            return false;
        }
    };
    private static final List<MultiBlockPattern> patterns = new ArrayList<MultiBlockPattern>();
    private final FilteredTank tank;

    static {
        char[][][] map = {
                { { 'O', 'O', 'O', 'O', 'O' }, { 'O', 'O', 'O', 'O', 'O' }, { 'O', 'O', 'O', 'O', 'O' },
                        { 'O', 'O', 'O', 'O', 'O' }, { 'O', 'O', 'O', 'O', 'O' } },
                { { 'O', 'O', 'O', 'O', 'O' }, { 'O', 'B', 'B', 'B', 'O' }, { 'O', 'B', 'B', 'B', 'O' },
                        { 'O', 'B', 'B', 'B', 'O' }, { 'O', 'O', 'O', 'O', 'O' } },
                { { 'O', 'O', 'O', 'O', 'O' }, { 'O', 'B', 'B', 'B', 'O' }, { 'O', 'B', 'A', 'B', 'O' },
                        { 'O', 'B', 'B', 'B', 'O' }, { 'O', 'O', 'O', 'O', 'O' } },
                { { 'O', 'O', 'O', 'O', 'O' }, { 'O', 'B', 'B', 'B', 'O' }, { 'O', 'B', 'B', 'B', 'O' },
                        { 'O', 'B', 'B', 'B', 'O' }, { 'O', 'O', 'O', 'O', 'O' } },
                { { 'O', 'O', 'O', 'O', 'O' }, { 'O', 'O', 'O', 'O', 'O' }, { 'O', 'O', 'O', 'O', 'O' },
                        { 'O', 'O', 'O', 'O', 'O' }, { 'O', 'O', 'O', 'O', 'O' } }, };
        patterns.add(new MultiBlockPattern(map, 2, 1, 2));
    }

    private IInventory invInput = new InventoryMapper(this, SLOT_INPUT, 1);
    private IInventory invOutput = new InventoryMapper(this, SLOT_OUTPUT, 1);

    public TileTankWater() {
        super("railcraft.gui.tank.water", 2, patterns);
        tank = new FilteredTank(TANK_CAPACITY, Fluids.WATER.get(), this);
        tankManager.add(tank);
    }

    public static void placeWaterTank(World world, int x, int y, int z, int water) {
        for (MultiBlockPattern pattern : TileTankWater.patterns) {
            Map<Character, Integer> blockMapping = new HashMap<Character, Integer>();
            blockMapping.put('B', EnumMachineAlpha.TANK_WATER.ordinal());
            TileEntity tile = pattern
                    .placeStructure(world, x, y, z, RailcraftBlocks.getBlockMachineAlpha(), blockMapping);
            if (tile instanceof TileTankWater) {
                TileTankWater master = (TileTankWater) tile;
                master.tank.setFluid(Fluids.WATER.get(water));
            }
            return;
        }
    }

    @Override
    public IEnumMachine getMachineType() {
        return EnumMachineAlpha.TANK_WATER;
    }

    @Override
    public String getTitle() {
        return LocalizationPlugin.translate("railcraft.gui.tank.water");
    }

    @Override
    public Slot getInputSlot(IInventory inv, int id, int x, int y) {
        return new SlotWaterOrEmpty(inv, id, x, y);
    }

    @Override
    public boolean blockActivated(EntityPlayer player, int side) {
        if (Game.isHost(worldObj)) {
            if (isStructureValid() && FluidHelper
                    .handleRightClick(getTankManager(), ForgeDirection.getOrientation(side), player, true, true))
                return true;
        } else if (FluidItemHelper.isContainer(player.inventory.getCurrentItem())) return true;
        return super.blockActivated(player, side);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (Game.isHost(getWorld())) {
            if (isMaster()) {
                if (worldObj.provider.dimensionId != -1 && clock % REFILL_INTERVAL == 0) {
                    float rate = REFILL_RATE;
                    BiomeGenBase biome = worldObj.getBiomeGenForCoords(xCoord, zCoord);
                    float humidity = biome.rainfall;
                    rate *= humidity;
                    // String debug = "Biome=" + biome.biomeName + ", Humidity=" + humidity;

                    boolean outside = false;
                    outer: for (int x = xCoord - 1; x <= xCoord + 1; x++) {
                        for (int z = zCoord - 1; z <= zCoord + 1; z++) {
                            // System.out.println(x + ", " + (yCoord + 3) + ", " + z);
                            if (worldObj.canBlockSeeTheSky(x, yCoord + 3, z)) {
                                outside = true;
                                break outer;
                            }
                        }
                    }

                    // debug += ", Outside=" + outside;
                    if (!outside) rate *= REFILL_PENALTY_INSIDE;
                    else if (worldObj.isRaining()) if (biome.getEnableSnow()) rate *= REFILL_PENALTY_SNOW; // debug +=
                                                                                                           // ",
                                                                                                           // Snow=true";
                    else rate *= REFILL_BOOST_RAIN; // debug += ", Rain=true";
                    int rateFinal = MathHelper.floor_float(rate);
                    if (rateFinal < REFILL_RATE_MIN) rateFinal = REFILL_RATE_MIN;
                    // debug += ", Refill=" + rateFinal;
                    // System.out.println(debug);

                    FluidStack fillStack = Fluids.WATER.get(rateFinal);
                    fill(ForgeDirection.UP, fillStack, true);
                }

                if (clock % FluidHelper.BUCKET_FILL_TIME == 0)
                    FluidHelper.processContainers(tankManager.get(0), this, SLOT_INPUT, SLOT_OUTPUT);
            }

            TankManager tMan = getTankManager();
            if (tMan != null) tMan.outputLiquid(tileCache, LIQUID_OUTPUT_FILTER, getOutputSides(), 0, OUTPUT_RATE);
        }
    }

    private ForgeDirection[] getOutputSides() {
        return LIQUID_OUTPUTS
                .get((getPatternPositionX() << X_SHIFT) | (getPatternPositionY() << Y_SHIFT) | getPatternPositionZ());
    }

    static {
        // Precompute all directions each TE can output to
        // values 1 - 3 are the pattern positions of the TEs in the structure
        ArrayList<ForgeDirection> outputSide = new ArrayList<>();
        for (byte y = 1; y <= 3; y++) {
            for (byte x = 1; x <= 3; x++) {
                for (byte z = 1; z <= 3; z++) {

                    if (x == 1) outputSide.add(ForgeDirection.WEST);
                    if (x == 3) outputSide.add(ForgeDirection.EAST);

                    if (y == 1) outputSide.add(ForgeDirection.DOWN);

                    if (z == 1) outputSide.add(ForgeDirection.NORTH);
                    if (z == 3) outputSide.add(ForgeDirection.SOUTH);

                    if (!outputSide.isEmpty()) {
                        LIQUID_OUTPUTS
                                .put((x << X_SHIFT) | (y << Y_SHIFT) | z, outputSide.toArray(new ForgeDirection[0]));
                        outputSide.clear();
                    }
                    // Top center block doesn't have defined direction
                    else LIQUID_OUTPUTS
                            .put((x << X_SHIFT) | (y << Y_SHIFT) | z, new ForgeDirection[] { ForgeDirection.UNKNOWN });
                }
            }
        }
    }

    @Override
    public boolean openGui(EntityPlayer player) {
        TileMultiBlock mBlock = getMasterBlock();
        if (mBlock != null) {
            GuiHandler.openGui(EnumGui.TANK, player, worldObj, mBlock.xCoord, mBlock.yCoord, mBlock.zCoord);
            return true;
        }
        return false;
    }

    @Override
    public IIcon getIcon(int side) {
        return EnumMachineAlpha.TANK_WATER.getTexture(side);
    }

    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        if (from != ForgeDirection.UP || resource == null || !Fluids.WATER.is(resource)) return 0;
        return super.fill(from, resource, doFill);
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        if (resource == null || !Fluids.WATER.is(resource)) return null;
        return super.drain(from, resource.amount, doDrain);
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return from == ForgeDirection.UP && Fluids.WATER.is(fluid);
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return from != ForgeDirection.UP && Fluids.WATER.is(fluid);
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        return SLOTS;
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack stack, int side) {
        return isItemValidForSlot(slot, stack);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack stack, int side) {
        return slot == SLOT_OUTPUT;
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        if (!super.isItemValidForSlot(slot, stack)) return false;
        switch (slot) {
            case SLOT_INPUT:
                return FluidItemHelper.isRoomInContainer(stack, Fluids.WATER.get())
                        || FluidItemHelper.containsFluid(stack, Fluids.WATER.get());
        }
        return false;
    }
}

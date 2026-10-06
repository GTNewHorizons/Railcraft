package mods.railcraft.common.blocks.machine.epsilon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import mods.railcraft.common.blocks.machine.IEnumMachine;
import mods.railcraft.common.blocks.machine.TileMachineItem;
import mods.railcraft.common.core.RailcraftConfig;
import mods.railcraft.common.gui.EnumGui;
import mods.railcraft.common.gui.GuiHandler;
import mods.railcraft.common.items.ItemLinkedEnderPearl;

public class TileAnchorProvider extends TileMachineItem implements ISidedInventory {

    private static final int[] SLOTS = new int[] { 0 };
    private static final int[] SLOTS_NO_ACCESS = new int[] {};

    public TileAnchorProvider() {
        super(1);
    }

    @Override
    public IEnumMachine getMachineType() {
        return EnumMachineEpsilon.ANCHOR_PROVIDER;
    }

    @Override
    public IIcon getIcon(int side) {
        return getMachineType().getTexture(side);
    }

    @Override
    public boolean blockActivated(EntityPlayer player, int side) {
        ItemStack held = player.getCurrentEquippedItem();
        if (held != null && held.getItem() instanceof ItemLinkedEnderPearl) return false;
        return super.blockActivated(player, side);
    }

    @Override
    public boolean openGui(EntityPlayer player) {
        GuiHandler.openGui(EnumGui.ANCHOR_PROVIDER, player, worldObj, xCoord, yCoord, zCoord);
        return true;
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return RailcraftConfig.anchorFuelWorld.containsKey(stack);
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        if (RailcraftConfig.anchorsCanInteractWithPipes()) return SLOTS;
        return SLOTS_NO_ACCESS;
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack stack, int side) {
        return RailcraftConfig.anchorsCanInteractWithPipes() && isItemValidForSlot(slot, stack);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack stack, int side) {
        return false;
    }

    @Override
    public float getResistance(Entity exploder) {
        return 60f;
    }

    @Override
    public float getHardness() {
        return 20;
    }
}

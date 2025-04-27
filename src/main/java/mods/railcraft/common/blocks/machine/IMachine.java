package mods.railcraft.common.blocks.machine;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;

import mods.railcraft.common.gui.tooltips.ToolTip;
import mods.railcraft.common.modules.ModuleManager.Module;

public interface IMachine {

    IIcon getTexture(int side);

    default ItemStack getItem() {
        return getItem(1);
    };

    Class<? extends TileMachineBase> getTileClass();

    TileEntity getTileEntity();

    ToolTip getToolTip(ItemStack stack, EntityPlayer player, boolean adv);

    default ItemStack getItem(int qty) {
        Block block = getBlock();
        if (block == null) return null;
        return new ItemStack(block, qty);
    }

    boolean isAvaliable();

    String getTag();

    Module getModule();

    default boolean isDepreciated() {
        return getModule() == null;
    };

    Block getBlock();
}

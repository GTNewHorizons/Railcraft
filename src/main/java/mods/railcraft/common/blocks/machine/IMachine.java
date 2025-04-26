package mods.railcraft.common.blocks.machine;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;

import mods.railcraft.common.gui.tooltips.ToolTip;

public interface IMachine {

    IIcon getTexture(int side);

    ItemStack getItem();

    Class<TileMachineBase> getTileClass();

    TileEntity getTileEntity();

    ToolTip getToolTip(ItemStack stack, EntityPlayer player, boolean adv);

    ItemStack getItem(int qty);

    boolean isAvaliable();

    String getTag();

    boolean isDepreciated();

    Block getBlock();
}

package mods.railcraft.common.blocks.machine;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import mods.railcraft.common.gui.tooltips.ToolTip;
import mods.railcraft.common.modules.ModuleManager;
import mods.railcraft.common.modules.ModuleManager.Module;

public interface IMachine {

    IIcon getTexture(int side);

    default ItemStack getItem() {
        return getItem(1);
    };

    Class<? extends TileMachineBase> getTileClass();

    default TileEntity getTileEntity() {
        try {
            return getTileClass().newInstance();
        } catch (Exception ex) {}
        return null;
    };

    ToolTip getToolTip(ItemStack stack, EntityPlayer player, boolean adv);

    default ItemStack getItem(int qty) {
        Block block = getBlock();
        if (block == null) return null;
        return new ItemStack(block, qty);
    }

    default boolean isAvailable() {
        return getBlock() != null && ModuleManager.isModuleLoaded(getModule());
    };

    String getTag();

    Module getModule();

    default boolean isDepreciated() {
        return getModule() == null;
    };

    Block getBlock();
}

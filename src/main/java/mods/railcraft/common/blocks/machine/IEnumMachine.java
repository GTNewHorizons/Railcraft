/*
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info This code is the property of CovertJaguar and may only be used
 * with explicit written permission unless otherwise specified on the license page at
 * http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.common.blocks.machine;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
/**
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public interface IEnumMachine extends IMachine {

    int ordinal();

    default int getCapacity() {
        return 0;
    }

    @Override
    default ItemStack getItem(int qty) {
        Block block = getBlock();
        if (block == null) return null;
        return new ItemStack(block, qty, ordinal());
    }
}

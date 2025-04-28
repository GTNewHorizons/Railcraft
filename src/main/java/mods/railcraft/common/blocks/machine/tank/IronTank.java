/*
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info This code is the property of CovertJaguar and may only be used
 * with explicit written permission unless otherwise specified on the license page at
 * http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.common.blocks.machine.tank;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;

import mods.railcraft.common.blocks.machine.Machines;
import mods.railcraft.common.plugins.forge.LocalizationPlugin;

/**
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public class IronTank extends MetalTank {

    private final Set<Block> tankBlocks = new HashSet<Block>();

    public IronTank() {
        tankBlocks.add(Machines.TANK_IRON_WALL.getBlock());
        tankBlocks.add(Machines.TANK_IRON_GAUGE.getBlock());
        tankBlocks.add(Machines.TANK_IRON_VALVE.getBlock());
    }

    @Override
    public String getTitle() {
        return LocalizationPlugin.translate("railcraft.gui.tank.iron");
    }

    @Override
    public boolean isTankBlock(Block block) {
        return tankBlocks.contains(block);
    }

    @Override
    public boolean isWallBlock(Block block) {
        return block == Machines.TANK_IRON_WALL.getBlock();
    }

    @Override
    public float getResistance(Entity exploder) {
        return 20F;
    }
}

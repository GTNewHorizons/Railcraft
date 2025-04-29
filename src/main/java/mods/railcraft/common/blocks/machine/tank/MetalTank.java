/*
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info This code is the property of CovertJaguar and may only be used
 * with explicit written permission unless otherwise specified on the license page at
 * http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.common.blocks.machine.tank;

import java.util.HashSet;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;

import mods.railcraft.common.plugins.forge.LocalizationPlugin;

/**
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public class MetalTank {

    public final TankMaterial material;
    public Block wall;
    public HashSet<Block> sideBlocks = new HashSet<Block>();

    public MetalTank(TankMaterial material, Block wall, Block... otherBlocks) {
        this.material = material;
        this.wall = wall;
        sideBlocks.add(wall);
        for (Block block : otherBlocks) {
            sideBlocks.add(block);
        }
    }

    public String getTitle() {
        return LocalizationPlugin.translate("railcraft.gui.tank." + material.name);
    };

    public boolean isTankBlock(Block block) {
        return sideBlocks.contains(block);
    };

    public boolean isWallBlock(Block block) {
        return this.wall == block;
    };

    public float getResistance(Entity exploder) {
        return material.explosionResistance;
    };

    public int capacityPerBlock() {
        return material.capacityPerBlock;
    }

    public boolean isAvailable() {
        return material.module.isEnabled() && Tanks.getWall(material) != null;
    }
}

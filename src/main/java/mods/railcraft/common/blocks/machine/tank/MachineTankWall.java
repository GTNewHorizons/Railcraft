package mods.railcraft.common.blocks.machine.tank;

import net.minecraft.block.Block;

import mods.railcraft.common.blocks.machine.BlockMachine;
import mods.railcraft.common.blocks.machine.MachineProxy;

public class MachineTankWall extends MachineTank {

    public MachineTankWall(TankMaterial material) {
        super(material, TileTankWall.class, "tank." + material.name + ".wall", 2, 1, 0, 0, 1, 1, 1, 1);
    }

    @Override
    public Block createBlock(MachineProxy proxy) {
        Block wall = new BlockMachine(0, proxy, false, 0).setBlockName("railcraft." + tag);
        wall.setHarvestLevel("pickaxe", 2);
        return wall;
    }
}

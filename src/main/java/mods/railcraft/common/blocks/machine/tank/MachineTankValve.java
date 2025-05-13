package mods.railcraft.common.blocks.machine.tank;

import net.minecraft.block.Block;

import mods.railcraft.common.blocks.machine.BlockMachine;
import mods.railcraft.common.blocks.machine.MachineProxy;

public class MachineTankValve extends MachineTank {

    public MachineTankValve(TankMaterial material) {
        super(material, TileTankValve.class, "tank." + material.name + ".valve", 4, 1, 0, 0, 1, 1, 1, 1, 2, 3);
    }

    @Override
    public Block createBlock(MachineProxy proxy) {
        Block valve = new BlockMachine(0, proxy, false, 0).setBlockName("railcraft." + tag);
        valve.setHarvestLevel("pickaxe", 2);
        return valve;
    }
}

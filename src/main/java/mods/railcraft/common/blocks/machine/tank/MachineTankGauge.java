package mods.railcraft.common.blocks.machine.tank;

import net.minecraft.block.Block;

import mods.railcraft.common.blocks.machine.BlockMachine;
import mods.railcraft.common.blocks.machine.MachineProxy;

public class MachineTankGauge extends MachineTank {

    public MachineTankGauge(TankMaterial material) {
        super(material, TileTankGauge.class, "tank." + material.name + ".gauge", 1, 5, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4);
    }

    @Override
    public Block createBlock(MachineProxy proxy) {
        Block gauge = new BlockMachine(0, proxy, false, 0).setBlockName("railcraft." + tag);
        gauge.setHarvestLevel("pickaxe", 2);
        return gauge;
    }
}

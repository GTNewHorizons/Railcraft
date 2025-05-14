package mods.railcraft.common.blocks.machine.tank_water;

import net.minecraft.block.Block;

import mods.railcraft.common.blocks.machine.BlockMachine;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.MachineProxy;
import mods.railcraft.common.modules.ModuleManager.Module;

public class MachineTankWater extends Machine {

    public MachineTankWater() {
        super(Module.FACTORY, TileTankWater.class, "tank_water", 2, 1, 0, 0, 1, 1, 1, 1);
    }

    @Override
    public Block createBlock(MachineProxy proxy) {
        Block tankWater = new BlockMachine(0, proxy, true, 255).setBlockName("railcraft." + tag);
        tankWater.setHarvestLevel("axe", 0);
        return tankWater;
    }
}

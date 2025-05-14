package mods.railcraft.common.blocks.machine.rolling_machine;

import net.minecraft.block.Block;

import mods.railcraft.common.blocks.machine.BlockMachine;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.MachineProxy;
import mods.railcraft.common.modules.ModuleManager.Module;

public class MachineRollingMachine extends Machine {

    public MachineRollingMachine() {
        super(Module.FACTORY, TileRollingMachine.class, "rolling_machine", 3, 1, 0, 1, 2, 2, 2, 2);
    }

    @Override
    public Block createBlock(MachineProxy proxy) {
        Block rollingMachine = new BlockMachine(0, proxy, true, 255).setBlockName("railcraft." + tag);
        rollingMachine.setHarvestLevel("pickaxe", 2);
        return rollingMachine;
    }
}

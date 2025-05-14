package mods.railcraft.common.blocks.machine.smoker;

import net.minecraft.block.Block;

import mods.railcraft.common.blocks.machine.BlockMachine;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.MachineProxy;
import mods.railcraft.common.modules.ModuleManager.Module;

public class MachineSmoker extends Machine {

    public MachineSmoker() {
        super(Module.STRUCTURES, TileSmoker.class, "smoker", 3, 1, 0, 1, 2, 2, 2, 2);
    }

    @Override
    public Block createBlock(MachineProxy proxy) {
        Block smoker = new BlockMachine(0, proxy, true, 255).setBlockName("railcraft." + tag);
        smoker.setHarvestLevel("pickaxe", 2);
        return smoker;
    }
}

package mods.railcraft.common.blocks.machine.sentinel;

import net.minecraft.block.Block;

import mods.railcraft.common.blocks.machine.BlockMachine;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.MachineProxy;
import mods.railcraft.common.modules.ModuleManager.Module;

public class MachineSentinel extends Machine {

    public MachineSentinel() {
        super(Module.CHUNK_LOADING, TileSentinel.class, "anchor.sentinel", 2, 1, 0, 0, 1, 1, 1, 1);
    }

    @Override
    public Block createBlock(MachineProxy proxy) {
        Block sentinel = new BlockMachine(0, proxy, false, 255).setBlockName("railcraft." + tag);
        sentinel.setHarvestLevel("pickaxe", 3);
        return sentinel;
    }
}

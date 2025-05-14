package mods.railcraft.common.blocks.machine.engraving_bench;

import net.minecraft.block.Block;

import mods.railcraft.common.blocks.machine.BlockMachine;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.MachineProxy;
import mods.railcraft.common.modules.ModuleManager.Module;

public class MachineEngravingBench extends Machine {

    public MachineEngravingBench() {
        super(Module.EMBLEM, TileEngravingBench.class, "engraving_bench", 4, 1, 0, 1, 3, 3, 3, 3, 2);
    }

    @Override
    public Block createBlock(MachineProxy proxy) {
        Block engravingBench = new BlockMachine(0, proxy, true, 255).setBlockName("railcraft." + tag);
        engravingBench.setHarvestLevel("pickaxe", 0);
        return engravingBench;
    }
}

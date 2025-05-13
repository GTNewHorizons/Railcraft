package mods.railcraft.common.blocks.machine.coke_oven;

import net.minecraft.block.Block;

import mods.railcraft.common.blocks.machine.BlockMachine;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.MachineProxy;
import mods.railcraft.common.modules.ModuleManager.Module;

public class MachineCokeOven extends Machine {

    public MachineCokeOven() {
        super(Module.FACTORY, TileCokeOven.class, "coke_oven", 3, 1, 0, 0, 0, 0, 1, 0, 1, 2);
    }

    @Override
    public Block createBlock(MachineProxy proxy) {
        Block cokeOven = new BlockMachine(0, proxy, true, 255).setBlockName("railcraft." + tag);
        cokeOven.setHarvestLevel("pickaxe", 0);
        return cokeOven;
    }
}

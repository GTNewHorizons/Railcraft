package mods.railcraft.common.blocks.machine.blast_furnace;

import net.minecraft.block.Block;

import mods.railcraft.common.blocks.machine.BlockMachine;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.MachineProxy;
import mods.railcraft.common.modules.ModuleManager.Module;

public class MachineBlastFurnace extends Machine {

    public MachineBlastFurnace() {
        super(Module.FACTORY, TileBlastFurnace.class, "blast_furnace", 2, 1, 0, 0, 1, 1, 1, 1);
    }

    @Override
    public Block createBlock(MachineProxy proxy) {
        Block blastFurnace = new BlockMachine(0, proxy, true, 255).setBlockName("railcraft." + tag);
        blastFurnace.setHarvestLevel("pickaxe", 2);
        return blastFurnace;
    }
}

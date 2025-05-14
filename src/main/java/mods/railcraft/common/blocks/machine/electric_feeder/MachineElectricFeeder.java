package mods.railcraft.common.blocks.machine.electric_feeder;

import net.minecraft.block.Block;

import mods.railcraft.common.blocks.machine.BlockMachine;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.MachineProxy;
import mods.railcraft.common.modules.ModuleManager.Module;

public class MachineElectricFeeder extends Machine {

    public MachineElectricFeeder() {
        super(Module.ELECTRICITY, TileElectricFeeder.class, "electric_feeder", 1, 1, 0);
    }

    @Override
    public Block createBlock(MachineProxy proxy) {
        Block blockFeeder = new BlockMachine(0, proxy, true, 255).setBlockName("railcraft." + tag);
        blockFeeder.setHarvestLevel("pickaxe", 2);
        return blockFeeder;
    }
}

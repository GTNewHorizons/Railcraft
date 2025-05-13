package mods.railcraft.common.blocks.machine.electric_feeder;

import net.minecraft.block.Block;

import mods.railcraft.common.blocks.machine.BlockMachine;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.MachineProxy;
import mods.railcraft.common.modules.ModuleManager.Module;

public class MachineElectricFeederAdmin extends Machine {

    public MachineElectricFeederAdmin() {
        super(Module.ELECTRICITY, TileElectricFeederAdmin.class, "electric_feeder.admin", 2, 1, 0, 0, 0, 0, 0, 0, 1);
    }

    @Override
    public Block createBlock(MachineProxy proxy) {
        Block blockFeeder = new BlockMachine(0, proxy, true, 255).setBlockName("railcraft." + tag);
        blockFeeder.setHarvestLevel("pickaxe", 2);
        return blockFeeder;
    }
}

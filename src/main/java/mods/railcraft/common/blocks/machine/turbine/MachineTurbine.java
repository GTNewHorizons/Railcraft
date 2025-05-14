package mods.railcraft.common.blocks.machine.turbine;

import net.minecraft.block.Block;

import mods.railcraft.common.blocks.machine.BlockMachine;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.MachineProxy;
import mods.railcraft.common.modules.ModuleManager.Module;

public class MachineTurbine extends Machine {

    public MachineTurbine() {
        super(Module.ELECTRICITY, TileSteamTurbine.class, "turbine", 3, 3, 2, 2, 2, 2, 6, 2, 0, 1, 3, 4, 5, 7);
    }

    @Override
    public Block createBlock(MachineProxy proxy) {
        Block turbine = new BlockMachine(0, proxy, true, 255).setBlockName("railcraft." + tag);
        turbine.setHarvestLevel("pickaxe", 2);
        return turbine;
    }
}

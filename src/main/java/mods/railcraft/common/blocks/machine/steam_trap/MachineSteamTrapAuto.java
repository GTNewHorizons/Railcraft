package mods.railcraft.common.blocks.machine.steam_trap;

import net.minecraft.block.Block;

import mods.railcraft.common.blocks.machine.BlockMachine;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.MachineProxy;
import mods.railcraft.common.modules.ModuleManager.Module;

public class MachineSteamTrapAuto extends Machine {

    public MachineSteamTrapAuto() {
        super(Module.EXTRAS, TileSteamTrapAuto.class, "steam_trap.auto", 4, 1, 0, 2, 1, 1, 1, 1, 0, 1, 2, 3);
    }

    @Override
    public Block createBlock(MachineProxy proxy) {
        Block steamTrapAuto = new BlockMachine(0, proxy, true, 255).setBlockName("railcraft." + tag);
        steamTrapAuto.setHarvestLevel("pickaxe", 2);
        return steamTrapAuto;
    }
}

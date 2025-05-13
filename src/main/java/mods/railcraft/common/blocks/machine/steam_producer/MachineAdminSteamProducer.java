package mods.railcraft.common.blocks.machine.steam_producer;

import net.minecraft.block.Block;

import mods.railcraft.common.blocks.machine.BlockMachine;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.MachineProxy;
import mods.railcraft.common.modules.ModuleManager.Module;

public class MachineAdminSteamProducer extends Machine {

    public MachineAdminSteamProducer() {
        super(Module.STEAM, TileAdminSteamProducer.class, "admin_steam_producer", 2, 1, 0, 0, 0, 0, 0, 0, 1);
    }

    @Override
    public Block createBlock(MachineProxy proxy) {
        Block adminProducer = new BlockMachine(0, proxy, true, 255).setBlockName("railcraft." + tag);
        adminProducer.setHarvestLevel("pickaxe", 2);
        return adminProducer;
    }
}

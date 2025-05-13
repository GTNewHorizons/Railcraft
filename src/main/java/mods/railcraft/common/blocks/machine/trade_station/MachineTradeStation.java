package mods.railcraft.common.blocks.machine.trade_station;

import net.minecraft.block.Block;

import mods.railcraft.common.blocks.machine.BlockMachine;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.MachineProxy;
import mods.railcraft.common.modules.ModuleManager.Module;

public class MachineTradeStation extends Machine {

    public MachineTradeStation() {
        super(Module.AUTOMATION, TileTradeStation.class, "trade_station", 3, 1, 0, 0, 1, 1, 2, 1);
    }

    @Override
    public Block createBlock(MachineProxy proxy) {
        Block tradeStation = new BlockMachine(0, proxy, true, 255).setBlockName("railcraft." + tag);
        tradeStation.setHarvestLevel("pickaxe", 2);
        return tradeStation;
    }
}

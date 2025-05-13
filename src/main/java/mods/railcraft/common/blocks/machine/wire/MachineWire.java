package mods.railcraft.common.blocks.machine.wire;

import net.minecraft.block.Block;

import mods.railcraft.common.blocks.machine.BlockMachine;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.MachineProxy;
import mods.railcraft.common.core.Railcraft;
import mods.railcraft.common.modules.ModuleManager.Module;

public class MachineWire extends Machine {

    public MachineWire() {
        super(Module.ELECTRICITY, TileWire.class, "wire", 1, 1, 0, 0, 0, 0, 0, 0);
    }

    @Override
    public Block createBlock(MachineProxy proxy) {
        int renderId = Railcraft.getProxy().getRenderId();
        Block wireBlock = new BlockMachine(renderId, proxy, false, 0).setBlockName("railcraft." + tag);
        wireBlock.setHarvestLevel("pickaxe", 2);
        return wireBlock;
    }
}

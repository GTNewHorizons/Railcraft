package mods.railcraft.common.blocks.machine.boiler;

import net.minecraft.block.Block;

import mods.railcraft.common.blocks.machine.BlockMachine;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.MachineProxy;
import mods.railcraft.common.blocks.machine.boiler.TileBoilerTank.TankPressure;
import mods.railcraft.common.core.Railcraft;
import mods.railcraft.common.modules.ModuleManager.Module;

public class MachineBoilerTank extends Machine {

    public MachineBoilerTank(TankPressure pressure) {
        super(
                Module.STEAM,
                getTileClass(pressure),
                "boiler.tank.pressure." + pressure.getName(),
                2,
                1,
                0,
                0,
                1,
                1,
                1,
                1);
    }

    @Override
    public Block createBlock(MachineProxy proxy) {
        int renderId = Railcraft.getProxy().getRenderId();
        Block boilerTank = new BlockMachine(renderId, proxy, false, 0).setBlockName("railcraft." + tag);
        boilerTank.setHarvestLevel("pickaxe", 2);
        return boilerTank;
    }

    private static Class<? extends TileBoilerTank> getTileClass(TankPressure tankPressure) {
        return switch (tankPressure) {
            case HIGH -> TileBoilerTankHigh.class;
            case LOW -> TileBoilerTankLow.class;
        };
    }
}

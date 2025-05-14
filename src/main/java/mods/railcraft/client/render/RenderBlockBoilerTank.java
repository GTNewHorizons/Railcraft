package mods.railcraft.client.render;

import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.Machines;
import mods.railcraft.common.blocks.machine.boiler.TileBoilerTank.TankPressure;

public class RenderBlockBoilerTank extends BlockRenderer {

    public RenderBlockBoilerTank(TankPressure pressure) {
        super(getBoilerTank(pressure).getBlock());
        addBlockRenderer(0, new RenderBoilerTank());
    }

    private static Machine getBoilerTank(TankPressure pressure) {
        return switch (pressure) {
            case LOW -> Machines.BOILER_TANK_LOW_PRESSURE;
            case HIGH -> Machines.BOILER_TANK_HIGH_PRESSURE;
        };
    }
}

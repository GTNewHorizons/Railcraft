package mods.railcraft.client.render;

import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.Machines;
import mods.railcraft.common.blocks.machine.tank.TankMaterial;

public class RenderBlockTankGauge extends BlockRenderer {

    public RenderBlockTankGauge(TankMaterial material) {
        super(getMachine(material).getBlock());
    }

    private static Machine getMachine(TankMaterial tankMaterial) {
        return switch (tankMaterial) {
            case IRON -> Machines.TANK_IRON_GAUGE;
            case STEEL -> Machines.TANK_STEEL_GAUGE;
        };
    }
}

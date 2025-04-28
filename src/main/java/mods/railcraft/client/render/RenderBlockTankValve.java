package mods.railcraft.client.render;

import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.Machines;
import mods.railcraft.common.blocks.machine.tank.TankMaterial;

public class RenderBlockTankValve extends BlockRenderer {

    public RenderBlockTankValve(TankMaterial material) {
        super(getMachine(material).getBlock());
    }

    private static Machine getMachine(TankMaterial tankMaterial) {
        return switch (tankMaterial) {
            case IRON -> Machines.TANK_IRON_VALVE;
            case STEEL -> Machines.TANK_STEEL_VALVE;
        };
    }
}

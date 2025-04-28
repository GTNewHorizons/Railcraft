package mods.railcraft.client.render;

import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.Machines;
import mods.railcraft.common.blocks.machine.tank.TankMaterial;

public class RenderBlockTankWall extends BlockRenderer {

    public RenderBlockTankWall(TankMaterial material) {
        super(getMachine(material).getBlock());
    }

    private static Machine getMachine(TankMaterial tankMaterial) {
        return switch (tankMaterial) {
            case IRON -> Machines.TANK_IRON_WALL;
            case STEEL -> Machines.TANK_STEEL_WALL;
        };
    }
}

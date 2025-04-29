package mods.railcraft.client.render;

import mods.railcraft.common.blocks.machine.tank.TankMaterial;
import mods.railcraft.common.blocks.machine.tank.Tanks;

public class RenderBlockTankWall extends BlockRenderer {

    public RenderBlockTankWall(TankMaterial material) {
        super(Tanks.getWall(material).getBlock());
    }
}

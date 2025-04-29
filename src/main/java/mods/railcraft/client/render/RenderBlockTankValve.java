package mods.railcraft.client.render;

import mods.railcraft.common.blocks.machine.tank.TankMaterial;
import mods.railcraft.common.blocks.machine.tank.Tanks;

public class RenderBlockTankValve extends BlockRenderer {

    public RenderBlockTankValve(TankMaterial material) {
        super(Tanks.getValve(material).getBlock());
    }
}

package mods.railcraft.client.render;

import mods.railcraft.common.blocks.machine.tank.TankMaterial;
import mods.railcraft.common.blocks.machine.tank.Tanks;

public class RenderBlockTankGauge extends BlockRenderer {

    public RenderBlockTankGauge(TankMaterial material) {
        super(Tanks.getGauge(material).getBlock());
    }
}

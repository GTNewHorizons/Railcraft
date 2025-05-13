package mods.railcraft.common.blocks.machine.tank;

import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machine;

public class MachineProxyTankWall implements IMachineProxy {

    private final TankMaterial material;

    public MachineProxyTankWall(TankMaterial material) {
        this.material = material;
    }

    @Override
    public Machine getMachine() {
        return Tanks.getWall(material);
    }
}

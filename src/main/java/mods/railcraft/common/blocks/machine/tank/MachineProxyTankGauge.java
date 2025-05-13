package mods.railcraft.common.blocks.machine.tank;

import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machine;

public class MachineProxyTankGauge implements IMachineProxy {

    private final TankMaterial material;

    public MachineProxyTankGauge(TankMaterial material) {
        this.material = material;
    }

    @Override
    public Machine getMachine() {
        return Tanks.getGauge(material);
    }
}

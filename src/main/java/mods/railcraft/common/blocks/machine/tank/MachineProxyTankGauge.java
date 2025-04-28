package mods.railcraft.common.blocks.machine.tank;

import net.minecraft.client.renderer.texture.IIconRegister;

import mods.railcraft.common.blocks.machine.IMachine;
import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.Machines;

public class MachineProxyTankGauge implements IMachineProxy {

    private final TankMaterial material;

    public MachineProxyTankGauge(TankMaterial material) {
        this.material = material;
    }

    private Machine getGauge() {
        return switch (material) {
            case IRON -> Machines.TANK_IRON_GAUGE;
            case STEEL -> Machines.TANK_STEEL_GAUGE;
        };
    }

    @Override
    public IMachine getMachine(int meta) {
        return getGauge();
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        getGauge().registerIcons(iconRegister);
    }
}

package mods.railcraft.common.blocks.machine.boiler;

import net.minecraft.client.renderer.texture.IIconRegister;

import mods.railcraft.common.blocks.machine.IMachine;
import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.Machines;
import mods.railcraft.common.blocks.machine.boiler.TileBoilerTank.TankPressure;

public class MachineProxyBoilerTank implements IMachineProxy {

    TankPressure pressure;

    public MachineProxyBoilerTank(TankPressure pressure) {
        this.pressure = pressure;
    }

    @Override
    public IMachine getMachine(int meta) {
        return getMachine(pressure);
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        getMachine(this.pressure).registerIcons(iconRegister);
    }

    private Machine getMachine(TankPressure pressure) {
        return switch (pressure) {
            case LOW -> Machines.BOILER_TANK_LOW_PRESSURE;
            case HIGH -> Machines.BOILER_TANK_HIGH_PRESSURE;
        };
    }
}

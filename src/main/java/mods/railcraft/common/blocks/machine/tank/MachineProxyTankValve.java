package mods.railcraft.common.blocks.machine.tank;

import net.minecraft.client.renderer.texture.IIconRegister;

import mods.railcraft.common.blocks.machine.IMachine;
import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.Machines;

public class MachineProxyTankValve implements IMachineProxy {

    private final TankMaterial material;

    public MachineProxyTankValve(TankMaterial material) {
        this.material = material;
    }

    private Machine getValve() {
        return switch (material) {
            case IRON -> Machines.TANK_IRON_VALVE;
            case STEEL -> Machines.TANK_STEEL_VALVE;
        };
    }

    @Override
    public IMachine getMachine(int meta) {
        return getValve();
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        getValve().registerIcons(iconRegister);
    }
}

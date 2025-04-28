package mods.railcraft.common.blocks.machine.tank;

import net.minecraft.client.renderer.texture.IIconRegister;

import mods.railcraft.common.blocks.machine.IMachine;
import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.Machines;

public class MachineProxyTankWall implements IMachineProxy {

    private final TankMaterial material;

    public MachineProxyTankWall(TankMaterial material) {
        this.material = material;
    }

    private Machine getWall() {
        return switch (material) {
            case IRON -> Machines.TANK_IRON_WALL;
            case STEEL -> Machines.TANK_STEEL_WALL;
        };
    }

    @Override
    public IMachine getMachine(int meta) {
        return getWall();
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        getWall().registerIcons(iconRegister);
    }
}

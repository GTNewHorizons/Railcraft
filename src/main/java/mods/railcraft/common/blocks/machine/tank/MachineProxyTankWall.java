package mods.railcraft.common.blocks.machine.tank;

import net.minecraft.client.renderer.texture.IIconRegister;

import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machine;

public class MachineProxyTankWall implements IMachineProxy {

    private final TankMaterial material;

    public MachineProxyTankWall(TankMaterial material) {
        this.material = material;
    }

    @Override
    public Machine getMachine(int meta) {
        return Tanks.getWall(material);
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        Tanks.getWall(material).registerIcons(iconRegister);
    }
}

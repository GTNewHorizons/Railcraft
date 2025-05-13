package mods.railcraft.common.blocks.machine.tank;

import net.minecraft.client.renderer.texture.IIconRegister;

import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machine;

public class MachineProxyTankValve implements IMachineProxy {

    private final TankMaterial material;

    public MachineProxyTankValve(TankMaterial material) {
        this.material = material;
    }

    @Override
    public Machine getMachine() {
        return Tanks.getValve(material);
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        Tanks.getValve(material).registerIcons(iconRegister);
    }
}

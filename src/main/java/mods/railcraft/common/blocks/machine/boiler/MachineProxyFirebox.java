package mods.railcraft.common.blocks.machine.boiler;

import net.minecraft.client.renderer.texture.IIconRegister;

import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.Machines;
import mods.railcraft.common.blocks.machine.boiler.TileBoilerFirebox.FireboxType;

public class MachineProxyFirebox implements IMachineProxy {

    FireboxType fireboxType;

    public MachineProxyFirebox(FireboxType fireboxType) {
        this.fireboxType = fireboxType;
    }

    @Override
    public Machine getMachine(int meta) {
        return getMachine(fireboxType);
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        getMachine(0).registerIcons(iconRegister);
    }

    private Machine getMachine(FireboxType fireboxType) {
        return switch (fireboxType) {
            case SOLID -> Machines.BOILER_FIREBOX_SOLID;
            case LIQUID -> Machines.BOILER_FIREBOX_LIQUID;
        };
    }
}

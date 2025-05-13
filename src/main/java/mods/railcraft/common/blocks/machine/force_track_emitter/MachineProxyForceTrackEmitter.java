package mods.railcraft.common.blocks.machine.force_track_emitter;

import net.minecraft.client.renderer.texture.IIconRegister;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.railcraft.common.blocks.machine.IMachine;
import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machines;

public class MachineProxyForceTrackEmitter implements IMachineProxy {

    @Override
    public IMachine getMachine() {
        return Machines.FORCE_TRACK_EMITTER;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        Machines.FORCE_TRACK_EMITTER.registerIcons(iconRegister);
    }
}

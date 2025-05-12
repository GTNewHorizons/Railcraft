package mods.railcraft.common.blocks.machine.force_track_emitter;

import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.TileMachineBase;
import mods.railcraft.common.modules.ModuleManager.Module;

public class MachineForceTrackEmitter extends Machine {

    public MachineForceTrackEmitter(Module module, Block block, Class<? extends TileMachineBase> tile, String tag,
            int... textureInfo) {
        super(module, block, tile, tag, textureInfo);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        IIcon emitterSide = iconRegister.registerIcon("railcraft:" + tag + ".side");
        texture = new IIcon[9];
        Arrays.fill(texture, emitterSide);
        texture[6] = iconRegister.registerIcon("railcraft:" + tag + ".side.unpowered");
        texture[7] = iconRegister.registerIcon("railcraft:" + tag + ".facing");
        texture[8] = iconRegister.registerIcon("railcraft:" + tag + ".facing.unpowered");
    }
}

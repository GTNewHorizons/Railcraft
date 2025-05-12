package mods.railcraft.common.blocks.machine.flux_transformer;

import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.TileMachineBase;
import mods.railcraft.common.modules.ModuleManager.Module;

public class MachineFluxTransformer extends Machine {

    public MachineFluxTransformer(Module module, Block block, Class<? extends TileMachineBase> tile, String tag,
            int... textureInfo) {
        super(module, block, tile, tag, textureInfo);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        IIcon transformerSide = iconRegister.registerIcon("railcraft:" + tag + ".side");
        IIcon transformerCap = iconRegister.registerIcon("railcraft:" + tag + ".cap");

        texture = new IIcon[6];
        Arrays.fill(texture, transformerSide);
        texture[0] = transformerCap;
        texture[1] = transformerCap;
    }
}

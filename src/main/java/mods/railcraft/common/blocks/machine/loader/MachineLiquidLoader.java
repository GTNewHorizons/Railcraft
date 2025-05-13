package mods.railcraft.common.blocks.machine.loader;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.railcraft.client.util.textures.TextureAtlasSheet;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.TileMachineBase;
import mods.railcraft.common.modules.ModuleManager.Module;

public class MachineLiquidLoader extends Machine {

    public IIcon[] pipeTexture = new IIcon[6];

    public MachineLiquidLoader(Module module, Block block, Class<? extends TileMachineBase> tile, String tag) {
        super(module, block, tile, tag);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        texture = new IIcon[8];
        IIcon[] icons = TextureAtlasSheet.unstitchIcons(iconRegister, "railcraft:" + tag, 5);
        IIcon cap = icons[0];
        IIcon side = icons[1];
        IIcon face = icons[2];
        texture[0] = cap;
        texture[1] = cap;
        for (int i = 2; i < 6; i++) {
            texture[i] = side;
        }
        texture[0] = face;
        System.arraycopy(icons, 3, texture, 6, 2);
        IIcon[] pipe = TextureAtlasSheet.unstitchIcons(iconRegister, "railcraft:loader.pipe", 2);
        pipeTexture[0] = pipe[0];
        pipeTexture[1] = pipe[0];
        for (int i = 2; i < 6; i++) {
            pipeTexture[i] = pipe[1];
        }
    }
}

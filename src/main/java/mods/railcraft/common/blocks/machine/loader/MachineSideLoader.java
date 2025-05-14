package mods.railcraft.common.blocks.machine.loader;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.railcraft.client.util.textures.TextureAtlasSheet;
import mods.railcraft.common.blocks.machine.BlockMachine;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.MachineProxy;
import mods.railcraft.common.blocks.machine.TileMachineBase;
import mods.railcraft.common.modules.ModuleManager.Module;

public class MachineSideLoader extends Machine {

    public MachineSideLoader(Module module, Class<? extends TileMachineBase> tile, String tag) {
        super(module, tile, tag);
    }

    @Override
    public Block createBlock(MachineProxy proxy) {
        Block loaderBlock = new BlockMachine(0, proxy, false, 255).setBlockName("railcraft." + tag);
        loaderBlock.setHarvestLevel("pickaxe", 2);
        loaderBlock.setCreativeTab(CreativeTabs.tabTransport);
        return loaderBlock;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        texture = new IIcon[6];
        IIcon[] icons = TextureAtlasSheet.unstitchIcons(iconRegister, "railcraft:" + tag, 3);
        IIcon cap = icons[0];
        IIcon side = icons[1];
        IIcon face = icons[2];
        texture[0] = cap;
        texture[1] = cap;
        for (int i = 2; i < 6; i++) {
            texture[i] = side;
        }
        texture[3] = face;
    }
}

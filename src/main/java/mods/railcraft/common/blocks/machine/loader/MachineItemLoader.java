package mods.railcraft.common.blocks.machine.loader;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.railcraft.client.util.textures.TextureAtlasSheet;
import mods.railcraft.common.blocks.machine.BlockMachine;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.MachineProxy;
import mods.railcraft.common.modules.ModuleManager.Module;

public class MachineItemLoader extends Machine {

    public MachineItemLoader() {
        super(Module.TRANSPORT, TileItemLoader.class, "loader.item");
    }

    @Override
    public Block createBlock(MachineProxy proxy) {
        Block itemLoader = new BlockMachine(0, proxy, false, 255).setBlockName("railcraft." + tag);
        itemLoader.setHarvestLevel("pickaxe", 2);
        return itemLoader;
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
        texture[0] = face;
    }
}

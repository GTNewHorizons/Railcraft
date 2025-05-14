package mods.railcraft.common.blocks.machine.loader;

import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.railcraft.common.blocks.machine.BlockMachine;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.MachineProxy;
import mods.railcraft.common.blocks.machine.TileMachineBase;
import mods.railcraft.common.modules.ModuleManager.Module;

public class MachineRFLoader extends Machine {

    public MachineRFLoader(Class<? extends TileMachineBase> tile, String tag) {
        super(Module.REDSTONE_FLUX, tile, tag);
    }

    @Override
    public Block createBlock(MachineProxy proxy) {
        Block rfLoader = new BlockMachine(0, proxy, false, 255).setBlockName("railcraft." + tag);
        rfLoader.setHarvestLevel("pickaxe", 2);
        return rfLoader;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        IIcon emitterSide = iconRegister.registerIcon("railcraft:" + tag + ".side");
        texture = new IIcon[9];
        Arrays.fill(texture, emitterSide);
        String rfLoaderTag = "loader.rf";
        texture[6] = iconRegister.registerIcon("railcraft:" + rfLoaderTag + ".side.unpowered");
        texture[3] = texture[7] = iconRegister.registerIcon("railcraft:" + tag + ".facing");
        texture[8] = iconRegister.registerIcon("railcraft:" + rfLoaderTag + ".facing.unpowered");
    }
}

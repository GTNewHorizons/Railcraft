package mods.railcraft.common.blocks.machine.flux_transformer;

import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.railcraft.common.blocks.machine.BlockMachine;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.MachineProxy;
import mods.railcraft.common.modules.ModuleManager.Module;

public class MachineFluxTransformer extends Machine {

    public MachineFluxTransformer() {
        super(Module.ELECTRICITY, TileFluxTransformer.class, "flux_transformer");
    }

    @Override
    public Block createBlock(MachineProxy proxy) {
        Block fluxTrafo = new BlockMachine(0, proxy, true, 255).setBlockName("railcraft." + tag);
        fluxTrafo.setHarvestLevel("pickaxe", 2);
        return fluxTrafo;
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

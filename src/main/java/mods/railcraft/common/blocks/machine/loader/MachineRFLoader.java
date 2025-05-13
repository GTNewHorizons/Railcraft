package mods.railcraft.common.blocks.machine.loader;

import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.Machines;
import mods.railcraft.common.blocks.machine.TileMachineBase;
import mods.railcraft.common.modules.ModuleManager.Module;

public class MachineRFLoader extends Machine {

    public MachineRFLoader(Module module, Block block, Class<? extends TileMachineBase> tile, String tag) {
        super(module, block, tile, tag);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        IIcon emitterSide = iconRegister.registerIcon("railcraft:" + tag + ".side");
        texture = new IIcon[9];
        Arrays.fill(texture, emitterSide);
        texture[6] = iconRegister.registerIcon("railcraft:" + Machines.RF_LOADER.getTag() + ".side.unpowered");
        texture[3] = texture[7] = iconRegister.registerIcon("railcraft:" + tag + ".facing");
        texture[8] = iconRegister.registerIcon("railcraft:" + tag + ".facing.unpowered");
    }
}

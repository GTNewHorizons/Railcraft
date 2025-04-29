package mods.railcraft.common.blocks.machine.tank;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.modules.ModuleManager.Module;

public class MachineTank extends Machine {

    public final TankMaterial material;

    public MachineTank(Module module, TankMaterial material, Block block, Class<? extends TileTankBase> tile,
            String tag, int... textureInfo) {
        super(module, block, tile, tag, textureInfo);
        this.material = material;
    }

    @Override
    public TileEntity getTileEntity() {
        try {
            return getTileClass().getDeclaredConstructor(TankMaterial.class).newInstance(material);
        } catch (Exception ex) {}
        return null;
    }
}

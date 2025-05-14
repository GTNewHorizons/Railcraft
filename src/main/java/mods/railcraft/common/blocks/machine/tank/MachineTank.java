package mods.railcraft.common.blocks.machine.tank;

import net.minecraft.tileentity.TileEntity;

import mods.railcraft.common.blocks.machine.Machine;

public abstract class MachineTank extends Machine {

    public final TankMaterial material;

    public MachineTank(TankMaterial material, Class<? extends TileTankBase> tile, String tag, int... textureInfo) {
        super(material.module, tile, tag, textureInfo);
        this.material = material;
    }

    @Override
    public TileEntity getTileEntity() {
        try {
            return tile.getDeclaredConstructor(TankMaterial.class).newInstance(material);
        } catch (Exception ex) {}
        return null;
    }
}

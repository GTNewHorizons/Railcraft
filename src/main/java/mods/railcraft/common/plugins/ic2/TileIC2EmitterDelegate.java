/*
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info This code is the property of CovertJaguar and may only be used
 * with explicit written permission unless otherwise specified on the license page at
 * http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.common.plugins.ic2;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import ic2.api.energy.tile.IEnergySource;

/**
 * @author CovertJaguar <http://www.railcraft.info/>
 */
@cpw.mods.fml.common.Optional.InterfaceList(
        value = {
                @cpw.mods.fml.common.Optional.Interface(iface = "ic2.api.energy.tile.IEnergySource", modid = "IC2"), })
public class TileIC2EmitterDelegate extends TileIC2Delegate implements IEnergySource {

    private final IEmitterDelegate delegate;

    public TileIC2EmitterDelegate(IEmitterDelegate delegate) {
        super(delegate.getTile());
        this.delegate = delegate;
    }

    @Override
    public boolean emitsEnergyTo(TileEntity receiver, ForgeDirection direction) {
        return delegate.emitsEnergyTo(receiver, direction);
    }

    @Override
    public double getOfferedEnergy() {
        return delegate.getOfferedEnergy();
    }

    @Override
    public void drawEnergy(double amount) {
        delegate.drawEnergy(amount);
    }

    @Override
    public int getSourceTier() {
        return delegate.getSourceTier();
    }
}

/*
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info This code is the property of CovertJaguar and may only be used
 * with explicit written permission unless otherwise specified on the license page at
 * http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.common.blocks.machine.epsilon;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;

import cpw.mods.fml.common.Optional;
import gregtech.api.interfaces.tileentity.IEnergyConnected;
import mods.railcraft.api.electricity.IElectricGrid;
import mods.railcraft.common.blocks.machine.IEnumMachine;
import mods.railcraft.common.blocks.machine.TileMachineBase;
import mods.railcraft.common.plugins.ic2.IC2Plugin;
import mods.railcraft.common.plugins.ic2.ISinkDelegate;
import mods.railcraft.common.plugins.ic2.TileIC2MultiEmitterDelegate;
import mods.railcraft.common.plugins.ic2.TileIC2SinkDelegate;
import mods.railcraft.common.util.misc.Game;

/**
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */

// This API exists in all GT versions, don't change to gregtech_nh
@Optional.InterfaceList(
        value = { @Optional.Interface(
                iface = "gregtech.api.interfaces.tileentity.IEnergyConnected",
                modid = "gregtech"), })
public class TileElectricFeeder extends TileMachineBase implements IElectricGrid, ISinkDelegate, IEnergyConnected {

    private final ChargeHandler chargeHandler = new ChargeHandler(this, ChargeHandler.ConnectType.BLOCK, 1);
    private TileEntity sinkDelegate;
    private boolean addedToIC2EnergyNet;

    @Override
    public IEnumMachine getMachineType() {
        return EnumMachineEpsilon.ELECTRIC_FEEDER;
    }

    @Override
    public IIcon getIcon(int side) {
        return getMachineType().getTexture(0);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (Game.isNotHost(getWorld())) return;

        if (!addedToIC2EnergyNet) {
            IC2Plugin.addTileToNet(getIC2Delegate());
            addedToIC2EnergyNet = true;
        }

        chargeHandler.tick();
    }

    private void dropFromNet() {
        if (addedToIC2EnergyNet) IC2Plugin.removeTileFromNet(getIC2Delegate());
    }

    @Override
    public void onChunkUnload() {
        super.onChunkUnload();
        dropFromNet();
    }

    @Override
    public void invalidate() {
        super.invalidate();
        dropFromNet();
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        chargeHandler.readFromNBT(data);
    }

    @Override
    public void writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        chargeHandler.writeToNBT(data);
    }

    @Override
    public ChargeHandler getChargeHandler() {
        return chargeHandler;
    }

    @Override
    public TileEntity getTile() {
        return this;
    }

    @Override
    public double getDemandedEnergy() {
        double chargeDifference = chargeHandler.getCapacity() - chargeHandler.getCharge();
        return chargeDifference > 0.0 ? chargeDifference : 0.0;
    }

    @Override
    public int getSinkTier() {
        return 3;
    }

    @Override
    public double injectEnergy(ForgeDirection directionFrom, double amount) {
        getChargeHandler().addCharge(amount);
        return 0.0;
    }

    @Override
    public boolean acceptsEnergyFrom(TileEntity emitter, ForgeDirection direction) {
        return !(emitter instanceof TileIC2MultiEmitterDelegate);
    }

    public TileEntity getIC2Delegate() {
        if (sinkDelegate == null) try {
            sinkDelegate = new TileIC2SinkDelegate(this);
        } catch (Throwable error) {
            Game.logErrorAPI("IndustrialCraft", error);
        }
        return sinkDelegate;
    }

    // no problem with these methods and GT6, don't change to gregtech_nh
    @Override
    @Optional.Method(modid = "gregtech")
    public byte getColorization() {
        return -1;
    }

    @Override
    @Optional.Method(modid = "gregtech")
    public byte setColorization(byte aColor) {
        return -1;
    }

    @Override
    @Optional.Method(modid = "gregtech")
    public long injectEnergyUnits(ForgeDirection side, long aVoltage, long aAmperage) {
        if (getDemandedEnergy() > aVoltage) {
            getChargeHandler().addCharge(aVoltage);
            return 1L;
        } else {
            return 0L;
        }
    }

    @Override
    @Optional.Method(modid = "gregtech")
    public boolean inputEnergyFrom(ForgeDirection side) {
        return true;
    }

    @Override
    @Optional.Method(modid = "gregtech")
    public boolean outputsEnergyTo(ForgeDirection side) {
        return false;
    }
}

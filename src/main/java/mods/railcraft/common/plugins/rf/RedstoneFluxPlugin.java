package mods.railcraft.common.plugins.rf;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import mods.railcraft.common.util.misc.AdjacentTileCache;

/**
 * Created by CovertJaguar on 5/12/2016 for Railcraft.
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public class RedstoneFluxPlugin {

    @cpw.mods.fml.common.Optional.Method(modid = "CoFHAPI|energy")
    public static int pushToTile(TileEntity tile, ForgeDirection side, int powerToTransfer) {
        if (canTileReceivePower(tile, side)) {
            IEnergyReceiver handler = (IEnergyReceiver) tile;
            if (powerToTransfer > 0) return handler.receiveEnergy(side, powerToTransfer, false);
        }
        return 0;
    }

    @cpw.mods.fml.common.Optional.Method(modid = "CoFHAPI|energy")
    public static int pushToTiles(IEnergyProvider provider, AdjacentTileCache tileCache, int pushPerSide) {
        int pushed = 0;
        for (ForgeDirection side : ForgeDirection.VALID_DIRECTIONS) {
            TileEntity tile = tileCache.getTileOnSide(side);
            if (canTileReceivePower(tile, side.getOpposite())) {
                IEnergyReceiver handler = (IEnergyReceiver) tile;
                int amountToPush = provider.extractEnergy(side, pushPerSide, true);
                if (amountToPush > 0) {
                    int amountPushed = handler.receiveEnergy(side.getOpposite(), amountToPush, false);
                    pushed += amountPushed;
                    provider.extractEnergy(side, amountPushed, false);
                }
            }
        }
        return pushed;
    }

    @cpw.mods.fml.common.Optional.Method(modid = "CoFHAPI|energy")
    public static boolean canTileReceivePower(TileEntity tile, ForgeDirection side) {
        if (tile instanceof IEnergyReceiver) {
            IEnergyReceiver handler = (IEnergyReceiver) tile;
            return handler.canConnectEnergy(side);
        }
        return false;
    }

    @cpw.mods.fml.common.Optional.Method(modid = "CoFHAPI|energy")
    public static Object createEnergyStorage(int par1) {
        return new EnergyStorage(par1);
    }

    @cpw.mods.fml.common.Optional.Method(modid = "CoFHAPI|energy")
    public static Object createEnergyStorage(int par1, int par2) {
        return new EnergyStorage(par1, par2);
    }

    @cpw.mods.fml.common.Optional.Method(modid = "CoFHAPI|energy")
    public static Object createEnergyStorage(int par1, int par2, int par3) {
        return new EnergyStorage(par1, par2, par3);
    }
}

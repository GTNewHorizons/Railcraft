package mods.railcraft.common.plugins.wdmla;

import com.gtnewhorizons.wdmla.api.IWDMlaClientRegistration;
import com.gtnewhorizons.wdmla.api.IWDMlaCommonRegistration;
import com.gtnewhorizons.wdmla.api.IWDMlaPlugin;
import com.gtnewhorizons.wdmla.api.WDMlaPlugin;
import com.gtnewhorizons.wdmla.plugin.universal.ItemStorageProvider;

import mods.railcraft.common.carts.EntityCartCargo;
import mods.railcraft.common.carts.EntityCartChest;
import mods.railcraft.common.carts.EntityCartTank;
import mods.railcraft.common.carts.EntityLocomotiveSteamSolid;

@WDMlaPlugin
public class RailcraftPlugin implements IWDMlaPlugin {

    @Override
    public void register(IWDMlaCommonRegistration registration) {
        registration.registerItemStorage(ItemStorageProvider.Extension.INSTANCE, EntityCartChest.class);
        registration.registerItemStorage(ItemStorageProvider.Extension.INSTANCE, EntityCartCargo.class);
        registration.registerItemStorage(ItemStorageProvider.Extension.INSTANCE, EntityLocomotiveSteamSolid.class);
        registration.registerFluidStorage(CartFluidProvider.INSTANCE, EntityCartTank.class);
        registration.registerFluidStorage(CartFluidProvider.INSTANCE, EntityLocomotiveSteamSolid.class);
    }

    @Override
    public void registerClient(IWDMlaClientRegistration registration) {
        registration.registerFluidStorageClient(CartFluidProvider.INSTANCE);
    }
}

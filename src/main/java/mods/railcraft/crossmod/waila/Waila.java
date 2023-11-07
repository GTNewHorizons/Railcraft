package mods.railcraft.crossmod.waila;

import cpw.mods.fml.common.event.FMLInterModComms;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;

public class Waila {

    public static void callbackRegister(IWailaRegistrar register) {
        final IWailaDataProvider wailaDataProvider = new RailCraftWailaDataProvider();
    }

    public static void init() {
        FMLInterModComms.sendMessage("Waila", "register", Waila.class.getName() + ".callbackRegister");
    }
}

package mods.railcraft.common.modules;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.Loader;
import mods.railcraft.common.util.misc.Game;

public class ModuleGT5 extends RailcraftModule {

    @Override
    public boolean canModuleLoad() {
        // gregapi is a GT6 marker, and this module is not compatible with that
        return Loader.isModLoaded("gregtech") && !Loader.isModLoaded("gregapi");
    }

    @Override
    public void initFirst() {
        Game.log(Level.INFO, "Overriding electric protection with GregTech hazmat system", this);
    }

    @Override
    public void printLoadError() {
        Game.log(Level.INFO, "Module disabled: {0}, no valid Gregtech version detected", this);
    }
}

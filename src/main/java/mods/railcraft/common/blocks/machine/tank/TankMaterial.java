package mods.railcraft.common.blocks.machine.tank;

import mods.railcraft.common.modules.ModuleManager.Module;

public enum TankMaterial {

    IRON(Module.TRANSPORT, "iron"),
    STEEL(Module.TRANSPORT, "steel");

    public final Module module;
    public final String name;

    private TankMaterial(Module module, String name) {
        this.module = module;
        this.name = name;
    }

}

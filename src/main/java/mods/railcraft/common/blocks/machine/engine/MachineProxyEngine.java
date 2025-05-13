package mods.railcraft.common.blocks.machine.engine;

import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.Machines;

public class MachineProxyEngine implements IMachineProxy {

    private EngineType engineType;

    public MachineProxyEngine(EngineType engineType) {
        this.engineType = engineType;
    }

    @Override
    public Machine getMachine() {
        return switch (engineType) {
            case HOBBY -> Machines.ENGINE_STEAM_HOBBY;
            case LOW -> Machines.ENGINE_STEAM_LOW;
            case HIGH -> Machines.ENGINE_STEAM_HIGH;
        };
    }
}

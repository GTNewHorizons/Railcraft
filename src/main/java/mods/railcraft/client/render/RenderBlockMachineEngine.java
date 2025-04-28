package mods.railcraft.client.render;

import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.Machines;
import mods.railcraft.common.blocks.machine.engine.EngineType;

public class RenderBlockMachineEngine extends BlockRenderer {
    public RenderBlockMachineEngine(EngineType engineType, RenderPneumaticEngine renderEngine) {
        super(getEngine(engineType).getBlock());
        addBlockRenderer(0, new DoNothingRenderer());
        addItemRenderer(0, renderEngine);
    }

    private static Machine getEngine(EngineType engineType) {
        return switch (engineType) {
            case HOBBY -> Machines.ENGINE_STEAM_HOBBY;
            case LOW -> Machines.ENGINE_STEAM_LOW;
            case HIGH -> Machines.ENGINE_STEAM_HIGH;
        };
    }
}

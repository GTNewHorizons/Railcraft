package mods.railcraft.common.blocks.machine.engine;

import net.minecraft.client.renderer.texture.IIconRegister;

import mods.railcraft.common.blocks.machine.IMachine;
import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.Machines;

public class MachineProxyEngine implements IMachineProxy {

    private EngineType engineType;

    public MachineProxyEngine(EngineType engineType) {
        this.engineType = engineType;
    }

    private Machine getMachine() {
        return switch (engineType) {
            case HOBBY -> Machines.ENGINE_STEAM_HOBBY;
            case LOW -> Machines.ENGINE_STEAM_LOW;
            case HIGH -> Machines.ENGINE_STEAM_HIGH;
        };
    }

    @Override
    public IMachine getMachine(int meta) {
        return getMachine();
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        getMachine().registerIcons(iconRegister);
    }
}

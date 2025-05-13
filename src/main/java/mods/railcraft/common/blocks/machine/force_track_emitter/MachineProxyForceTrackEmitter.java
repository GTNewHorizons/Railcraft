package mods.railcraft.common.blocks.machine.force_track_emitter;

import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.Machines;

public class MachineProxyForceTrackEmitter implements IMachineProxy {

    @Override
    public Machine getMachine() {
        return Machines.FORCE_TRACK_EMITTER;
    }
}

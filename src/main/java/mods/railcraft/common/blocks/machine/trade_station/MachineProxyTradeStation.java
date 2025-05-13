package mods.railcraft.common.blocks.machine.trade_station;

import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.Machines;

public class MachineProxyTradeStation implements IMachineProxy {

    @Override
    public Machine getMachine() {
        return Machines.TRADE_STATION;
    }
}

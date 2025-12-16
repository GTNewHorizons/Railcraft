package mods.railcraft.common.util.misc;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.ForgeChunkManager;

import mods.railcraft.api.core.WorldCoordinate;
import mods.railcraft.common.blocks.RailcraftTileEntity;
import mods.railcraft.common.plugins.forge.ChatPlugin;

public class TicketManager {

    public final Map<UUID, ForgeChunkManager.Ticket> tickets = new ConcurrentHashMap<>();
    private final Map<EntityPlayer, WorldCoordinate> sentinelPairingMap = new ConcurrentHashMap<>();

    public WorldCoordinate getTarget(EntityPlayer player) {
        return sentinelPairingMap.get(player);
    }

    public void setTarget(RailcraftTileEntity tile, EntityPlayer player) {
        sentinelPairingMap.put(player, new WorldCoordinate(tile));
        ChatPlugin.sendLocalizedChatFromServer(player, "railcraft.gui.anchor.pair.start", tile.getLocalizationTag());
    }

    public void removeTarget(EntityPlayer player) {
        sentinelPairingMap.remove(player);
    }

}

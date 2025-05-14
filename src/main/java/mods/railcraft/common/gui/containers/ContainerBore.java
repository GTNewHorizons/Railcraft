/*
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info This code is the property of CovertJaguar and may only be used
 * with explicit written permission unless otherwise specified on the license page at
 * http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.common.gui.containers;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.railcraft.common.carts.EntityTunnelBore;
import mods.railcraft.common.gui.slots.SlotBallast;
import mods.railcraft.common.gui.slots.SlotBore;
import mods.railcraft.common.gui.slots.SlotFuel;
import mods.railcraft.common.gui.slots.SlotTrack;

public class ContainerBore extends RailcraftContainer {

    private EntityTunnelBore bore;
    private int lastBurnTime;
    private int lastFuel;

    public ContainerBore(InventoryPlayer playerInv, EntityTunnelBore bore) {
        super(bore);
        this.bore = bore;

        addSlot(new SlotBore(bore, 0, 17, 36));

        for (int i = 0; i < 6; i++) {
            addSlot(new SlotFuel(bore, i + 1, 62 + i * 18, 36));
        }

        for (int i = 0; i < 9; i++) {
            addSlot(new SlotBallast(bore, i + 7, 8 + i * 18, 72));
        }

        for (int i = 0; i < 9; i++) {
            addSlot(new SlotTrack(bore, i + 16, 8 + i * 18, 108));
        }

        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 9; k++) {
                addSlot(new Slot(playerInv, k + i * 9 + 9, 8 + k * 18, 140 + i * 18));
            }
        }

        for (int i = 0; i < 9; i++) {
            addSlot(new Slot(playerInv, i, 8 + i * 18, 198));
        }
    }

    @Override
    public void addCraftingToCrafters(ICrafting icrafting) {
        super.addCraftingToCrafters(icrafting);
        icrafting.sendProgressBarUpdate(this, 0, bore.getBurnTime());
        icrafting.sendProgressBarUpdate(this, 1, bore.getFuel());
    }

    @Override
    public void sendUpdateToClient() {
        super.sendUpdateToClient();

        for (int var1 = 0; var1 < this.crafters.size(); ++var1) {
            ICrafting var2 = (ICrafting) this.crafters.get(var1);

            if (this.lastBurnTime != this.bore.getBurnTime()) {
                var2.sendProgressBarUpdate(this, 0, this.bore.getBurnTime());
            }

            if (this.lastFuel != this.bore.getFuel()) {
                var2.sendProgressBarUpdate(this, 1, this.bore.getFuel());
            }
        }

        this.lastBurnTime = this.bore.getBurnTime();
        this.lastFuel = this.bore.getFuel();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int value) {
        switch (id) {
            case 0:
                this.bore.setBurnTime(value);
                break;
            case 1:
                this.bore.setFuel(value);
                break;
        }
    }
}

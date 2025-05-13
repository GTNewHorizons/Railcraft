package mods.railcraft.common.blocks.machine.tank;

import java.util.HashMap;
import java.util.Map;

import mods.railcraft.common.fluids.FluidHelper;
import mods.railcraft.common.modules.ModuleAdvancedTanks;
import mods.railcraft.common.modules.ModuleManager.Module;

public enum TankMaterial {

    IRON(Module.TRANSPORT, "iron", 20F, 16 * FluidHelper.BUCKET_VOLUME),
    STEEL(Module.TRANSPORT, "steel", 25F, 32 * FluidHelper.BUCKET_VOLUME),
    ALUMINIUM(Module.ADVTANKS, "aluminium", 25F, ModuleAdvancedTanks.CAPACITY_PER_BLOCK_ALUMINIUM),
    STAINLESS(Module.ADVTANKS, "stainless", 25F, ModuleAdvancedTanks.CAPACITY_PER_BLOCK_STAINLESS),
    TITANIUM(Module.ADVTANKS, "titanium", 25F, ModuleAdvancedTanks.CAPACITY_PER_BLOCK_TITANIUM),
    TUNGSTENSTEEL(Module.ADVTANKS, "tungstensteel", 25F, ModuleAdvancedTanks.CAPACITY_PER_BLOCK_TUNGSTENSTEEL),
    PALLADIUM(Module.ADVTANKS, "palladium", 25F, ModuleAdvancedTanks.CAPACITY_PER_BLOCK_PALLADIUM),
    IRIDIUM(Module.ADVTANKS, "iridium", 25F, ModuleAdvancedTanks.CAPACITY_PER_BLOCK_IRIDIUM),
    OSMIUM(Module.ADVTANKS, "osmium", 25F, ModuleAdvancedTanks.CAPACITY_PER_BLOCK_OSMIUM),
    NEUTRONIUM(Module.ADVTANKS, "neutronium", 25F, ModuleAdvancedTanks.CAPACITY_PER_BLOCK_NEUTRONIUM);

    public final Module module;
    public final String name;
    public final float explosionResistance;
    public final int capacityPerBlock;

    private TankMaterial(Module module, String name, float explosionResistance, int capacityPerBlock) {
        this.module = module;
        this.name = name;
        this.explosionResistance = explosionResistance;
        this.capacityPerBlock = capacityPerBlock;
    }

    public static final Map<String, TankMaterial> NAME_MAP = new HashMap<String, TankMaterial>();
    static {
        for (TankMaterial material : values()) {
            NAME_MAP.put(material.name, material);
        }
    }
}

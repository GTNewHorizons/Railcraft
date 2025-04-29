package mods.railcraft.common.blocks.machine.tank;

import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.Machines;

public class Tanks {

    public static final MetalTank IRON = registerTank(TankMaterial.IRON);
    public static final MetalTank STEEL = registerTank(TankMaterial.STEEL);
    public static final MetalTank ALUMINIUM = registerTank(TankMaterial.ALUMINIUM);
    public static final MetalTank STAINLESS = registerTank(TankMaterial.STAINLESS);
    public static final MetalTank TITANIUM = registerTank(TankMaterial.TITANIUM);
    public static final MetalTank TUNGSTENSTEEL = registerTank(TankMaterial.TUNGSTENSTEEL);
    public static final MetalTank PALLADIUM = registerTank(TankMaterial.PALLADIUM);
    public static final MetalTank IRIDIUM = registerTank(TankMaterial.IRIDIUM);
    public static final MetalTank OSMIUM = registerTank(TankMaterial.OSMIUM);
    public static final MetalTank NEUTRONIUM = registerTank(TankMaterial.NEUTRONIUM);

    private static MetalTank registerTank(TankMaterial material) {
        if (material.module.isEnabled()) {
            Machine wall = getWall(material);
            if (wall != null && wall.isAvailable()) {
                return new MetalTank(
                        material,
                        wall.getBlock(),
                        getGauge(material).getBlock(),
                        getValve(material).getBlock());
            }
        }
        return null;
    }

    public static MetalTank getTank(TankMaterial material) {
        return switch (material) {
            case IRON -> IRON;
            case STEEL -> STEEL;
            case ALUMINIUM -> ALUMINIUM;
            case STAINLESS -> STAINLESS;
            case TITANIUM -> TITANIUM;
            case TUNGSTENSTEEL -> TUNGSTENSTEEL;
            case PALLADIUM -> PALLADIUM;
            case IRIDIUM -> IRIDIUM;
            case OSMIUM -> OSMIUM;
            case NEUTRONIUM -> NEUTRONIUM;
        };
    }

    public static Machine getWall(TankMaterial tankMaterial) {
        return Machines.tankWalls.get(tankMaterial);
    }

    public static Machine getValve(TankMaterial tankMaterial) {
        return Machines.tankValves.get(tankMaterial);
    }

    public static Machine getGauge(TankMaterial tankMaterial) {
        return Machines.tankGauges.get(tankMaterial);
    }
}

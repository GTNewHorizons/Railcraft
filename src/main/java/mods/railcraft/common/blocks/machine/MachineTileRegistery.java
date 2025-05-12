/*
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info This code is the property of CovertJaguar and may only be used
 * with explicit written permission unless otherwise specified on the license page at
 * http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.common.blocks.machine;

import cpw.mods.fml.common.registry.GameRegistry;
import mods.railcraft.common.blocks.machine.alpha.TileAnchorAdmin;
import mods.railcraft.common.blocks.machine.alpha.TileAnchorPassive;
import mods.railcraft.common.blocks.machine.alpha.TileAnchorPersonal;
import mods.railcraft.common.blocks.machine.alpha.TileAnchorWorld;
import mods.railcraft.common.blocks.machine.alpha.TileSmoker;
import mods.railcraft.common.blocks.machine.alpha.TileSteamOven;
import mods.railcraft.common.blocks.machine.alpha.TileSteamTurbine;
import mods.railcraft.common.blocks.machine.alpha.TileTradeStation;
import mods.railcraft.common.blocks.machine.blast_furnace.TileBlastFurnace;
import mods.railcraft.common.blocks.machine.boiler.TileBoilerFireboxFluid;
import mods.railcraft.common.blocks.machine.boiler.TileBoilerFireboxSolid;
import mods.railcraft.common.blocks.machine.boiler.TileBoilerTankHigh;
import mods.railcraft.common.blocks.machine.boiler.TileBoilerTankLow;
import mods.railcraft.common.blocks.machine.chest.TileChestMetals;
import mods.railcraft.common.blocks.machine.chest.TileChestVoid;
import mods.railcraft.common.blocks.machine.coke_oven.TileCokeOven;
import mods.railcraft.common.blocks.machine.electric_feeder.TileElectricFeeder;
import mods.railcraft.common.blocks.machine.electric_feeder.TileElectricFeederAdmin;
import mods.railcraft.common.blocks.machine.engine.TileEngineSteamHigh;
import mods.railcraft.common.blocks.machine.engine.TileEngineSteamHobby;
import mods.railcraft.common.blocks.machine.engine.TileEngineSteamLow;
import mods.railcraft.common.blocks.machine.engraving_bench.TileEngravingBench;
import mods.railcraft.common.blocks.machine.feed_station.TileFeedStation;
import mods.railcraft.common.blocks.machine.flux_transformer.TileFluxTransformer;
import mods.railcraft.common.blocks.machine.force_track_emitter.TileForceTrackEmitter;
import mods.railcraft.common.blocks.machine.gamma.TileDispenserCart;
import mods.railcraft.common.blocks.machine.gamma.TileDispenserTrain;
import mods.railcraft.common.blocks.machine.gamma.TileEnergyLoader;
import mods.railcraft.common.blocks.machine.gamma.TileEnergyUnloader;
import mods.railcraft.common.blocks.machine.gamma.TileFluidLoader;
import mods.railcraft.common.blocks.machine.gamma.TileFluidUnloader;
import mods.railcraft.common.blocks.machine.gamma.TileItemLoader;
import mods.railcraft.common.blocks.machine.gamma.TileItemLoaderAdvanced;
import mods.railcraft.common.blocks.machine.gamma.TileItemUnloader;
import mods.railcraft.common.blocks.machine.gamma.TileItemUnloaderAdvanced;
import mods.railcraft.common.blocks.machine.gamma.TileRFLoader;
import mods.railcraft.common.blocks.machine.gamma.TileRFUnloader;
import mods.railcraft.common.blocks.machine.rock_crusher.TileRockCrusher;
import mods.railcraft.common.blocks.machine.rolling_machine.TileRollingMachine;
import mods.railcraft.common.blocks.machine.sentinel.TileSentinel;
import mods.railcraft.common.blocks.machine.steam_producer.TileAdminSteamProducer;
import mods.railcraft.common.blocks.machine.steam_trap.TileSteamTrapAuto;
import mods.railcraft.common.blocks.machine.steam_trap.TileSteamTrapManual;
import mods.railcraft.common.blocks.machine.tank.TileTankGauge;
import mods.railcraft.common.blocks.machine.tank.TileTankValve;
import mods.railcraft.common.blocks.machine.tank.TileTankWall;
import mods.railcraft.common.blocks.machine.tank_water.TileTankWater;
import mods.railcraft.common.blocks.machine.wire.TileWire;

/**
 * @author CovertJaguar <http://www.railcraft.info>
 */
public class MachineTileRegistery {

    public static void registerTileEntities() {
        // Alpha
        GameRegistry.registerTileEntity(TileAnchorWorld.class, "RCWorldAnchorTile");
        GameRegistry.registerTileEntity(TileAnchorPersonal.class, "RCPersonalAnchorTile");
        GameRegistry.registerTileEntity(TileAnchorAdmin.class, "RCAdminAnchorTile");
        GameRegistry.registerTileEntity(TileFeedStation.class, "RCFeedStationTile");
        GameRegistry.registerTileEntity(TileTradeStation.class, "RCTradeStationTile");
        GameRegistry.registerTileEntity(TileSteamTurbine.class, "RCSteamTurbineTile");
        GameRegistry.registerTileEntity(TileBlastFurnace.class, "RCBlastFurnaceTile");
        GameRegistry.registerTileEntity(TileCokeOven.class, "RCCokeOvenTile");
        GameRegistry.registerTileEntity(TileRockCrusher.class, "RCRockCrusherTile");
        GameRegistry.registerTileEntity(TileRollingMachine.class, "RCRollingMachineTile");
        GameRegistry.registerTileEntity(TileTankWater.class, "RCWaterTankTile");
        GameRegistry.registerTileEntity(TileSteamOven.class, "RCSteamOvenTile");
        GameRegistry.registerTileEntity(TileSmoker.class, "RCSmokerTile");
        GameRegistry.registerTileEntity(TileSteamTrapManual.class, "RCSteamTrapManualTile");
        GameRegistry.registerTileEntity(TileSteamTrapAuto.class, "RCSteamTrapAutoTile");
        GameRegistry.registerTileEntity(TileAnchorPassive.class, "RCPassiveAnchorTile");

        GameRegistry.registerTileEntity(TileSentinel.class, "RCAnchorSentinelTile");
        GameRegistry.registerTileEntity(TileEngineSteamHobby.class, "RCEngineSteamHobby");
        GameRegistry.registerTileEntity(TileEngineSteamLow.class, "RCEngineSteamLow");
        GameRegistry.registerTileEntity(TileEngineSteamHigh.class, "RCEngineSteamHigh");
        GameRegistry.registerTileEntity(TileBoilerFireboxSolid.class, "RCBoilerFireboxSoildTile");
        GameRegistry.registerTileEntity(TileBoilerFireboxFluid.class, "RCBoilerFireboxLiquidTile");
        GameRegistry.registerTileEntity(TileBoilerTankLow.class, "RCBoilerTankLowTile");
        GameRegistry.registerTileEntity(TileBoilerTankHigh.class, "RCBoilerTankHighTile");
        GameRegistry.registerTileEntity(TileTankWall.class, "RCIronTankWallTile");
        GameRegistry.registerTileEntity(TileTankGauge.class, "RCIronTankGaugeTile");
        GameRegistry.registerTileEntity(TileTankValve.class, "RCIronTankValveTile");
        GameRegistry.registerTileEntity(TileChestVoid.class, "RCVoidChestTile");
        GameRegistry.registerTileEntity(TileChestMetals.class, "RCMetalsChestTile");

        // Gamma
        GameRegistry.registerTileEntity(TileDispenserCart.class, "RCMinecartDispenserTile");
        GameRegistry.registerTileEntity(TileEnergyLoader.class, "RCLoaderTileEnergy");
        GameRegistry.registerTileEntity(TileEnergyUnloader.class, "RCUnloaderTileEnergy");
        GameRegistry.registerTileEntity(TileDispenserTrain.class, "RCTrainDispenserTile");
        GameRegistry.registerTileEntity(TileItemLoader.class, "RCLoaderTile");
        GameRegistry.registerTileEntity(TileItemLoaderAdvanced.class, "RCLoaderAdvancedTile");
        GameRegistry.registerTileEntity(TileItemUnloader.class, "RCUnloaderTile");
        GameRegistry.registerTileEntity(TileItemUnloaderAdvanced.class, "RCUnloaderAdvancedTile");
        GameRegistry.registerTileEntity(TileFluidLoader.class, "RCLoaderTileLiquid");
        GameRegistry.registerTileEntity(TileFluidUnloader.class, "RCUnloaderTileLiquid");
        GameRegistry.registerTileEntity(TileRFLoader.class, "RCLoaderTileRF");
        GameRegistry.registerTileEntity(TileRFUnloader.class, "RCUnloaderTileRF");

        GameRegistry.registerTileEntity(TileWire.class, "RCWireTile");
        GameRegistry.registerTileEntity(TileElectricFeeder.class, "RCElectricFeederTile");
        GameRegistry.registerTileEntity(TileElectricFeederAdmin.class, "RCElectricFeederAdminTile");
        GameRegistry.registerTileEntity(TileAdminSteamProducer.class, "RCAdminSteamProducerTile");
        GameRegistry.registerTileEntity(TileFluxTransformer.class, "RCFluxTransformerTile");
        GameRegistry.registerTileEntity(TileForceTrackEmitter.class, "RCForceTrackEmitterTile");
        GameRegistry.registerTileEntity(TileEngravingBench.class, "RCEngravingBenchTile");
    }
}

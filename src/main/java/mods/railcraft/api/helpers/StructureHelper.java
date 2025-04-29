/*
 * ****************************************************************************** Copyright 2011-2015 CovertJaguar This
 * work (the API) is licensed under the "MIT" License, see LICENSE.md for details.
 * ***************************************************************************
 */

package mods.railcraft.api.helpers;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

import mods.railcraft.common.blocks.machine.tank.TankMaterial;

/**
 *
 * @author CovertJaguar <http://www.railcraft.info/>
 */
public interface StructureHelper {

    void placeBlastFurnace(World world, int x, int y, int z, ItemStack input, ItemStack output, ItemStack fuel);

    void placeCokeOven(World world, int x, int y, int z, int creosote, ItemStack input, ItemStack output);

    void placeFluidBoiler(World world, int x, int y, int z, int width, int height, boolean highPressure, int water,
            FluidStack fuel);

    void placeRockCrusher(World world, int x, int y, int z, int patternIndex, List<ItemStack> input,
            List<ItemStack> output);

    void placeSolidBoiler(World world, int x, int y, int z, int width, int height, boolean highPressure, int water,
            List<ItemStack> fuel);

    void placeSteamOven(World world, int x, int y, int z, List<ItemStack> input, List<ItemStack> output);

    void placeMultiTank(World world, int x, int y, int z, int patternIndex, TankMaterial material, FluidStack fluid);

    void placeWaterTank(World world, int x, int y, int z, int water);

    void placeFluxTransformer(World world, int x, int y, int z);
}

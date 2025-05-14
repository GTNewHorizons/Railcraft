/*
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info This code is the property of CovertJaguar and may only be used
 * with explicit written permission unless otherwise specified on the license page at
 * http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.client.render;

import java.util.EnumSet;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.common.util.ForgeDirection;

import com.gtnewhorizons.angelica.api.ThreadSafeISBRH;

import mods.railcraft.api.electricity.GridTools;
import mods.railcraft.api.electricity.IElectricGrid;
import mods.railcraft.api.electricity.IElectricGrid.ChargeHandler.ConnectType;
import mods.railcraft.client.render.RenderFakeBlock.RenderInfo;
import mods.railcraft.common.blocks.frame.BlockFrame;
import mods.railcraft.common.blocks.machine.Machines;
import mods.railcraft.common.blocks.machine.wire.TileWire;
import mods.railcraft.common.blocks.machine.wire.TileWire.AddonType;
import mods.railcraft.common.blocks.tracks.TrackTools;
import mods.railcraft.common.plugins.forge.WorldPlugin;

/**
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
@ThreadSafeISBRH(perThread = false)
public class RenderBlockWire extends BlockRenderer {

    private final RenderBlockFrame renderFrame;

    public RenderBlockWire() {
        super(Machines.WIRE.getBlock());
        if (BlockFrame.getBlock() != null) renderFrame = new RenderBlockFrame();
        else renderFrame = null;
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
            RenderBlocks renderblocks) {
        EnumSet<ForgeDirection> wireCons = EnumSet.noneOf(ForgeDirection.class);

        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
            TileEntity tile = WorldPlugin.getTileEntityOnSide(world, x, y, z, dir);
            if (tile instanceof TileWire) wireCons.add(dir);
        }

        EnumSet<ForgeDirection> plugCons = EnumSet.noneOf(ForgeDirection.class);

        EnumSet<ForgeDirection> search = EnumSet.allOf(ForgeDirection.class);
        search.remove(ForgeDirection.UNKNOWN);
        search.removeAll(wireCons);

        for (ForgeDirection dir : search) {
            TileEntity tile = WorldPlugin.getTileEntityOnSide(world, x, y, z, dir);
            if (tile instanceof IElectricGrid iElectricGrid
                    && iElectricGrid.getChargeHandler().getType() == ConnectType.BLOCK)
                plugCons.add(dir);
        }

        wireCons.addAll(plugCons);

        boolean powered = false;
        IElectricGrid above = GridTools.getGridObjectAt(world, x, y + 1, z);
        if (above != null && TrackTools.isRailBlockAt(world, x, y + 1, z)) {
            wireCons.add(ForgeDirection.UP);
            plugCons.add(ForgeDirection.UP);
            // renderPlatform(renderblocks, world, x, y, z, block);
            powered = true;
        }

        renderWire(renderblocks, world, x, y, z, block, wireCons);
        renderPlug(renderblocks, world, x, y, z, block, plugCons);

        TileEntity tile = WorldPlugin.getBlockTile(world, x, y, z);
        if (tile instanceof TileWire wire) {
            if (wire.getAddon() == AddonType.FRAME) {
                BlockFrame.poweredTexture = powered;
                renderFrame(renderblocks, world, x, y, z, block);
                BlockFrame.poweredTexture = false;
            }
        }

        block.setBlockBounds(0, 0, 0, 1, 1, 1);
        return true;
    }

    private void renderFrame(RenderBlocks renderblocks, IBlockAccess world, int x, int y, int z, Block block) {
        if (renderFrame != null) renderFrame.renderWorldBlock(
                world,
                x,
                y,
                z,
                BlockFrame.getBlock(),
                BlockFrame.getBlock().getRenderType(),
                renderblocks);
    }

    private void renderWire(RenderBlocks renderblocks, IBlockAccess world, int x, int y, int z, Block block,
            EnumSet<ForgeDirection> wireCons) {
        float pix = RenderTools.PIXEL;
        float max = 0.999F;
        float min = 0.001F;

        if (wireCons.isEmpty()) {
            block.setBlockBounds(6 * pix, 6 * pix, 6 * pix, 10 * pix, 10 * pix, 10 * pix);
            RenderTools.renderStandardBlock(renderblocks, block, x, y, z);
            block.setBlockBounds(0, 0, 0, 1, 1, 1);
            return;
        }

        boolean down = wireCons.contains(ForgeDirection.DOWN);
        boolean up = wireCons.contains(ForgeDirection.UP);
        if (down || up) {
            block.setBlockBounds(6 * pix, down ? min : 6 * pix, 6 * pix, 10 * pix, up ? max : 10 * pix, 10 * pix);
            RenderTools.renderStandardBlock(renderblocks, block, x, y, z);
        }

        boolean north = wireCons.contains(ForgeDirection.NORTH);
        boolean south = wireCons.contains(ForgeDirection.SOUTH);
        if (north || south) {
            block.setBlockBounds(
                    6 * pix - 0.0001f,
                    6 * pix - 0.0001f,
                    north ? min : 6 * pix - 0.0001f,
                    10 * pix + 0.0001f,
                    10 * pix + 0.0001f,
                    south ? max : 10 * pix + 0.0001f);
            RenderTools.renderStandardBlock(renderblocks, block, x, y, z);
        }

        boolean west = wireCons.contains(ForgeDirection.WEST);
        boolean east = wireCons.contains(ForgeDirection.EAST);
        if (west || east) {
            block.setBlockBounds(
                    west ? min : 6 * pix - 0.0002f,
                    6 * pix - 0.0002f,
                    6 * pix - 0.0002f,
                    east ? max : 10 * pix + 0.0002f,
                    10 * pix + 0.0002f,
                    10 * pix + 0.0002f);
            RenderTools.renderStandardBlock(renderblocks, block, x, y, z);
        }
    }

    private void renderPlug(RenderBlocks renderblocks, IBlockAccess world, int x, int y, int z, Block block,
            EnumSet<ForgeDirection> plugCons) {
        if (plugCons.isEmpty()) return;

        float pix = RenderTools.PIXEL;

        float center = 8 * pix;
        float length = 4 * pix;
        float width = 2 * pix;
        float thickness = 4 * pix;
        float[][] plugA = new float[3][2];
        float[][] plugB = new float[3][2];

        // X START - END
        plugA[0][0] = center - length;
        plugA[0][1] = center + length;
        // Y START - END
        plugA[1][0] = 0.001F;
        plugA[1][1] = thickness;
        // Z START - END
        plugA[2][0] = center - width;
        plugA[2][1] = center + width;

        // X START - END
        plugB[0][0] = center - width;
        plugB[0][1] = center + width;
        // Y START - END
        plugB[1][0] = 0.001F;
        plugB[1][1] = thickness;
        // Z START - END
        plugB[2][0] = center - length;
        plugB[2][1] = center + length;

        float[][] rotated;
        for (ForgeDirection dir : plugCons) {
            rotated = MatrixTransformations.deepClone(plugA);
            MatrixTransformations.transform(rotated, dir);
            block.setBlockBounds(
                    rotated[0][0],
                    rotated[1][0],
                    rotated[2][0],
                    rotated[0][1],
                    rotated[1][1],
                    rotated[2][1]);
            RenderTools.renderStandardBlock(renderblocks, block, x, y, z);

            rotated = MatrixTransformations.deepClone(plugB);
            MatrixTransformations.transform(rotated, dir);
            block.setBlockBounds(
                    rotated[0][0],
                    rotated[1][0],
                    rotated[2][0],
                    rotated[0][1],
                    rotated[1][1],
                    rotated[2][1]);
            RenderTools.renderStandardBlock(renderblocks, block, x, y, z);
        }
    }

    @Override
    public void renderItem(RenderBlocks renderblocks, ItemStack item, IItemRenderer.ItemRenderType renderType) {
        float pix = RenderTools.PIXEL;
        float max = 0.999F;
        float min = 0.001F;

        RenderInfo info = new RenderInfo();
        info.template = getBlock();
        info.setBlockBounds(6 * pix, min, 6 * pix, 10 * pix, max, 10 * pix);
        RenderFakeBlock.renderBlockOnInventory(renderblocks, info, 1);

        info.setBlockBounds(6 * pix - 0.0001f, 6 * pix - 0.0001f, min, 10 * pix + 0.0001f, 10 * pix + 0.0001f, max);
        RenderFakeBlock.renderBlockOnInventory(renderblocks, info, 1);

        info.setBlockBounds(min, 6 * pix - 0.0002f, 6 * pix - 0.0002f, max, 10 * pix + 0.0002f, 10 * pix + 0.0002f);
        RenderFakeBlock.renderBlockOnInventory(renderblocks, info, 1);
    }
}

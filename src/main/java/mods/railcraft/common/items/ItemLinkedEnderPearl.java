package mods.railcraft.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.railcraft.common.blocks.machine.epsilon.TileAnchorProvider;
import mods.railcraft.common.plugins.forge.ChatPlugin;
import mods.railcraft.common.plugins.forge.LocalizationPlugin;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.List;

public class ItemLinkedEnderPearl extends ItemRailcraft {

    public static final String NBT_PROVIDER_X = "providerX";
    public static final String NBT_PROVIDER_Y = "providerY";
    public static final String NBT_PROVIDER_Z = "providerZ";
    public static final String NBT_PROVIDER_DIM = "providerDim";

    public ItemLinkedEnderPearl() {
        setMaxStackSize(1);
        setUnlocalizedName("railcraft.linked.ender.pearl");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            if (player.isSneaking() && isLinked(stack)) {
                NBTTagCompound nbt = stack.getTagCompound();
                nbt.removeTag(NBT_PROVIDER_X);
                nbt.removeTag(NBT_PROVIDER_Y);
                nbt.removeTag(NBT_PROVIDER_Z);
                nbt.removeTag(NBT_PROVIDER_DIM);
                ChatPlugin.sendLocalizedChatFromServer(player, "railcraft.linked.ender.pearl.action.unlink");
            }
        }
        return stack;
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side,
            float hitX, float hitY, float hitZ) {
        if (world.isRemote || player.isSneaking()) return false;

        TileEntity tile = world.getTileEntity(x, y, z);
        if (!(tile instanceof TileAnchorProvider)) return false;

        NBTTagCompound nbt = stack.getTagCompound();
        if (nbt == null) {
            nbt = new NBTTagCompound();
            stack.setTagCompound(nbt);
        }
        nbt.setInteger(NBT_PROVIDER_X, x);
        nbt.setInteger(NBT_PROVIDER_Y, y);
        nbt.setInteger(NBT_PROVIDER_Z, z);
        nbt.setInteger(NBT_PROVIDER_DIM, world.provider.dimensionId);
        ChatPlugin.sendLocalizedChatFromServer(player, "railcraft.linked.ender.pearl.action.link", x, y, z, world.provider.dimensionId);
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean adv) {
        if (stack.hasTagCompound()) {
            NBTTagCompound nbt = stack.getTagCompound();
            if (nbt.hasKey(NBT_PROVIDER_DIM)) {
                int x = nbt.getInteger(NBT_PROVIDER_X);
                int y = nbt.getInteger(NBT_PROVIDER_Y);
                int z = nbt.getInteger(NBT_PROVIDER_Z);
                int dim = nbt.getInteger(NBT_PROVIDER_DIM);
                list.add(LocalizationPlugin.translate("railcraft.linked.ender.pearl.info.linked", x, y, z, dim));
            } else {
                list.add(LocalizationPlugin.translate("railcraft.linked.ender.pearl.info.unlinked"));
            }
        } else {
            list.add(LocalizationPlugin.translate("railcraft.linked.ender.pearl.info.unlinked"));
        }
    }

    @Override
    public boolean hasEffect(ItemStack stack, int pass) {
        return isLinked(stack);
    }

    public static boolean isLinked(ItemStack stack) {
        return stack != null && stack.hasTagCompound() && stack.getTagCompound().hasKey(NBT_PROVIDER_DIM);
    }
}

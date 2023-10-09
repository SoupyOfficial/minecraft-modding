package com.idtech.item;

import com.idtech.BaseMod;
import com.idtech.ModTab;
import com.idtech.block.BlockMod;
import com.idtech.block.ExampleSpecialBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;

public class RemoteDetonatorItem extends Item {
    private static final String TAG_KEY = BaseMod.MODID + ":blockPos";
    private static Properties properties = new Properties().tab(ModTab.INSTANCE);
    public static Item INSTANCE = new RemoteDetonatorItem(properties).setRegistryName("remotedetonator");

    public RemoteDetonatorItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        ItemStack itemStack = pContext.getItemInHand();
        BlockPos clickedPos = pContext.getClickedPos();

        return linkBlock(level, itemStack, clickedPos) ? InteractionResult.SUCCESS : InteractionResult.PASS;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);

        //Check if there is a valid linked block
        int[] coords = pPlayer.getItemInHand(pUsedHand).getOrCreateTag().getIntArray(TAG_KEY);

        if (coords == null || coords.length <= 0) {
            return InteractionResultHolder.pass(itemStack);
        }

        BlockPos linkedPos = new BlockPos(coords[0], coords[1], coords[2]);
        BlockState blockState = pLevel.getBlockState(linkedPos);

        if (blockState.is(BlockMod.REMOTE_DETONATION_BLOCK)) {
            pLevel.setBlock(linkedPos, Blocks.REDSTONE_BLOCK.defaultBlockState(), 11);
            TntBlock.explode(pLevel, linkedPos);
            unlinkBlock(itemStack);
            return InteractionResultHolder.success(itemStack);
        } else {
            unlinkBlock(itemStack);
            return InteractionResultHolder.pass(itemStack);
        }
    }

    private static void unlinkBlock(ItemStack itemStack) {
        itemStack.getOrCreateTag().putIntArray(TAG_KEY, new int[0]);
    }

    private static boolean linkBlock(Level level, ItemStack itemStack, BlockPos clickedPos) {
        if (!level.getBlockState(clickedPos).is(BlockMod.REMOTE_DETONATION_BLOCK)) {
            unlinkBlock(itemStack);
            return false;
        }

        itemStack.getOrCreateTag().putIntArray(TAG_KEY, List.of(clickedPos.getX(), clickedPos.getY(), clickedPos.getZ()));
        return true;
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     *
     * @param pStack
     * @param pLevel
     * @param pTooltipComponents
     * @param pIsAdvanced
     */
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        int[] coords = pStack.getOrCreateTag().getIntArray(TAG_KEY);

        if (coords != null && coords.length > 0) {
            pTooltipComponents.add(new TextComponent("X: " + coords[0] + ", Y: " + coords[1] + ", Z: " + coords[2]));
        }
    }
}
package com.idtech.item;

import com.idtech.ModTab;
import com.idtech.entity.ExplosionProjectile;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FireballWandItem extends Item {

    private static Properties properties = new Properties().tab(ModTab.INSTANCE);
    public static Item INSTANCE = new FireballWandItem(properties).setRegistryName("fireballwand");

    public FireballWandItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level levelIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemStack = playerIn.getItemInHand(handIn);

        levelIn.playSound((Player)null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.FIRECHARGE_USE, SoundSource.NEUTRAL, 0.5f, 0.4f / (levelIn.getRandom().nextFloat() * 0.4f + 0.8f));

        if(!levelIn.isClientSide) {
            ExplosionProjectile projectile = new ExplosionProjectile(levelIn, playerIn);
            projectile.setItem(itemStack);
            projectile.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), 0.0f, 1.5f, 1.0f);
            levelIn.addFreshEntity(projectile);
        }
        if (!playerIn.getAbilities().instabuild) {
            itemStack.shrink(1);
        }

        return InteractionResultHolder.sidedSuccess(itemStack, levelIn.isClientSide());
    }
}

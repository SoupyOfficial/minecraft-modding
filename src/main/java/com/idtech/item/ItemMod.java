package com.idtech.item;

import com.idtech.ModTab;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ItemMod {

    //BASIC ITEMS
    public static final Item STRUCTURE_GEL = ItemUtils.buildBasicItem("structuregel", ModTab.INSTANCE);
    public static final Item PIG_IRON = ItemUtils.buildBasicItem("pigiron", ModTab.INSTANCE);

    //FOODS
    public static FoodProperties TotallyNotPotionofPoison = (new FoodProperties.Builder().nutrition(5).saturationMod(1.4f).effect(new MobEffectInstance(MobEffects.HUNGER, 5000, 1), 1.0f).effect(new MobEffectInstance(MobEffects.HUNGER, 5000, 1), 1.0f).alwaysEat().build());
    //public static Item yummyFoodItem = ItemUtils.buildFoodItem("yummyfood", yummyFood);

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {

        //BASIC ITEMS
        event.getRegistry().register(STRUCTURE_GEL);
        event.getRegistry().register(PIG_IRON);
        event.getRegistry().register(RemoteDetonatorItem.INSTANCE);

        // ITEMS

        // TOOLS

        // FOOD

        // ARMOR

        //PROJECTILES
        event.getRegistry().register(FireballWandItem.INSTANCE);
    }
}

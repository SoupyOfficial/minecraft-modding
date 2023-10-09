package com.idtech.entity;

import com.idtech.BaseMod;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class Zombo extends Zombie {

    public Zombo(EntityType<? extends Zombie> entityIn, Level levelIn) {
        super(entityIn, levelIn);
    }

    public static EntityType<Zombo> TYPE = (EntityType<Zombo>)
            EntityType.Builder.of(Zombo::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8).build("zombo").setRegistryName(BaseMod.MODID, "zombo");

    public static Item EGG = EntityUtils.buildEntitySpawnEgg(TYPE, 0xfef9f8 , 0x383737);

}

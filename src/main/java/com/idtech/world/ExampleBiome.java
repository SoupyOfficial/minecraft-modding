package com.idtech.world;

import com.idtech.BaseMod;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.StructureFeatures;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.function.BiConsumer;

public class ExampleBiome {

    public static Biome INSTANCE = ExampleBiome(new MobSpawnSettings.Builder(), new BiomeGenerationSettings.Builder()).setRegistryName(BaseMod.MODID, "examplebiome");

    public static Biome ExampleBiome(MobSpawnSettings.Builder mobSpawn, BiomeGenerationSettings.Builder builder) {

        BiomeDefaultFeatures.addGroveTrees(builder);
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultOres(builder);

        //BiomeSpecialEffects.GrassColorModifier.create("newgrass", "newgrass", 0x0000FF);

        //StructureFeatures.registerStructures(BiConsumer);

        return (new Biome.BiomeBuilder())
                .precipitation(Biome.Precipitation.RAIN)
                .biomeCategory(Biome.BiomeCategory.PLAINS)
                .temperature(0F)
                .downfall(1F)
                .specialEffects((new BiomeSpecialEffects.Builder()
                        .waterColor(0x3F76E4)
                        .waterFogColor(0x050533)
                        .fogColor(0xC0D8FF)
                        .skyColor(0x00000000)
                        .build()
                ))
                .mobSpawnSettings(mobSpawn.build())
                .generationSettings(builder.build())
                .build();
    }
}

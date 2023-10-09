package com.idtech.world;

import com.idtech.BaseMod;
import com.idtech.block.BlockMod;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.StructureFeatures;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = BaseMod.MODID)
public class WorldMod {

    private static ResourceKey<Biome> EXAMPLE_BIOME = ResourceKey.create(Registry.BIOME_REGISTRY, ExampleBiome.INSTANCE.getRegistryName());
    private static ConfiguredFeature<?, ?> OVERWORLD_PIG_IRON_FEATURE = new ConfiguredFeature(Feature.ORE, new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, BlockMod.PIG_IRON_BLOCK.defaultBlockState(), 9));
    public static PlacedFeature OVERWORLD_PIG_IRON_PLACED_FEATURE = OVERWORLD_PIG_IRON_FEATURE.placed(List.of(
            CountPlacement.of(20),
            InSquarePlacement.spread(),
            HeightRangePlacement.triangle(
                    VerticalAnchor.absolute(-80),
                    VerticalAnchor.absolute(80)
            ),
            BiomeFilter.biome()
    ));

    public static void registerBiomes(final RegistryEvent.Register<Biome> event){
        event.getRegistry().register(ExampleBiome.INSTANCE);

    }

    public static void setupBiomes() {
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(EXAMPLE_BIOME, 9000));
    }



    @SubscribeEvent
    public static void addFeatures(BiomeLoadingEvent event) {
        FeatureUtils.register("pigiron_feature", OVERWORLD_PIG_IRON_FEATURE);
        PlacementUtils.register("pigiron_feature", OVERWORLD_PIG_IRON_PLACED_FEATURE);

        BiomeGenerationSettingsBuilder biomeGen = event.getGeneration();

        if(event.getName().getPath().equals(EXAMPLE_BIOME.location().getPath())) {
            biomeGen.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OVERWORLD_PIG_IRON_PLACED_FEATURE);
        }
    }
}
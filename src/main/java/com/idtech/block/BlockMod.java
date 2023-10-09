package com.idtech.block;


import com.idtech.BaseMod;
import com.idtech.ModTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
//import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class BlockMod {

    //Basic Block
    public static final Block CASTLE_WALL = BlockUtils.createBasicBlock("castlewall", Material.STONE);
    public static final Item CASTLE_WALL_ITEM = BlockUtils.createBlockItem(CASTLE_WALL, ModTab.INSTANCE);

    public static final Block TRANSPARENT_BLOCK = new Block(BlockBehaviour.Properties.of(Material.STONE)
            .noOcclusion()
            .strength(1f, 0.6f)
    ).setRegistryName(BaseMod.MODID,"transparent");
    public static final Item TRANSPARENT_BLOCK_ITEM = BlockUtils.createBlockItem(TRANSPARENT_BLOCK, ModTab.INSTANCE);

    public static final Block REMOTE_DETONATION_BLOCK = BlockUtils.createBasicBlock("remotedetonation", Material.STONE);
    public static final Item REMOTE_DETONATION_BLOCK_ITEM = BlockUtils.createBlockItem(REMOTE_DETONATION_BLOCK, ModTab.INSTANCE);

    public static final Block PIG_IRON_BLOCK = BlockUtils.createBasicBlock("pigironblock", Material.STONE, 3.5F);
    public static final Item PIG_IRON_BLOCK_ITEM = BlockUtils.createBlockItem(PIG_IRON_BLOCK, ModTab.INSTANCE);
    private static final String[] IMAGE_ARRAY = {"1", "2", "3", "4", "5"};
    private static final ImageMaker IMAGE_MAKER = new ImageMaker(IMAGE_ARRAY);

    @SubscribeEvent
    public static void registerBlockItems(RegistryEvent.Register<Item> event) {

        event.getRegistry().register(CASTLE_WALL_ITEM);
        event.getRegistry().register(TRANSPARENT_BLOCK_ITEM);
        event.getRegistry().register(ExampleSpecialBlock.ITEM);
        event.getRegistry().register(REMOTE_DETONATION_BLOCK_ITEM);
        event.getRegistry().register(PIG_IRON_BLOCK_ITEM);
        event.getRegistry().register(RedstoneLampBlock.ITEM);
        ImageMaker.registerBlockItems(event);

    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {

        event.getRegistry().register(CASTLE_WALL);
        event.getRegistry().register(TRANSPARENT_BLOCK);
        event.getRegistry().register(ExampleSpecialBlock.INSTANCE);
        event.getRegistry().register(REMOTE_DETONATION_BLOCK);
        event.getRegistry().register(PIG_IRON_BLOCK);
        event.getRegistry().register(RedstoneLampBlock.INSTANCE);
        ImageMaker.registerBlocks(event);

    }
}






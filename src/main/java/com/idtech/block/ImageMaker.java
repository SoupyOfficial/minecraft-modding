package com.idtech.block;

import com.idtech.ModTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ImageMaker {
    private final String[] imageArray;
    private static Block[] blocks = new Block[0];
    private static Item[] blockItems = new Item[0];

    public ImageMaker(String[] imageArray) {
        this.imageArray = imageArray;
        this.blocks = new Block[imageArray.length];
        this.blockItems = new Item[imageArray.length];
        createBlocksAndItems();
    }

    private void createBlocksAndItems() {
        for (int i = 0; i < imageArray.length; i++) {
            String imageName = imageArray[i];

            // Create the block
            Block block = BlockUtils.createBasicBlock(imageName, Material.STONE);
            blocks[i] = block;

            // Create the block item
            Item blockItem = BlockUtils.createBlockItem(block, ModTab.INSTANCE);
            blockItems[i] = blockItem;
        }
    }

    @SubscribeEvent
    public static void registerBlockItems(RegistryEvent.Register<Item> event) {
        // Loop through blockItems and register them
        for (Item blockItem : blockItems) {
            event.getRegistry().register(blockItem);
        }
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        // Loop through blocks and register them
        for (Block block : blocks) {
            event.getRegistry().register(block);
        }
    }
}

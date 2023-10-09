package com.idtech.block;

import com.idtech.BaseMod;
import com.idtech.ModTab;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RedstoneTorchBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.levelgen.RandomSource;
import net.minecraft.world.level.material.Material;

import javax.annotation.Nullable;

public class RedstoneLampBlock extends Block {
    public static final BooleanProperty LIT_PROPERTY = RedstoneTorchBlock.LIT;
    private static Properties DEFAULT_PROPERTIES = Properties.of(Material.STONE);

    public static Block INSTANCE = new RedstoneLampBlock(DEFAULT_PROPERTIES).setRegistryName(BaseMod.MODID, "redstonelamp");
    public static Item ITEM = BlockUtils.createBlockItem(INSTANCE, ModTab.INSTANCE);

    public RedstoneLampBlock(BlockBehaviour.Properties blockProperties) {
        super(blockProperties);
        this.registerDefaultState(this.defaultBlockState().setValue(LIT_PROPERTY, Boolean.valueOf(false)));
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext blockContext) {
        return this.defaultBlockState().setValue(LIT_PROPERTY, Boolean.valueOf(blockContext.getLevel().hasNeighborSignal(blockContext.getClickedPos())));
    }

    public void neighborChanged(BlockState blockState, Level world, BlockPos blockPos, Block neighborBlock, BlockPos neighborPos, boolean isMoving) {
        if (!world.isClientSide) {
            boolean hasRedstoneSignal = world.hasNeighborSignal(blockPos);
            if (!hasRedstoneSignal) {
                world.setBlock(blockPos, blockState.setValue(LIT_PROPERTY, false), 2);
            } else {
                boolean isLit = blockState.getValue(LIT_PROPERTY);
                if (isLit != hasRedstoneSignal) {
                    if (isLit) {
                        world.scheduleTick(blockPos, this, 4);
                    } else {
                        world.setBlock(blockPos, blockState.cycle(LIT_PROPERTY), 2);
                    }
                }
            }
        }
    }


    public void tick(BlockState blockState, ServerLevel world, BlockPos blockPos, RandomSource random) {
        if (blockState.getValue(LIT_PROPERTY) && !world.hasNeighborSignal(blockPos)) {
            world.setBlock(blockPos, blockState.cycle(LIT_PROPERTY), 2);
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT_PROPERTY);
    }
}


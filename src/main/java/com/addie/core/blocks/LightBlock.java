package com.addie.core.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class LightBlock extends Block {

    public static final BooleanProperty LIT = Properties.LIT;
    public static final EnumProperty<BlockHalf> HALF = Properties.BLOCK_HALF;

    private static final VoxelShape FLOOR_SHAPE = Block.createCuboidShape(5, 0, 5, 11, 6, 11);
    private static final VoxelShape CEILING_SHAPE = Block.createCuboidShape(5, 10, 5, 11, 16, 11);

    public LightBlock(Settings settings) {
        super(settings.luminance(state -> state.get(LIT) ? 10 : 0));
        setDefaultState(getStateManager().getDefaultState()
                .with(LIT, false)
                .with(HALF, BlockHalf.BOTTOM));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIT, HALF);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction side = ctx.getSide();
        if (side == Direction.DOWN) {
            return getDefaultState().with(HALF, BlockHalf.TOP);
        } else {
            return getDefaultState().with(HALF, BlockHalf.BOTTOM);
        }
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos,
                               Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (!world.isClient) {
            boolean powered = world.isReceivingRedstonePower(pos);
            if (state.get(LIT) != powered) {
                world.setBlockState(pos, state.with(LIT, powered), Block.NOTIFY_ALL);
            }
        }
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return state.get(HALF) == BlockHalf.TOP ? CEILING_SHAPE : FLOOR_SHAPE;
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return false;
    }
}

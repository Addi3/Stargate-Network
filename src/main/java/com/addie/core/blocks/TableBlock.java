package com.addie.core.blocks;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

import java.util.stream.Stream;

public class TableBlock extends Block {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;


    protected static final VoxelShape SHAPE_NORTH = Stream.of(
            Block.createCuboidShape(-1, 0, 0, 17, 13, 16),
            Block.createCuboidShape(-1, 0, -7, 17, 13, 0),
            Block.createCuboidShape(0, 0, -12, 16, 13, -7)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    protected static final VoxelShape SHAPE_SOUTH = Stream.of(
            Block.createCuboidShape(-1, 0, 0, 17, 13, 16),
            Block.createCuboidShape(-1, 0, 16, 17, 13, 23),
            Block.createCuboidShape(0, 0, 23, 16, 13, 28)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    protected static final VoxelShape SHAPE_WEST = Stream.of(
            Block.createCuboidShape(0, 0, -1, 16, 13, 17),
            Block.createCuboidShape(-7, 0, -1, 0, 13, 17),
            Block.createCuboidShape(-12, 0, 0, -7, 13, 16)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    protected static final VoxelShape SHAPE_EAST = Stream.of(
            Block.createCuboidShape(0, 0, -1, 16, 13, 17),
            Block.createCuboidShape(16, 0, -1, 23, 13, 17),
            Block.createCuboidShape(23, 0, 0, 28, 13, 16)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();


    public TableBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction dir = state.get(FACING);
        return switch (dir) {
            case NORTH -> SHAPE_NORTH;
            case SOUTH -> SHAPE_SOUTH;
            case EAST  -> SHAPE_EAST;
            case WEST  -> SHAPE_WEST;
            default -> VoxelShapes.fullCube();
        };
    }

    @Override
    public boolean isShapeFullCube(BlockState state, BlockView world, BlockPos pos) {
        return true;
    }


    @Override
    public VoxelShape getCullingShape(BlockState state, BlockView world, BlockPos pos) {
        return VoxelShapes.empty();
    }


    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}

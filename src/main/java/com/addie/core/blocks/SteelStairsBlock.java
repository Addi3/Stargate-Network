package com.addie.core.blocks;


import com.addie.core.blockentites.WallLightBlockEntity;
import com.llamalad7.mixinextras.lib.antlr.runtime.atn.SemanticContext;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
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
import org.jetbrains.annotations.Nullable;

public class SteelStairsBlock extends Block {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;


    protected static final VoxelShape SHAPE_NORTH = VoxelShapes.combineAndSimplify(
            Block.createCuboidShape(0, 0, 0, 16, 8, 8),
            Block.createCuboidShape(0, 8, 8, 16, 16, 16),
            BooleanBiFunction.OR
    );

    protected static final VoxelShape SHAPE_SOUTH = VoxelShapes.combineAndSimplify(
            Block.createCuboidShape(0, 0, 8, 16, 8, 16),
            Block.createCuboidShape(0, 8, 0, 16, 16, 8),
            BooleanBiFunction.OR
    );

    protected static final VoxelShape SHAPE_WEST = VoxelShapes.combineAndSimplify(
            Block.createCuboidShape(0, 0, 0, 8, 8, 16),
            Block.createCuboidShape(8, 8, 0, 16, 16, 16),
            BooleanBiFunction.OR
    );

    protected static final VoxelShape SHAPE_EAST = VoxelShapes.combineAndSimplify(
            Block.createCuboidShape(8, 0, 0, 16, 8, 16),
            Block.createCuboidShape(0, 8, 0, 8, 16, 16),
            BooleanBiFunction.OR
    );


    public SteelStairsBlock(Settings settings) {
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

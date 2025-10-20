package com.addie.core.blocks;


import com.addie.core.blockentites.LongFancyLightBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

public class LongFancyLightBlock extends BlockWithEntity implements BlockEntityProvider {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;


    protected static final VoxelShape SHAPE_NORTH = Block.createCuboidShape(-16, 15, -8, 32, 31, 24);
    protected static final VoxelShape SHAPE_SOUTH = Block.createCuboidShape(-16, 15, -8, 32, 31, 24);
    protected static final VoxelShape SHAPE_WEST  = Block.createCuboidShape(-8, 15, -16, 24, 31, 32);
    protected static final VoxelShape SHAPE_EAST  = Block.createCuboidShape(-8, 15, -16, 24, 31, 32);

    public LongFancyLightBlock(Settings settings) {
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

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new LongFancyLightBlockEntity(pos, state);
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

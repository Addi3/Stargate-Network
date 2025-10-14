package com.addie.core.blocks;

import com.addie.core.StargateNetworkBlockEntityTypes;
import com.addie.core.StargateNetworkSounds;
import com.addie.core.blockentites.ElevatorDoorBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ElevatorDoorBlock extends Block implements BlockEntityProvider  {
    public static final BooleanProperty OPEN = BooleanProperty.of("open");
	public  ElevatorDoorBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(OPEN, false));
    }

    protected static final VoxelShape SHAPE= Block.createCuboidShape(
            -16, 0, 0, 32, 48, 16
    );

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return state.get(OPEN) ? VoxelShapes.empty() : SHAPE;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(OPEN);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.getBlockEntity(pos) instanceof ElevatorDoorBlockEntity be) {

            if (!world.isClient) { // Server-side only
                boolean isOpen = state.get(ElevatorDoorBlock.OPEN);

                world.setBlockState(pos, state.with(ElevatorDoorBlock.OPEN, !isOpen), Block.NOTIFY_ALL);

                world.playSound(
                        null,
                        pos,
                        StargateNetworkSounds.BLAST_DOOR_SMALL,
                        SoundCategory.BLOCKS,
                        3.0f,
                        1.0f
                );
            }


            be.useOn(world, player.isSneaking(), player);
            return ActionResult.SUCCESS;
        }

        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return StargateNetworkBlockEntityTypes.ELEVATOR_DOOR_BLOCK.instantiate(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull World world, @NotNull BlockState state,
                                                                  @NotNull BlockEntityType<T> type) {
        return (world1, blockPos, blockState, ticker) -> {
            if (ticker instanceof ElevatorDoorBlockEntity be) {
                be.tick(world, blockPos, blockState, be);
            }
        };
    }
}


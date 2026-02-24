package com.addie.core.blocks;

import com.addie.StargateNetwork;
import com.addie.core.StargateNetworkItems;
import com.addie.core.StargateNetworkSounds;
import com.addie.core.blockentites.AncientObeliskBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ObeliskBlock extends BlockWithEntity {

    protected static final VoxelShape SHAPE = Block.createCuboidShape(0, 0, 0, 16, 32, 16);
    public static final BooleanProperty POWERED = BooleanProperty.of("powered");

    public ObeliskBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(POWERED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new AncientObeliskBlockEntity(pos, state);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, Hand hand, BlockHitResult hit) {

        ItemStack stack = player.getStackInHand(hand);

        if (!world.isClient && stack.getItem() == StargateNetworkItems.ANCIENT_GLASS_CRYSTAL) {
            boolean powered = !state.get(POWERED);
            world.setBlockState(pos, state.with(POWERED, powered));
            world.playSound(null, pos, SoundEvents.BLOCK_RESPAWN_ANCHOR_SET_SPAWN,
                    SoundCategory.BLOCKS, 1f, 1f);

            if (!player.isCreative()) {
                stack.decrement(1);
            }

            return ActionResult.SUCCESS;
        }

        BlockEntity be = world.getBlockEntity(pos);
        if (!(be instanceof AncientObeliskBlockEntity obelisk)) return ActionResult.PASS;

        if (obelisk.isPowered() && obelisk.getLinkedObelisk() != null) {
            BlockPos target = obelisk.getLinkedObelisk();

            BlockPos[] sides = { target.north(), target.south(), target.east(), target.west() };
            BlockPos safePos = null;
            for (BlockPos side : sides) {
                if (world.isAir(side) && world.isAir(side.up())) {
                    safePos = side;
                    break;
                }
            }

            if (safePos != null) {
                player.teleport(safePos.getX() + 0.5, safePos.getY(), safePos.getZ() + 0.5);
            }

            if (world instanceof ServerWorld serverWorld) {
                for (int i = 0; i < 10; i++) {
                    serverWorld.spawnParticles(
                            StargateNetwork.TRANSPORT_BEAM,
                            pos.getX() + 0.5 + (serverWorld.random.nextDouble() - 0.5),
                            pos.getY() + 1.0 + serverWorld.random.nextDouble() * 0.5,
                            pos.getZ() + 0.5 + (serverWorld.random.nextDouble() - 0.5),
                            5, 0.5, 0.5, 0.5, 0.0
                    );

                    serverWorld.spawnParticles(
                            StargateNetwork.TRANSPORT_BEAM,
                            target.getX() + 0.5 + (serverWorld.random.nextDouble() - 0.5),
                            target.getY() + 1.0 + serverWorld.random.nextDouble() * 0.5,
                            target.getZ() + 0.5 + (serverWorld.random.nextDouble() - 0.5),
                            5, 0.5, 0.5, 0.5, 0.0
                    );
                }
            }
            world.playSound(null, pos, StargateNetworkSounds.OBELISK_TELEPORT, SoundCategory.BLOCKS, 1f, 1f);
            world.playSound(null, target, StargateNetworkSounds.OBELISK_TELEPORT, SoundCategory.BLOCKS, 1f, 1f);
        }

        return ActionResult.SUCCESS;
    }
}

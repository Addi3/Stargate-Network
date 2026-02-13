package com.addie.core.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import org.jetbrains.annotations.Nullable;

public class WaystoneBlock extends Block {

    protected static final VoxelShape SHAPE= Block.createCuboidShape(
            0, 0, 0, 16, 32, 16
    );


    public WaystoneBlock(Settings settings) {
        super(settings);
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
    public boolean isShapeFullCube(BlockState state, BlockView world, BlockPos pos) {
        return true;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, Hand hand, BlockHitResult hit) {

        world.playSound(null, pos, SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS, 3.0f, 1.0f
        );

        if (!world.isClient()) {

            if (world instanceof ServerWorld serverWorld) {

                serverWorld.spawnParticles(
                        ParticleTypes.FLASH,
                        pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5,
                        5,
                        0.5, 0.5, 0.5,
                        0.0
                );
            }

            BlockPos nearest = WaystoneManager.getNearest(pos, 100);

            if (nearest != null) {
                BlockPos[] sides = {
                        nearest.north(),
                        nearest.south(),
                        nearest.east(),
                        nearest.west()
                };

                for (BlockPos sidePos : sides) {
                    if (world.isAir(sidePos) && world.isAir(sidePos.up())) {
                        player.teleport(sidePos.getX() + 0.5, sidePos.getY(), sidePos.getZ() + 0.5);
                        break;
                    }
                }
            }
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, net.minecraft.item.ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        if (!world.isClient()) {
            WaystoneManager.add(pos);
        }
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient()) {
            WaystoneManager.remove(pos);
        }
        super.onBreak(world, pos, state, player);
    }
}

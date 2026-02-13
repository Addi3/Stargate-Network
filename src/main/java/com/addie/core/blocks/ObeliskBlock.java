package com.addie.core.blocks;

import com.addie.core.StargateNetworkItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
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
import net.minecraft.client.item.TooltipContext;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class ObeliskBlock extends Block {

    public static final BooleanProperty POWERED = BooleanProperty.of("powered");
    public static final BooleanProperty ACTIVE = BooleanProperty.of("active");

    protected static final VoxelShape SHAPE = Block.createCuboidShape(0, 0, 0, 16, 32, 16);

    public ObeliskBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(POWERED, false).with(ACTIVE, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWERED, ACTIVE);
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
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(hand);

        if (player.isSneaking() && state.get(POWERED)&& world instanceof ServerWorld serverWorld) {
            if (!world.isClient()) {
                boolean newActive = !state.get(ACTIVE);
                serverWorld.spawnParticles(
                        ParticleTypes.ENCHANT,
                        pos.getX() + 0.5, pos.getY() + 1.8, pos.getZ() + 0.5,
                        20,
                        0.5, 0.5, 0.5,
                        0.0
                );
                world.setBlockState(pos, state.with(ACTIVE, newActive), Block.NOTIFY_ALL);
                world.playSound(null, pos,
                        newActive ? SoundEvents.BLOCK_BEACON_POWER_SELECT : SoundEvents.BLOCK_BEACON_ACTIVATE,
                        SoundCategory.BLOCKS,
                        1.5f, 1.0f);
            }
            return ActionResult.SUCCESS;
        }

        if (!state.get(POWERED) && stack.isOf(StargateNetworkItems.ZPM) && world instanceof ServerWorld serverWorld) {
            if (!world.isClient()) {

                world.setBlockState(pos, state.with(POWERED, true), Block.NOTIFY_ALL);
                world.playSound(null, pos,
                        SoundEvents.BLOCK_RESPAWN_ANCHOR_SET_SPAWN,
                        SoundCategory.BLOCKS,
                        2.0f, 1.0f);

                serverWorld.spawnParticles(
                        ParticleTypes.ENCHANT,
                        pos.getX() + 0.5, pos.getY() + 1.8, pos.getZ() + 0.5,
                        20,
                        0.5, 0.5, 0.5,
                        0.0
                );
                if (!player.isCreative()) {
                    stack.decrement(1);
                }
            }
            return ActionResult.SUCCESS;
        }

        if (!state.get(POWERED) || !state.get(ACTIVE)) {
            return ActionResult.SUCCESS;
        }

        if (!world.isClient() && world instanceof ServerWorld serverWorld && !player.isSneaking()) {
            world.playSound(null, pos,
                    /// TODO- replace with actual sound from the show!
                    SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE,
                    SoundCategory.BLOCKS,
                    2.0f, 1.0f);
            serverWorld.spawnParticles(
                    ParticleTypes.FLASH,
                    pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5,
                    5,
                    0.5, 0.5, 0.5,
                    0.0
            );
            ObeliskManager manager = new ObeliskManager(serverWorld);
            BlockPos nearest = manager.getNearest(pos, 50);
            if (nearest != null) {
                BlockPos[] sides = {nearest.north(), nearest.south(), nearest.east(), nearest.west()};
                for (BlockPos sidePos : sides) {
                    if (world.getBlockState(sidePos).isAir() && world.getBlockState(sidePos.up()).isAir()) {
                        player.teleport(sidePos.getX() + 0.5, sidePos.getY(), sidePos.getZ() + 0.5);
                        break;
                    }
                }
            }
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        if (!world.isClient() && world instanceof ServerWorld serverWorld) {
            ObeliskManager manager = new ObeliskManager(serverWorld);
            manager.add(pos);
        }
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient() && world instanceof ServerWorld serverWorld) {
            ObeliskManager manager = new ObeliskManager(serverWorld);
            manager.remove(pos);
        }
        super.onBreak(world, pos, state, player);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        super.appendTooltip(stack, world, tooltip, options);
        ///  Should say smth like "Range Limit 50 blocks"
        tooltip.add(Text.translatable("block.tooltip.obelisk").formatted(Formatting.GOLD));

    }

}

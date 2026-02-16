package com.addie.core.blocks;

import com.addie.core.StargateNetworkItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
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
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class ZPMHubBlock extends Block {

    public static final BooleanProperty CONTAINS_ZPM = BooleanProperty.of("contains_zpm");
    public static final BooleanProperty CONTAINS_OVERCHARGED_ZPM = BooleanProperty.of("contains_overcharged_zpm");
    public static final BooleanProperty CONTAINS_DEPLEATED_ZPM = BooleanProperty.of("contains_depleated_zpm");

    protected static final VoxelShape SHAPE = Block.createCuboidShape(2, 0, 2, 14, 16, 14);

    public ZPMHubBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(CONTAINS_ZPM, false)
                .with(CONTAINS_OVERCHARGED_ZPM, false)
                .with(CONTAINS_DEPLEATED_ZPM, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(CONTAINS_ZPM, CONTAINS_OVERCHARGED_ZPM, CONTAINS_DEPLEATED_ZPM);
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
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, Hand hand, BlockHitResult hit) {

        ItemStack heldStack = player.getStackInHand(hand);
        Item heldItem = heldStack.getItem();

        if (!heldStack.isEmpty()) {

            BooleanProperty property = null;

            if (heldItem == StargateNetworkItems.ZPM && !state.get(CONTAINS_ZPM)) {
                property = CONTAINS_ZPM;
            } else if (heldItem == StargateNetworkItems.OVERCHARGED_ZPM && !state.get(CONTAINS_OVERCHARGED_ZPM)) {
                property = CONTAINS_OVERCHARGED_ZPM;
            } else if (heldItem == StargateNetworkItems.DEPLETED_ZPM && !state.get(CONTAINS_DEPLEATED_ZPM)) {
                property = CONTAINS_DEPLEATED_ZPM;
            }
            if (property != null) {
                if (!world.isClient) {
                    world.playSound(null, pos, SoundEvents.UI_STONECUTTER_TAKE_RESULT,
                            SoundCategory.BLOCKS, 1f, 1f);

                    world.setBlockState(pos, state.with(property, true));
                    if (property == CONTAINS_OVERCHARGED_ZPM) {
                        world.scheduleBlockTick(pos, this, 40); // 2 seconds delay
                    }

                    if (!player.getAbilities().creativeMode) {
                        heldStack.decrement(1);
                    }
                }
                return ActionResult.SUCCESS;
            }
        }
        if (heldStack.isEmpty()) {

            BooleanProperty property = null;
            Item itemToDrop = null;

            if (state.get(CONTAINS_ZPM)) {
                property = CONTAINS_ZPM;
                itemToDrop = StargateNetworkItems.ZPM;
            } else if (state.get(CONTAINS_OVERCHARGED_ZPM)) {
                property = CONTAINS_OVERCHARGED_ZPM;
                itemToDrop = StargateNetworkItems.OVERCHARGED_ZPM;
            } else if (state.get(CONTAINS_DEPLEATED_ZPM)) {
                property = CONTAINS_DEPLEATED_ZPM;
                itemToDrop = StargateNetworkItems.DEPLETED_ZPM;
            }

            if (property != null) {
                if (!world.isClient) {
                    world.playSound(null, pos, SoundEvents.UI_STONECUTTER_TAKE_RESULT,
                            SoundCategory.BLOCKS, 1f, 1.5f);

                    world.setBlockState(pos, state.with(property, false));
                    Block.dropStack(world, pos, new ItemStack(itemToDrop));
                }
                return ActionResult.SUCCESS;
            }
        }

        return ActionResult.PASS;
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return true;
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        if (state.get(CONTAINS_DEPLEATED_ZPM)) return 0;
        return (state.get(CONTAINS_ZPM) || state.get(CONTAINS_OVERCHARGED_ZPM)) ? 15 : 0;
    }

    @Override
    public int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        if (state.get(CONTAINS_DEPLEATED_ZPM)) return 0;
        return (state.get(CONTAINS_ZPM) || state.get(CONTAINS_OVERCHARGED_ZPM)) ? 15 : 0;
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(CONTAINS_OVERCHARGED_ZPM)) {

            world.createExplosion(
                    null,
                    pos.getX() + 0.5,
                    pos.getY() + 0.5,
                    pos.getZ() + 0.5,
                    100.0f,
                    World.ExplosionSourceType.BLOCK
            );

            world.removeBlock(pos, false);
        }
    }


}

package com.addie.core.blockentites;


import com.addie.StargateNetwork;
import com.addie.core.StargateNetworkBlockEntityTypes;
import com.addie.core.blocks.ElevatorDoorBlock;
import dev.amble.lib.animation.AnimatedBlockEntity;
import dev.amble.lib.client.bedrock.BedrockAnimationReference;
import dev.amble.lib.client.bedrock.BedrockModelReference;
import lombok.Getter;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ElevatorDoorBlockEntity  extends BlockEntity implements AnimatedBlockEntity, BlockEntityTicker<ElevatorDoorBlockEntity> {
    private static final BedrockModelReference MODEL = new BedrockModelReference(StargateNetwork.MOD_ID, "elevator_door_block");

    @Getter
    private final AnimationState animationState = new AnimationState();
    @Getter
    private int age = 0;

    public ElevatorDoorBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public ElevatorDoorBlockEntity(BlockPos pos, BlockState state) {
        this(StargateNetworkBlockEntityTypes.ELEVATOR_DOOR_BLOCK, pos, state);
    }

    @Override
    public String getModId() {
        return StargateNetwork.MOD_ID;
    }

    @Override
    public String getTexturePrefix() {
        return "block";
    }

    @Override
    public @Nullable BedrockModelReference getModel() {
        return MODEL;
    }

    @Override
    public boolean hasEmission() {
        return false;
    }

    @Override
    public void tick(World world, BlockPos pos, BlockState state, ElevatorDoorBlockEntity blockEntity) {
        age++;
    }

    public void useOn(World world, BlockPos pos, PlayerEntity player) {
        if (world.isClient) return;

        BlockState state = world.getBlockState(pos);

        if (state.get(ElevatorDoorBlock.OPEN)) {
            this.playAnimation(new BedrockAnimationReference("elevator_door_block", "animation.model.open"));
        } else {
            this.playAnimation(new BedrockAnimationReference("elevator_door_block", "animation.model.close"));
        }
    }

}


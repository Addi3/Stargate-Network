package com.addie.renderers;

import com.addie.StargateNetwork;
import com.addie.core.blockentites.FancyLightBlockEntity;
import com.addie.core.blocks.FancyLightBlock;
import com.addie.models.FancyLightModel;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;

public class ElevatorDoorRenderer<T extends ElevatorDoorBlockEntity> implements BlockEntityRenderer<T> {


    @Override
    public void render(T entity, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light, int overlay) {

        BlockState blockState = entity.getCachedState();
        if (!(blockState.getBlock() instanceof ElevatorDoorBlock)) {
            return;
        }

        Direction direction = blockState.get(ElevatorDoorBlock.FACING);

        
        float rotationDegrees = switch (direction) {
            case NORTH -> 180f;
            case SOUTH -> 0f;
            case WEST -> 90f;
            case EAST -> -90f;
            default -> 0f;
        };

        matrices.push();
        matrices.translate(0.5f, 1.5f, 0.5f);
        matrices.multiply(RotationAxis.NEGATIVE_Y.rotationDegrees(rotationDegrees));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180));

        float scale = entity.getScale();
        matrices.scale(scale, scale, scale);

        matrices.pop();
    }
}

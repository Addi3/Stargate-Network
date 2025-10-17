package com.addie.renderers;

import com.addie.StargateNetwork;
import com.addie.core.blockentites.LongFancyLightBlockEntity;
import com.addie.core.blocks.FancyLightBlock;
import com.addie.core.blocks.LongFancyLightBlock;
import com.addie.models.LongFancyLightModel;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;

public class LongFancyLightRenderer<T extends LongFancyLightBlockEntity> implements BlockEntityRenderer<T> {

    public static final Identifier LIGHT_TEXTURE = new Identifier(
            StargateNetwork.MOD_ID, "textures/blockentities/fancy_lights/long_fancy_light.png");
    public static final Identifier EMISSIVE_LIGHT_TEXTURE = new Identifier(
            StargateNetwork.MOD_ID, "textures/blockentities/fancy_lights/long_fancy_light_emission.png");

    private final LongFancyLightModel longfancylightModel;

    public LongFancyLightRenderer(BlockEntityRendererFactory.Context ctx) {
        this.longfancylightModel = new LongFancyLightModel(LongFancyLightModel.getTexturedModelData().createModel());
    }

    @Override
    public void render(T entity, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light, int overlay) {

        BlockState blockState = entity.getCachedState();
        if (!(blockState.getBlock() instanceof LongFancyLightBlock)) {
            return;
        }

        Direction direction = blockState.get(LongFancyLightBlock.FACING);

        
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


        this.longfancylightModel.render(matrices,
                vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(LIGHT_TEXTURE)),
                light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);


        this.longfancylightModel.render(matrices,
                vertexConsumers.getBuffer(RenderLayer.getEntityTranslucentEmissive(EMISSIVE_LIGHT_TEXTURE)),
                0xF000F00, overlay, 1.0F, 1.0F, 1.0F, 1.0F);

        matrices.pop();
    }
}

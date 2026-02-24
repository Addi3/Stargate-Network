package com.addie.renderers;

import com.addie.StargateNetwork;
import com.addie.core.entites.PuddleJumperEntity;
import com.addie.models.PuddleJumperModel;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class PuddleJumperRenderer extends LivingEntityRenderer<PuddleJumperEntity, PuddleJumperModel> {

    private static final Identifier TEXTURE =
            new Identifier(StargateNetwork.MOD_ID, "textures/entity/puddle_jumper.png");
    private static final Identifier EMISSIVE_TEXTURE =
            new Identifier(StargateNetwork.MOD_ID, "textures/entity/puddle_jumper_emissive.png");

    public PuddleJumperRenderer(EntityRendererFactory.Context context) {
        super(context, new PuddleJumperModel(PuddleJumperModel.getTexturedModelData().createModel()), 1.5f);
    }

    @Override
    public void render(PuddleJumperEntity entity, float entityYaw, float tickDelta,
                       MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {

        matrices.push();

        float yaw = entity.prevYaw + (entity.getYaw() - entity.prevYaw) * tickDelta;

        matrices.multiply(net.minecraft.util.math.RotationAxis.POSITIVE_Y.rotationDegrees(-yaw));

        float scale = 2.8f;
        matrices.scale(scale, scale, scale);

        super.render(entity, entityYaw, tickDelta, matrices, vertexConsumers, light);

        matrices.push();
        matrices.multiply(net.minecraft.util.math.RotationAxis.POSITIVE_Y.rotationDegrees(0));
        matrices.multiply(net.minecraft.util.math.RotationAxis.POSITIVE_X.rotationDegrees(180));

        this.model.render(
                matrices,
                vertexConsumers.getBuffer(RenderLayer.getEntityTranslucentEmissive(EMISSIVE_TEXTURE)),
                0xF000F0,
                0,       
                1f, 1f, 1f, 1f
        );

        matrices.pop();
        matrices.pop();
    }

    @Override
    public Identifier getTexture(PuddleJumperEntity entity) {
        return TEXTURE;
    }
}
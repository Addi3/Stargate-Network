package com.addie.renderers;

import com.addie.StargateNetwork;
import com.addie.core.entites.PuddleJumperEntity;
import com.addie.models.PuddleJumperModel;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class PuddleJumperRenderer extends LivingEntityRenderer<PuddleJumperEntity, PuddleJumperModel> {

    private static final Identifier TEXTURE =
            new Identifier(StargateNetwork.MOD_ID, "textures/entity/puddle_jumper.png");

    private static final Identifier EMISSIVE_TEXTURE =
            new Identifier(StargateNetwork.MOD_ID, "textures/entity/puddle_jumper_emissive.png");

    private static final float SCALE = 2.8f;

    public PuddleJumperRenderer(EntityRendererFactory.Context context) {
        super(context, new PuddleJumperModel(PuddleJumperModel.getTexturedModelData().createModel()), 1.5f);

        this.addFeature(new EyesFeatureRenderer<PuddleJumperEntity, PuddleJumperModel>(this) {
            @Override
            public RenderLayer getEyesTexture() {
                return RenderLayer.getEntityTranslucentEmissive(EMISSIVE_TEXTURE);
            }
        });
    }

    @Override
    public void render(PuddleJumperEntity entity, float entityYaw, float tickDelta,
                       MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {

        matrices.push();

        matrices.scale(SCALE, SCALE, SCALE);

        super.render(entity, entityYaw, tickDelta, matrices, vertexConsumers, light);

        matrices.pop();
    }

    @Override
    public Identifier getTexture(PuddleJumperEntity entity) {
        return TEXTURE;
    }

    @Override
    protected boolean hasLabel(PuddleJumperEntity entity) {
        return false;
    }
}
package com.addie.models;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class FloodLightModel extends SinglePartEntityModel {
    private final ModelPart root;

    public FloodLightModel(ModelPart root) {
        this.root = root.getChild("root");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData root = modelPartData.addChild("root",
                ModelPartBuilder.create()
                        .uv(0, 0).cuboid(-3.0F, -6.0F, -3.0F, 7.0F, 3.0F, 4.0F, new Dilation(0.0F))
                        .uv(0, 12).cuboid(-3.0F, -7.0F, -1.0F, 7.0F, 1.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.pivot(-0.5F, 27.0F, 7.0F));

        ModelPartData cube_r1 = root.addChild("cube_r1",
                ModelPartBuilder.create()
                        .uv(0, 7).cuboid(-3.5F, 0.0F, -2.4F, 7.0F, 1.0F, 4.0F, new Dilation(0.05F)),
                ModelTransform.of(0.5F, -7.1F, -0.8F, 0.48F, 0.0F, 0.0F));

        ModelPartData cube_r2 = root.addChild("cube_r2",
                ModelPartBuilder.create()
                        .uv(8, 15).cuboid(-0.5F, -0.25F, 0.0F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F))
                        .uv(4, 15).cuboid(-0.5F, -1.25F, -0.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.4F))
                        .uv(0, 15).cuboid(-0.5F, -1.25F, -0.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.2F)),
                ModelTransform.of(2.5F, -7.35F, -2.0F, 0.0F, -0.5236F, 0.0F));

        ModelPartData cube_r3 = root.addChild("cube_r3",
                ModelPartBuilder.create()
                        .uv(4, 15).cuboid(-0.5F, -1.25F, -0.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.4F))
                        .uv(0, 15).cuboid(-0.5F, -1.25F, -0.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.2F))
                        .uv(8, 15).cuboid(-0.5F, -0.25F, 0.0F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F)),
                ModelTransform.of(-1.5F, -7.35F, -2.0F, 0.0F, 0.4363F, 0.0F));

        return TexturedModelData.of(modelData, 32, 32);
    }

    @Override
    public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        // No animation logic needed
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay,
                       float red, float green, float blue, float alpha) {
        matrices.push();
        matrices.scale(1.2F, 1.2F, 1.2F);
        matrices.translate(0.F, -0.25F, -0.08F);
        root.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        matrices.pop();
    }

    @Override
    public ModelPart getPart() {
        return root;
    }
}

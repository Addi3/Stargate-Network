package com.addie.models;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class WallLightModel extends SinglePartEntityModel {
    private final ModelPart root;
    public WallLightModel(ModelPart root) {
        this.root = root.getChild("root");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create().uv(0, 18).cuboid(-5.0F, -10.0F, 0.0F, 11.0F, 9.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 8).cuboid(-4.0F, -8.0F, -4.3F, 9.0F, 6.0F, 4.0F, new Dilation(0.5F)), ModelTransform.pivot(-0.5F, 24.0F, 7.0F));

        ModelPartData cube_r1 = root.addChild("cube_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, 0.0F, -3.0F, 11.0F, 2.0F, 6.0F, new Dilation(0.05F)), ModelTransform.of(0.0F, -9.2F, -2.5F, 0.2618F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }


    @Override
    public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        root.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart getPart() {
        return root;
    }
}


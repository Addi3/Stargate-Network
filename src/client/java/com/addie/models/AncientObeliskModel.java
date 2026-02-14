package com.addie.models;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class AncientObeliskModel extends SinglePartEntityModel {
    private final ModelPart root;
    public AncientObeliskModel(ModelPart root) {
        this.root = root.getChild("root");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create().uv(48, 29).cuboid(-7.0F, -32.0F, 5.0F, 8.0F, 32.0F, 6.0F, new Dilation(0.0F))
                .uv(48, 29).mirrored().cuboid(-17.0F, -32.0F, 5.0F, 8.0F, 32.0F, 6.0F, new Dilation(0.0F)).mirrored(false)
                .uv(0, 0).cuboid(-15.0F, -15.0F, 1.0F, 14.0F, 15.0F, 14.0F, new Dilation(0.0F))
                .uv(0, 29).cuboid(-14.0F, -31.0F, 2.0F, 12.0F, 16.0F, 12.0F, new Dilation(0.0F))
                .uv(56, 0).cuboid(-13.0F, -41.0F, 3.0F, 10.0F, 10.0F, 10.0F, new Dilation(0.0F))
                .uv(0, 57).cuboid(-12.0F, -57.0F, 4.0F, 8.0F, 16.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(8.0F, 24.0F, -8.0F));

        ModelPartData cube_r1 = root.addChild("cube_r1", ModelPartBuilder.create().uv(48, 29).cuboid(1.0F, -19.0F, -3.0F, 8.0F, 32.0F, 6.0F, new Dilation(0.0F))
                .uv(48, 29).mirrored().cuboid(-9.0F, -19.0F, -3.0F, 8.0F, 32.0F, 6.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-8.0F, -13.0F, 8.0F, 0.0F, -1.5708F, 0.0F));
        return TexturedModelData.of(modelData, 128, 128);
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


package com.addie.models;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class FancyLightModel extends SinglePartEntityModel {
    private final ModelPart root;
    public FancyLightModel(ModelPart root) {
        this.root = root.getChild("root");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create().uv(50, 40).cuboid(-15.0F, -11.0F, -15.0F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-15.0F, -5.0F, -15.0F, 5.0F, 10.0F, 30.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(10.0F, -5.0F, -15.0F, 5.0F, 10.0F, 30.0F, new Dilation(0.0F))
                .uv(54, 40).cuboid(-14.0F, 5.3F, -10.0F, 3.0F, 0.0F, 20.0F, new Dilation(0.0F))
                .uv(54, 40).cuboid(11.0F, 5.3F, -10.0F, 3.0F, 0.0F, 20.0F, new Dilation(0.0F))
                .uv(50, 40).cuboid(12.0F, -11.0F, -15.0F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F))
                .uv(50, 40).cuboid(-15.0F, -11.0F, 12.0F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F))
                .uv(50, 40).cuboid(12.0F, -11.0F, 12.0F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 3.0F, 0.0F));

        ModelPartData cube_r1 = root.addChild("cube_r1", ModelPartBuilder.create().uv(54, 40).cuboid(-15.0F, 5.0F, -10.0F, 3.0F, 0.0F, 20.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.3F, 26.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData cube_r2 = root.addChild("cube_r2", ModelPartBuilder.create().uv(54, 40).cuboid(-15.0F, 5.0F, -10.0F, 3.0F, 0.0F, 20.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.3F, 1.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData cube_r3 = root.addChild("cube_r3", ModelPartBuilder.create().uv(0, 40).cuboid(-15.0F, -5.0F, -10.0F, 5.0F, 10.0F, 20.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 25.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData cube_r4 = root.addChild("cube_r4", ModelPartBuilder.create().uv(0, 40).cuboid(-15.0F, -5.0F, -10.0F, 5.0F, 10.0F, 20.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));
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


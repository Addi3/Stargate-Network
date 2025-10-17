package com.addie.models;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class LongFancyLightModel extends SinglePartEntityModel {
    private final ModelPart root;
    public LongFancyLightModel(ModelPart root) {
        this.root = root.getChild("root");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.75F, 8.05F, 0.0F));

        ModelPartData cube_r1 = root.addChild("cube_r1", ModelPartBuilder.create().uv(96, 25).mirrored().cuboid(-4.5F, -6.75F, -30.45F, 9.0F, 7.0F, 3.0F, new Dilation(0.0F)).mirrored(false)
                .uv(0, 96).mirrored().cuboid(-10.5F, 0.25F, -31.45F, 21.0F, 10.0F, 5.0F, new Dilation(0.0F)).mirrored(false)
                .uv(96, 0).mirrored().cuboid(-12.5F, 10.55F, -31.45F, 25.0F, 0.0F, 5.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-1.5F, -10.3F, 0.0F, 0.0F, 1.5708F, 0.0F));

        ModelPartData cube_r2 = root.addChild("cube_r2", ModelPartBuilder.create().uv(0, 0).cuboid(-19.4F, -9.75F, -20.75F, 5.0F, 10.0F, 43.0F, new Dilation(0.0F))
                .uv(0, 53).cuboid(-19.4F, 0.55F, -20.75F, 5.0F, 0.0F, 43.0F, new Dilation(0.0F))
                .uv(96, 25).cuboid(-4.5F, -16.75F, -30.45F, 9.0F, 7.0F, 3.0F, new Dilation(0.0F))
                .uv(0, 96).cuboid(-10.5F, -9.75F, -31.45F, 21.0F, 10.0F, 5.0F, new Dilation(0.0F))
                .uv(96, 0).cuboid(-12.5F, 0.55F, -31.45F, 25.0F, 0.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.3F, 0.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData cube_r3 = root.addChild("cube_r3", ModelPartBuilder.create().uv(96, 5).cuboid(3.3199F, -9.75F, -28.303F, 14.0F, 10.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.4F, 0.0F, 0.0F, -0.6981F, 0.0F));

        ModelPartData cube_r4 = root.addChild("cube_r4", ModelPartBuilder.create().uv(96, 5).mirrored().cuboid(-17.3199F, -9.75F, -28.303F, 14.0F, 10.0F, 5.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-1.5F, -0.4F, 0.0F, 0.0F, 0.6981F, 0.0F));

        ModelPartData cube_r5 = root.addChild("cube_r5", ModelPartBuilder.create().uv(96, 5).mirrored().cuboid(-7.0F, -5.0F, -2.5F, 14.0F, 10.0F, 5.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-25.9913F, -5.15F, 13.1328F, 0.0F, 2.4435F, 0.0F));

        ModelPartData cube_r6 = root.addChild("cube_r6", ModelPartBuilder.create().uv(0, 53).cuboid(-19.4F, 0.25F, -22.25F, 5.0F, 0.0F, 43.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-19.4F, -10.05F, -22.25F, 5.0F, 10.0F, 43.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        ModelPartData cube_r7 = root.addChild("cube_r7", ModelPartBuilder.create().uv(96, 5).cuboid(-7.0F, -5.0F, -2.5F, 14.0F, 10.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(24.4913F, -5.15F, 13.1328F, 0.0F, -2.4435F, 0.0F));
        return TexturedModelData.of(modelData, 256, 256);
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


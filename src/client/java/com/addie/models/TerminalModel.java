package com.addie.models;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class TerminalModel extends SinglePartEntityModel {
    private final ModelPart root;
    public TerminalModel(ModelPart root) {
        this.root = root.getChild("root");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create().uv(0, 0).cuboid(-6.1155F, 3.9959F, -5.6523F, 12.0F, 13.0F, 12.0F, new Dilation(0.0F))
                .uv(26, 68).cuboid(-6.1155F, -12.0041F, -0.6523F, 12.0F, 8.0F, 7.0F, new Dilation(0.0F))
                .uv(33, 88).cuboid(-6.1155F, -12.0041F, -0.9523F, 12.0F, 8.0F, 0.0F, new Dilation(0.0F))
                .uv(0, 30).cuboid(-6.1155F, -4.0041F, -4.6523F, 0.0F, 8.0F, 11.0F, new Dilation(0.0F))
                .uv(0, 30).mirrored().cuboid(5.8845F, -4.0041F, -4.6523F, 0.0F, 8.0F, 11.0F, new Dilation(0.0F)).mirrored(false)
                .uv(0, 77).cuboid(-6.1125F, 1.9959F, -4.6523F, 0.0F, 2.0F, 11.0F, new Dilation(0.0F))
                .uv(36, 49).cuboid(-6.1155F, -4.0041F, 6.3477F, 12.0F, 8.0F, 0.0F, new Dilation(0.0F))
                .uv(-2, 47).cuboid(-6.1155F, -4.0041F, -1.6523F, 12.0F, 0.0F, 8.0F, new Dilation(0.0F))
                .uv(0, 77).cuboid(5.8825F, 1.9959F, -4.6523F, 0.0F, 2.0F, 11.0F, new Dilation(0.0F)), ModelTransform.of(0.1155F, 7.0041F, -0.3477F, 0.0F, 3.1416F, 0.0F));

        ModelPartData cube_r1 = root.addChild("cube_r1", ModelPartBuilder.create().uv(-1, 77).cuboid(-4.0F, -1.5F, 0.0F, 8.0F, 3.0F, 0.0F, new Dilation(-0.001F)), ModelTransform.of(-0.1155F, -1.7099F, -2.5755F, 2.618F, 0.0F, 0.0F));

        ModelPartData cube_r2 = root.addChild("cube_r2", ModelPartBuilder.create().uv(13, 70).cuboid(2.0F, 11.0F, -0.2F, 2.0F, 3.0F, 0.0F, new Dilation(-0.001F))
                .uv(0, 55).cuboid(-6.0F, 4.0F, 0.0F, 12.0F, 10.0F, 3.0F, new Dilation(-0.001F)), ModelTransform.of(-0.1155F, -8.1051F, 1.3477F, -0.5236F, 0.0F, 0.0F));

        ModelPartData cube_r3 = root.addChild("cube_r3", ModelPartBuilder.create().uv(1, 73).cuboid(-1.5F, -1.0F, 0.0F, 3.0F, 2.0F, 0.0F, new Dilation(-0.001F)), ModelTransform.of(-1.6155F, 2.1872F, -4.8255F, -2.618F, 0.0F, 3.1416F));
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


package com.addie.models;

import com.addie.core.entites.PuddleJumperEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class PuddleJumperModel extends SinglePartEntityModel<PuddleJumperEntity> {

    private final ModelPart root;
    public PuddleJumperModel(ModelPart root) {
        this.root = root.getChild("root");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 16.0F, 12.9F));

        ModelPartData exterior = root.addChild("exterior", ModelPartBuilder.create().uv(0, 128).cuboid(-8.0F, -16.0F, -41.0F, 16.0F, 16.0F, 48.0F, new Dilation(0.6F))
                .uv(0, 0).cuboid(-8.0F, -16.0F, -41.0F, 16.0F, 16.0F, 48.0F, new Dilation(0.0F))
                .uv(139, 109).cuboid(-7.7F, -10.0F, -45.0F, 0.0F, 10.0F, 8.0F, new Dilation(0.0F))
                .uv(139, 109).mirrored().cuboid(7.7F, -10.0F, -45.0F, 0.0F, 10.0F, 8.0F, new Dilation(0.0F)).mirrored(false)
                .uv(0, 64).cuboid(-8.0F, -16.0F, -41.0F, 16.0F, 16.0F, 48.0F, new Dilation(0.3F)), ModelTransform.pivot(0.0F, 8.0F, 17.0F));

        ModelPartData cube_r1 = exterior.addChild("cube_r1", ModelPartBuilder.create().uv(128, 17).cuboid(-8.0F, -17.0F, 0.0F, 16.0F, 17.0F, 0.0F, new Dilation(0.0F))
                .uv(137, 73).cuboid(-8.0F, -17.0F, 0.1F, 16.0F, 17.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.3054F, 0.0F, 0.0F));

        ModelPartData cube_r2 = exterior.addChild("cube_r2", ModelPartBuilder.create().uv(134, 53).cuboid(-8.0F, -17.0F, -0.1F, 16.0F, 17.0F, 0.0F, new Dilation(0.0F))
                .uv(128, 0).cuboid(-8.0F, -17.0F, 0.0F, 16.0F, 17.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -41.0F, -0.3054F, 0.0F, 0.0F));

        ModelPartData interior = root.addChild("interior", ModelPartBuilder.create().uv(139, 172).cuboid(-7.0F, -4.0F, -6.0F, 3.0F, 1.0F, 23.0F, new Dilation(0.0F))
                .uv(131, 207).cuboid(-7.0F, -3.0F, -6.0F, 2.0F, 2.0F, 23.0F, new Dilation(0.0F))
                .uv(120, 165).cuboid(-7.0F, -4.1F, -6.0F, 3.0F, 0.0F, 23.0F, new Dilation(0.0F))
                .uv(194, 0).cuboid(-7.0F, -1.0F, -23.7F, 7.0F, 0.0F, 41.0F, new Dilation(0.0F))
                .uv(194, 0).cuboid(0.0F, -1.0F, -23.7F, 7.0F, 0.0F, 41.0F, new Dilation(0.0F))
                .uv(164, 130).cuboid(-7.0F, -2.0F, -23.5F, 14.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(145, 137).cuboid(-7.0F, -2.0F, -18.5F, 4.0F, 1.0F, 12.0F, new Dilation(0.0F))
                .uv(207, 105).cuboid(-7.0F, -7.0F, -21.5F, 6.0F, 5.0F, 2.0F, new Dilation(0.0F))
                .uv(207, 105).cuboid(1.0F, -7.0F, -21.5F, 6.0F, 5.0F, 2.0F, new Dilation(0.0F))
                .uv(216, 135).cuboid(-3.5F, -8.0F, -19.5F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(216, 135).cuboid(-5.0F, -8.0F, -19.5F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(216, 135).cuboid(3.5F, -8.0F, -19.5F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(216, 135).cuboid(5.0F, -8.0F, -19.5F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(229, 169).cuboid(3.0F, -5.0F, -17.0F, 3.0F, 2.0F, 3.0F, new Dilation(0.0F))
                .uv(224, 178).cuboid(4.0F, -3.0F, -16.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(221, 168).cuboid(2.5F, -6.0F, -17.0F, 1.0F, 0.0F, 3.0F, new Dilation(0.0F))
                .uv(221, 168).cuboid(5.5F, -6.0F, -17.0F, 1.0F, 0.0F, 3.0F, new Dilation(0.0F))
                .uv(199, 164).cuboid(3.0F, -9.0F, -14.0F, 3.0F, 5.0F, 1.0F, new Dilation(0.0F))
                .uv(199, 164).mirrored().cuboid(-6.0F, -9.0F, -14.0F, 3.0F, 5.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
                .uv(221, 168).mirrored().cuboid(-6.5F, -6.0F, -17.0F, 1.0F, 0.0F, 3.0F, new Dilation(0.0F)).mirrored(false)
                .uv(145, 137).mirrored().cuboid(3.0F, -2.0F, -18.5F, 4.0F, 1.0F, 12.0F, new Dilation(0.0F)).mirrored(false)
                .uv(229, 169).mirrored().cuboid(-6.0F, -5.0F, -17.0F, 3.0F, 2.0F, 3.0F, new Dilation(0.0F)).mirrored(false)
                .uv(221, 168).mirrored().cuboid(-3.5F, -6.0F, -17.0F, 1.0F, 0.0F, 3.0F, new Dilation(0.0F)).mirrored(false)
                .uv(224, 178).mirrored().cuboid(-5.0F, -3.0F, -16.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 8.0F, 0.0F));

        ModelPartData cube_r3 = interior.addChild("cube_r3", ModelPartBuilder.create().uv(221, 168).cuboid(1.0F, -1.5F, -2.5F, 1.0F, 0.0F, 3.0F, new Dilation(0.0F))
                .uv(221, 168).cuboid(-2.0F, -1.5F, -2.5F, 1.0F, 0.0F, 3.0F, new Dilation(0.0F))
                .uv(229, 169).cuboid(-1.5F, -0.5F, -2.5F, 3.0F, 2.0F, 3.0F, new Dilation(0.0F))
                .uv(224, 178).cuboid(-0.5F, 1.5F, -1.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(199, 164).cuboid(-1.5F, -4.5F, 0.5F, 3.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(5.0F, -4.5F, -8.5F, 0.0F, 0.8727F, 0.0F));

        ModelPartData cube_r4 = interior.addChild("cube_r4", ModelPartBuilder.create().uv(224, 178).mirrored().cuboid(-0.5F, 1.5F, -1.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
                .uv(221, 168).mirrored().cuboid(1.0F, -1.5F, -2.5F, 1.0F, 0.0F, 3.0F, new Dilation(0.0F)).mirrored(false)
                .uv(229, 169).mirrored().cuboid(-1.5F, -0.5F, -2.5F, 3.0F, 2.0F, 3.0F, new Dilation(0.0F)).mirrored(false)
                .uv(221, 168).mirrored().cuboid(-2.0F, -1.5F, -2.5F, 1.0F, 0.0F, 3.0F, new Dilation(0.0F)).mirrored(false)
                .uv(199, 164).mirrored().cuboid(-1.5F, -4.5F, 0.5F, 3.0F, 5.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-5.0F, -4.5F, -8.5F, 0.0F, -0.8727F, 0.0F));

        ModelPartData cube_r5 = interior.addChild("cube_r5", ModelPartBuilder.create().uv(203, 86).cuboid(-2.0F, -1.1F, -2.0F, 4.0F, 0.0F, 3.0F, new Dilation(0.0F))
                .uv(204, 70).cuboid(-2.0F, -1.0F, -2.0F, 4.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -7.0F, -20.0F, -0.7854F, 0.0F, 0.0F));

        ModelPartData cube_r6 = interior.addChild("cube_r6", ModelPartBuilder.create().uv(202, 91).cuboid(-2.0F, -3.6F, -2.0F, 4.0F, 0.0F, 4.0F, new Dilation(0.0F))
                .uv(201, 52).cuboid(-2.0F, -3.5F, -2.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -3.5F, -19.0F, 0.0F, -0.7854F, 0.0F));

        ModelPartData cube_r7 = interior.addChild("cube_r7", ModelPartBuilder.create().uv(202, 199).cuboid(-7.5F, -13.0F, -1.1F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(185, 0).cuboid(-8.0F, -16.4F, 0.0F, 16.0F, 17.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.2F, -7.0F, -0.1745F, 0.0F, 0.0F));

        ModelPartData cube_r8 = interior.addChild("cube_r8", ModelPartBuilder.create().uv(120, 165).cuboid(-1.25F, -1.05F, -11.5F, 3.0F, 0.0F, 23.0F, new Dilation(0.0F))
                .uv(139, 172).cuboid(-1.25F, -0.95F, -11.5F, 3.0F, 1.0F, 23.0F, new Dilation(0.0F))
                .uv(131, 207).cuboid(-1.25F, 0.05F, -11.5F, 2.0F, 2.0F, 23.0F, new Dilation(0.0F)), ModelTransform.of(5.75F, -3.05F, 5.5F, 0.0F, 3.1416F, 0.0F));
        return TexturedModelData.of(modelData, 256, 256);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        root.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart getPart() {
        return root;
    }

    @Override
    public void setAngles(PuddleJumperEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

    }
}


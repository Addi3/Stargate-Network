package com.addie.renderers;

import com.addie.StargateNetwork;
import com.addie.core.blockentites.AncientObeliskBlockEntity;
import com.addie.models.AncientObeliskModel;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

import java.util.List;

public class AncientObeliskRenderer<T extends AncientObeliskBlockEntity> implements BlockEntityRenderer<T> {

    public static final Identifier MONITOR_TEXTURE = new Identifier(StargateNetwork.MOD_ID,
            "textures/blockentities/ancient_obelisk.png");

    private final TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
    private final AncientObeliskModel anicentObeliskModel;

    public AncientObeliskRenderer(BlockEntityRendererFactory.Context ctx) {
        this.anicentObeliskModel = new AncientObeliskModel(AncientObeliskModel.getTexturedModelData().createModel());
    }

    @Override
    public void render(AncientObeliskBlockEntity entity, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light, int overlay) {

        matrices.push();
        matrices.translate(0.5f, 1.5f, 0.5f);
        float scale = entity.getScale();
        matrices.scale(scale, scale, scale);
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180f));
        this.anicentObeliskModel.render(
                matrices,
                vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(MONITOR_TEXTURE)),
                light, overlay,
                1f, 1f, 1f, 1f
        );
        matrices.pop();

        int packedLight = 0xF000F0;
        float textScale = 0.02f;
        float lineHeight = 10f;

        // NORTH
        renderDirectionText(matrices, vertexConsumers, packedLight, 0.51, 1.9, 1.08, 0f,
                List.of(
                        Text.literal("A B").setStyle(Style.EMPTY.withFont(new Identifier("minecraft", "illageralt"))),
                        Text.literal("C D").setStyle(Style.EMPTY.withFont(new Identifier("minecraft", "illageralt"))),
                        Text.literal("E F").setStyle(Style.EMPTY.withFont(new Identifier("minecraft", "illageralt"))),
                        Text.literal("G H").setStyle(Style.EMPTY.withFont(new Identifier("minecraft", "illageralt"))),
                        Text.literal("I J").setStyle(Style.EMPTY.withFont(new Identifier("minecraft", "illageralt"))),
                        Text.literal("K L").setStyle(Style.EMPTY.withFont(new Identifier("minecraft", "illageralt"))),
                        Text.literal("M N").setStyle(Style.EMPTY.withFont(new Identifier("minecraft", "illageralt"))),
                        Text.literal("O P").setStyle(Style.EMPTY.withFont(new Identifier("minecraft", "illageralt"))),
                        Text.literal("Q R").setStyle(Style.EMPTY.withFont(new Identifier("minecraft", "illageralt")))
                ), textScale, lineHeight);

        // EAST
        renderDirectionText(matrices, vertexConsumers, packedLight, 1.08, 1.9, 0.51, 90f,
                List.of(
                        Text.literal("A B").setStyle(Style.EMPTY.withFont(new Identifier("minecraft", "illageralt"))),
                        Text.literal("C D").setStyle(Style.EMPTY.withFont(new Identifier("minecraft", "illageralt"))),
                        Text.literal("E F").setStyle(Style.EMPTY.withFont(new Identifier("minecraft", "illageralt"))),
                        Text.literal("G H").setStyle(Style.EMPTY.withFont(new Identifier("minecraft", "illageralt"))),
                        Text.literal("I J").setStyle(Style.EMPTY.withFont(new Identifier("minecraft", "illageralt"))),
                        Text.literal("K L").setStyle(Style.EMPTY.withFont(new Identifier("minecraft", "illageralt"))),
                        Text.literal("M N").setStyle(Style.EMPTY.withFont(new Identifier("minecraft", "illageralt"))),
                        Text.literal("O P").setStyle(Style.EMPTY.withFont(new Identifier("minecraft", "illageralt"))),
                        Text.literal("Q R").setStyle(Style.EMPTY.withFont(new Identifier("minecraft", "illageralt")))
                ), textScale, lineHeight);

        // SOUTH
        renderDirectionText(matrices, vertexConsumers, packedLight, 0.51, 1.9, -0.08, 180f,
                List.of(
                        Text.literal("A B").setStyle(Style.EMPTY.withFont(new Identifier("minecraft", "illageralt"))),
                        Text.literal("C D").setStyle(Style.EMPTY.withFont(new Identifier("minecraft", "illageralt"))),
                        Text.literal("E F").setStyle(Style.EMPTY.withFont(new Identifier("minecraft", "illageralt"))),
                        Text.literal("G H").setStyle(Style.EMPTY.withFont(new Identifier("minecraft", "illageralt"))),
                        Text.literal("I J").setStyle(Style.EMPTY.withFont(new Identifier("minecraft", "illageralt"))),
                        Text.literal("K L").setStyle(Style.EMPTY.withFont(new Identifier("minecraft", "illageralt"))),
                        Text.literal("M N").setStyle(Style.EMPTY.withFont(new Identifier("minecraft", "illageralt"))),
                        Text.literal("O P").setStyle(Style.EMPTY.withFont(new Identifier("minecraft", "illageralt"))),
                        Text.literal("Q R").setStyle(Style.EMPTY.withFont(new Identifier("minecraft", "illageralt")))
                ), textScale, lineHeight);

        // WEST
        renderDirectionText(matrices, vertexConsumers, packedLight, -0.08, 1.9, 0.51, 270f,
                List.of(
                        Text.literal("A B").setStyle(Style.EMPTY.withFont(new Identifier("minecraft", "illageralt"))),
                        Text.literal("C D").setStyle(Style.EMPTY.withFont(new Identifier("minecraft", "illageralt"))),
                        Text.literal("E F").setStyle(Style.EMPTY.withFont(new Identifier("minecraft", "illageralt"))),
                        Text.literal("G H").setStyle(Style.EMPTY.withFont(new Identifier("minecraft", "illageralt"))),
                        Text.literal("I J").setStyle(Style.EMPTY.withFont(new Identifier("minecraft", "illageralt"))),
                        Text.literal("K L").setStyle(Style.EMPTY.withFont(new Identifier("minecraft", "illageralt"))),
                        Text.literal("M N").setStyle(Style.EMPTY.withFont(new Identifier("minecraft", "illageralt"))),
                        Text.literal("O P").setStyle(Style.EMPTY.withFont(new Identifier("minecraft", "illageralt"))),
                        Text.literal("Q R").setStyle(Style.EMPTY.withFont(new Identifier("minecraft", "illageralt")))
                ), textScale, lineHeight);
    }

    private void renderDirectionText(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int packedLight,
                                     double x, double y, double z, float rotationY, List<Text> lines,
                                     float scale, float lineHeight) {

        matrices.push();
        matrices.translate(x, y, z);
        matrices.scale(scale, -scale, scale);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(rotationY));

        for (int i = 0; i < lines.size(); i++) {
            OrderedText orderedText = lines.get(i).asOrderedText();
            textRenderer.drawWithOutline(
                    orderedText,
                    -textRenderer.getWidth(lines.get(i)) / 2f,
                    i * lineHeight,
                    0x2c2c2f,
                    0x19191b,
                    matrices.peek().getPositionMatrix(),
                    vertexConsumers,
                    packedLight
            );
        }

        matrices.pop();
    }
}

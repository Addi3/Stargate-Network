package com.addie.renderers;


import com.addie.StargateNetwork;
import com.addie.core.blockentites.TerminalBlockEntity;
import com.addie.models.TerminalModel;
import net.minecraft.block.BlockState;
import net.minecraft.block.SkullBlock;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.RotationPropertyHelper;

public class TerminalRenderer<T extends TerminalBlockEntity> implements BlockEntityRenderer<T> {

    public static final Identifier TERMINAL_TEXTURE = new Identifier(StargateNetwork.MOD_ID,
            "textures/blockentities/terminal/terminal.png");
  public static final Identifier EMISSIVE_TERMINAL_TEXTURE = new Identifier(StargateNetwork.MOD_ID,
          "textures/blockentities/terminal/terminal_emission.png");
    private final TerminalModel terminalModel;

    public TerminalRenderer(BlockEntityRendererFactory.Context ctx) {
        this.terminalModel = new TerminalModel(TerminalModel.getTexturedModelData().createModel());
    }

    @Override
    public void render(T entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        BlockState blockState = entity.getCachedState();

        int k = blockState.get(SkullBlock.ROTATION);
        float h = RotationPropertyHelper.toDegrees(k);

        matrices.push();
        matrices.translate(0.5f, 1.5f, 0.5f);
        matrices.multiply(RotationAxis.NEGATIVE_Y.rotationDegrees(h));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180));


        float scale = entity.getScale();
        matrices.scale(scale, scale, scale);

        this.terminalModel.render(matrices,
                vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(TERMINAL_TEXTURE)), light, overlay, 1.0F,
                1.0F, 1.0F, 1.0F);
  this.terminalModel.render(matrices,
          vertexConsumers.getBuffer(RenderLayer.getEntityTranslucentEmissive(EMISSIVE_TERMINAL_TEXTURE)),
          0xF000F00, overlay, 1.0F, 1.0F, 1.0F, 1.0F);

        matrices.pop();
    }
}
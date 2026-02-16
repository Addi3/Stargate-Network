package com.addie;



import com.addie.core.StargateNetworkBlockEntityTypes;
import com.addie.core.StargateNetworkBlocks;
import com.addie.renderers.AncientObeliskRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class StargateNetworkClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
        BlockRenderLayerMapRegister();
        blockEntityRendererRegister();

	}

    public static void blockEntityRendererRegister() {
        BlockEntityRendererFactories.register(StargateNetworkBlockEntityTypes.ANCIENT_OBELISK_BLOCK_ENTITY_TYPE, AncientObeliskRenderer::new);

    }

    public static void BlockRenderLayerMapRegister() {
        BlockRenderLayerMap.INSTANCE.putBlock(StargateNetworkBlocks.ANCIENT_OBELISK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(StargateNetworkBlocks.ZPM_HUB, RenderLayer.getCutout());
    }

}
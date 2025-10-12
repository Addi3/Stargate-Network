package com.addie;


import com.addie.core.StargateNetworkBlockEntityTypes;
import com.addie.core.StargateNetworkBlocks;
import com.addie.renderers.TerminalRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class StargateNetworkClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
        blockEntityRendererRegister();
        BlockRenderLayerMapRegister();
	}

    public static void blockEntityRendererRegister() {

        BlockEntityRendererFactories.register(StargateNetworkBlockEntityTypes.TERMINAL_BLOCK_ENTITY_TYPE, TerminalRenderer::new);
    }

    public static void BlockRenderLayerMapRegister() {
        BlockRenderLayerMap.INSTANCE.putBlock(StargateNetworkBlocks.LIGHT, RenderLayer.getCutout());
    }


}
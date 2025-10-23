package com.addie;


import com.addie.core.StargateNetworkBlockEntityTypes;
import com.addie.core.StargateNetworkBlocks;
import com.addie.renderers.*;
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

        BlockEntityRendererFactories.register(StargateNetworkBlockEntityTypes.FANCY_LIGHT_BLOCK_ENTITY_TYPE, FancyLightRenderer::new);

        BlockEntityRendererFactories.register(StargateNetworkBlockEntityTypes.LONG_FANCY_LIGHT_BLOCK_ENTITY_TYPE, LongFancyLightRenderer::new);

        BlockEntityRendererFactories.register(StargateNetworkBlockEntityTypes.WALL_LIGHT_BLOCK_ENTITY_TYPE, WallLightRenderer::new);

        BlockEntityRendererFactories.register(StargateNetworkBlockEntityTypes.FLOOD_LIGHT_BLOCK_ENTITY_TYPE, FloodLightRenderer::new);
    }

    public static void BlockRenderLayerMapRegister() {
        BlockRenderLayerMap.INSTANCE.putBlock(StargateNetworkBlocks.LIGHT_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(StargateNetworkBlocks.FANCY_LIGHT_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(StargateNetworkBlocks.LONG_FANCY_LIGHT_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(StargateNetworkBlocks.FLOOD_LIGHT_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(StargateNetworkBlocks.STEEL_STAIRS_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(StargateNetworkBlocks.GRATED_STEEL_STAIRS_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(StargateNetworkBlocks.TABLE_BLOCK, RenderLayer.getCutout());
    }


}
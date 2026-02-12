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
        BlockRenderLayerMapRegister();
	}
    public static void BlockRenderLayerMapRegister() {
    }


}
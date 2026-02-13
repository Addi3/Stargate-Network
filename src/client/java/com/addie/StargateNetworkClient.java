package com.addie;



import com.addie.core.StargateNetworkBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class StargateNetworkClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
        BlockRenderLayerMapRegister();
	}
    public static void BlockRenderLayerMapRegister() {
        BlockRenderLayerMap.INSTANCE.putBlock(StargateNetworkBlocks.ANCIENT_WAYSTONE, RenderLayer.getCutout());
    }


}
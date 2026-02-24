package com.addie;



import com.addie.core.StargateNetworkBlockEntityTypes;
import com.addie.core.StargateNetworkBlocks;
import com.addie.particles.TransportBeamParticle;
import com.addie.renderers.AncientObeliskRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

import static com.addie.StargateNetwork.TRANSPORT_BEAM;

public class StargateNetworkClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
        BlockRenderLayerMapRegister();
        blockEntityRendererRegister();
        registerParticles();
	}

    public static void blockEntityRendererRegister() {
        BlockEntityRendererFactories.register(StargateNetworkBlockEntityTypes.ANCIENT_OBELISK_BLOCK_ENTITY_TYPE, AncientObeliskRenderer::new);

    }

    public static void BlockRenderLayerMapRegister() {
        BlockRenderLayerMap.INSTANCE.putBlock(StargateNetworkBlocks.ANCIENT_OBELISK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(StargateNetworkBlocks.ZPM_HUB, RenderLayer.getCutout());
    }

    public void registerParticles() {
        ParticleFactoryRegistry.getInstance().register(TRANSPORT_BEAM, TransportBeamParticle.Factory::new);
    }
}
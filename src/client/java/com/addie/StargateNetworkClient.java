package com.addie;

import com.addie.core.StargateNetworkBlockEntityTypes;
import com.addie.renderers.TerminalRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class StargateNetworkClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
        blockEntityRendererRegister();
	}

    public static void blockEntityRendererRegister() {

        BlockEntityRendererFactories.register(StargateNetworkBlockEntityTypes.TERMINAL_BLOCK_ENTITY_TYPE, TerminalRenderer::new);
    }

}
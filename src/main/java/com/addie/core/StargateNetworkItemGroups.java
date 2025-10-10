package com.addie.core;

import com.addie.StargateNetwork;
import dev.amble.lib.container.impl.ItemGroupContainer;
import dev.amble.lib.itemgroup.AItemGroup;
import net.minecraft.item.ItemStack;

public class StargateNetworkItemGroups implements ItemGroupContainer {

    public static final AItemGroup MAIN = AItemGroup.builder(StargateNetwork.id("item_group"))
            .icon(() -> new ItemStack(StargateNetworkBlocks.TERMINAL_BLOCK.asItem()))
            .build();
}
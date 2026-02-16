package com.addie.core.items;


import com.addie.core.StargateNetworkItems;
import net.minecraft.item.ItemStack;

public class ZPMItem extends ZPMTooltipHandlerItem {

    public ZPMItem(Settings settings) {
        super(settings, true);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return stack.getItem() == StargateNetworkItems.OVERCHARGED_ZPM;
    }

}

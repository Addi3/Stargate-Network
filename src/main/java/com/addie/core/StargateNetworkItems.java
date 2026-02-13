package com.addie.core;

import dev.amble.lib.container.impl.ItemContainer;
import dev.amble.lib.datagen.util.NoEnglish;
import dev.amble.lib.item.AItemSettings;
import net.minecraft.item.Item;

public class StargateNetworkItems extends ItemContainer {

    ///  TODO- make obtainable, maybe some new structure in the end (ancient outpost?)??
    @NoEnglish
    public static final Item ZPM = new Item (new AItemSettings().group(StargateNetworkItemGroups.MAIN));
}

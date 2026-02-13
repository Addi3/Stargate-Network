package com.addie.core;

import com.addie.core.blocks.WaystoneBlock;
import dev.amble.lib.block.ABlockSettings;
import dev.amble.lib.container.impl.BlockContainer;
import dev.amble.lib.datagen.util.NoEnglish;
import dev.amble.lib.datagen.util.PickaxeMineable;
import dev.amble.lib.item.AItemSettings;
import net.minecraft.block.Block;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.sound.BlockSoundGroup;

public class StargateNetworkBlocks extends BlockContainer {

        @NoEnglish
        @PickaxeMineable(tool = PickaxeMineable.Tool.STONE)
    public static final Block ANCIENT_WAYSTONE = new WaystoneBlock(ABlockSettings.create()
                .itemSettings(new AItemSettings()
                        //.group(AITExtrasItemGroups.MAIN)
                ).requiresTool()
                .strength(2.0F, 3.0F).pistonBehavior(PistonBehavior.IGNORE).sounds(BlockSoundGroup.DEEPSLATE).nonOpaque());


}

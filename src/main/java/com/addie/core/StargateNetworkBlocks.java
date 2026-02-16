package com.addie.core;

import com.addie.core.blocks.AncientSymbolBlock;
import com.addie.core.blocks.ObeliskBlock;
import com.addie.core.blocks.ZPMHubBlock;
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
    public static final Block ANCIENT_OBELISK = new ObeliskBlock(ABlockSettings.create()
                .itemSettings(new AItemSettings()
                        .group(StargateNetworkItemGroups.MAIN)
                ).requiresTool()
                .strength(2.0F, 3.0F).pistonBehavior(PistonBehavior.IGNORE).sounds(BlockSoundGroup.DEEPSLATE).nonOpaque());

        @NoEnglish
        @PickaxeMineable(tool = PickaxeMineable.Tool.STONE)
    public static final Block CARVED_ANCIENT_STONE = new AncientSymbolBlock(ABlockSettings.create()
                .itemSettings(new AItemSettings()
                        .group(StargateNetworkItemGroups.MAIN)
                ).requiresTool()
                .strength(1.5F, 6.0F).pistonBehavior(PistonBehavior.NORMAL).sounds(BlockSoundGroup.STONE).nonOpaque());

    @NoEnglish
    @PickaxeMineable(tool = PickaxeMineable.Tool.IRON)
    public static final Block ZPM_HUB = new ZPMHubBlock(ABlockSettings.create()
            .itemSettings(new AItemSettings()
                    .group(StargateNetworkItemGroups.MAIN)
            ).requiresTool()
            .strength(3.5F, 10.0F).pistonBehavior(PistonBehavior.IGNORE).sounds(BlockSoundGroup.COPPER).nonOpaque());
}

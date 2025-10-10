package com.addie.datagen;

import com.addie.core.StargateNetworkBlocks;
import com.addie.core.StargateNetworkItems;
import com.addie.datagen.providers.StargateNetworkItemTagProvider;
import com.addie.datagen.providers.StargateNetworkModelGen;
import com.addie.datagen.providers.StargateNetworkRecipeProvider;
import dev.amble.lib.datagen.lang.AmbleLanguageProvider;
import dev.amble.lib.datagen.lang.LanguageType;
import dev.amble.lib.datagen.loot.AmbleBlockLootTable;
import dev.amble.lib.datagen.tag.AmbleBlockTagProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.block.Blocks;

public class StargateNetworkDataGenerator  implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator gen) {
        FabricDataGenerator.Pack pack = gen.createPack();

        genLang(pack);
        generateRecipes(pack);
        genLoot(pack);
        genTags(pack);
        genModels(pack);
        generateItemTags(pack);
    }


    private void genModels(FabricDataGenerator.Pack pack) {
        pack.addProvider(((output, registriesFuture) -> {
           StargateNetworkModelGen provider = new StargateNetworkModelGen(output);
            provider.withBlocks(StargateNetworkBlocks.class);
            provider.withItems(StargateNetworkItems.class);
            return provider;
        }));

    }

    public void generateItemTags(FabricDataGenerator.Pack pack) {
        pack.addProvider(StargateNetworkItemTagProvider::new);
    }

    private void genLang(FabricDataGenerator.Pack pack) {
        genEnglish(pack);
    }

    private void genTags(FabricDataGenerator.Pack pack) {
        pack.addProvider((((output, registriesFuture) -> new AmbleBlockTagProvider(output, registriesFuture).withBlocks(StargateNetworkBlocks.class))));
    }

    private void genLoot(FabricDataGenerator.Pack pack) {
        pack.addProvider((((output, registriesFuture) -> new AmbleBlockLootTable(output).withBlocks(StargateNetworkBlocks.class))));
    }
    public void generateRecipes(FabricDataGenerator.Pack pack) {
        pack.addProvider((((output, registriesFuture) -> {
            StargateNetworkRecipeProvider provider = new StargateNetworkRecipeProvider(output);


            provider.addStonecutting(Blocks.YELLOW_CONCRETE, StargateNetworkBlocks.CAUTION_BLOCK,1);

            return provider;

        })));
    }

    private void genEnglish(FabricDataGenerator.Pack pack) {
        pack.addProvider((((output, registriesFuture) -> {
            AmbleLanguageProvider provider = new AmbleLanguageProvider(output, LanguageType.EN_US);

        // Blocks
            provider.addTranslation(StargateNetworkBlocks.CAUTION_BLOCK,"Caution Block");

            return provider;
        })));
    }
}
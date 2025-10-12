package com.addie.datagen;

import com.addie.core.StargateNetworkBlocks;
import com.addie.core.StargateNetworkItems;
import com.addie.datagen.providers.StargateNetworkItemTagProvider;
import com.addie.datagen.providers.StargateNetworkModelGen;
import com.addie.datagen.providers.StargateNetworkRecipeProvider;
import dev.amble.lib.datagen.lang.AmbleLanguageProvider;
import dev.amble.lib.datagen.lang.LanguageType;
import dev.amble.lib.datagen.loot.AmbleBlockLootTable;
import dev.amble.lib.datagen.sound.AmbleSoundProvider;
import dev.amble.lib.datagen.tag.AmbleBlockTagProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;

import static net.minecraft.data.server.recipe.RecipeProvider.conditionsFromItem;
import static net.minecraft.data.server.recipe.RecipeProvider.hasItem;

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
        generateSoundData(pack);
    }


    private void genModels(FabricDataGenerator.Pack pack) {
        pack.addProvider(((output, registriesFuture) -> {
           StargateNetworkModelGen provider = new StargateNetworkModelGen(output);
            provider.withBlocks(StargateNetworkBlocks.class);
            provider.withItems(StargateNetworkItems.class);
            return provider;
        }));

    }

    public void generateSoundData(FabricDataGenerator.Pack pack) {
        pack.addProvider((((output, registriesFuture) -> new AmbleSoundProvider(output))));
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

            ;provider.addShapedRecipe(ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, StargateNetworkBlocks.TERMINAL_BLOCK, 1)
                    .pattern("BII")
                    .pattern("RII")
                    .pattern("CRI")
                    .input('I',Items.IRON_INGOT)
                    .input('R',Items.REDSTONE)
                    .input('B',Blocks.BLACK_CONCRETE)
                    .input('C',Blocks.COMPARATOR)
                    .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                    .criterion(hasItem(Items.REDSTONE), conditionsFromItem(Items.REDSTONE))
                    .criterion(hasItem(Blocks.BLACK_CONCRETE), conditionsFromItem(Blocks.BLACK_CONCRETE))
                    .criterion(hasItem(Blocks.COMPARATOR), conditionsFromItem(Blocks.COMPARATOR)));

            ;provider.addShapedRecipe(ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, StargateNetworkBlocks.LIGHT, 1)
                    .pattern("R")
                    .pattern("I")
                    .input('I',Items.IRON_INGOT)
                    .input('R',Blocks.REDSTONE_LAMP)
                    .criterion(hasItem(Blocks.REDSTONE_LAMP), conditionsFromItem(Blocks.REDSTONE_LAMP))
                    .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT)));


            provider.addStonecutting(Blocks.YELLOW_CONCRETE, StargateNetworkBlocks.CAUTION_BLOCK,1);

            return provider;

        })));
    }

    private void genEnglish(FabricDataGenerator.Pack pack) {
        pack.addProvider((((output, registriesFuture) -> {
            AmbleLanguageProvider provider = new AmbleLanguageProvider(output, LanguageType.EN_US);

        // Misc
            provider.addTranslation("itemGroup.stargate-network.item_group",  "Stargate Network");

        // Blocks
            provider.addTranslation(StargateNetworkBlocks.CAUTION_BLOCK,"Caution Block");
            provider.addTranslation(StargateNetworkBlocks.TERMINAL_BLOCK,"Terminal");
            provider.addTranslation(StargateNetworkBlocks.LIGHT,"Emergancy Light");

            return provider;
        })));
    }
}
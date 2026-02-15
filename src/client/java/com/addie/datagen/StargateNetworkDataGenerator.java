package com.addie.datagen;

import com.addie.StargateNetwork;
import com.addie.core.StargateNetworkBlocks;
import com.addie.core.StargateNetworkItemGroups;
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
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
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

            ;provider.addShapedRecipe(ShapedRecipeJsonBuilder.create(RecipeCategory.TRANSPORTATION, StargateNetworkBlocks.ANCIENT_OBELISK, 2)
                    .pattern(" B ")
                    .pattern("DBD")
                    .pattern("DLD")
                    .input('B',Blocks.STONE_BRICKS)
                    .input('L',Blocks.LODESTONE)
                    .input('D',Blocks.POLISHED_DEEPSLATE_WALL)
                    .criterion(hasItem(Blocks.STONE_BRICKS), conditionsFromItem(Blocks.STONE_BRICKS))
                    .criterion(hasItem(Blocks.LODESTONE), conditionsFromItem(Blocks.LODESTONE))
                    .criterion(hasItem(Blocks.POLISHED_DEEPSLATE_WALL), conditionsFromItem(Blocks.POLISHED_DEEPSLATE_WALL)));

            ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, StargateNetworkItems.OBELISK_LINKER, 1);
            provider.addShapelessRecipe(ShapelessRecipeJsonBuilder
                    .create(RecipeCategory.MISC, StargateNetworkItems.OBELISK_LINKER, 1)
                    .input(Items.MAP)
                    .input(Items.COMPASS)
                    .criterion(hasItem(Items.MAP), conditionsFromItem(Items.MAP))
                    .criterion(hasItem(Items.COMPASS), conditionsFromItem(Items.COMPASS)));


            provider.addStonecutting(Blocks.STONE, StargateNetworkBlocks.CARVED_ANCIENT_STONE,1);

            return provider;

        })));
    }

    private void genEnglish(FabricDataGenerator.Pack pack) {
        pack.addProvider((((output, registriesFuture) -> {
            AmbleLanguageProvider provider = new AmbleLanguageProvider(output, LanguageType.EN_US);

            // Blocks
        provider.addTranslation(StargateNetworkBlocks.ANCIENT_OBELISK,"Ancient Obelisk");
        provider.addTranslation(StargateNetworkBlocks.CARVED_ANCIENT_STONE,"Carved Ancient Stone");

            // Items
        provider.addTranslation(StargateNetworkItems.ZPM,"ZPM");
        provider.addTranslation(StargateNetworkItems.ANCIENT_GLASS_CRYSTAL,"Ancient Glass Crystal");
        provider.addTranslation(StargateNetworkItems.OBELISK_LINKER,"Ancient Obelisk Linker");

            // Misc
        provider.addTranslation(StargateNetworkItemGroups.MAIN,"Stargate Network");
        provider.addTranslation("item.linker.nopower","Cannot Link, No Power!");
        provider.addTranslation("item.linker.selected","Obelisk Selected!");
        provider.addTranslation("item.linker.linked","Obelisks Successfully Linked!");
        provider.addTranslation("item.linker.tolink","Shift + Right-CLick To Link!");
        provider.addTranslation("effect.stargate-network.radiation","Radiation");
        provider.addTranslation("death.attack.radiation","%1$s succumbed to radiation poisoning");
        provider.addTranslation("death.attack.radiation.player","%1$s was killed by %2$s while irradiated");

            return provider;
        })));
    }
}
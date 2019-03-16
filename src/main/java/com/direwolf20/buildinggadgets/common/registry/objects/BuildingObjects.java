package com.direwolf20.buildinggadgets.common.registry.objects;

import com.direwolf20.buildinggadgets.common.config.Config;
import com.direwolf20.buildinggadgets.common.utils.ref.NBTKeys;
import com.direwolf20.buildinggadgets.common.utils.ref.Reference;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

//@Mod.EventBusSubscriber(modid = BuildingGadgets.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BuildingObjects {

    // Creative tab
    public static ItemGroup creativeTab = new ItemGroup(Reference.MODID){
        @Override
        public ItemStack createIcon() {
            ItemStack stack = new ItemStack(BGItems.gadgetBuilding);
            stack.getOrCreateTag().setByte(NBTKeys.CREATIVE_MARKER, (byte) 0); 
            return stack;
        }
    };

    // Materials
    public static final Material EFFECT_BLOCK_MATERIAL = new Material.Builder(MaterialColor.AIR).notSolid().build();


    public static void initColorHandlers() { //TODO ItemBlock Creative Tabs
        BlockColors blockColors = Minecraft.getInstance().getBlockColors();
        //TODO non conditional registry
        if (Config.GENERAL.enablePaste.get()) BGBlocks.constructionBlock.initColorHandler(blockColors);
    }

    public static void init() {
        BGBlocks.init();
        BGItems.init();
        BGEntities.init();
        BGTileEntities.init();
        DistExecutor.runWhenOn(Dist.CLIENT, () -> BuildingObjects::clientInit);
    }

    public static void cleanup() {
        BGBlocks.cleanup();
        BGItems.cleanup();
        BGEntities.cleanup();
        BGTileEntities.cleanup();
    }

    private static void clientInit() {
        BGEntities.clientInit();
        BGTileEntities.clientInit();
    }
}
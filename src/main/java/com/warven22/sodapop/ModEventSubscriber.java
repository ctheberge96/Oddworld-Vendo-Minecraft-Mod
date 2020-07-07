package com.warven22.sodapop;

import com.warven22.sodapop.init.ModItemGroups;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistryEntry;

@EventBusSubscriber(modid = SodaPop.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {

	public ModEventSubscriber() {
		
	}
	
	/*@SubscribeEvent
	public static void onRegisterItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(
			setup(new Item(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)), "example_ingot")
		);
	}
	
	@SubscribeEvent
	public static void onRegisterBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(
			setup(new ExampleBlock(), "example_block")
		);
	}*/
	
	public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final String name) {
		return setup(entry, new ResourceLocation(SodaPop.MODID, name));
	}
	public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName) {
		entry.setRegistryName(registryName);
		return entry;
	}
}
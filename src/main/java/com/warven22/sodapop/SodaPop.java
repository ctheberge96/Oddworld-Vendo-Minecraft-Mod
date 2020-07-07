package com.warven22.sodapop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.warven22.sodapop.blocks.VendoBottom;
import com.warven22.sodapop.blocks.VendoTop;
import com.warven22.sodapop.blocks.blockitems.VendoItem;
import com.warven22.sodapop.init.ModItemGroups;
import com.warven22.sodapop.items.ExampleItem;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod(SodaPop.MODID)
public class SodaPop {
	public static final String MODID = "soda-pop";
	public static final Logger LOGGER = LogManager.getLogger();
	
	// Deferred Registers
	private static final DeferredRegister<Item> REGISTER_ITEMS = new DeferredRegister<Item>(ForgeRegistries.ITEMS, MODID);
	private static final DeferredRegister<Block> REGISTER_BLOCKS = new DeferredRegister<Block>(ForgeRegistries.BLOCKS, MODID);
	
	// Items
	public static final RegistryObject<Item> EXAMPLE_ITEM = REGISTER_ITEMS.register("example_item", () -> new ExampleItem());
	
	// Blocks
	private static final VendoBottom vendoBottom = new VendoBottom();
	public static final RegistryObject<VendoBottom> VENDO_BOTTOM = REGISTER_BLOCKS.register("vendo_bottom", () -> vendoBottom);
	public static final RegistryObject<VendoTop> VENDO_TOP = REGISTER_BLOCKS.register("vendo_top", () -> new VendoTop());
	public static final RegistryObject<Item> VENDO_ITEM = REGISTER_ITEMS.register("vendo", () -> new VendoItem(vendoBottom));
	
	public SodaPop() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		REGISTER_ITEMS.register(bus);
		REGISTER_BLOCKS.register(bus);
	}

}
package com.warven22.sodapop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.warven22.sodapop.blocks.VendoBottom;
import com.warven22.sodapop.blocks.VendoTop;
import com.warven22.sodapop.blocks.blockitems.VendoItem;
import com.warven22.sodapop.items.ExpressoCan;
import com.warven22.sodapop.items.VendoCoin;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
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
	private static final DeferredRegister<SoundEvent> REGISTER_SOUNDS = new DeferredRegister<SoundEvent>(ForgeRegistries.SOUND_EVENTS, MODID);
	
	// Items
	public static final RegistryObject<Item> VENDO_COIN = REGISTER_ITEMS.register("vendo_coin", () -> new VendoCoin());
	public static final RegistryObject<Item> CAN_EXPRESSO = REGISTER_ITEMS.register("can_expresso", () -> new ExpressoCan());
	
	// Blocks
	private static final VendoBottom vendoBottom = new VendoBottom();
	public static final RegistryObject<VendoBottom> VENDO_BOTTOM = REGISTER_BLOCKS.register("vendo_bottom", () -> vendoBottom);
	public static final RegistryObject<VendoTop> VENDO_TOP = REGISTER_BLOCKS.register("vendo_top", () -> new VendoTop());
	public static final RegistryObject<Item> VENDO_ITEM = REGISTER_ITEMS.register("vendo", () -> new VendoItem(vendoBottom));
	
	// Sounds
	public static final RegistryObject<SoundEvent> VENDO_DENY = REGISTER_SOUNDS.register("vendo_deny", () -> new SoundEvent(new ResourceLocation(MODID, "vendo_deny")));
	public static final RegistryObject<SoundEvent> VENDO_APPROVE = REGISTER_SOUNDS.register("vendo_approve", () -> new SoundEvent(new ResourceLocation(MODID, "vendo_approve")));
	
	public SodaPop() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		REGISTER_ITEMS.register(bus);
		REGISTER_BLOCKS.register(bus);
		REGISTER_SOUNDS.register(bus);
	}

}
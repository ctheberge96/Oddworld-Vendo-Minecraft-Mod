package com.warven22.sodapop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.warven22.sodapop.blocks.VendoBottomBase;
import com.warven22.sodapop.blocks.VendoTopBase;
import com.warven22.sodapop.blocks.blockitems.VendoItem;
import com.warven22.sodapop.effects.Zap;
import com.warven22.sodapop.items.BounceCan;
import com.warven22.sodapop.items.ExpressoCan;
import com.warven22.sodapop.items.HealthUpCan;
import com.warven22.sodapop.items.VendoCoin;
import com.warven22.sodapop.items.ZapCan;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
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
	public static final DeferredRegister<Item> REGISTER_ITEMS = new DeferredRegister<Item>(ForgeRegistries.ITEMS, MODID);
	public static final DeferredRegister<Block> REGISTER_BLOCKS = new DeferredRegister<Block>(ForgeRegistries.BLOCKS, MODID);
	public static final DeferredRegister<SoundEvent> REGISTER_SOUNDS = new DeferredRegister<SoundEvent>(ForgeRegistries.SOUND_EVENTS, MODID);
	public static final DeferredRegister<Effect> REGISTER_EFFECTS = new DeferredRegister<Effect>(ForgeRegistries.POTIONS, MODID);
	
	// Items
	public static final RegistryObject<Item> VENDO_COIN = REGISTER_ITEMS.register("vendo_coin", () -> new VendoCoin());
	public static final RegistryObject<Item> CAN_EXPRESSO = REGISTER_ITEMS.register("can_expresso", () -> new ExpressoCan());
	public static final RegistryObject<Item> CAN_BOUNCE = REGISTER_ITEMS.register("can_bounce", () -> new BounceCan());
	public static final RegistryObject<Item> CAN_HEALTHUP = REGISTER_ITEMS.register("can_healthup", () -> new HealthUpCan());
	public static final RegistryObject<Item> CAN_ZAP = REGISTER_ITEMS.register("can_zap", () -> new ZapCan());
	
	// Blocks
	
	// Expresso
	private static final VendoBottomBase vendoBottomExpresso = new VendoBottomBase(VendoBottomBase.VendoCan.EXPRESSO, 10);
	public static final RegistryObject<VendoBottomBase> VENDO_BOTTOM_EXPRESSO = REGISTER_BLOCKS.register("vendo_expresso_bottom", () -> vendoBottomExpresso);
	public static final RegistryObject<VendoTopBase> VENDO_TOP_EXPRESSO = REGISTER_BLOCKS.register("vendo_expresso_top", () -> new VendoTopBase());
	public static final RegistryObject<Item> VENDO_ITEM_EXPRESSO = REGISTER_ITEMS.register("vendo_expresso", () -> new VendoItem(vendoBottomExpresso));
	
	// Bounce
	private static final VendoBottomBase vendoBottomBounce = new VendoBottomBase(VendoBottomBase.VendoCan.BOUNCE, 5);
	public static final RegistryObject<VendoBottomBase> VENDO_BOTTOM_BOUNCE = REGISTER_BLOCKS.register("vendo_bounce_bottom", () -> vendoBottomBounce);
	public static final RegistryObject<VendoTopBase> VENDO_TOP_BOUNCE = REGISTER_BLOCKS.register("vendo_bounce_top", () -> new VendoTopBase());
	public static final RegistryObject<Item> VENDO_ITEM_BOUNCE = REGISTER_ITEMS.register("vendo_bounce", () -> new VendoItem(vendoBottomBounce));
	
	// Health Up
	private static final VendoBottomBase vendoBottomHealthUp = new VendoBottomBase(VendoBottomBase.VendoCan.HEALTHUP, 5);
	public static final RegistryObject<VendoBottomBase> VENDO_BOTTOM_HEALTHUP = REGISTER_BLOCKS.register("vendo_healthup_bottom", () -> vendoBottomHealthUp);
	public static final RegistryObject<VendoTopBase> VENDO_TOP_HEALTHUP = REGISTER_BLOCKS.register("vendo_healthup_top", () -> new VendoTopBase());
	public static final RegistryObject<Item> VENDO_ITEM_HEALTHUP = REGISTER_ITEMS.register("vendo_healthup", () -> new VendoItem(vendoBottomHealthUp));
	
	// Sounds
	public static final RegistryObject<SoundEvent> VENDO_DENY = REGISTER_SOUNDS.register("vendo_deny", () -> new SoundEvent(new ResourceLocation(MODID, "vendo_deny")));
	public static final RegistryObject<SoundEvent> VENDO_APPROVE = REGISTER_SOUNDS.register("vendo_approve", () -> new SoundEvent(new ResourceLocation(MODID, "vendo_approve")));
	public static final RegistryObject<SoundEvent> CAN_DRINK = REGISTER_SOUNDS.register("can_drink", () -> new SoundEvent(new ResourceLocation(MODID, "can_drink")));
	
	// Effects
	public static final RegistryObject<Effect> EFFECT_ZAP = REGISTER_EFFECTS.register("effect_zap", () -> new Zap());
	
	public SodaPop() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		REGISTER_EFFECTS.register(bus);
		REGISTER_ITEMS.register(bus);
		REGISTER_BLOCKS.register(bus);
		REGISTER_SOUNDS.register(bus);
	}

}
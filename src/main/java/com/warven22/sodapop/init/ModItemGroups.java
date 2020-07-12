package com.warven22.sodapop.init;

import org.apache.logging.log4j.util.Supplier;

import com.warven22.sodapop.SodaPop;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroups {
	public static final ItemGroup MOD_ITEM_GROUP = new ModItemGroup(SodaPop.MODID, () -> new ItemStack(ModItems.VENDO_COIN));
	
	public static class ModItemGroup extends ItemGroup {

		private final Supplier<ItemStack> iconSupplier;
		
		public ModItemGroup(String name, Supplier<ItemStack> iconSupplier) {
			super(name);
			
			this.iconSupplier = iconSupplier;
		}

		@Override
		public ItemStack createIcon() {
			return iconSupplier.get();
		}
		
	}

}

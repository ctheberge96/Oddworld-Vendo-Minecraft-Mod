package com.warven22.sodapop.items;

import com.warven22.sodapop.init.ModItemGroups;

import net.minecraft.item.Item;

public class VendoCoin extends Item {

	public VendoCoin() {
		super(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP).maxStackSize(64));
	}
}
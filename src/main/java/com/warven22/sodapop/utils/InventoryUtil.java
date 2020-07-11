package com.warven22.sodapop.utils;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class InventoryUtil {
	public static void addItemToInventory(PlayerInventory inventory, Item item, int count) {
		inventory.addItemStackToInventory(new ItemStack(item, count));
	}
	public static void addSingleItemToInventory(PlayerInventory inventory, Item item) {
		addItemToInventory(inventory, item, 1);
	}
}
package com.warven22.sodapop.items;

import com.warven22.sodapop.init.ModItemGroups;
import com.warven22.sodapop.utils.PotionEffectUtil;
import com.warven22.sodapop.utils.TimeUtil;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Food.Builder;
import net.minecraft.potion.Effects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.world.World;

public class ExpressoCan extends Item {
	
	public ExpressoCan() {
		super(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP).
				maxStackSize(64).
				food(new Builder().setAlwaysEdible().build()));
	}

	  // what animation to use when the player holds the "use" button
	  @Override
	  public UseAction getUseAction(ItemStack stack) {
	    return UseAction.DRINK;
	  }

	  // how long the drinking will last for, in ticks (1 tick = 1/20 second)
	  @Override
	  public int getUseDuration(ItemStack stack) {
		  return TimeUtil.getTicksFromSeconds(2);
	  }

	  @Override
	  public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving)
	  {
		stack.shrink(1);
		PotionEffectUtil.addEffectToLivingEntity(Effects.SPEED, 30, 4, entityLiving);
	    return stack;
	  }
}
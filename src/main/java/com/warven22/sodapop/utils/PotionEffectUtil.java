package com.warven22.sodapop.utils;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;

public class PotionEffectUtil {
	public static void addEffectToLivingEntity(Effect effect, float durationInSeconds, int level, LivingEntity entity) {
		entity.addPotionEffect(new EffectInstance(effect, TimeUtil.getTicksFromSeconds(durationInSeconds), level-1));
	}
}
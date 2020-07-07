package com.warven22.sodapop.blocks.blockitems;

import com.warven22.sodapop.blocks.VendoBottom;
import com.warven22.sodapop.init.ModItemGroups;

import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class VendoItem extends BlockItem {
	public VendoItem(VendoBottom block) {
		super(block, new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP));
	}
	
	@Override
	protected boolean canPlace(BlockItemUseContext context, BlockState state) {
		// Can only place if the block above is air, as this block will spawn two blocks
		BlockPos pos = context.getPos();
		World world = context.getWorld();
		return super.canPlace(context, state) && world.getBlockState(pos.up()).isAir(world, pos.up());
	}
}
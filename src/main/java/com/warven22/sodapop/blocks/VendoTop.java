package com.warven22.sodapop.blocks;

import java.util.stream.Stream;

import com.warven22.sodapop.init.ModBlocks;
import com.warven22.sodapop.init.ModItemGroups;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;

public class VendoTop extends Block {
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	
	private static final VoxelShape VENDO_TOP_SHAPE = Stream.of(
			Block.makeCuboidShape(3, 0, 6, 13, 16, 7), 
			Block.makeCuboidShape(0, 0, 11, 16, 16, 16), 
			Block.makeCuboidShape(1, 0, 9, 15, 16, 11), 
			Block.makeCuboidShape(2, 0, 7, 14, 16, 9))
			.reduce((v1,v2)->{return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
	
	public VendoTop() {
		super(Block.Properties.create(Material.ROCK)
				.lightValue(10)
				.hardnessAndResistance(3.0f, 3.0f)
				.harvestTool(ToolType.PICKAXE)
				.sound(SoundType.ANVIL));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return VENDO_TOP_SHAPE;
	}
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		// TODO Auto-generated method stub
		super.fillStateContainer(builder);
		builder.add(FACING);
	}
}
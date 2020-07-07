package com.warven22.sodapop.blocks;

import java.util.stream.Stream;

import com.warven22.sodapop.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class VendoBottom extends Block {
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	
	private static final VoxelShape VENDO_BOTTOM_SHAPE_N = Stream.of(
			Block.makeCuboidShape(2, 3, 7, 14, 12, 9), 
			Block.makeCuboidShape(0, 3, 13, 16, 12, 16), 
			Block.makeCuboidShape(3, 3, 6, 13, 12, 7), 
			Block.makeCuboidShape(0, 3, 11, 16, 12, 13), 
			Block.makeCuboidShape(1, 3, 9, 15, 12, 11), 
			Block.makeCuboidShape(4, 0, 7, 12, 3, 8), 
			Block.makeCuboidShape(3, 0, 8, 13, 3, 10), 
			Block.makeCuboidShape(2, 0, 10, 14, 3, 12), 
			Block.makeCuboidShape(1, 0, 12, 15, 3, 14), 
			Block.makeCuboidShape(1, 0, 14, 15, 3, 16), 
			Block.makeCuboidShape(5, 4, 5, 11, 11, 6), 
			Block.makeCuboidShape(6, 0, 5, 10, 1, 7), 
			Block.makeCuboidShape(6, 1, 4, 10, 2, 5), 
			Block.makeCuboidShape(10, 1, 5, 11, 3, 7), 
			Block.makeCuboidShape(5, 1, 5, 6, 3, 7), 
			Block.makeCuboidShape(3, 12, 6, 13, 16, 7), 
			Block.makeCuboidShape(0, 12, 11, 16, 16, 16), 
			Block.makeCuboidShape(1, 12, 9, 15, 16, 11), 
			Block.makeCuboidShape(2, 12, 7, 14, 16, 9))
			.reduce((v1,v2)->{return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
	private static final VoxelShape VENDO_BOTTOM_SHAPE_E = Stream.of(
			Block.makeCuboidShape(7, 3, 2, 9, 12, 14), 
			Block.makeCuboidShape(0, 3, 0, 3, 12, 16), 
			Block.makeCuboidShape(9, 3, 3, 10, 12, 13), 
			Block.makeCuboidShape(3, 3, 0, 5, 12, 16), 
			Block.makeCuboidShape(5, 3, 1, 7, 12, 15), 
			Block.makeCuboidShape(8, 0, 4, 9, 3, 12), 
			Block.makeCuboidShape(6, 0, 3, 8, 3, 13), 
			Block.makeCuboidShape(4, 0, 2, 6, 3, 14), 
			Block.makeCuboidShape(2, 0, 1, 4, 3, 15), 
			Block.makeCuboidShape(0, 0, 1, 2, 3, 15), 
			Block.makeCuboidShape(10, 4, 5, 11, 11, 11), 
			Block.makeCuboidShape(9, 0, 6, 11, 1, 10), 
			Block.makeCuboidShape(11, 1, 6, 12, 2, 10), 
			Block.makeCuboidShape(9, 1, 10, 11, 3, 11), 
			Block.makeCuboidShape(9, 1, 5, 11, 3, 6), 
			Block.makeCuboidShape(9, 12, 3, 10, 16, 13), 
			Block.makeCuboidShape(0, 12, 0, 5, 16, 16), 
			Block.makeCuboidShape(5, 12, 1, 7, 16, 15), 
			Block.makeCuboidShape(7, 12, 2, 9, 16, 14))
			.reduce((v1,v2)->{return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
	private static final VoxelShape VENDO_BOTTOM_SHAPE_S = Stream.of(
			Block.makeCuboidShape(2, 3, 7, 14, 12, 9), 
			Block.makeCuboidShape(0, 3, 0, 16, 12, 3), 
			Block.makeCuboidShape(3, 3, 9, 13, 12, 10), 
			Block.makeCuboidShape(0, 3, 3, 16, 12, 5), 
			Block.makeCuboidShape(1, 3, 5, 15, 12, 7), 
			Block.makeCuboidShape(4, 0, 8, 12, 3, 9), 
			Block.makeCuboidShape(3, 0, 6, 13, 3, 8), 
			Block.makeCuboidShape(2, 0, 4, 14, 3, 6), 
			Block.makeCuboidShape(1, 0, 2, 15, 3, 4), 
			Block.makeCuboidShape(1, 0, 0, 15, 3, 2), 
			Block.makeCuboidShape(5, 4, 10, 11, 11, 11), 
			Block.makeCuboidShape(6, 0, 9, 10, 1, 11), 
			Block.makeCuboidShape(6, 1, 11, 10, 2, 12), 
			Block.makeCuboidShape(5, 1, 9, 6, 3, 11), 
			Block.makeCuboidShape(10, 1, 9, 11, 3, 11), 
			Block.makeCuboidShape(3, 12, 9, 13, 16, 10), 
			Block.makeCuboidShape(0, 12, 0, 16, 16, 5), 
			Block.makeCuboidShape(1, 12, 5, 15, 16, 7), 
			Block.makeCuboidShape(2, 12, 7, 14, 16, 9))
			.reduce((v1,v2)->{return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
	private static final VoxelShape VENDO_BOTTOM_SHAPE_W = Stream.of(
			Block.makeCuboidShape(7, 3, 2, 9, 12, 14), 
			Block.makeCuboidShape(13, 3, 0, 16, 12, 16), 
			Block.makeCuboidShape(6, 3, 3, 7, 12, 13), 
			Block.makeCuboidShape(11, 3, 0, 13, 12, 16), 
			Block.makeCuboidShape(9, 3, 1, 11, 12, 15), 
			Block.makeCuboidShape(7, 0, 4, 8, 3, 12), 
			Block.makeCuboidShape(8, 0, 3, 10, 3, 13), 
			Block.makeCuboidShape(10, 0, 2, 12, 3, 14), 
			Block.makeCuboidShape(12, 0, 1, 14, 3, 15), 
			Block.makeCuboidShape(14, 0, 1, 16, 3, 15), 
			Block.makeCuboidShape(5, 4, 5, 6, 11, 11), 
			Block.makeCuboidShape(5, 0, 6, 7, 1, 10), 
			Block.makeCuboidShape(4, 1, 6, 5, 2, 10), 
			Block.makeCuboidShape(5, 1, 5, 7, 3, 6), 
			Block.makeCuboidShape(5, 1, 10, 7, 3, 11), 
			Block.makeCuboidShape(6, 12, 3, 7, 16, 13), 
			Block.makeCuboidShape(11, 12, 0, 16, 16, 16), 
			Block.makeCuboidShape(9, 12, 1, 11, 16, 15), 
			Block.makeCuboidShape(7, 12, 2, 9, 16, 14))
			.reduce((v1,v2)->{return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
	
	public VendoBottom() {
		super(Block.Properties.create(Material.ROCK)
				.lightValue(10)
				.hardnessAndResistance(3.0f, 3.0f)
				.harvestTool(ToolType.PICKAXE)
				.sound(SoundType.ANVIL));
		setDefaultState(stateContainer.getBaseState().with(FACING, Direction.NORTH));
	}
	
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity player, ItemStack stack) {
		BlockState topState = ModBlocks.VENDO_TOP.getDefaultState().with(FACING, state.get(FACING));
		world.setBlockState(pos.up(), topState);
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		switch (state.get(FACING)) {
		case EAST:
			return VENDO_BOTTOM_SHAPE_E;
		case SOUTH:
			return VENDO_BOTTOM_SHAPE_S;
		case WEST:
			return VENDO_BOTTOM_SHAPE_W;
		default:
			return VENDO_BOTTOM_SHAPE_N;
		}
	}

	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(FACING);
	}

	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
		super.onBlockHarvested(worldIn, pos, state, player);
		// Destroys top
		worldIn.setBlockState(pos.up(), Blocks.AIR.getDefaultState());
	}
	
	
	
	
}
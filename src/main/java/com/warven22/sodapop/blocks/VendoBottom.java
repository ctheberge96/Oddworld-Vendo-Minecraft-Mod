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
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;

public class VendoBottom extends Block {
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	
	private static final VoxelShape VENDO_BOTTOM_SHAPE = Stream.of(
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
		world.setBlockState(pos.up(), ModBlocks.VENDO_TOP.getDefaultState().with(FACING, state.get(FACING)));
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		/*switch (state.get(FACING)) {
		case DOWN:
			break;
		case EAST:
			break;
		case NORTH:
			break;
		case SOUTH:
			break;
		case UP:
			break;
		case WEST:
			break;
		default:
			break;
		}*/
		return VENDO_BOTTOM_SHAPE;
	}

	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(FACING);
	}

}
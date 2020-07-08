package com.warven22.sodapop.blocks;

import java.util.stream.Stream;

import com.warven22.sodapop.init.ModBlocks;
import com.warven22.sodapop.init.ModItems;
import com.warven22.sodapop.init.ModSounds;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;

public class VendoBottom extends Block {
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	//public static final IntegerProperty PRICE = IntegerProperty.create("PRICE", 0, Integer.MAX_VALUE);
	
	public static final VoxelShape VENDO_SHAPE_N = Stream.of(
			Block.makeCuboidShape(3, 0, 6, 13, 16, 7), 
			Block.makeCuboidShape(0, 0, 11, 16, 16, 16), 
			Block.makeCuboidShape(1, 0, 9, 15, 16, 11), 
			Block.makeCuboidShape(2, 0, 7, 14, 16, 9))
			.reduce((v1,v2)->{return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
	public static final VoxelShape VENDO_SHAPE_E = Stream.of(
			Block.makeCuboidShape(9, 0, 3, 10, 16, 13), 
			Block.makeCuboidShape(0, 0, 0, 5, 16, 16), 
			Block.makeCuboidShape(5, 0, 1, 7, 16, 15), 
			Block.makeCuboidShape(7, 0, 2, 9, 16, 14))
			.reduce((v1,v2)->{return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
	public static final VoxelShape VENDO_SHAPE_S = Stream.of(
			Block.makeCuboidShape(3, 0, 9, 13, 16, 10), 
			Block.makeCuboidShape(0, 0, 0, 16, 16, 5), 
			Block.makeCuboidShape(1, 0, 5, 15, 16, 7), 
			Block.makeCuboidShape(2, 0, 7, 14, 16, 9))
			.reduce((v1,v2)->{return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
	public static final VoxelShape VENDO_SHAPE_W = Stream.of(
			Block.makeCuboidShape(6, 0, 3, 7, 16, 13), 
			Block.makeCuboidShape(11, 0, 0, 16, 16, 16), 
			Block.makeCuboidShape(9, 0, 1, 11, 16, 15), 
			Block.makeCuboidShape(7, 0, 2, 9, 16, 14))
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
			return VENDO_SHAPE_E;
		case SOUTH:
			return VENDO_SHAPE_S;
		case WEST:
			return VENDO_SHAPE_W;
		default:
			return VENDO_SHAPE_N;
		}
	}

	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(FACING);
		//builder.add(PRICE);
	}

	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
		super.onBlockHarvested(worldIn, pos, state, player);
		// Destroys top
		worldIn.setBlockState(pos.up(), Blocks.AIR.getDefaultState());
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		if (handIn != Hand.MAIN_HAND) return ActionResultType.FAIL;
		if (worldIn instanceof ServerWorld) return ActionResultType.FAIL;
		int totalPrice = 10;//state.get(PRICE).intValue();
		PlayerInventory playerInv = player.inventory;
		int coins = playerInv.count(ModItems.VENDO_COIN);
		if (totalPrice <= coins) {
			// Enough coins, so we can remove them
			while (totalPrice > 0) {
				int nextCoinStackIndex = playerInv.getSlotFor(new ItemStack(ModItems.VENDO_COIN));
				if (nextCoinStackIndex == -1) {
					break;
				}
				ItemStack coinStack = playerInv.getStackInSlot(nextCoinStackIndex);
				int totalTaken = 0;
				if (coinStack.getCount() <= totalPrice) {
					totalTaken = coinStack.getCount();
					playerInv.removeStackFromSlot(nextCoinStackIndex);
				} else {
					totalTaken = totalPrice;
					playerInv.decrStackSize(nextCoinStackIndex, totalPrice);
				}
				totalPrice -= totalTaken;
			}
			worldIn.playSound(player, pos, ModSounds.VENDO_APPROVE, SoundCategory.BLOCKS, .25f, 1f);
			playerInv.addItemStackToInventory(new ItemStack(ModItems.CAN_EXPRESSO, 1));
		} else {
			// Not enough coins, so don't touch anything
			worldIn.playSound(player, pos, ModSounds.VENDO_DENY, SoundCategory.BLOCKS, .25f, 1f);
			player.sendMessage(new StringTextComponent(String.format("You need %d coins to buy a can of Expresso!", totalPrice)));
		}
		return ActionResultType.SUCCESS;
	}
	
}
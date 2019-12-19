package nc.multiblock.fission.solid.block;

import nc.block.property.ISidedProperty;
import nc.block.property.PropertySidedEnum;
import nc.multiblock.fission.block.BlockFissionPart;
import nc.multiblock.fission.solid.SolidFissionCellSetting;
import nc.multiblock.fission.solid.tile.TileSolidFissionCell;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSolidFissionCell extends BlockFissionPart implements ISidedProperty<SolidFissionCellSetting> {

	//private static EnumFacing placementSide = null;
	
	public BlockSolidFissionCell() {
		super();
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileSolidFissionCell();
	}
	
	private static final PropertySidedEnum<SolidFissionCellSetting> DOWN = PropertySidedEnum.create("down", SolidFissionCellSetting.class, EnumFacing.DOWN);
	private static final PropertySidedEnum<SolidFissionCellSetting> UP = PropertySidedEnum.create("up", SolidFissionCellSetting.class, EnumFacing.UP);
	private static final PropertySidedEnum<SolidFissionCellSetting> NORTH = PropertySidedEnum.create("north", SolidFissionCellSetting.class, EnumFacing.NORTH);
	private static final PropertySidedEnum<SolidFissionCellSetting> SOUTH = PropertySidedEnum.create("south", SolidFissionCellSetting.class, EnumFacing.SOUTH);
	private static final PropertySidedEnum<SolidFissionCellSetting> WEST = PropertySidedEnum.create("west", SolidFissionCellSetting.class, EnumFacing.WEST);
	private static final PropertySidedEnum<SolidFissionCellSetting> EAST = PropertySidedEnum.create("east", SolidFissionCellSetting.class, EnumFacing.EAST);
	
	@Override
	public SolidFissionCellSetting getProperty(IBlockAccess world, BlockPos pos, EnumFacing facing) {
		if (world.getTileEntity(pos) instanceof TileSolidFissionCell) {
			return ((TileSolidFissionCell) world.getTileEntity(pos)).getCellSetting(facing);
		}
		return SolidFissionCellSetting.DISABLED;
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, DOWN, UP, NORTH, SOUTH, WEST, EAST);
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		return state.withProperty(DOWN, getProperty(world, pos, EnumFacing.DOWN)).withProperty(UP, getProperty(world, pos, EnumFacing.UP)).withProperty(NORTH, getProperty(world, pos, EnumFacing.NORTH)).withProperty(SOUTH, getProperty(world, pos, EnumFacing.SOUTH)).withProperty(WEST, getProperty(world, pos, EnumFacing.WEST)).withProperty(EAST, getProperty(world, pos, EnumFacing.EAST));
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}
	
	/*@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (hand != EnumHand.MAIN_HAND || player == null) return false;
		
		if (player.getHeldItemMainhand().isEmpty() && world.getTileEntity(pos) instanceof TileSolidFissionCell) {
			TileSolidFissionCell vessel = (TileSolidFissionCell) world.getTileEntity(pos);
			EnumFacing side = player.isSneaking() ? facing.getOpposite() : facing;
			vessel.toggleCellSetting(side);
			if (!world.isRemote) player.sendMessage(getToggleMessage(player, vessel, side));
			return true;
		}
		return super.onBlockActivated(world, pos, state, player, hand, facing, hitX, hitY, hitZ);
	}
	
	private static TextComponentString getToggleMessage(EntityPlayer player, TileSolidFissionCell vessel, EnumFacing side) {
		SolidFissionCellSetting setting = vessel.getCellSetting(side);
		String message = player.isSneaking() ? "nc.block.fluid_toggle_opposite" : "nc.block.fluid_toggle";
		TextFormatting color = setting == SolidFissionCellSetting.DEPLETED_OUT ? TextFormatting.LIGHT_PURPLE : (setting == SolidFissionCellSetting.FUEL_SPREAD ? TextFormatting.GREEN : (setting == SolidFissionCellSetting.DEFAULT ? TextFormatting.WHITE : TextFormatting.GRAY));
		return new TextComponentString(Lang.localise(message) + " " + color + Lang.localise("nc.block.salt_vessel_fluid_side." + setting.getName()));
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		placementSide = null;
		if (placer != null && placer.isSneaking()) placementSide = facing.getOpposite();
		return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand);
	}
	
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		if (placementSide ==  null) return;
		BlockPos from = pos.offset(placementSide);
		if (world.getTileEntity(pos) instanceof TileSolidFissionCell && world.getTileEntity(from) instanceof TileSolidFissionCell) {
			TileSolidFissionCell vessel = (TileSolidFissionCell) world.getTileEntity(pos);
			TileSolidFissionCell other = (TileSolidFissionCell) world.getTileEntity(from);
			vessel.setInventoryConnections(InventoryConnection.cloneArray(other.getInventoryConnections()));
			vessel.setCellSettings(other.getCellSettings().clone());
			vessel.markDirtyAndNotify();
		}
	}*/
}

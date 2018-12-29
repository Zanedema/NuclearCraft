package nc.tile.dummy;

import nc.config.NCConfig;
import nc.tile.energy.ITileEnergy;
import nc.tile.energyFluid.IBufferable;
import nc.tile.fluid.ITileFluid;
import nc.tile.internal.energy.EnergyConnection;
import nc.tile.internal.fluid.FluidConnection;
import nc.tile.internal.fluid.TankSorption;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class TileMachineInterface extends TileDummy<TileEntity> implements IBufferable {
	
	public TileMachineInterface() {
		super(TileEntity.class, "machine_interface", ITileEnergy.energyConnectionAll(EnergyConnection.BOTH), TankSorption.BOTH, NCConfig.machine_update_rate, null, ITileFluid.fluidConnectionAll(FluidConnection.BOTH));
	}
	
	@Override
	public void update() {
		super.update();
		if(!world.isRemote) {
			pushEnergy();
			pushFluid();
		}
	}
	
	// Find Master
	
	@Override
	public void findMaster() {
		for (EnumFacing side : EnumFacing.VALUES) {
			TileEntity tile = world.getTileEntity(getPos().offset(side));
			if (tile != null) {
				if (isMaster(getPos().offset(side))) {
					masterPosition = getPos().offset(side);
					return;
				}
			}
		}
		masterPosition = null;
	}
	
	// Special case for interface type
	@Override
	public boolean isMaster(BlockPos pos) {
		return world.getTileEntity(pos) instanceof IInterfaceable;
	}
}

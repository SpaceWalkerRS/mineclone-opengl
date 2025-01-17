package mineclone.common.world.block;

import java.util.List;
import java.util.Random;

import mineclone.common.world.Direction;
import mineclone.common.world.EntityHitbox;
import mineclone.common.world.IServerWorld;
import mineclone.common.world.IWorld;
import mineclone.common.world.block.signal.SignalType;
import mineclone.common.world.block.signal.wire.ConnectionSide;
import mineclone.common.world.block.signal.wire.WireType;
import mineclone.common.world.block.state.IBlockState;

public interface IBlock {

	String getName();

	IBlockState getDefaultState();

	default boolean isAir() {
		return false;
	}

	default IBlockState getPlacementState(IWorld world, IBlockPosition pos, IBlockState state) {
		return state;
	}

	default boolean canExist(IWorld world, IBlockPosition pos, IBlockState state) {
		return true;
	}

	default void onAdded(IServerWorld world, IBlockPosition pos, IBlockState state) {
	}

	default void onRemoved(IServerWorld world, IBlockPosition pos, IBlockState state) {
	}

	default void onChanged(IServerWorld world, IBlockPosition pos, IBlockState oldState, IBlockState newState) {
	}

	default void updateNeighbors(IServerWorld world, IBlockPosition pos, IBlockState state) {
		world.updateNeighbors(pos);
	}

	default void updateNeighborShapes(IServerWorld world, IBlockPosition pos, IBlockState state) {
		world.updateNeighborShapes(pos, state);
	}

	default void update(IServerWorld world, IBlockPosition pos, IBlockState state) {
	}

	default void updateShape(IServerWorld world, IBlockPosition pos, IBlockState state, Direction dir, IBlockPosition neighborPos, IBlockState neighborState) {
	}

	default void randomUpdate(IServerWorld world, IBlockPosition pos, IBlockState state, Random random) {
	}

	default boolean doesRandomUpdates(IBlockState state) {
		return false;
	}

	default void getEntityHitboxes(IWorld world, IBlockPosition pos, IBlockState state, List<EntityHitbox> hitboxes) {
		if (hasEntityHitbox(world, pos, state)) {
			float x = pos.getX();
			float y = pos.getY();
			float z = pos.getZ();

			hitboxes.add(new EntityHitbox(x, y, z, x + 1.0f, y + 1.0f, z + 1.0f));
		}
	}

	default boolean hasEntityHitbox(IWorld world, IBlockPosition pos, IBlockState state) {
		return isSolid();
	}

	default boolean isSolid() {
		return false;
	}

	default boolean canGrowVegetation(IBlockState state) {
		return false;
	}

	default boolean isAligned(IBlockState state, Direction dir) {
		return isSolid();
	}

	default boolean isSignalSource(SignalType type) {
		return false;
	}

	default int getSignal(IServerWorld world, IBlockPosition pos, IBlockState state, Direction dir, SignalType type) {
		return type.min();
	}

	default int getDirectSignal(IServerWorld world, IBlockPosition pos, IBlockState state, Direction dir, SignalType type) {
		return type.min();
	}

	default boolean isWire() {
		return false;
	}

	default boolean isWire(SignalType type) {
		return false;
	}

	default boolean isWire(WireType type) {
		return false;
	}

	default boolean shouldWireConnect(IWorld world, IBlockPosition pos, IBlockState state, ConnectionSide side, WireType type) {
		return false;
	}

	default boolean isSignalConsumer(SignalType type) {
		return false;
	}

	default boolean isSignalConductor(IBlockState state, Direction dir, SignalType type) {
		return isAligned(state, dir);
	}
}

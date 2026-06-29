package com.besson.tutorial.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.jetbrains.annotations.Nullable;

public class SofaBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<Type> TYPE = EnumProperty.create("type", Type.class);

    public SofaBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(TYPE, Type.SINGLE));
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRot) {
        return pState.setValue(FACING, pRot.rotate(pState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pPos, BlockPos pNeighborPos) {
        return this.getRelatedBlockState(pState, pLevel, pPos, pState.getValue(FACING));
    }

    private BlockState getRelatedBlockState(BlockState state, LevelAccessor level, BlockPos pos, Direction direction) {
        boolean left = isRelatedInDirection(level, pos, direction, true);
        boolean right = isRelatedInDirection(level, pos, direction, false);
        if (left && right) {
            return state.setValue(TYPE, Type.MIDDLE);
        } else if (left) {
            return state.setValue(TYPE, Type.RIGHT);
        } else if (right) {
            return state.setValue(TYPE, Type.LEFT);
        }
        return state.setValue(TYPE, Type.SINGLE);
    }

    private boolean isRelatedInDirection(LevelAccessor level, BlockPos pos, Direction direction, boolean counterClockwise) {
        Direction rotate = counterClockwise ? direction.getCounterClockWise() : direction.getClockWise();
        return this.isRelatedBlock(level, pos, rotate, direction);
    }

    private boolean isRelatedBlock(LevelAccessor level, BlockPos pos, Direction rotate, Direction direction) {
        BlockState state = level.getBlockState(pos.relative(rotate));
        if (state.getBlock() == this) {
            Direction direction1 = state.getValue(FACING);
            return direction1.equals(direction);
        }
        return false;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, TYPE);
    }

    public enum Type implements StringRepresentable {
        SINGLE("single"),
        LEFT("left"),
        RIGHT("right"),
        MIDDLE("middle");

        private final String name;

        Type(String name) {
            this.name = name;
        }

        @Override
        public String getSerializedName() {
            return this.name;
        }
    }
}

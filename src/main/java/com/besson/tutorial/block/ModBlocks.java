package com.besson.tutorial.block;

import com.besson.tutorial.TutorialMod;
import com.besson.tutorial.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TutorialMod.MOD_ID);

    public static final RegistryObject<Block> ICE_ETHER_BLOCK =
            registerBlocks("ice_ether_block", () -> new Block(BlockBehaviour.Properties.of().strength(3.0F, 2.0F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> RAW_ICE_ETHER_BLOCK =
            registerBlocks("raw_ice_ether_block", () -> new Block(BlockBehaviour.Properties.of().strength(3.0F, 2.0F)));
    public static final RegistryObject<Block> ICE_ETHER_ORE =
            registerBlocks("ice_ether_ore", () -> new Block(BlockBehaviour.Properties.of().strength(3.0F, 2.0F).requiresCorrectToolForDrops()));

    public static final RegistryObject<StairBlock> ICE_ETHER_STAIRS =
            registerBlocks("ice_ether_stairs",
                    () -> new StairBlock(() -> ICE_ETHER_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.of().strength(3.0F, 2.0F)));
    public static final RegistryObject<SlabBlock> ICE_ETHER_SLAB =
            registerBlocks("ice_ether_slab",
                    () -> new SlabBlock(BlockBehaviour.Properties.of().strength(3.0F, 2.0F)));
    public static final RegistryObject<ButtonBlock> ICE_ETHER_BUTTON =
            registerBlocks("ice_ether_button",
                    () -> new ButtonBlock(BlockBehaviour.Properties.of().strength(1.0F, 2.0F), BlockSetType.STONE, 100, false));
    public static final RegistryObject<PressurePlateBlock> ICE_ETHER_PRESSURE_PLATE =
            registerBlocks("ice_ether_pressure_plate",
                    () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of().strength(1.0F, 2.0F), BlockSetType.STONE));
    public static final RegistryObject<FenceGateBlock> ICE_ETHER_FENCE_GATE =
            registerBlocks("ice_ether_fence_gate",
                    () -> new FenceGateBlock(BlockBehaviour.Properties.of().strength(3.0F, 2.0F), WoodType.OAK));
    public static final RegistryObject<FenceBlock> ICE_ETHER_FENCE =
            registerBlocks("ice_ether_fence",
                    () -> new FenceBlock(BlockBehaviour.Properties.of().strength(3.0F, 2.0F)));
    public static final RegistryObject<WallBlock> ICE_ETHER_WALL =
            registerBlocks("ice_ether_wall",
                    () -> new WallBlock(BlockBehaviour.Properties.of().strength(3.0F, 2.0F)));
    public static final RegistryObject<DoorBlock> ICE_ETHER_DOOR =
            registerBlocks("ice_ether_door",
                    () -> new DoorBlock(BlockBehaviour.Properties.of().strength(3.0F, 2.0F).noOcclusion(), BlockSetType.IRON));
    public static final RegistryObject<TrapDoorBlock> ICE_ETHER_TRAPDOOR =
            registerBlocks("ice_ether_trapdoor",
                    () -> new TrapDoorBlock(BlockBehaviour.Properties.of().strength(3.0F, 2.0F).noOcclusion(), BlockSetType.OAK));

    private static <T extends Block> void registerBlockItems(String name, RegistryObject<T> block){
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static <T extends Block>RegistryObject<T> registerBlocks(String name, Supplier<T> blocks){
        RegistryObject<T> block = BLOCKS.register(name, blocks);
        registerBlockItems(name, block);
        return block;
    }
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}

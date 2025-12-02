package com.besson.tutorial.item;

import com.besson.tutorial.TutorialMod;
import com.besson.tutorial.tag.ModBlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;
import java.util.function.Supplier;

public enum ModToolTiers implements Tier {
    FIRE_ETHER(5, 2031, 12.0f, 5.0f, 30, () -> Ingredient.of(ModItems.FIRE_ETHER.get()));


    public static final Tier ICE_ETHER = TierSortingRegistry.registerTier(
            new ForgeTier(5, 1400, 11f, 3f, 26,
                    ModBlockTags.ORE_TAGS, () -> Ingredient.of(ModItems.ICE_ETHER.get())),
            ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "ice_ether"), List.of(Tiers.NETHERITE), List.of());

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final Supplier<Ingredient> repairIngredient;

    private ModToolTiers(int pLevel, int pUses, float pSpeed, float pDamage, int pEnchantmentValue, Supplier<Ingredient> pRepairIngredient) {
        this.level = pLevel;
        this.uses = pUses;
        this.speed = pSpeed;
        this.damage = pDamage;
        this.enchantmentValue = pEnchantmentValue;
        this.repairIngredient = pRepairIngredient;
    }

    @Override
    public int getUses() {
        return uses;
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return damage;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient.get();
    }
}

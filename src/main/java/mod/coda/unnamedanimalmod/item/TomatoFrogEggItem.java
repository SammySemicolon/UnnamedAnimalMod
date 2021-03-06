package mod.coda.unnamedanimalmod.item;

import mod.coda.unnamedanimalmod.entity.TomatoFrogEntity;
import mod.coda.unnamedanimalmod.init.UAMEntities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class TomatoFrogEggItem extends Item {
    public TomatoFrogEggItem(Item.Properties builder) {
        super(builder);
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        TomatoFrogEntity child = UAMEntities.TOMATO_FROG.get().create(worldIn);
        if (!worldIn.isRemote && child != null) {
            child.setGrowingAge(-24000);
            child.setPosition(playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ());
            worldIn.addEntity(child);
            worldIn.playSound(playerIn, playerIn.getPosition(), SoundEvents.ENTITY_ITEM_FRAME_REMOVE_ITEM, SoundCategory.NEUTRAL, 1.0F, 1.0F);
        }

        playerIn.addStat(Stats.ITEM_USED.get(this));
        if (!playerIn.abilities.isCreativeMode) {
            itemstack.shrink(1);
        }

        return ActionResult.resultSuccess(itemstack);
    }
}
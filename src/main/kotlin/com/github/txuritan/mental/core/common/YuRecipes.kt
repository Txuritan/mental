/*
 * MIT License
 *
 * Copyright (c) 2017 Ian Cronkright
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.txuritan.mental.core.common

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.inventory.*
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.IRecipe
import net.minecraft.util.NonNullList
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.ReflectionHelper
import java.util.*

/**
 * @author Ian 'Txuritan' Cronkright
 */
class YuRecipes : IRecipe {
    override fun getRemainingItems(p0: InventoryCrafting?): NonNullList<ItemStack> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun matches(inv: InventoryCrafting, world: World): Boolean {
        val player = findPlayer(inv)
        return player != null && player.uniqueID == UUID.fromString("cff87b25-3d9c-4f44-accf-cecbbf2e4b15") && hasDirt(inv)
    }

    override fun getCraftingResult(inv: InventoryCrafting): ItemStack {
        return ItemStack(Items.DIAMOND)
    }

    override fun getRecipeSize(): Int {
        return 1
    }

    override fun getRecipeOutput(): ItemStack {
        return ItemStack(Items.DIAMOND)
    }

    companion object {

        private fun hasDirt(inv: InventoryCrafting): Boolean {
            val dirt = Item.getItemFromBlock(Blocks.DIRT)
            return (0..inv.sizeInventory - 1).map { inv.getStackInSlot(it) }.any { it != null && it.item === dirt }
        }

        private val eventHandlerField = ReflectionHelper.findField(InventoryCrafting::class.java, "eventHandler")
        private val containerPlayerPlayerField = ReflectionHelper.findField(ContainerPlayer::class.java, "player")
        private val slotCraftingPlayerField = ReflectionHelper.findField(SlotCrafting::class.java, "player")

        private fun findPlayer(inv: InventoryCrafting): EntityPlayer? {
            try {
                val container = eventHandlerField.get(inv) as Container
                if (container is ContainerPlayer) {
                    return containerPlayerPlayerField.get(container) as EntityPlayer
                } else if (container is ContainerWorkbench) {
                    return slotCraftingPlayerField.get(container.getSlot(0)) as EntityPlayer
                } else {
                    return null
                }
            } catch (e: Exception) {
                e.stackTrace
                return null
            }
        }
    }
}
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

package com.github.txuritan.mental.core.common.util

import com.github.txuritan.mental.core.common.item.IItemModelProvider
import com.github.txuritan.mental.core.common.item.IItemOreDict
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraftforge.fml.common.registry.GameRegistry

/**
 * @author Ian 'Txuritan/Captain Daro'Ma'Sohni Tavia' Cronkright
 */
object RegisterUtils {
    fun <T : Item> registerItem(item : T) : T {
        GameRegistry.register(item)

        if (item is IItemModelProvider) {
            item.registerItemModel(item)
        }

        if (item is IItemOreDict) {
            item.initOreDict()
        }

        return item
    }

    fun <T : Block> registerItemBlock(block : T, itemBlock : ItemBlock) : T {
        GameRegistry.register(block)
        GameRegistry.register(itemBlock)

        if (block is IItemModelProvider) {
            block.registerItemModel(itemBlock)
        }

        if (block is IItemOreDict) {
            block.initOreDict()
        }

        if (itemBlock is IItemOreDict) {
            itemBlock.initOreDict()
        }

        return block
    }

    fun <T : Block> registerBlock(block : T) : T {
        val itemBlock = ItemBlock(block)
        itemBlock.registryName = block.registryName !!
        return registerItemBlock(block, itemBlock)
    }
}

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

package com.github.txuritan.mental.core.common.item.tools

import com.github.txuritan.mental.core.common.Mental
import com.github.txuritan.mental.core.common.item.IItemModelProvider
import com.github.txuritan.mental.core.common.item.IItemOreDict
import net.minecraft.item.Item
import net.minecraft.item.ItemSpade
import net.minecraftforge.oredict.OreDictionary

/**
 * @author Ian 'Txuritan/Captain Daro'Ma'Sohni Tavia' Cronkright
 */
open class ItemShovelBase(material : Item.ToolMaterial, private val name : String, private val oreNames : Array<String>) : ItemSpade(material), IItemModelProvider, IItemOreDict {

    init {
        setRegistryName(name)
        unlocalizedName = name
    }

    override fun registerItemModel(item : Item) {
        Mental.proxy.registerItemRenderer(this, 0, name)
    }

    override fun initOreDict() {
        for (oreName in oreNames) {
            OreDictionary.registerOre(oreName, this)
        }
    }

}

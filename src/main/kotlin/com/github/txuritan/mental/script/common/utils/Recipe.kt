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

package com.github.txuritan.mental.script.common.utils

import com.github.txuritan.mental.script.common.IGlobal
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.oredict.OreDictionary
import net.minecraftforge.oredict.ShapedOreRecipe
import org.luaj.vm2.LuaValue

/**
 * @author Ian 'Txuritan/Captain Daro'Ma'Sohni Tavia' Cronkright
 */
class Recipe : IGlobal {
    override var name : String = "recipe"

    fun addShapedRecipe(output : LuaValue, crafting : LuaValue, elements : LuaValue) {

    }

    fun addShapedOreRecipe(output : LuaValue, crafting : LuaValue, elements : LuaValue) {
        if (elements.length() == 1) {
            GameRegistry.addRecipe(
                ShapedOreRecipe(
                    OreDictionary.getOres(output.tojstring())[0],
                    crafting.get(1), crafting.get(2), crafting.get(3),
                    elements.get(1).get(1).tochar(), elements.get(1).get(2).tojstring()
                )
            )
        } else if (elements.length() == 2) {
            GameRegistry.addRecipe(
                ShapedOreRecipe(
                    OreDictionary.getOres(output.tojstring())[0],
                    crafting.get(1), crafting.get(2), crafting.get(3),
                    elements.get(1).get(1).tochar(), elements.get(1).get(2).tojstring(),
                    elements.get(2).get(1).tochar(), elements.get(2).get(2).tojstring()
                )
            )
        } else if (elements.length() == 3) {
            GameRegistry.addRecipe(
                ShapedOreRecipe(
                    OreDictionary.getOres(output.tojstring())[0],
                    crafting.get(1), crafting.get(2), crafting.get(3),
                    elements.get(1).get(1).tochar(), elements.get(1).get(2).tojstring(),
                    elements.get(2).get(1).tochar(), elements.get(2).get(2).tojstring(),
                    elements.get(3).get(1).tochar(), elements.get(3).get(2).tojstring()
                )
            )
        } else if (elements.length() == 4) {
            GameRegistry.addRecipe(
                ShapedOreRecipe(
                    OreDictionary.getOres(output.tojstring())[0],
                    crafting.get(1), crafting.get(2), crafting.get(3),
                    elements.get(1).get(1).tochar(), elements.get(1).get(2).tojstring(),
                    elements.get(2).get(1).tochar(), elements.get(2).get(2).tojstring(),
                    elements.get(3).get(1).tochar(), elements.get(3).get(2).tojstring(),
                    elements.get(4).get(1).tochar(), elements.get(4).get(2).tojstring()
                )
            )
        } else if (elements.length() == 5) {
            GameRegistry.addRecipe(
                ShapedOreRecipe(
                    OreDictionary.getOres(output.tojstring())[0],
                    crafting.get(1), crafting.get(2), crafting.get(3),
                    elements.get(1).get(1).tochar(), elements.get(1).get(2).tojstring(),
                    elements.get(2).get(1).tochar(), elements.get(2).get(2).tojstring(),
                    elements.get(3).get(1).tochar(), elements.get(3).get(2).tojstring(),
                    elements.get(4).get(1).tochar(), elements.get(4).get(2).tojstring(),
                    elements.get(5).get(1).tochar(), elements.get(5).get(2).tojstring()
                )
            )
        } else if (elements.length() == 6) {
            GameRegistry.addRecipe(
                ShapedOreRecipe(
                    OreDictionary.getOres(output.tojstring())[0],
                    crafting.get(1), crafting.get(2), crafting.get(3),
                    elements.get(1).get(1).tochar(), elements.get(1).get(2).tojstring(),
                    elements.get(2).get(1).tochar(), elements.get(2).get(2).tojstring(),
                    elements.get(3).get(1).tochar(), elements.get(3).get(2).tojstring(),
                    elements.get(4).get(1).tochar(), elements.get(4).get(2).tojstring(),
                    elements.get(5).get(1).tochar(), elements.get(5).get(2).tojstring(),
                    elements.get(6).get(1).tochar(), elements.get(6).get(2).tojstring()
                )
            )
        } else if (elements.length() == 7) {
            GameRegistry.addRecipe(
                ShapedOreRecipe(
                    OreDictionary.getOres(output.tojstring())[0],
                    crafting.get(1), crafting.get(2), crafting.get(3),
                    elements.get(1).get(1).tochar(), elements.get(1).get(2).tojstring(),
                    elements.get(2).get(1).tochar(), elements.get(2).get(2).tojstring(),
                    elements.get(3).get(1).tochar(), elements.get(3).get(2).tojstring(),
                    elements.get(4).get(1).tochar(), elements.get(4).get(2).tojstring(),
                    elements.get(5).get(1).tochar(), elements.get(5).get(2).tojstring(),
                    elements.get(6).get(1).tochar(), elements.get(6).get(2).tojstring(),
                    elements.get(7).get(1).tochar(), elements.get(7).get(2).tojstring()
                )
            )
        } else if (elements.length() == 8) {
            GameRegistry.addRecipe(
                ShapedOreRecipe(
                    OreDictionary.getOres(output.tojstring())[0],
                    crafting.get(1), crafting.get(2), crafting.get(3),
                    elements.get(1).get(1).tochar(), elements.get(1).get(2).tojstring(),
                    elements.get(2).get(1).tochar(), elements.get(2).get(2).tojstring(),
                    elements.get(3).get(1).tochar(), elements.get(3).get(2).tojstring(),
                    elements.get(4).get(1).tochar(), elements.get(4).get(2).tojstring(),
                    elements.get(5).get(1).tochar(), elements.get(5).get(2).tojstring(),
                    elements.get(6).get(1).tochar(), elements.get(6).get(2).tojstring(),
                    elements.get(7).get(1).tochar(), elements.get(7).get(2).tojstring(),
                    elements.get(8).get(1).tochar(), elements.get(8).get(2).tojstring()
                )
            )
        } else if (elements.length() == 9) {
            GameRegistry.addRecipe(
                ShapedOreRecipe(
                    OreDictionary.getOres(output.tojstring())[0],
                    crafting.get(1), crafting.get(2), crafting.get(3),
                    elements.get(1).get(1).tochar(), elements.get(1).get(2).tojstring(),
                    elements.get(2).get(1).tochar(), elements.get(2).get(2).tojstring(),
                    elements.get(3).get(1).tochar(), elements.get(3).get(2).tojstring(),
                    elements.get(4).get(1).tochar(), elements.get(4).get(2).tojstring(),
                    elements.get(5).get(1).tochar(), elements.get(5).get(2).tojstring(),
                    elements.get(6).get(1).tochar(), elements.get(6).get(2).tojstring(),
                    elements.get(7).get(1).tochar(), elements.get(7).get(2).tojstring(),
                    elements.get(8).get(1).tochar(), elements.get(8).get(2).tojstring(),
                    elements.get(9).get(1).tochar(), elements.get(9).get(2).tojstring()
                )
            )
        }
    }
}

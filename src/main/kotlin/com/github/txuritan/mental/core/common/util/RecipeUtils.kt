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

import com.github.txuritan.mental.core.common.exceptions.RecipeException
import net.minecraft.item.crafting.IRecipe
import net.minecraftforge.common.config.Property
import net.minecraftforge.oredict.OreDictionary
import net.minecraftforge.oredict.ShapedOreRecipe

/**
 * @author Ian 'Txuritan/Captain Daro'Ma'Sohni Tavia' Cronkright
 */
object RecipeUtils {
    @Throws(RecipeException::class)
    fun configRecipe(property: Property): IRecipe {
        val recipeLength = property.stringList.size
        val recipeList = property.stringList

        if ((recipeLength % 2) == 0) {
            if (recipeLength == 6) {
                return ShapedOreRecipe(
                        OreDictionary.getOres(recipeList[0])[0],
                        recipeList[1],
                        recipeList[2],
                        recipeList[3],
                        recipeList[4][0], recipeList[5]
                )
            } else if (recipeLength == 8) {
                return ShapedOreRecipe(
                        OreDictionary.getOres(recipeList[0])[0],
                        recipeList[1],
                        recipeList[2],
                        recipeList[3],
                        recipeList[4][0], recipeList[5],
                        recipeList[6][0], recipeList[7]
                )
            } else if (recipeLength == 10) {
                return ShapedOreRecipe(
                        OreDictionary.getOres(recipeList[0])[0],
                        recipeList[1],
                        recipeList[2],
                        recipeList[3],
                        recipeList[4][0], recipeList[5],
                        recipeList[6][0], recipeList[7],
                        recipeList[8][0], recipeList[9]
                )
            } else if (recipeLength == 12) {
                return ShapedOreRecipe(
                        OreDictionary.getOres(recipeList[0])[0],
                        recipeList[1],
                        recipeList[2],
                        recipeList[3],
                        recipeList[4][0], recipeList[5],
                        recipeList[6][0], recipeList[7],
                        recipeList[8][0], recipeList[9],
                        recipeList[10][0], recipeList[11]
                )
            } else if (recipeLength == 14) {
                return ShapedOreRecipe(
                        OreDictionary.getOres(recipeList[0])[0],
                        recipeList[1],
                        recipeList[2],
                        recipeList[3],
                        recipeList[4][0], recipeList[5],
                        recipeList[6][0], recipeList[7],
                        recipeList[8][0], recipeList[9],
                        recipeList[10][0], recipeList[11],
                        recipeList[12][0], recipeList[13]
                )
            } else if (recipeLength == 16) {
                return ShapedOreRecipe(
                        OreDictionary.getOres(recipeList[0])[0],
                        recipeList[1],
                        recipeList[2],
                        recipeList[3],
                        recipeList[4][0], recipeList[5],
                        recipeList[6][0], recipeList[7],
                        recipeList[8][0], recipeList[9],
                        recipeList[10][0], recipeList[11],
                        recipeList[12][0], recipeList[13],
                        recipeList[14][0], recipeList[15]
                )
            } else if (recipeLength == 18) {
                return ShapedOreRecipe(
                        OreDictionary.getOres(recipeList[0])[0],
                        recipeList[1],
                        recipeList[2],
                        recipeList[3],
                        recipeList[4][0], recipeList[5],
                        recipeList[6][0], recipeList[7],
                        recipeList[8][0], recipeList[9],
                        recipeList[10][0], recipeList[11],
                        recipeList[12][0], recipeList[13],
                        recipeList[14][0], recipeList[15],
                        recipeList[16][0], recipeList[17]
                )
            } else if (recipeLength == 20) {
                return ShapedOreRecipe(
                        OreDictionary.getOres(recipeList[0])[0],
                        recipeList[1],
                        recipeList[2],
                        recipeList[3],
                        recipeList[4][0], recipeList[5],
                        recipeList[6][0], recipeList[7],
                        recipeList[8][0], recipeList[9],
                        recipeList[10][0], recipeList[11],
                        recipeList[12][0], recipeList[13],
                        recipeList[14][0], recipeList[15],
                        recipeList[16][0], recipeList[17],
                        recipeList[18][0], recipeList[19]
                )
            } else if (recipeLength == 22) {
                return ShapedOreRecipe(
                        OreDictionary.getOres(recipeList[0])[0],
                        recipeList[1],
                        recipeList[2],
                        recipeList[3],
                        recipeList[4][0], recipeList[5],
                        recipeList[6][0], recipeList[7],
                        recipeList[8][0], recipeList[9],
                        recipeList[10][0], recipeList[11],
                        recipeList[12][0], recipeList[13],
                        recipeList[14][0], recipeList[15],
                        recipeList[16][0], recipeList[17],
                        recipeList[18][0], recipeList[19],
                        recipeList[20][0], recipeList[21]
                )
            } else if (recipeLength == 24) {
                return ShapedOreRecipe(
                        OreDictionary.getOres(recipeList[0])[0],
                        recipeList[1],
                        recipeList[2],
                        recipeList[3],
                        recipeList[4][0], recipeList[5],
                        recipeList[6][0], recipeList[7],
                        recipeList[8][0], recipeList[9],
                        recipeList[10][0], recipeList[11],
                        recipeList[12][0], recipeList[13],
                        recipeList[14][0], recipeList[15],
                        recipeList[16][0], recipeList[17],
                        recipeList[18][0], recipeList[19],
                        recipeList[20][0], recipeList[21],
                        recipeList[22][0], recipeList[23]
                )
            } else {
                throw RecipeException("Config recipe for ${property.name} is either a odd number in length or not there or too long (length: $recipeLength)")
            }
        } else {
            throw RecipeException("Config recipe for ${property.name} is either a odd number in length (length: $recipeLength)")
        }
    }
}

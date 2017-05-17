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

import com.github.txuritan.mental.core.common.Mental
import net.minecraft.client.Minecraft
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.io.File
import java.io.IOException

/**
 * @author Ian 'Txuritan/Captain Daro'Ma'Sohni Tavia' Cronkright
 */
object References {

    @JvmStatic
    val LOGGER : Logger = LogManager.getLogger(Mental::class.java.simpleName) !!

    lateinit var minecraft : File

    init {
        try {
            minecraft = Minecraft.getMinecraft().mcDataDir.canonicalFile
        } catch (ioException : IOException) {
            LOGGER.error("Tried to get Minecraft's CanonicalFile threw IOException", ioException)
        }

    }

    const val MOD_ID : String = "mental"
    const val MOD_NAME : String = "Mental"
    const val BUILD : String = "GRADLE:BUILD"
    const val VERSION : String = BUILD
    const val CLIENT_PROXY_CLASS : String = "com.github.txuritan.mental.core.client.ClientProxy"
    const val COMMON_PROXY_CLASS : String = "com.github.txuritan.mental.core.common.CommonProxy"
    const val URL_BASE : String = "https://txuritan.github.io/mental/"
    const val UPDATE_URL : String = "${URL_BASE}update.json"

    @JvmStatic
    var MENTAL_CREATIVE_TAB : CreativeTabs = object : CreativeTabs("mental.creativeTab") {
        init {
            backgroundImageName = "mental.png"
        }

        override fun getTabIconItem() : ItemStack {
            return ItemStack(Items.BLAZE_ROD)
        }

        override fun hasSearchBar() : Boolean {
            return true
        }
    }
}

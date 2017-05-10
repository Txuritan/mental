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
import com.github.txuritan.mental.core.common.config.MentalConfig
import net.minecraft.client.Minecraft
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import org.apache.logging.log4j.LogManager
import java.io.File
import java.io.IOException

/**
 * @author Ian 'Txuritan/Captain Daro'Ma'Sohni Tavia' Cronkright
 */
public object References {

    @JvmStatic
    val LOGGER = LogManager.getLogger(Mental::class.java.simpleName)!!

    lateinit var minecraft: File

    init {
        try {
            minecraft = Minecraft.getMinecraft().mcDataDir.canonicalFile
        } catch (ioException: IOException) {
            LOGGER.error("Tried to get Minecraft's CanonicalFile threw IOException", ioException)
        }

    }

    var DEBUG = MentalConfig.debug

    const val MOD_ID = "mental"
    const val MOD_NAME = "Mental"
    const val BUILD = "GRADLE:BUILD"
    const val VERSION = BUILD
    const val CLIENT_PROXY_CLASS = "com.github.txuritan.mental.core.client.ClientProxy"
    const val COMMON_PROXY_CLASS = "com.github.txuritan.mental.core.common.CommonProxy"
    val URL_BASE = "https://txuritan.github.io/mental/"
    const val UPDATE_URL = "https://txuritan.github.io/mental/update.json"

    @JvmStatic
    var MENTAL_CREATIVE_TAB: CreativeTabs = object : CreativeTabs("mental.creativeTab") {
        override fun getTabIconItem(): ItemStack {
            return ItemStack(Items.BLAZE_ROD)
        }

        override fun hasSearchBar(): Boolean {
            return true
        }
    }
}
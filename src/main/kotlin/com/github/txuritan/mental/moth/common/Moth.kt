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

package com.github.txuritan.mental.moth.common

import com.github.txuritan.mental.core.common.IModule
import com.github.txuritan.mental.core.common.util.References
import net.minecraftforge.common.config.Configuration
import net.minecraftforge.fml.common.ModMetadata
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

/**
 * @author Ian 'Txuritan/Captain Daro'Ma'Sohni Tavia' Cronkright
 */
object Moth : IModule {

    var moths : Moths = Moths

    override fun setupConfig(configuration : Configuration) {
        moths.moths.filterIsInstance<IMoth>().forEach { it.setupConfig(configuration) }
    }

    override fun preInit(event : FMLPreInitializationEvent) {
        moths.moths.filterIsInstance<IMoth>().forEach { it.preInit(event) }

        val modMetadata : ModMetadata = event.modMetadata
        modMetadata.modId = References.MOD_ID
        var description : String = modMetadata.description
        description += "\nEnabled moths\n"
        moths.moths.filterIsInstance<IMoth>().forEach {
            description += "    * ${it.MOTH.capitalize()}: ${if (it.configEnabledAll !!) "§2True§f" else "§4False§f"}\n"
        }
        modMetadata.description = description
    }

    override fun init(event : FMLInitializationEvent) {
        moths.moths.filterIsInstance<IMoth>().forEach { it.init(event) }
    }

    override fun postInit(event : FMLPostInitializationEvent) {
        moths.moths.filterIsInstance<IMoth>().forEach { it.postInit(event) }
    }
}

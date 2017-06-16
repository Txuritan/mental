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

package com.github.txuritan.mental.material.common

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
object Material : IModule {

    var elements : Elements = Elements
    var alloys : Alloys = Alloys

    override fun setupConfig(configuration : Configuration) {
        elements.elements.filterIsInstance<IElement>().forEach { it.setupConfig(configuration) }
        alloys.alloys.filterIsInstance<IAlloy>().forEach { it.setupConfig(configuration) }
    }

    override fun preInit(event : FMLPreInitializationEvent) {

        val modMetadata : ModMetadata = event.modMetadata
        modMetadata.modId = References.MOD_ID

        elements.elements.filterIsInstance<IElement>().forEach { it.preInit(event) }

        var description : String = "§fTons of ores.\n\nMoth too.\n\nEnabled elements\n"
        elements.elements.filterIsInstance<IElement>().forEach {
            description += "    * ${it.ELEMENT.capitalize()}: ${if (it.configEnabledAll !!) "§2True§f" else "§4False§f"}\n"
        }

        alloys.alloys.filterIsInstance<IAlloy>().forEach { it.preInit(event) }

        description += "\nEnabled alloys\n"
        alloys.alloys.filterIsInstance<IAlloy>().forEach {
            description += "    * ${it.ALLOY.capitalize()}: ${if (it.configEnabledAll !!) "§2True§f" else "§4False§f"}\n"
        }

        modMetadata.description = description
    }

    override fun init(event : FMLInitializationEvent) {
        alloys.alloys.filterIsInstance<IElement>().forEach { it.init(event) }
        alloys.alloys.filterIsInstance<IAlloy>().forEach { it.init(event) }
    }

    override fun postInit(event : FMLPostInitializationEvent) {
        alloys.alloys.filterIsInstance<IElement>().forEach { it.postInit(event) }
        alloys.alloys.filterIsInstance<IAlloy>().forEach { it.postInit(event) }
    }

}

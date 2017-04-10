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

import com.github.txuritan.mental.core.common.config.ConfigHandler
import com.github.txuritan.mental.core.common.handler.EventHandlers
import com.github.txuritan.mental.core.common.util.References
import com.github.txuritan.mental.material.common.Material
import com.github.txuritan.mental.tree.common.Tree
import net.minecraft.item.Item
import net.minecraftforge.common.config.Configuration
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

/**
 * @author Ian 'Txuritan' Cronkright
 */
open class CommonProxy {

    open fun registerItemRenderer(item: Item, meta: Int, id: String) {

    }

    open fun preInit(event: FMLPreInitializationEvent) {
        References.LOGGER.info("CommonProxy preInit")
        ConfigHandler.config(Configuration(event.suggestedConfigurationFile))

        Material.preInit(event)
        Tree.preInit(event)

        //GameRegistry.addRecipe(YuRecipes())
    }

    open fun init(event: FMLInitializationEvent) {
        EventHandlers.events()

        Material.init(event)
        Tree.init(event)
    }

    open fun postInit(event: FMLPostInitializationEvent) {
        Material.postInit(event)
        Tree.postInit(event)
    }
}

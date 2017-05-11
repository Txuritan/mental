package com.github.txuritan.mental.material.common

import net.minecraftforge.common.config.Configuration
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

/**
 * @author Ian 'Txuritan/Captain Daro'Ma'Sohni Tavia' Cronkright
 */
interface IElement {

    val ELEMENT: String

    var configEnabledAll: Boolean?

    fun setupConfig(configuration: Configuration)

    fun preInit(event: FMLPreInitializationEvent)

    fun init(event: FMLInitializationEvent)

    fun postInit(event: FMLPostInitializationEvent)

}

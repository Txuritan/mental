package com.github.txuritan.mental.compatibility.common

import net.minecraftforge.common.config.Configuration
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

/**
 * @author Ian 'Txuritan/Captian Daro'Ma'Sohni Tavia' Cronkright
 */
interface ICompatibility {

    fun setupConfig(configuration: Configuration)

    fun preInit(event: FMLPreInitializationEvent)

    fun init(event: FMLInitializationEvent)

    fun postInit(event: FMLPostInitializationEvent)

}

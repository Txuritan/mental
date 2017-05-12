package com.github.txuritan.mental.compatibility.common

import com.github.txuritan.mental.core.common.IModule
import net.minecraftforge.common.config.Configuration
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

/**
 * @author Ian 'Txuritan/Captian Daro'Ma'Sohni Tavia' Cronkright
 */
object Compatibility : IModule {

    var compatibilities: Compatibilities = Compatibilities

    override fun setupConfig(configuration: Configuration) {
        compatibilities.compatibilities.filterIsInstance<ICompatibility>().forEach { it.setupConfig(configuration) }
    }

    override fun preInit(event: FMLPreInitializationEvent) {
        compatibilities.compatibilities.filterIsInstance<ICompatibility>().forEach { it.preInit(event) }
    }

    override fun init(event: FMLInitializationEvent) {
        compatibilities.compatibilities.filterIsInstance<ICompatibility>().forEach { it.init(event) }
    }

    override fun postInit(event: FMLPostInitializationEvent) {
        compatibilities.compatibilities.filterIsInstance<ICompatibility>().forEach { it.postInit(event) }
    }

}

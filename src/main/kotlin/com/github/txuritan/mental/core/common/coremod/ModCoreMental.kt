package com.github.txuritan.mental.core.common.coremod

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin
import kotlin.reflect.jvm.jvmName

@IFMLLoadingPlugin.MCVersion("1.11.2")
@IFMLLoadingPlugin.TransformerExclusions("com.github.txuritan.mental.core.common.coremod.")
class ModCoreMental:IFMLLoadingPlugin {
    override fun getModContainerClass(): String {
        return ModContainerMental::class.jvmName
    }

    override fun getASMTransformerClass(): Array<String> {
        return emptyArray()
    }

    override fun getSetupClass(): String? {
        return ModSetupMental::class.jvmName
    }

    override fun injectData(data:Map<String, Any>) {

    }

    override fun getAccessTransformerClass(): String? {
        return null
    }
}

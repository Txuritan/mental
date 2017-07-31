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

package com.github.txuritan.mental.script.common

import com.github.txuritan.mental.core.common.IModule
import com.github.txuritan.mental.core.common.util.References
import com.github.txuritan.mental.script.common.utils.Recipe
import net.minecraftforge.common.config.Configuration
import net.minecraftforge.fml.common.ProgressManager
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import org.luaj.vm2.Globals
import org.luaj.vm2.LuaValue
import org.luaj.vm2.lib.jse.CoerceJavaToLua
import org.luaj.vm2.lib.jse.JsePlatform
import java.io.File

/**
 * @author Ian 'Txuritan/Captain Daro'Ma'Sohni Tavia' Cronkright
 */
object Script : IModule {

    val globalFunctions : MutableList<Any> = mutableListOf(
        Recipe()
    )

    override fun setupConfig(configuration : Configuration) {

    }

    override fun preInit(event : FMLPreInitializationEvent) {

    }


    override fun init(event : FMLInitializationEvent) {
        File("${References.minecraft.canonicalPath}${File.separatorChar}scripts${File.separatorChar}").mkdirs()

        val files = File("${References.minecraft.canonicalPath}${File.separatorChar}scripts${File.separatorChar}").listFiles()
        val scriptAmount : Int = files.size

        val results = ArrayList<String>()
        files
            .filter { it.isFile }
            .mapTo(results) { it.name }

        val progressbar : ProgressManager.ProgressBar = ProgressManager.push("Mental Script Loader: ", scriptAmount)
        val globals : Globals = JsePlatform.standardGlobals()

        globalFunctions.filterIsInstance<IGlobal>().forEach { globals.set(it.name, CoerceJavaToLua.coerce(it)) }

        results.forEach {
            progressbar.step("[Loading: $it]")

            println("Mental Script Loader: [Loading: $it]")

            val chunk : LuaValue = globals.loadfile("${References.minecraft.canonicalPath}${File.separatorChar}scripts${File.separatorChar}$it")
            chunk.call()

            println("Mental Script Loader: [Loaded: $it]")
        }
        ProgressManager.pop(progressbar)
    }


    override fun postInit(event : FMLPostInitializationEvent) {

    }
}

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

package com.github.txuritan.mental.kotlin

import net.minecraftforge.fml.common.FMLModContainer
import net.minecraftforge.fml.common.ILanguageAdapter
import net.minecraftforge.fml.common.ModContainer
import net.minecraftforge.fml.relauncher.Side
import org.apache.logging.log4j.LogManager
import java.lang.reflect.Field
import java.lang.reflect.Method

/**
 * @author Ian 'Txuritan' Cronkright
 */
class KotlinAdapter : ILanguageAdapter {

    private val log = LogManager.getLogger("KotlinAdapter")

    override fun supportsStatics(): Boolean {
        return false
    }

    override fun setProxy(target: Field, proxyTarget: Class<*>, proxy: Any) {
        log.debug("Setting proxy: ${target.declaringClass.simpleName}.${target.name} -> $proxy")
        target.set(proxyTarget.kotlin.objectInstance, proxy)
    }

    override fun getNewInstance(container: FMLModContainer, objectClass: Class<*>, classLoader: ClassLoader, factoryMarkedAnnotation: Method?): Any {
        log.debug("FML has asked for ${objectClass.simpleName} to be constructed")
        return objectClass.kotlin.objectInstance ?: objectClass.newInstance()
    }

    override fun setInternalProxies(mod: ModContainer?, side: Side?, loader: ClassLoader?) {

    }

}
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

package com.github.txuritan.mental.core.common.config

import com.github.txuritan.mental.core.common.util.References
import net.minecraftforge.common.config.Configuration

/**
 * @author Ian 'Txuritan' Cronkright
 */
object MentalConfig {

    var debug: Boolean = false

    var stickDrop: Boolean = false
    var stickDropChance: Int = 0

    @JvmStatic
    fun setupConfig(configuration: Configuration) {

        debug = configuration.getBoolean("debug", References.MOD_ID + "", false, "Will log debug information if true")

        stickDrop = configuration.getBoolean("enabled", References.MOD_ID + ".event.stickDrop", true, "A stick will drop when you break leaves")
        stickDropChance = configuration.getInt("chance", References.MOD_ID + ".event.stickDrop", 50, 0, 100, "Drop chance")

    }
}

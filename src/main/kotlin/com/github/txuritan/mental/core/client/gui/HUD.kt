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

package com.github.txuritan.mental.core.client.gui

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Gui
import net.minecraft.client.gui.ScaledResolution

/**
 * @author Ian 'Txuritan/Captain Daro'Ma'Sohni Tavia' Cronkright
 */
class HUD(mc : Minecraft) : Gui() {
    init {

        val player = mc.player

        val scaled = ScaledResolution(mc)

        //val _100height : Int = scaled.scaledHeight - (scaled.scaledHeight / 9.2592593).toInt()
        //val _100fromSide : Int = (scaled.scaledWidth / 5.208333331).toInt()
        //val _320width : Int = (scaled.scaledWidth / 21.875).toInt()

        val _130fromBottom : Int = (scaled.scaledHeight / pmn(130, 1080)).toInt()
        val _120fromBottom : Int = (scaled.scaledHeight / pmn(120, 1080)).toInt()
        val _98fromBottom : Int = (scaled.scaledHeight / pmn(98, 1080)).toInt()

        val _100fromLeft : Int = (scaled.scaledWidth / pmn(100, 1920)).toInt()
        val _320fromLeft : Int = (scaled.scaledWidth / pmn(320, 1920)).toInt()
        val _420fromLeft : Int = (scaled.scaledWidth / pmn(420, 1920)).toInt()

        drawHorizontalLine(
            _100fromLeft,
            _420fromLeft,
            _130fromBottom,
            argb(255, 149, 152, 154)
        )

        drawRect(
            _100fromLeft,
            _120fromBottom,
            _420fromLeft,
            _98fromBottom,
            argb(255, 149, 152, 154)
        )


        drawHorizontalLine(
            scaled.scaledWidth - _420fromLeft,
            scaled.scaledWidth - _100fromLeft,
            _130fromBottom,
            argb(255, 149, 152, 154)
        )
    }

    fun argb(alpha : Int, red : Int, green : Int, blue : Int) : Int {
        return (alpha shl 24) or (red shl 16) or (green shl 8) or blue
    }

    fun pmn(m : Int, n : Int) : Double {
        return m.toDouble() / n.toDouble() * 100.0
    }
}

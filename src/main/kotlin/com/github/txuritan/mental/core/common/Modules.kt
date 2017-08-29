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

import com.github.txuritan.mental.butterfly.common.Butterfly
import com.github.txuritan.mental.compatibility.common.Compatibility
import com.github.txuritan.mental.fish.common.Fish
import com.github.txuritan.mental.material.common.Material
import com.github.txuritan.mental.moth.common.Moth
import com.github.txuritan.mental.script.common.Script
import com.github.txuritan.mental.tree.common.Tree

/**
 * @author Ian 'Txuritan/Captain Daro'Ma'Sohni Tavia' Cronkright
 */
object Modules {
    var modules : MutableList<Any> = mutableListOf(
        Butterfly,
        Fish,
        Material,
        Tree,
        Moth,
        Compatibility,
        Script
    )
}

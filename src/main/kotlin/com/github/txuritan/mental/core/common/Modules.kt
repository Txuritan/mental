package com.github.txuritan.mental.core.common

import com.github.txuritan.mental.compatibility.common.Compatibility
import com.github.txuritan.mental.material.common.Material
import com.github.txuritan.mental.tree.common.Tree

/**
 * @author Ian 'Txuritan/Captian Daro'Ma'Sohni Tavia' Cronkright
 */
object Modules {
    var modules: MutableList<Any> = mutableListOf(
        Material,
        Tree,
        Compatibility
    )
}

import os
import sys


def templateElement(ore_name, hue, saturation, lightness, world):
    return """/*
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

// Imagemagick h: """ + str(hue) + """ s: """ + str(saturation) + """ l: """ + str(lightness) + """

package com.github.txuritan.mental.material.common.element

import com.github.txuritan.mental.material.common.bases.Element

/**
 * @author Ian 'Txuritan/Captain Daro'Ma'Sohni Tavia' Cronkright
 */
object """ + ore_name.title() + """ : Element() {

    override val ELEMENT : String = \"""" + ore_name + """\"

    override var GENERATION : String = \"""" + world + """\"

}
"""


def templateElementBlockstateBlock(ore_name):
    return """{
  "forge_marker": 1,
  "defaults": {
    "model": "minecraft:cube_all",
    "transform": "forge:default-block"
  },
  "variants": {
    "normal": {
      "model": "mental:""" + ore_name + """_block"
    }
  }
}
"""


def templateElementBlockstateOre(ore_name):
    return """{
  "forge_marker": 1,
  "defaults": {
    "model": "minecraft:cube_all",
    "transform": "forge:default-block"
  },
  "variants": {
    "normal": {
      "model": "mental:""" + ore_name + """_ore"
    }
  }
}
"""


def templateElementModelsBlockBlock(ore_name):
    return """{
  "parent": "block/cube_all",
  "textures": {
    "all": "mental:blocks/""" + ore_name + """_block"
  }
}
"""


def templateElementModelsBlockOre(ore_name):
    return """{
  "parent": "block/cube_all",
  "textures": {
    "all": "mental:blocks/""" + ore_name + """_ore"
  }
}
"""


def templateElementModelsItemArmorChest(ore_name):
    return """{
  "parent": "item/generated",
  "textures": {
    "layer0": "mental:items/""" + ore_name + """_armor_chest"
  }
}"""


def templateElementModelsItemArmorFeet(ore_name):
    return """{
  "parent": "item/generated",
  "textures": {
    "layer0": "mental:items/""" + ore_name + """_armor_feet"
  }
}"""


def templateElementModelsItemArmorHead(ore_name):
    return """{
  "parent": "item/generated",
  "textures": {
    "layer0": "mental:items/""" + ore_name + """_armor_head"
  }
}"""


def templateElementModelsItemArmorLegs(ore_name):
    return """{
  "parent": "item/generated",
  "textures": {
    "layer0": "mental:items/""" + ore_name + """_armor_legs"
  }
}"""


def templateElementModelsItemAxe(ore_name):
    return """{
  "parent": "item/handheld",
  "textures": {
    "layer0": "mental:items/""" + ore_name + """_axe"
  }
}"""


def templateElementModelsItemBlock(ore_name):
    return """{
  "parent": "mental:block/""" + ore_name + """_block"
}
"""


def templateElementModelsItemBow(ore_name):
    return """{
  "parent": "item/generated",
  "textures": {
    "layer0": "mental:items/""" + ore_name + """_bow_standby"
  },
  "display": {
    "thirdperson_righthand": {
      "rotation": [ -80, 260, -40 ],
      "translation": [ -1, -2, 2.5 ],
      "scale": [ 0.9, 0.9, 0.9 ]
    },
    "thirdperson_lefthand": {
      "rotation": [ -80, -280, 40 ],
      "translation": [ -1, -2, 2.5 ],
      "scale": [ 0.9, 0.9, 0.9 ]
    },
    "firstperson_righthand": {
      "rotation": [ 0, -90, 25 ],
      "translation": [ 1.13, 3.2, 1.13],
      "scale": [ 0.68, 0.68, 0.68 ]
    },
    "firstperson_lefthand": {
      "rotation": [ 0, 90, -25 ],
      "translation": [ 1.13, 3.2, 1.13],
      "scale": [ 0.68, 0.68, 0.68 ]
    }
  },
  "overrides": [
    {
      "predicate": {
        "pulling": 1
      },
      "model": "mental:item/""" + ore_name + """_bow_pulling_0"
    },
    {
      "predicate": {
        "pulling": 1,
        "pull": 0.65
      },
      "model": "mental:item/""" + ore_name + """_bow_pulling_1"
    },
    {
      "predicate": {
        "pulling": 1,
        "pull": 0.9
      },
      "model": "mental:item/""" + ore_name + """_bow_pulling_2"
    }
  ]
}
"""


def templateElementModelsItemBowPulling0(ore_name):
    return """{
  "parent": "item/bow",
  "textures": {
    "layer0": "mental:items/""" + ore_name + """_bow_pulling_0"
  }
}"""


def templateElementModelsItemBowPulling1(ore_name):
    return """{
  "parent": "item/bow",
  "textures": {
    "layer0": "mental:items/""" + ore_name + """_bow_pulling_1"
  }
}"""


def templateElementModelsItemBowPulling2(ore_name):
    return """{
  "parent": "item/bow",
  "textures": {
    "layer0": "mental:items/""" + ore_name + """_bow_pulling_2"
  }
}"""


def templateElementModelsItemChunk(ore_name):
    return """{
  "parent": "item/generated",
  "textures": {
    "layer0": "mental:items/""" + ore_name + """_chunk"
  }
}
"""


def templateElementModelsItemDust(ore_name):
    return """{
  "parent": "item/generated",
  "textures": {
    "layer0": "mental:items/""" + ore_name + """_dust"
  }
}
"""


def templateElementModelsItemHoe(ore_name):
    return """{
  "parent": "item/handheld",
  "textures": {
    "layer0": "mental:items/""" + ore_name + """_hoe"
  }
}"""


def templateElementModelsItemIngot(ore_name):
    return """{
  "parent": "item/generated",
  "textures": {
    "layer0": "mental:items/""" + ore_name + """_ingot"
  }
}
"""


def templateElementModelsItemNugget(ore_name):
    return """{
  "parent": "item/generated",
  "textures": {
    "layer0": "mental:items/""" + ore_name + """_nugget"
  }
}
"""


def templateElementModelsItemOre(ore_name):
    return """{
  "parent": "mental:block/""" + ore_name + """_ore"
}
"""


def templateElementModelsItemPickaxe(ore_name):
    return """{
  "parent": "item/handheld",
  "textures": {
    "layer0": "mental:items/""" + ore_name + """_pickaxe"
  }
}"""


def templateElementModelsItemPlate(ore_name):
    return """{
  "parent": "item/generated",
  "textures": {
    "layer0": "mental:items/""" + ore_name + """_plate"
  }
}
"""


def templateElementModelsItemShears(ore_name):
    return """{
  "parent": "item/handheld",
  "textures": {
    "layer0": "mental:items/""" + ore_name + """_shears"
  }
}"""


def templateElementModelsItemShovel(ore_name):
    return """{
  "parent": "item/handheld",
  "textures": {
    "layer0": "mental:items/""" + ore_name + """_shovel"
  }
}"""


def templateElementModelsItemSword(ore_name):
    return """{
  "parent": "item/handheld",
  "textures": {
    "layer0": "mental:items/""" + ore_name + """_sword"
  }
}"""


def makeFile(java, ore_name, file_path, contents):
    if java:
        file = open(os.path.join(file_path, ore_name + ".kt"), "w")
        file.write(contents)
        file.close()
    else:
        file = open(os.path.join(file_path, ore_name + ".json"), "w")
        file.write(contents)
        file.close()


if len(sys.argv) > 1:
    if sys.argv[1] == "--help" or sys.argv[1] == "-help" or sys.argv[1] == "-h":
        print("python mimbp.py <ore name> <hue> <saturation> <lightness> <\"end\", \"overworld\", \"nether\">")
    else:
        ore_name = str(sys.argv[1])
        hue = str(sys.argv[2])
        saturation = str(sys.argv[3])
        lightness = str(sys.argv[4])
        generation = str(sys.argv[5])

        src_main_path = os.path.join(os.getcwd(), "src", "main")
        mental_path = os.path.join(src_main_path, "kotlin", "com", "github", "txuritan", "mental")
        mental_resources_path = os.path.join(src_main_path, "resources", "assets", "mental")
        models_path = os.path.join(mental_resources_path, "models")

        elements_path = os.path.join(mental_path, "material", "common", "element")
        makeFile(True, ore_name.title(), elements_path, templateElement(ore_name, hue, saturation, lightness, generation))

        blockstates_path = os.path.join(mental_resources_path, "blockstates")
        makeFile(False, ore_name + "_block", blockstates_path, templateElementBlockstateBlock(ore_name))
        makeFile(False, ore_name + "_ore", blockstates_path, templateElementBlockstateOre(ore_name))

        models_block_path = os.path.join(models_path, "block")
        makeFile(False, ore_name + "_block", models_block_path, templateElementModelsBlockBlock(ore_name))
        makeFile(False, ore_name + "_ore", models_block_path, templateElementModelsBlockOre(ore_name))

        models_item_path = os.path.join(models_path, "item")
        makeFile(False, ore_name + "_armor_chest", models_item_path, templateElementModelsItemArmorChest(ore_name))
        makeFile(False, ore_name + "_armor_feet", models_item_path, templateElementModelsItemArmorFeet(ore_name))
        makeFile(False, ore_name + "_armor_head", models_item_path, templateElementModelsItemArmorHead(ore_name))
        makeFile(False, ore_name + "_armor_legs", models_item_path, templateElementModelsItemArmorLegs(ore_name))
        makeFile(False, ore_name + "_axe", models_item_path, templateElementModelsItemAxe(ore_name))
        makeFile(False, ore_name + "_block", models_item_path, templateElementModelsItemBlock(ore_name))
        makeFile(False, ore_name + "_bow", models_item_path, templateElementModelsItemBow(ore_name))
        makeFile(False, ore_name + "_bow_pulling_0", models_item_path, templateElementModelsItemBowPulling0(ore_name))
        makeFile(False, ore_name + "_bow_pulling_1", models_item_path, templateElementModelsItemBowPulling1(ore_name))
        makeFile(False, ore_name + "_bow_pulling_2", models_item_path, templateElementModelsItemBowPulling2(ore_name))
        makeFile(False, ore_name + "_chunk", models_item_path, templateElementModelsItemChunk(ore_name))
        makeFile(False, ore_name + "_dust", models_item_path, templateElementModelsItemDust(ore_name))
        makeFile(False, ore_name + "_hoe", models_item_path, templateElementModelsItemHoe(ore_name))
        makeFile(False, ore_name + "_ingot", models_item_path, templateElementModelsItemIngot(ore_name))
        makeFile(False, ore_name + "_nugget", models_item_path, templateElementModelsItemNugget(ore_name))
        makeFile(False, ore_name + "_ore", models_item_path, templateElementModelsItemOre(ore_name))
        makeFile(False, ore_name + "_pickaxe", models_item_path, templateElementModelsItemPickaxe(ore_name))
        makeFile(False, ore_name + "_plate", models_item_path, templateElementModelsItemPlate(ore_name))
        makeFile(False, ore_name + "_shears", models_item_path, templateElementModelsItemShears(ore_name))
        makeFile(False, ore_name + "_shovel", models_item_path, templateElementModelsItemShovel(ore_name))
        makeFile(False, ore_name + "_sword", models_item_path, templateElementModelsItemSword(ore_name))

        for filename in os.listdir(os.path.join(mental_resources_path, "textures", "xcf")):
            if filename[:-4] == "block" or filename[:-4] == "ore":
                os.system(
                    "convert " +
                    os.path.abspath(os.path.join(mental_resources_path, "textures", "xcf", filename)) +
                    " -background transparent" +
                    " -modulate " + str(lightness) + "," + str(saturation) + "," + str(hue) +
                    " -flatten " +
                    os.path.abspath(os.path.join(mental_resources_path, "textures", "blocks", ore_name + "_" + filename[:-3] + "png"))
            )
            elif filename[:-4] == "armor_layer_1" or filename[:-4] == "armor_layer_2":
                os.system(
                    "convert " +
                    os.path.abspath(os.path.join(mental_resources_path, "textures", "xcf", filename)) +
                    " -background transparent" +
                    " -modulate " + str(lightness) + "," + str(saturation) + "," + str(hue) +
                    " -flatten " +
                    os.path.abspath(os.path.join(mental_resources_path, "textures", "models", "armor", ore_name + "_" + filename[:-3] + "png"))
                )
            else:
                os.system(
                    "convert " +
                    os.path.abspath(os.path.join(mental_resources_path, "textures", "xcf", filename)) +
                    " -background transparent" +
                    " -modulate " + str(lightness) + "," + str(saturation) + "," + str(hue) +
                    " -flatten " +
                    os.path.abspath(os.path.join(mental_resources_path, "textures", "items", ore_name + "_" + filename[:-3] + "png"))
                )

else:
    print("python mimbp.py <ore name> <hue> <saturation> <lightness> <\"end\", \"overworld\", \"nether\">")

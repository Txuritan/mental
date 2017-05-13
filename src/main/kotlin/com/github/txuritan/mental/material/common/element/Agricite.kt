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

// gimp colorize h:0 s:88 l:-4

package com.github.txuritan.mental.material.common.element

import com.github.txuritan.mental.core.common.block.BlockBase
import com.github.txuritan.mental.core.common.exceptions.RecipeException
import com.github.txuritan.mental.core.common.item.ItemBase
import com.github.txuritan.mental.core.common.item.tools.*
import com.github.txuritan.mental.core.common.item.weapons.ItemArmorBase
import com.github.txuritan.mental.core.common.item.weapons.ItemBowBase
import com.github.txuritan.mental.core.common.item.weapons.ItemSwordBase
import com.github.txuritan.mental.core.common.util.RecipeUtils
import com.github.txuritan.mental.core.common.util.References
import com.github.txuritan.mental.core.common.util.RegisterUtils
import com.github.txuritan.mental.material.common.IElement
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.block.state.pattern.BlockStateMatcher
import net.minecraft.init.Blocks
import net.minecraft.init.SoundEvents
import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.item.Item
import net.minecraft.item.ItemArmor
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.DimensionType
import net.minecraft.world.World
import net.minecraft.world.chunk.IChunkGenerator
import net.minecraft.world.chunk.IChunkProvider
import net.minecraft.world.gen.feature.WorldGenMinable
import net.minecraftforge.common.config.Configuration
import net.minecraftforge.common.config.Property
import net.minecraftforge.common.util.EnumHelper
import net.minecraftforge.fml.common.IWorldGenerator
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.oredict.ShapedOreRecipe
import java.util.*

/**
 * @author Ian 'Txuritan/Captain Daro'Ma'Sohni Tavia' Cronkright
 */
object Agricite : IElement {
    override val ELEMENT : String = "agricite"

    private val ELEMENT_ORE_DIC_ORE = "${ELEMENT}Ore"
    private val ELEMENT_ORE_DIC_WHOLE = "${ELEMENT}Block"

    internal class Ore(hardness : Float?, resistance : Float?, private val itemDropped : Item) : BlockBase(Material.ROCK, "${ELEMENT}_ore", arrayOf(ELEMENT_ORE_DIC_ORE)) {
        init {
            setHardness(hardness !!)
            setResistance(resistance !!)
            soundType = SoundType.STONE
            setCreativeTab(References.MENTAL_CREATIVE_TAB)
        }

        override fun getItemDropped(state : IBlockState?, rand : Random?, fortune : Int) : Item {
            return itemDropped
        }
    }

    internal class Whole(hardness : Float?, resistance : Float?) : BlockBase(Material.ROCK, "${ELEMENT}_block", arrayOf(ELEMENT_ORE_DIC_WHOLE)) {
        init {
            setHardness(hardness !!)
            setResistance(resistance !!)
            soundType = SoundType.STONE
            setCreativeTab(References.MENTAL_CREATIVE_TAB)
        }
    }

    private val ELEMENT_ORE_DIC_CHUNK = "${ELEMENT}Chunk"
    private val ELEMENT_ORE_DIC_DUST = "${ELEMENT}Dust"
    private val ELEMENT_ORE_DIC_INGOT = "${ELEMENT}Ingot"
    private val ELEMENT_ORE_DIC_NUGGET = "${ELEMENT}Nugget"
    private val ELEMENT_ORE_DIC_PLATE = "${ELEMENT}Plate"

    internal class Chunk : ItemBase("${ELEMENT}_chunk", arrayOf(ELEMENT_ORE_DIC_CHUNK)) {
        init {
            setCreativeTab(References.MENTAL_CREATIVE_TAB)
        }
    }

    internal class Dust : ItemBase("${ELEMENT}_dust", arrayOf(ELEMENT_ORE_DIC_DUST)) {
        init {
            setCreativeTab(References.MENTAL_CREATIVE_TAB)
        }
    }

    internal class Ingot : ItemBase("${ELEMENT}_ingot", arrayOf(ELEMENT_ORE_DIC_INGOT)) {
        init {
            setCreativeTab(References.MENTAL_CREATIVE_TAB)
        }
    }

    internal class Nugget : ItemBase("${ELEMENT}_nugget", arrayOf(ELEMENT_ORE_DIC_NUGGET)) {
        init {
            setCreativeTab(References.MENTAL_CREATIVE_TAB)
        }
    }

    internal class Plate : ItemBase("${ELEMENT}_plate", arrayOf(ELEMENT_ORE_DIC_PLATE)) {
        init {
            setCreativeTab(References.MENTAL_CREATIVE_TAB)
        }
    }

    private val ELEMENT_ORE_DIC_ARMOR = "${ELEMENT}Armor"
    private val ELEMENT_ORE_DIC_ARMOR_HEAD = "${ELEMENT}ArmorHead"
    private val ELEMENT_ORE_DIC_ARMOR_CHEST = "${ELEMENT}ArmorChest"
    private val ELEMENT_ORE_DIC_ARMOR_LEGS = ELEMENT + "ArmorLegs"
    private val ELEMENT_ORE_DIC_ARMOR_FEET = "${ELEMENT}ArmorFeet"
    private val ELEMENT_ORE_DIC_AXE = "${ELEMENT}Axe"
    private val ELEMENT_ORE_DIC_BOW = "${ELEMENT}Bow"
    private val ELEMENT_ORE_DIC_HOE = "${ELEMENT}Hoe"
    private val ELEMENT_ORE_DIC_PICKAXE = "${ELEMENT}Pickaxe"
    private val ELEMENT_ORE_DIC_SHEARS = "${ELEMENT}Shears"
    private val ELEMENT_ORE_DIC_SHOVEL = "${ELEMENT}Shovel"
    private val ELEMENT_ORE_DIC_SWORD = "${ELEMENT}Sword"

    internal class Armor(material : ItemArmor.ArmorMaterial, slot : EntityEquipmentSlot) : ItemArmorBase(material, slot, "${ELEMENT}_armor_" + slot.getName(), arrayOf(ELEMENT_ORE_DIC_ARMOR + slot.getName().capitalize())) {
        init {
            creativeTab = References.MENTAL_CREATIVE_TAB
        }
    }

    internal class Axe(material : Item.ToolMaterial) : ItemAxeBase(material, "${ELEMENT}_axe", arrayOf(ELEMENT_ORE_DIC_AXE)) {
        init {
            creativeTab = References.MENTAL_CREATIVE_TAB
        }
    }

    internal class Bow(maxDamage : Int?) : ItemBowBase(maxDamage, "${ELEMENT}_bow", arrayOf(ELEMENT_ORE_DIC_BOW)) {
        init {
            creativeTab = References.MENTAL_CREATIVE_TAB
        }
    }

    internal class Hoe(material : Item.ToolMaterial) : ItemHoeBase(material, "${ELEMENT}_hoe", arrayOf(ELEMENT_ORE_DIC_HOE)) {
        init {
            creativeTab = References.MENTAL_CREATIVE_TAB
        }
    }

    internal class Pickaxe(material : Item.ToolMaterial) : ItemPickaxeBase(material, "${ELEMENT}_pickaxe", arrayOf(ELEMENT_ORE_DIC_PICKAXE)) {
        init {
            creativeTab = References.MENTAL_CREATIVE_TAB
        }
    }

    internal class Shears(damage : Int?) : ItemShearsBase(damage, "${ELEMENT}_shears", arrayOf(ELEMENT_ORE_DIC_SHEARS)) {
        init {
            creativeTab = References.MENTAL_CREATIVE_TAB
        }
    }

    internal class Shovel(material : Item.ToolMaterial) : ItemShovelBase(material, "${ELEMENT}_shovel", arrayOf(ELEMENT_ORE_DIC_SHOVEL)) {
        init {
            creativeTab = References.MENTAL_CREATIVE_TAB
        }
    }

    internal class Sword(material : Item.ToolMaterial) : ItemSwordBase(material, "${ELEMENT}_sword", arrayOf(ELEMENT_ORE_DIC_SWORD)) {
        init {
            creativeTab = References.MENTAL_CREATIVE_TAB
        }
    }

    internal class WorldGenerator(private val minY : Int?, private val maxY : Int?, private val size : Int?, private val sizeBound : Int?, private val chance : Int?) : IWorldGenerator {

        override fun generate(random : Random, chunkX : Int, chunkZ : Int, world : World, chunkGenerator : IChunkGenerator, chunkProvider : IChunkProvider) {
            when (world.provider.dimensionType) {
                DimensionType.THE_END -> generateEnd(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider)
                DimensionType.OVERWORLD -> System.out.println("OVERWORLD: what")
                DimensionType.NETHER -> System.out.println("NETHER: what")
                null -> System.out.println("null: what")
            }
        }

        fun generateEnd(random : Random, chunkX : Int, chunkZ : Int, world : World, chunkGenerator : IChunkGenerator, chunkProvider : IChunkProvider) {
            generateOre(Agricite.ore !!.defaultState, world, random, chunkX * 16, chunkZ * 16, minY !!, maxY !!, size !! + random.nextInt(sizeBound !!), chance !!)
        }

        private fun generateOre(ore : IBlockState, world : World, random : Random, x : Int, z : Int, minY : Int, maxY : Int, size : Int, chances : Int) {
            val deltaY = maxY - minY
            for (i in 0 .. chances - 1) {
                val pos = BlockPos(x + random.nextInt(16), minY + random.nextInt(deltaY), z + random.nextInt(16))
                val generator = WorldGenMinable(ore, size, BlockStateMatcher.forBlock(Blocks.END_STONE))
                generator.generate(world, random, pos)
            }
        }
    }

    private var configBlockOreHardness : Float? = null
    private var configBlockOreResistance : Float? = null

    private var configBlockWholeHardness : Float? = null
    private var configBlockWholeResistance : Float? = null

    override var configEnabledAll : Boolean? = null

    private var configEnabledArmor : Boolean? = null
    private var configEnabledArmorChest : Boolean? = null
    private var configEnabledArmorFeet : Boolean? = null
    private var configEnabledArmorHead : Boolean? = null
    private var configEnabledArmorLegs : Boolean? = null

    private var configEnabledBlocks : Boolean? = null
    private var configEnabledBlockOre : Boolean? = null
    private var configEnabledBlockWhole : Boolean? = null

    private var configEnabledItems : Boolean? = null
    private var configEnabledItemChunk : Boolean? = null
    private var configEnabledItemDust : Boolean? = null
    private var configEnabledItemIngot : Boolean? = null
    private var configEnabledItemNugget : Boolean? = null
    private var configEnabledItemPlate : Boolean? = null

    private var configEnabledItemTools : Boolean? = null
    private var configEnabledItemToolAxe : Boolean? = null
    private var configEnabledItemToolBow : Boolean? = null
    private var configEnabledItemToolHoe : Boolean? = null
    private var configEnabledItemToolPickaxe : Boolean? = null
    private var configEnabledItemToolShears : Boolean? = null
    private var configEnabledItemToolShovel : Boolean? = null
    private var configEnabledItemToolSword : Boolean? = null

    private var configGenerationMinY : Int? = null
    private var configGenerationMaxY : Int? = null
    private var configGenerationSize : Int? = null
    private var configGenerationSizeBound : Int? = null
    private var configGenerationChance : Int? = null
    private var configGenerationWeight : Int? = null

    private var configMaterialArmorDurability : Int? = null

    private var configMaterialArmorReductionBoots : Int? = null
    private var configMaterialArmorReductionChestplate : Int? = null
    private var configMaterialArmorReductionHelmet : Int? = null
    private var configMaterialArmorReductionLeggings : Int? = null

    private var configMaterialArmorEnchantability : Int? = null
    private var configMaterialArmorToughness : Float? = null

    private var configMaterialToolDamage : Int? = null
    private var configMaterialToolDurability : Int? = null
    private var configMaterialToolEnchantability : Int? = null
    private var configMaterialToolHarvest : Int? = null
    private var configMaterialToolMining : Int? = null
    private var configMaterialToolMaxDamage : Int? = null

    private var configEnabledRecipe : Boolean? = null

    private var configRecipeBlocksWhole : Property? = null

    private var configRecipeItemsPlate : Property? = null

    private var configRecipeArmorHead : Property? = null
    private var configRecipeArmorChest : Property? = null
    private var configRecipeArmorLegs : Property? = null
    private var configRecipeArmorFeet : Property? = null

    private var configRecipeItemsAxe : Property? = null
    private var configRecipeItemsBow : Property? = null
    private var configRecipeItemsHoe : Property? = null
    private var configRecipeItemsPickaxe : Property? = null
    private var configRecipeItemsShears : Property? = null
    private var configRecipeItemsShovel : Property? = null
    private var configRecipeItemsSword : Property? = null

    private var configMaterialArmor : ItemArmor.ArmorMaterial? = null
    private var configMaterialTool : Item.ToolMaterial? = null

    override fun setupConfig(configuration : Configuration) {
        configBlockOreHardness = configuration.getFloat("hardness", "${References.MOD_ID}.$ELEMENT.block.ore", 3.0f, 0.0f, 10.0f, "The hardness of the ore whole")
        configBlockOreResistance = configuration.getFloat("resistance", "${References.MOD_ID}.$ELEMENT.block.ore", 5.0f, 0.0f, 10.0f, "The resistance of the ore whole")

        configBlockWholeHardness = configuration.getFloat("hardness", "${References.MOD_ID}.$ELEMENT.block.whole", 3.0f, 0.0f, 10.0f, "The hardness of the whole whole")
        configBlockWholeResistance = configuration.getFloat("resistance", "${References.MOD_ID}.$ELEMENT.block.whole", 5.0f, 0.0f, 10.0f, "The resistance of the whole whole")

        configEnabledAll = configuration.getBoolean("all", "${References.MOD_ID}.$ELEMENT.enabled", true, "Set to false to disable anything $ELEMENT")

        configEnabledArmor = configuration.getBoolean("armor", "${References.MOD_ID}.$ELEMENT.enabled.armor", true, "Set to false to disable $ELEMENT armor")
        configEnabledArmorChest = configuration.getBoolean("chest", "${References.MOD_ID}.$ELEMENT.enabled.armor", true, "Set to false to disable $ELEMENT chestplate")
        configEnabledArmorFeet = configuration.getBoolean("feet", "${References.MOD_ID}.$ELEMENT.enabled.armor", true, "Set to false to disable $ELEMENT boots")
        configEnabledArmorHead = configuration.getBoolean("head", "${References.MOD_ID}.$ELEMENT.enabled.armor", true, "Set to false to disable $ELEMENT helmet")
        configEnabledArmorLegs = configuration.getBoolean("legs", "${References.MOD_ID}.$ELEMENT.enabled.armor", true, "Set to false to disable $ELEMENT boots")

        configEnabledBlocks = configuration.getBoolean("blocks", "${References.MOD_ID}.$ELEMENT.enabled.block", true, "Set to false to disable $ELEMENT blocks")
        configEnabledBlockOre = configuration.getBoolean("ore", "${References.MOD_ID}.$ELEMENT.enabled.block", true, "Set to false to disable $ELEMENT ore")
        configEnabledBlockWhole = configuration.getBoolean("whole", "${References.MOD_ID}.$ELEMENT.enabled.block", true, "Set to false to disable block of $ELEMENT")

        configEnabledItems = configuration.getBoolean("items", "${References.MOD_ID}.$ELEMENT.enabled.item", true, "Set to false to disable $ELEMENT items")
        configEnabledItemChunk = configuration.getBoolean("chunk", "${References.MOD_ID}.$ELEMENT.enabled.item", true, "Set to false to disable $ELEMENT chunk")
        configEnabledItemDust = configuration.getBoolean("dust", "${References.MOD_ID}.$ELEMENT.enabled.item", true, "Set to false to disable $ELEMENT dust")
        configEnabledItemIngot = configuration.getBoolean("ingot", "${References.MOD_ID}.$ELEMENT.enabled.item", true, "Set to false to disable $ELEMENT ingot")
        configEnabledItemNugget = configuration.getBoolean("nugget", "${References.MOD_ID}.$ELEMENT.enabled.item", true, "Set to false to disable $ELEMENT nugget")
        configEnabledItemPlate = configuration.getBoolean("plate", "${References.MOD_ID}.$ELEMENT.enabled.item", true, "Set to false to disable $ELEMENT plate")

        configEnabledItemTools = configuration.getBoolean("tools", "${References.MOD_ID}.$ELEMENT.enabled.item.tool", true, "Set to false to disable $ELEMENT tools")
        configEnabledItemToolAxe = configuration.getBoolean("axe", "${References.MOD_ID}.$ELEMENT.enabled.item.tool", true, "Set to false to disable $ELEMENT axe")
        configEnabledItemToolBow = configuration.getBoolean("bow", "${References.MOD_ID}.$ELEMENT.enabled.item.tool", true, "Set to false to disable $ELEMENT bow")
        configEnabledItemToolHoe = configuration.getBoolean("hoe", "${References.MOD_ID}.$ELEMENT.enabled.item.tool", true, "Set to false to disable $ELEMENT hoe")
        configEnabledItemToolPickaxe = configuration.getBoolean("pickaxe", "${References.MOD_ID}.$ELEMENT.enabled.item.tool", true, "Set to false to disable $ELEMENT pickaxe")
        configEnabledItemToolShears = configuration.getBoolean("shears", "${References.MOD_ID}.$ELEMENT.enabled.item.tool", true, "Set to false to disable $ELEMENT shears")
        configEnabledItemToolShovel = configuration.getBoolean("shovel", "${References.MOD_ID}.$ELEMENT.enabled.item.tool", true, "Set to false to disable $ELEMENT shovel")
        configEnabledItemToolSword = configuration.getBoolean("sword", "${References.MOD_ID}.$ELEMENT.enabled.item.tool", true, "Set to false to disable $ELEMENT sword")

        configEnabledRecipe = configuration.getBoolean("configEnabledAll", "${References.MOD_ID}.$ELEMENT.enabled.recipe", false, "Enabled to allow custom recipes")

        configGenerationMinY = configuration.getInt("min-y", "${References.MOD_ID}.$ELEMENT.generation", 20, 1, 256, "The lowest the ore can spawn")
        configGenerationMaxY = configuration.getInt("max-y", "${References.MOD_ID}.$ELEMENT.generation", 60, 1, 256, "The highest the ore can spawn")
        configGenerationSize = configuration.getInt("size", "${References.MOD_ID}.$ELEMENT.generation", 4, 1, 256, "Ore vein size")
        configGenerationSizeBound = configuration.getInt("sizeBound", "${References.MOD_ID}.$ELEMENT.generation", 4, 1, 256, "Max value of additional random size")
        configGenerationChance = configuration.getInt("chance", "${References.MOD_ID}.$ELEMENT.generation", 6, 1, 256, "Spawn chance")
        configGenerationWeight = configuration.getInt("weight", "${References.MOD_ID}.$ELEMENT.generation", 3, 1, 256, "Spawn weight (only change if you have spawning problems)")

        configMaterialArmorDurability = configuration.getInt("durability", "${References.MOD_ID}.$ELEMENT.material.armor", 15, 0, 100, "Durability of $ELEMENT armor")

        configMaterialArmorReductionBoots = configuration.getInt("boots", "${References.MOD_ID}.$ELEMENT.material.armor.reduction", 1, 0, 100, "Boots reduction of $ELEMENT armor")
        configMaterialArmorReductionChestplate = configuration.getInt("chestplate", "${References.MOD_ID}.$ELEMENT.material.armor.reduction", 5, 0, 100, "Chestplate reduction of $ELEMENT armor")
        configMaterialArmorReductionHelmet = configuration.getInt("helmet", "${References.MOD_ID}.$ELEMENT.material.armor.reduction", 2, 0, 100, "Helmet reduction of $ELEMENT armor")
        configMaterialArmorReductionLeggings = configuration.getInt("leggings", "${References.MOD_ID}.$ELEMENT.material.armor.reduction", 4, 0, 100, "Leggings reduction of $ELEMENT armor")

        configMaterialArmorEnchantability = configuration.getInt("enchantability", "${References.MOD_ID}.$ELEMENT.material.armor", 12, 0, 100, "Enchantability of $ELEMENT armor")
        configMaterialArmorToughness = configuration.getFloat("enchantability", "${References.MOD_ID}.$ELEMENT.material.armor", 2.0f, 0.0f, 100.0f, "Enchantability of $ELEMENT armor")

        configMaterialToolDamage = configuration.getInt("damage", "${References.MOD_ID}.$ELEMENT.material.tool", 4, 0, 1000, "Damage of $ELEMENT tools")
        configMaterialToolDurability = configuration.getInt("durability", "${References.MOD_ID}.$ELEMENT.material.tool", 200, 0, 1000, "Durability of $ELEMENT tools")
        configMaterialToolEnchantability = configuration.getInt("enchantability", "${References.MOD_ID}.$ELEMENT.material.tool", 12, 0, 1000, "Enchantability of $ELEMENT tools")
        configMaterialToolHarvest = configuration.getInt("harvest", "${References.MOD_ID}.$ELEMENT.material.tool", 70, 0, 1000, "Harvest level of $ELEMENT tools")
        configMaterialToolMining = configuration.getInt("mining", "${References.MOD_ID}.$ELEMENT.material.tool", 9, 0, 1000, "Mining speed of $ELEMENT tools")
        configMaterialToolMaxDamage = configuration.getInt("maxDamage", "${References.MOD_ID}.$ELEMENT.material.tool", 500, 0, 10000, "Max damage of $ELEMENT shears")

        configRecipeArmorHead = configuration.get("${References.MOD_ID}.$ELEMENT.recipe.armor", "head", arrayOf(ELEMENT_ORE_DIC_ARMOR_HEAD, "iii", "i_i", "___", "i", ELEMENT_ORE_DIC_INGOT))
        configRecipeArmorChest = configuration.get("${References.MOD_ID}.$ELEMENT.recipe.armor", "chest", arrayOf(ELEMENT_ORE_DIC_ARMOR_CHEST, "i_i", "iii", "iii", "i", ELEMENT_ORE_DIC_INGOT))
        configRecipeArmorLegs = configuration.get("${References.MOD_ID}.$ELEMENT.recipe.armor", "legs", arrayOf(ELEMENT_ORE_DIC_ARMOR_LEGS, "iii", "i_i", "i_i", "i", ELEMENT_ORE_DIC_INGOT))
        configRecipeArmorFeet = configuration.get("${References.MOD_ID}.$ELEMENT.recipe.armor", "feet", arrayOf(ELEMENT_ORE_DIC_ARMOR_FEET, "___", "i_i", "i_i", "i", ELEMENT_ORE_DIC_INGOT))

        configRecipeBlocksWhole = configuration.get("${References.MOD_ID}.$ELEMENT.recipe.block", "whole", arrayOf(ELEMENT_ORE_DIC_WHOLE, "iii", "iii", "iii", "i", ELEMENT_ORE_DIC_INGOT))

        configRecipeItemsPlate = configuration.get("${References.MOD_ID}.$ELEMENT.recipe.item", "plate", arrayOf(ELEMENT_ORE_DIC_PLATE, "___", "iii", "___", "i", ELEMENT_ORE_DIC_INGOT))

        configRecipeItemsAxe = configuration.get("${References.MOD_ID}.$ELEMENT.recipe.item.tool", "axe", arrayOf(ELEMENT_ORE_DIC_AXE, "ii_", "is_", "_s_", "i", ELEMENT_ORE_DIC_INGOT, "s", "stickWood"))
        configRecipeItemsBow = configuration.get("${References.MOD_ID}.$ELEMENT.recipe.item.tool", "bow", arrayOf(ELEMENT_ORE_DIC_BOW, "_it", "s_t", "_it", "i", ELEMENT_ORE_DIC_INGOT, "s", "stickWood", "t", "string"))
        configRecipeItemsHoe = configuration.get("${References.MOD_ID}.$ELEMENT.recipe.item.tool", "hoe", arrayOf(ELEMENT_ORE_DIC_HOE, "ii_", "_s_", "_s_", "i", ELEMENT_ORE_DIC_INGOT, "s", "stickWood"))
        configRecipeItemsPickaxe = configuration.get("${References.MOD_ID}.$ELEMENT.recipe.item.tool", "pickaxe", arrayOf(ELEMENT_ORE_DIC_PICKAXE, "iii", "_s_", "_s_", "i", ELEMENT_ORE_DIC_INGOT, "s", "stickWood"))
        configRecipeItemsShears = configuration.get("${References.MOD_ID}.$ELEMENT.recipe.item.tool", "shears", arrayOf(ELEMENT_ORE_DIC_SHEARS, "___", "i__", "_i_", "i", ELEMENT_ORE_DIC_INGOT))
        configRecipeItemsShovel = configuration.get("${References.MOD_ID}.$ELEMENT.recipe.item.tool", "shovel", arrayOf(ELEMENT_ORE_DIC_SHOVEL, "_i_", "_s_", "_s_", "i", ELEMENT_ORE_DIC_INGOT, "s", "stickWood"))
        configRecipeItemsSword = configuration.get("${References.MOD_ID}.$ELEMENT.recipe.item.tool", "sword", arrayOf(ELEMENT_ORE_DIC_SWORD, "_i_", "_i_", "_s_", "i", ELEMENT_ORE_DIC_INGOT, "s", "stickWood"))

        configMaterialArmor = EnumHelper.addArmorMaterial(
            "${ELEMENT}_armor_material",
            "${References.MOD_ID}:${ELEMENT}_armor",
            configMaterialArmorDurability !!,
            intArrayOf(
                configMaterialArmorReductionHelmet !!,
                configMaterialArmorReductionChestplate !!,
                configMaterialArmorReductionLeggings !!,
                configMaterialArmorReductionBoots !!
            ),
            configMaterialArmorEnchantability !!,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
            configMaterialArmorToughness !!
        )

        configMaterialTool = EnumHelper.addToolMaterial(
            "${ELEMENT}_tool_material",
            configMaterialToolHarvest !!,
            configMaterialToolDurability !!,
            configMaterialToolMining !!.toFloat(),
            configMaterialToolDamage !!.toFloat(),
            configMaterialToolEnchantability !!
        )
    }

    private var ore : Block? = null
    private var whole : Block? = null

    private var chunk : Item? = null
    private var dust : Item? = null
    private var ingot : Item? = null
    private var nugget : Item? = null
    private var plate : Item? = null

    private var armorHead : ItemArmor? = null
    private var armorChest : ItemArmor? = null
    private var armorLegs : ItemArmor? = null
    private var armorFeet : ItemArmor? = null

    private var axe : Item? = null
    private var bow : Item? = null
    private var hoe : Item? = null
    private var pickaxe : Item? = null
    private var shears : Item? = null
    private var shovel : Item? = null
    private var sword : Item? = null

    override fun preInit(event : FMLPreInitializationEvent) {
        if (configEnabledAll !!) {
            if (configEnabledItemIngot !!) {
                if (configEnabledItemChunk !!) chunk = RegisterUtils.registerItem(Chunk())
                if (configEnabledItemDust !!) dust = RegisterUtils.registerItem(Dust())
                if (configEnabledItemIngot !!) ingot = RegisterUtils.registerItem(Ingot())
                if (configEnabledItemNugget !!) nugget = RegisterUtils.registerItem(Nugget())
                if (configEnabledItemPlate !!) plate = RegisterUtils.registerItem(Plate())
            }
            if (configEnabledBlockOre !!) {
                if (configEnabledBlockOre !! && configEnabledItemChunk !!) ore = RegisterUtils.registerBlock(Ore(configBlockOreHardness, configBlockOreResistance, chunk !!))
                if (configEnabledBlockWhole !!) whole = RegisterUtils.registerBlock(Whole(configBlockWholeHardness, configBlockWholeResistance))
            }
            if (configEnabledArmor !!) {
                if (configEnabledArmorHead !!) armorHead = RegisterUtils.registerItem(Armor(configMaterialArmor !!, EntityEquipmentSlot.HEAD))
                if (configEnabledArmorChest !!) armorChest = RegisterUtils.registerItem(Armor(configMaterialArmor !!, EntityEquipmentSlot.CHEST))
                if (configEnabledArmorLegs !!) armorLegs = RegisterUtils.registerItem(Armor(configMaterialArmor !!, EntityEquipmentSlot.LEGS))
                if (configEnabledArmorFeet !!) armorFeet = RegisterUtils.registerItem(Armor(configMaterialArmor !!, EntityEquipmentSlot.FEET))
            }
            if (configEnabledItemTools !!) {
                if (configEnabledItemToolAxe !!) axe = RegisterUtils.registerItem(Axe(configMaterialTool !!))
                if (configEnabledItemToolBow !!) bow = RegisterUtils.registerItem(Bow(configMaterialToolDamage))
                if (configEnabledItemToolHoe !!) hoe = RegisterUtils.registerItem(Hoe(configMaterialTool !!))
                if (configEnabledItemToolPickaxe !!) pickaxe = RegisterUtils.registerItem(Pickaxe(configMaterialTool !!))
                if (configEnabledItemToolShears !!) shears = RegisterUtils.registerItem(Shears(configMaterialToolMaxDamage))
                if (configEnabledItemToolShovel !!) shovel = RegisterUtils.registerItem(Shovel(configMaterialTool !!))
                if (configEnabledItemToolSword !!) sword = RegisterUtils.registerItem(Sword(configMaterialTool !!))
            }
            if (configEnabledBlockOre !! && configEnabledItemIngot !!) {
                GameRegistry.addSmelting(ore !!, ItemStack(ingot), 1f)
            }
            if (configEnabledRecipe !!) {
                try {
                    if (configEnabledBlocks !!) {
                        if (configEnabledBlockWhole !!) GameRegistry.addRecipe(RecipeUtils.configRecipe(configRecipeBlocksWhole !!))
                    }
                    if (configEnabledArmor !!) {
                        if (configEnabledArmorHead !!) GameRegistry.addRecipe(RecipeUtils.configRecipe(configRecipeArmorHead !!))
                        if (configEnabledArmorChest !!) GameRegistry.addRecipe(RecipeUtils.configRecipe(configRecipeArmorChest !!))
                        if (configEnabledArmorLegs !!) GameRegistry.addRecipe(RecipeUtils.configRecipe(configRecipeArmorLegs !!))
                        if (configEnabledArmorFeet !!) GameRegistry.addRecipe(RecipeUtils.configRecipe(configRecipeArmorFeet !!))
                    }
                    if (configEnabledItems !!) {
                        if (configEnabledItemPlate !!) GameRegistry.addRecipe(RecipeUtils.configRecipe(configRecipeItemsPlate !!))
                    }
                    if (configEnabledItemTools !!) {
                        if (configEnabledItemToolAxe !!) GameRegistry.addRecipe(RecipeUtils.configRecipe(configRecipeItemsAxe !!))
                        if (configEnabledItemToolBow !!) GameRegistry.addRecipe(RecipeUtils.configRecipe(configRecipeItemsBow !!))
                        if (configEnabledItemToolHoe !!) GameRegistry.addRecipe(RecipeUtils.configRecipe(configRecipeItemsHoe !!))
                        if (configEnabledItemToolPickaxe !!) GameRegistry.addRecipe(RecipeUtils.configRecipe(configRecipeItemsPickaxe !!))
                        if (configEnabledItemToolShears !!) GameRegistry.addRecipe(RecipeUtils.configRecipe(configRecipeItemsShears !!))
                        if (configEnabledItemToolShovel !!) GameRegistry.addRecipe(RecipeUtils.configRecipe(configRecipeItemsShovel !!))
                        if (configEnabledItemToolSword !!) GameRegistry.addRecipe(RecipeUtils.configRecipe(configRecipeItemsSword !!))
                    }
                } catch (e : RecipeException) {
                    References.LOGGER.error("Recipe Exception in ${ELEMENT.capitalize()}", e)
                }
            } else {
                if (configEnabledBlocks !!) {
                    if (configEnabledBlockWhole !!) GameRegistry.addRecipe(ShapedOreRecipe(whole, "iii", "iii", "iii", 'i', "Ingot"))
                }
                if (configEnabledArmor !!) {
                    if (configEnabledArmorHead !!) GameRegistry.addRecipe(ShapedOreRecipe(armorHead, "iii", "i_i", "___", 'i', "Ingot"))
                    if (configEnabledArmorChest !!) GameRegistry.addRecipe(ShapedOreRecipe(armorChest, "i_i", "iii", "iii", 'i', "Ingot"))
                    if (configEnabledArmorLegs !!) GameRegistry.addRecipe(ShapedOreRecipe(armorLegs, "iii", "i_i", "i_i", 'i', "Ingot"))
                    if (configEnabledArmorFeet !!) GameRegistry.addRecipe(ShapedOreRecipe(armorFeet, "___", "i_i", "i_i", 'i', "Ingot"))
                }
                if (configEnabledItemTools !!) {
                    if (configEnabledItemToolAxe !!) GameRegistry.addRecipe(ShapedOreRecipe(axe, "ii_", "is_", "_s_", 'i', ELEMENT_ORE_DIC_INGOT, 's', "stickWood"))
                    if (configEnabledItemToolBow !!) GameRegistry.addRecipe(ShapedOreRecipe(bow, "_it", "s_t", "_it", 'i', ELEMENT_ORE_DIC_INGOT, 's', "stickWood", 't', "string"))
                    if (configEnabledItemToolHoe !!) GameRegistry.addRecipe(ShapedOreRecipe(hoe, "ii_", "_s_", "_s_", 'i', ELEMENT_ORE_DIC_INGOT, 's', "stickWood"))
                    if (configEnabledItemToolPickaxe !!) GameRegistry.addRecipe(ShapedOreRecipe(pickaxe, "iii", "_s_", "_s_", 'i', ELEMENT_ORE_DIC_INGOT, 's', "stickWood"))
                    if (configEnabledItemToolShears !!) GameRegistry.addRecipe(ShapedOreRecipe(shears, "___", "i__", "_i_", 'i', ELEMENT_ORE_DIC_INGOT))
                    if (configEnabledItemToolShovel !!) GameRegistry.addRecipe(ShapedOreRecipe(shovel, "_i_", "_s_", "_s_", 'i', ELEMENT_ORE_DIC_INGOT, 's', "stickWood"))
                    if (configEnabledItemToolSword !!) GameRegistry.addRecipe(ShapedOreRecipe(sword, "_i_", "_i_", "_s_", 'i', ELEMENT_ORE_DIC_INGOT, 's', "stickWood"))
                }
            }
        }
    }

    override fun init(event : FMLInitializationEvent) {
        if (configEnabledAll !!) {
            if (configEnabledBlockOre !! && configEnabledItemChunk !!) {
                GameRegistry.registerWorldGenerator(
                    WorldGenerator(
                        configGenerationMinY,
                        configGenerationMaxY,
                        configGenerationSize,
                        configGenerationSizeBound,
                        configGenerationChance
                    ),
                    configGenerationWeight !!
                )
            }
        }
    }

    override fun postInit(event : FMLPostInitializationEvent) {
        if (configEnabledAll !!) {

        }
    }
}

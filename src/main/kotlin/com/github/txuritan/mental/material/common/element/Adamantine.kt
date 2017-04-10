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

// gimp colorize h:0 s:78 l:-36

package com.github.txuritan.mental.material.common.element

import com.github.txuritan.mental.core.common.block.BlockBase
import com.github.txuritan.mental.core.common.exceptions.RecipeException
import com.github.txuritan.mental.core.common.item.ItemBase
import com.github.txuritan.mental.core.common.item.tools.*
import com.github.txuritan.mental.core.common.item.weapons.ItemArmorBase
import com.github.txuritan.mental.core.common.item.weapons.ItemBowBase
import com.github.txuritan.mental.core.common.item.weapons.ItemSwordBase
import com.github.txuritan.mental.core.common.util.RecipeUtils
import com.github.txuritan.mental.core.common.util.References.MENTAL_CREATIVE_TAB
import com.github.txuritan.mental.core.common.util.References.MOD_ID
import com.github.txuritan.mental.core.common.util.RegisterUtils
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.init.SoundEvents
import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.item.Item
import net.minecraft.item.ItemArmor
import net.minecraft.util.math.BlockPos
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
 * @author Ian 'Txuritan' Cronkright
 */
object Adamantine {

    private val ELEMENT = "adamantine"

    private val ELEMENT_ORE_DIC_ORE = "${ELEMENT}Ore"
    private val ELEMENT_ORE_DIC_WHOLE = "${ELEMENT}Block"

    internal class AdamantineOre(hardness: Float?, resistance: Float?, private val itemDropped: Item) : BlockBase(Material.ROCK, "adamantine_ore", arrayOf(ELEMENT_ORE_DIC_ORE)) {

        init {
            setHardness(hardness!!)
            setResistance(resistance!!)
            soundType = SoundType.STONE
            setCreativeTab(MENTAL_CREATIVE_TAB)
        }

        override fun getItemDropped(state: IBlockState?, rand: Random?, fortune: Int): Item {
            return itemDropped
        }
    }

    internal class AdamantineWhole(hardness: Float?, resistance: Float?) : BlockBase(Material.ROCK, "adamantine_block", arrayOf(ELEMENT_ORE_DIC_WHOLE)) {
        init {
            setHardness(hardness!!)
            setResistance(resistance!!)
            soundType = SoundType.STONE
            setCreativeTab(MENTAL_CREATIVE_TAB)
        }
    }

    private val ELEMENT_ORE_DIC_CHUNK = "${ELEMENT}Chunk"
    private val ELEMENT_ORE_DIC_DUST = "${ELEMENT}Dust"
    private val ELEMENT_ORE_DIC_INGOT = "${ELEMENT}Ingot"
    private val ELEMENT_ORE_DIC_NUGGET = "${ELEMENT}Nugget"

    internal class AdamantineChunk : ItemBase("adamantine_chunk", arrayOf(ELEMENT_ORE_DIC_CHUNK)) {
        init {
            setCreativeTab(MENTAL_CREATIVE_TAB)
        }
    }

    internal class AdamantineDust : ItemBase("adamantine_dust", arrayOf(ELEMENT_ORE_DIC_DUST)) {
        init {
            setCreativeTab(MENTAL_CREATIVE_TAB)
        }
    }

    internal class AdamantineIngot : ItemBase("adamantine_ingot", arrayOf(ELEMENT_ORE_DIC_INGOT)) {
        init {
            setCreativeTab(MENTAL_CREATIVE_TAB)
        }
    }

    internal class AdamantineNugget : ItemBase("adamantine_nugget", arrayOf(ELEMENT_ORE_DIC_NUGGET)) {
        init {
            setCreativeTab(MENTAL_CREATIVE_TAB)
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

    internal class AdamantineArmor(material: ItemArmor.ArmorMaterial, slot: EntityEquipmentSlot) : ItemArmorBase(material, slot, "adamantine_armor_" + slot.getName(), arrayOf(ELEMENT_ORE_DIC_ARMOR + slot.getName().capitalize())) {
        init {
            creativeTab = MENTAL_CREATIVE_TAB
        }
    }

    internal class AdamantineAxe(material: Item.ToolMaterial) : ItemAxeBase(material, "adamantine_axe", arrayOf(ELEMENT_ORE_DIC_AXE)) {
        init {
            creativeTab = MENTAL_CREATIVE_TAB
        }
    }

    internal class AdamantineBow(maxDamage: Int?) : ItemBowBase(maxDamage, "adamantine_bow", arrayOf(ELEMENT_ORE_DIC_BOW)) {
        init {
            creativeTab = MENTAL_CREATIVE_TAB
        }
    }

    internal class AdamantineHoe(material: Item.ToolMaterial) : ItemHoeBase(material, "adamantine_hoe", arrayOf(ELEMENT_ORE_DIC_HOE)) {
        init {
            creativeTab = MENTAL_CREATIVE_TAB
        }
    }

    internal class AdamantinePickaxe(material: Item.ToolMaterial) : ItemPickaxeBase(material, "adamantine_pickaxe", arrayOf(ELEMENT_ORE_DIC_PICKAXE)) {
        init {
            creativeTab = MENTAL_CREATIVE_TAB
        }
    }

    internal class AdamantineShears(damage: Int?) : ItemShearsBase(damage, "adamantine_shears", arrayOf(ELEMENT_ORE_DIC_SHEARS)) {
        init {
            creativeTab = MENTAL_CREATIVE_TAB
        }
    }

    internal class AdamantineShovel(material: Item.ToolMaterial) : ItemShovelBase(material, "adamantine_shovel", arrayOf(ELEMENT_ORE_DIC_SHOVEL)) {
        init {
            creativeTab = MENTAL_CREATIVE_TAB
        }
    }

    internal class AdamantineSword(material: Item.ToolMaterial) : ItemSwordBase(material, "adamantine_sword", arrayOf(ELEMENT_ORE_DIC_SWORD)) {
        init {
            creativeTab = MENTAL_CREATIVE_TAB
        }
    }

    internal class AdamantineWorldGenerator(private val minY: Int?, private val maxY: Int?, private val size: Int?, private val sizeBound: Int?, private val chance: Int?) : IWorldGenerator {

        override fun generate(random: Random, chunkX: Int, chunkZ: Int, world: World, chunkGenerator: IChunkGenerator, chunkProvider: IChunkProvider) {
            when (world.provider.dimension) {
                1 -> generateEnd(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider)
            }
        }

        @Suppress("UNUSED_PARAMETER")
        fun generateEnd(random: Random, chunkX: Int, chunkZ: Int, world: World, chunkGenerator: IChunkGenerator, chunkProvider: IChunkProvider) {
            generateOre(Adamantine.ore!!.defaultState, world, random, chunkX * 16, chunkZ * 16, minY!!, maxY!!, size!! + random.nextInt(sizeBound!!), chance!!)
        }

        private fun generateOre(ore: IBlockState, world: World, random: Random, x: Int, z: Int, minY: Int, maxY: Int, size: Int, chances: Int) {
            val deltaY = maxY - minY
            for (i in 0..chances - 1) {
                val pos = BlockPos(x + random.nextInt(16), minY + random.nextInt(deltaY), z + random.nextInt(16))
                val generator = WorldGenMinable(ore, size)
                generator.generate(world, random, pos)
            }
        }
    }

    private var blockOreHardness: Float? = null
    private var blockOreResistance: Float? = null

    private var blockWholeHardness: Float? = null
    private var blockWholeResistance: Float? = null

    private var enabled: Boolean? = null
    private var enabledArmor: Boolean? = null
    private var enabledBlocks: Boolean? = null
    private var enabledGen: Boolean? = null
    private var enabledItems: Boolean? = null
    private var enabledTools: Boolean? = null

    private var genMinY: Int? = null
    private var genMaxY: Int? = null
    private var genSize: Int? = null
    private var genSizeBound: Int? = null
    private var genChance: Int? = null
    private var genWeight: Int? = null

    private var materialArmorDurability: Int? = null

    private var materialArmorReductionBoots: Int? = null
    private var materialArmorReductionChestplate: Int? = null
    private var materialArmorReductionHelmet: Int? = null
    private var materialArmorReductionLeggings: Int? = null

    private var materialArmorEnchantability: Int? = null
    private var materialArmorToughness: Float? = null

    private var materialToolDamage: Int? = null
    private var materialToolDurability: Int? = null
    private var materialToolEnchantability: Int? = null
    private var materialToolHarvest: Int? = null
    private var materialToolMining: Int? = null
    private var materialToolMaxDamage: Int? = null

    private var recipeEnabled: Boolean? = null

    private var recipeBlocksWhole: Property? = null

    private var recipeArmorHead: Property? = null
    private var recipeArmorChest: Property? = null
    private var recipeArmorLegs: Property? = null
    private var recipeArmorFeet: Property? = null

    private var recipeItemsAxe: Property? = null
    private var recipeItemsBow: Property? = null
    private var recipeItemsHoe: Property? = null
    private var recipeItemsPickaxe: Property? = null
    private var recipeItemsShears: Property? = null
    private var recipeItemsShovel: Property? = null
    private var recipeItemsSword: Property? = null

    private var armorMaterial: ItemArmor.ArmorMaterial? = null
    private var toolMaterial: Item.ToolMaterial? = null

    @JvmStatic
    fun setupConfig(configuration: Configuration) {
        blockOreHardness = configuration.getFloat("hardness", "$MOD_ID.$ELEMENT.whole.ore", 3.0f, 0.0f, 10.0f, "The hardness of the ore whole")
        blockOreResistance = configuration.getFloat("resistance", "$MOD_ID.$ELEMENT.whole.ore", 5.0f, 0.0f, 10.0f, "The resistance of the ore whole")

        blockWholeHardness = configuration.getFloat("hardness", "$MOD_ID.$ELEMENT.whole.whole", 3.0f, 0.0f, 10.0f, "The hardness of the whole whole")
        blockWholeResistance = configuration.getFloat("resistance", "$MOD_ID.$ELEMENT.whole.whole", 5.0f, 0.0f, 10.0f, "The resistance of the whole whole")

        enabled = configuration.getBoolean("all", "$MOD_ID.$ELEMENT.enabled", true, "Set to false to disable anything $ELEMENT")
        enabledArmor = configuration.getBoolean("armor", "$MOD_ID.$ELEMENT.enabled.armor", true, "Set to false to disable $ELEMENT armor")
        enabledBlocks = configuration.getBoolean("ore", "$MOD_ID.$ELEMENT.enabled.whole.ore", true, "Set to false to disable $ELEMENT ore")
        enabledGen = configuration.getBoolean("generation", "$MOD_ID.$ELEMENT.enabled.whole.whole", true, "Set to false to disable $ELEMENT world generation")
        enabledItems = configuration.getBoolean("ingot", "$MOD_ID.$ELEMENT.enabled.item.ingot", true, "Set to false to disable $ELEMENT ingot")
        enabledTools = configuration.getBoolean("tools", "$MOD_ID.$ELEMENT.enabled.item", true, "Set to false to disable $ELEMENT tools")

        genMinY = configuration.getInt("min-y", "$MOD_ID.$ELEMENT.generation", 20, 1, 256, "The lowest the ore can spawn")
        genMaxY = configuration.getInt("max-y", "$MOD_ID.$ELEMENT.generation", 60, 1, 256, "The highest the ore can spawn")
        genSize = configuration.getInt("size", "$MOD_ID.$ELEMENT.generation", 4, 1, 256, "Ore vein size")
        genSizeBound = configuration.getInt("sizeBound", "$MOD_ID.$ELEMENT.generation", 4, 1, 256, "Max value of additional random size")
        genChance = configuration.getInt("chance", "$MOD_ID.$ELEMENT.generation", 6, 1, 256, "Spawn chance")
        genWeight = configuration.getInt("weight", "$MOD_ID.$ELEMENT.generation", 3, 1, 256, "Spawn weight (only change if you have spawning problems)")

        materialArmorDurability = configuration.getInt("durability", "$MOD_ID.$ELEMENT.material.armor", 15, 0, 100, "Durability of $ELEMENT armor")

        materialArmorReductionBoots = configuration.getInt("boots", "$MOD_ID.$ELEMENT.material.armor.reduction", 1, 0, 100, "Boots reduction of $ELEMENT armor")
        materialArmorReductionChestplate = configuration.getInt("chestplate", "$MOD_ID.$ELEMENT.material.armor.reduction", 5, 0, 100, "Chestplate reduction of $ELEMENT armor")
        materialArmorReductionHelmet = configuration.getInt("helmet", "$MOD_ID.$ELEMENT.material.armor.reduction", 2, 0, 100, "Helmet reduction of $ELEMENT armor")
        materialArmorReductionLeggings = configuration.getInt("leggings", "$MOD_ID.$ELEMENT.material.armor.reduction", 4, 0, 100, "Leggings reduction of $ELEMENT armor")

        materialArmorEnchantability = configuration.getInt("enchantability", "$MOD_ID.$ELEMENT.material.armor", 12, 0, 100, "Enchantability of $ELEMENT armor")
        materialArmorToughness = configuration.getFloat("enchantability", "$MOD_ID.$ELEMENT.material.armor", 2.0f, 0.0f, 100.0f, "Enchantability of $ELEMENT armor")

        materialToolDamage = configuration.getInt("damage", "$MOD_ID.$ELEMENT.material.tool", 4, 0, 1000, "Damage of $ELEMENT tools")
        materialToolDurability = configuration.getInt("durability", "$MOD_ID.$ELEMENT.material.tool", 200, 0, 1000, "Durability of $ELEMENT tools")
        materialToolEnchantability = configuration.getInt("enchantability", "$MOD_ID.$ELEMENT.material.tool", 12, 0, 1000, "Enchantability of $ELEMENT tools")
        materialToolHarvest = configuration.getInt("harvest", "$MOD_ID.$ELEMENT.material.tool", 70, 0, 1000, "Harvest level of $ELEMENT tools")
        materialToolMining = configuration.getInt("mining", "$MOD_ID.$ELEMENT.material.tool", 9, 0, 1000, "Mining speed of $ELEMENT tools")
        materialToolMaxDamage = configuration.getInt("maxDamage", "$MOD_ID.$ELEMENT.material.tool", 500, 0, 10000, "Max damage of $ELEMENT shears")

        recipeEnabled = configuration.getBoolean("enabled", "$MOD_ID.$ELEMENT.recipe", false, "Enabled to allow custom recipes")

        recipeBlocksWhole = configuration.get("$MOD_ID.$ELEMENT.recipe.blocks", "whole", arrayOf(ELEMENT_ORE_DIC_WHOLE, "iii", "iii", "iii", "i", ELEMENT_ORE_DIC_INGOT))

        recipeArmorHead = configuration.get("$MOD_ID.$ELEMENT.recipe.armor", "head", arrayOf(ELEMENT_ORE_DIC_ARMOR_HEAD, "iii", "i_i", "___", "i", ELEMENT_ORE_DIC_INGOT))
        recipeArmorChest = configuration.get("$MOD_ID.$ELEMENT.recipe.armor", "chest", arrayOf(ELEMENT_ORE_DIC_ARMOR_CHEST, "i_i", "iii", "iii", "i", ELEMENT_ORE_DIC_INGOT))
        recipeArmorLegs = configuration.get("$MOD_ID.$ELEMENT.recipe.armor", "legs", arrayOf(ELEMENT_ORE_DIC_ARMOR_LEGS, "iii", "i_i", "i_i", "i", ELEMENT_ORE_DIC_INGOT))
        recipeArmorFeet = configuration.get("$MOD_ID.$ELEMENT.recipe.armor", "feet", arrayOf(ELEMENT_ORE_DIC_ARMOR_FEET, "___", "i_i", "i_i", "i", ELEMENT_ORE_DIC_INGOT))

        recipeItemsAxe = configuration.get("$MOD_ID.$ELEMENT.recipe.items", "axe", arrayOf(ELEMENT_ORE_DIC_AXE, "ii_", "is_", "is_", "i", ELEMENT_ORE_DIC_INGOT, "s", "stickWood"))
        recipeItemsBow = configuration.get("$MOD_ID.$ELEMENT.recipe.items", "bow", arrayOf(ELEMENT_ORE_DIC_BOW, "_it", "s_t", "_it", "i", ELEMENT_ORE_DIC_INGOT, "s", "stickWood", "t", "string"))
        recipeItemsHoe = configuration.get("$MOD_ID.$ELEMENT.recipe.items", "hoe", arrayOf(ELEMENT_ORE_DIC_HOE, "ii_", "_s_", "_s_", "i", ELEMENT_ORE_DIC_INGOT, "s", "stickWood"))
        recipeItemsPickaxe = configuration.get("$MOD_ID.$ELEMENT.recipe.items", "pickaxe", arrayOf(ELEMENT_ORE_DIC_PICKAXE, "iii", "_s_", "_s_", "i", ELEMENT_ORE_DIC_INGOT, "s", "stickWood"))
        recipeItemsShears = configuration.get("$MOD_ID.$ELEMENT.recipe.items", "shears", arrayOf(ELEMENT_ORE_DIC_SHEARS, "___", "i__", "_i_", "i", ELEMENT_ORE_DIC_INGOT))
        recipeItemsShovel = configuration.get("$MOD_ID.$ELEMENT.recipe.items", "shovel", arrayOf(ELEMENT_ORE_DIC_SHOVEL, "_i_", "_s_", "_s_", "i", ELEMENT_ORE_DIC_INGOT, "s", "stickWood"))
        recipeItemsSword = configuration.get("$MOD_ID.$ELEMENT.recipe.items", "sword", arrayOf(ELEMENT_ORE_DIC_SWORD, "_i_", "_i_", "_s_", "i", ELEMENT_ORE_DIC_INGOT, "s", "stickWood"))

        armorMaterial = EnumHelper.addArmorMaterial(
                "${ELEMENT}_armor_material",
                "$MOD_ID:${ELEMENT}_armor",
                materialArmorDurability!!,
                intArrayOf(materialArmorReductionHelmet!!, materialArmorReductionChestplate!!, materialArmorReductionLeggings!!, materialArmorReductionBoots!!),
                materialArmorEnchantability!!, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
                materialArmorToughness!!
        )

        toolMaterial = EnumHelper.addToolMaterial(
                ELEMENT + "_tool_material",
                materialToolHarvest!!,
                materialToolDurability!!,
                materialToolMining!!.toFloat(),
                materialToolDamage!!.toFloat(),
                materialToolEnchantability!!
        )
    }

    private var ore: Block? = null
    private var whole: Block? = null

    private var chunk: Item? = null
    private var dust: Item? = null
    private var ingot: Item? = null
    private var nugget: Item? = null

    private var armorHead: ItemArmor? = null
    private var armorChest: ItemArmor? = null
    private var armorLegs: ItemArmor? = null
    private var armorFeet: ItemArmor? = null

    private var axe: Item? = null
    private var bow: Item? = null
    private var hoe: Item? = null
    private var pickaxe: Item? = null
    private var shears: Item? = null
    private var shovel: Item? = null
    private var sword: Item? = null

    @Suppress("UNUSED_PARAMETER")
    fun preInit(event: FMLPreInitializationEvent) {
        if (enabled!!) {
            if (enabledItems!!) {
                chunk = RegisterUtils.registerItem(AdamantineChunk())
                dust = RegisterUtils.registerItem(AdamantineDust())
                ingot = RegisterUtils.registerItem(AdamantineIngot())
                nugget = RegisterUtils.registerItem(AdamantineNugget())
            }

            if (enabledBlocks!!) {
                ore = RegisterUtils.registerBlock(AdamantineOre(blockOreHardness, blockOreResistance, chunk!!))
                whole = RegisterUtils.registerBlock(AdamantineWhole(blockWholeHardness, blockWholeResistance))
            }

            if (enabledArmor!!) {
                armorHead = RegisterUtils.registerItem(AdamantineArmor(armorMaterial!!, EntityEquipmentSlot.HEAD))
                armorChest = RegisterUtils.registerItem(AdamantineArmor(armorMaterial!!, EntityEquipmentSlot.CHEST))
                armorLegs = RegisterUtils.registerItem(AdamantineArmor(armorMaterial!!, EntityEquipmentSlot.LEGS))
                armorFeet = RegisterUtils.registerItem(AdamantineArmor(armorMaterial!!, EntityEquipmentSlot.FEET))
            }

            if (enabledTools!!) {
                axe = RegisterUtils.registerItem(AdamantineAxe(toolMaterial!!))
                bow = RegisterUtils.registerItem(AdamantineBow(materialToolDamage))
                hoe = RegisterUtils.registerItem(AdamantineHoe(toolMaterial!!))
                pickaxe = RegisterUtils.registerItem(AdamantinePickaxe(toolMaterial!!))
                shears = RegisterUtils.registerItem(AdamantineShears(materialToolMaxDamage))
                shovel = RegisterUtils.registerItem(AdamantineShovel(toolMaterial!!))
                sword = RegisterUtils.registerItem(AdamantineSword(toolMaterial!!))
            }

            if (enabledGen!!) {
                GameRegistry.registerWorldGenerator(AdamantineWorldGenerator(genMinY, genMaxY, genSize, genSizeBound, genChance), genWeight!!)
            }

            recipes(recipeEnabled)
        }
    }

    @Suppress("UNUSED_PARAMETER")
    fun init(event: FMLInitializationEvent) {
        if (enabled!!) {

        }
    }

    @Suppress("UNUSED_PARAMETER")
    fun postInit(event: FMLPostInitializationEvent) {
        if (enabled!!) {

        }
    }

    private fun recipes(customRecipes: Boolean?) {
        if (customRecipes!!) {
            try {
                if (enabledBlocks!!) {
                    GameRegistry.addRecipe(RecipeUtils.configRecipe(recipeBlocksWhole!!))
                }

                if (enabledArmor!!) {
                    GameRegistry.addRecipe(RecipeUtils.configRecipe(recipeArmorHead!!))
                    GameRegistry.addRecipe(RecipeUtils.configRecipe(recipeArmorChest!!))
                    GameRegistry.addRecipe(RecipeUtils.configRecipe(recipeArmorLegs!!))
                    GameRegistry.addRecipe(RecipeUtils.configRecipe(recipeArmorFeet!!))
                }

                if (enabledTools!!) {
                    GameRegistry.addRecipe(RecipeUtils.configRecipe(recipeItemsAxe!!))
                    GameRegistry.addRecipe(RecipeUtils.configRecipe(recipeItemsBow!!))
                    GameRegistry.addRecipe(RecipeUtils.configRecipe(recipeItemsHoe!!))
                    GameRegistry.addRecipe(RecipeUtils.configRecipe(recipeItemsPickaxe!!))
                    GameRegistry.addRecipe(RecipeUtils.configRecipe(recipeItemsShears!!))
                    GameRegistry.addRecipe(RecipeUtils.configRecipe(recipeItemsShovel!!))
                    GameRegistry.addRecipe(RecipeUtils.configRecipe(recipeItemsSword!!))
                }
            } catch (e: RecipeException) {
                e.printStackTrace()
            }
        } else {
            if (enabledBlocks!!) {
                GameRegistry.addRecipe(ShapedOreRecipe(whole, "iii", "iii", "iii", 'i', "adamantineIngot"))
            }

            if (enabledArmor!!) {
                GameRegistry.addRecipe(ShapedOreRecipe(armorHead, "iii", "i_i", "___", 'i', "adamantineIngot"))
                GameRegistry.addRecipe(ShapedOreRecipe(armorChest, "i_i", "iii", "iii", 'i', "adamantineIngot"))
                GameRegistry.addRecipe(ShapedOreRecipe(armorLegs, "iii", "i_i", "i_i", 'i', "adamantineIngot"))
                GameRegistry.addRecipe(ShapedOreRecipe(armorFeet, "___", "i_i", "i_i", 'i', "adamantineIngot"))
            }

            if (enabledTools!!) {
                GameRegistry.addRecipe(ShapedOreRecipe(axe, "ii_", "is_", "is_", 'i', ELEMENT_ORE_DIC_INGOT, 's', "stickWood"))
                GameRegistry.addRecipe(ShapedOreRecipe(bow, "_it", "s_t", "_it", 'i', ELEMENT_ORE_DIC_INGOT, 's', "stickWood", 't', "string"))
                GameRegistry.addRecipe(ShapedOreRecipe(hoe, "ii_", "_s_", "_s_", 'i', ELEMENT_ORE_DIC_INGOT, 's', "stickWood"))
                GameRegistry.addRecipe(ShapedOreRecipe(pickaxe, "iii", "_s_", "_s_", 'i', ELEMENT_ORE_DIC_INGOT, 's', "stickWood"))
                GameRegistry.addRecipe(ShapedOreRecipe(shears, "___", "i__", "_i_", 'i', ELEMENT_ORE_DIC_INGOT))
                GameRegistry.addRecipe(ShapedOreRecipe(shovel, "_i_", "_s_", "_s_", 'i', ELEMENT_ORE_DIC_INGOT, 's', "stickWood"))
                GameRegistry.addRecipe(ShapedOreRecipe(sword, "_i_", "_i_", "_s_", 'i', ELEMENT_ORE_DIC_INGOT, 's', "stickWood"))
            }
        }
    }
}

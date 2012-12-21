/*
 * This file is part of BukkitBridge.
 *
 * Copyright (c) 2012, VanillaDev <http://www.spout.org/>
 * BukkitBridge is licensed under the GNU General Public License.
 *
 * BukkitBridge is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * BukkitBridge is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.spout.bridge;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;

import org.spout.api.geo.discrete.Point;
import org.spout.api.geo.discrete.Transform;
import org.spout.api.inventory.ItemStack;
import org.spout.api.material.Material;
import org.spout.api.material.block.BlockFace;
import org.spout.api.material.block.BlockSnapshot;
import org.spout.api.math.Vector3;

import org.spout.bridge.bukkit.BridgeServer;
import org.spout.bridge.bukkit.BridgeWorld;
import org.spout.bridge.bukkit.inventory.BridgeItemStack;

import org.spout.vanilla.material.VanillaMaterial;
import org.spout.vanilla.material.VanillaMaterials;
import org.spout.vanilla.material.enchantment.ArmorEnchantment;
import org.spout.vanilla.material.enchantment.BowEnchantment;
import org.spout.vanilla.material.enchantment.Enchantments;
import org.spout.vanilla.material.enchantment.SwordEnchantment;
import org.spout.vanilla.material.enchantment.ToolEnchantment;

/**
 * Various utilities for converting Bukkit --> Spout objects and vice versa
 */
public class BukkitUtil {
	private BukkitUtil() {
	}
	
	private static BridgeServer getServer() {
		return (BridgeServer) Bukkit.getServer();
	}

	/**
	 * Creates a Bukkit Location from a SpoutAPI Transform
	 *
	 * @param transform The SpoutAPI Transform to convert to a Location
	 * @return A Location with the same values as given Transform
	 */
	public static Location fromTransform(Transform transform) {
		Location loc = fromPoint(transform.getPosition());
		loc.setPitch(transform.getRotation().getPitch());
		loc.setYaw(transform.getRotation().getYaw());
		return loc;
	}

	/**
	 * Creates a Bukkit block from a SpoutAPI block
	 *
	 * @param block The SpoutAPI block to convert to a Bukkit block
	 * @return The corresponding Bukkit block
	 */
	public static Block fromBlock(org.spout.api.geo.cuboid.Block block) {
		BridgeWorld world = getServer().getWorld(block.getWorld().getUID());
		return world.getBlockAt(block.getX(), block.getY(), block.getZ());
	}
	
	/**
	 * Creates a SpoutAPI block from a Bukkit block
	 *
	 * @param point The Bukkit block to convert to a SpoutAPI block
	 * @return The corresponding SpoutAPI block
	 */
	public static org.spout.api.geo.cuboid.Block toBlock(Block block) {
		Point point = toPoint(block.getLocation());
		return point.getWorld().getBlock(point);
	}

	/**
	 * Creates a Bukkit Location from a SpoutAPI Point
	 *
	 * @param point The SpoutAPI Point to convert to a Location
	 * @return A Location with the same values as given Point
	 */
	public static Location fromPoint(Point point) {
		String name = point.getWorld().getName();
		World world = Bukkit.getWorld(name);
		if (world == null) {
			throw new IllegalArgumentException("World '" + name + "' not found.");
		}
		return new Location(world, point.getX(), point.getY(), point.getZ());
	}

	/**
	 * Creates a SpoutAPI Point from a Bukkit Location
	 *
	 * @param loc The Bukkit Location to convert to a Point
	 * @return A SpoutAPI Point with the same values as given Location
	 */
	public static Point toPoint(Location loc) {
		return new Point(toVector3(loc), ((BridgeWorld) loc.getWorld()).getHandle());
	}

	/**
	 * Creates a SpoutAPI Vector3 from a Bukkit Location
	 *
	 * @param loc The Bukkit Location to convert to a Vector3
	 * @return A SpoutAPI Vector3 with the same values as given Bukkit Location
	 */
	public static Vector3 toVector3(Location loc) {
		return new Vector3(loc.getX(), loc.getY(), loc.getZ());
	}

	/**
	 * Creates a BridgeItemStack from a SpoutAPI ItemStack
	 *
	 * @param item The SpoutAPI ItemStack to convert to a BridgeItemStack
	 * @return The BridgeItemStack with the same values and type as given Spout ItemStack
	 */
	public static BridgeItemStack fromItemStack(ItemStack item) {
		if (item == null){
			return null;
		}
		Material material = item.getMaterial();
		org.bukkit.Material mat = null;
		if (material instanceof VanillaMaterial) {
			mat = org.bukkit.Material.getMaterial(((VanillaMaterial) material).getMinecraftId());
		} else {
			throw new IllegalArgumentException("Tried to create a BridgeItemStack with a non-vanilla Material.");
		}

		int amount = item.getAmount();
		byte data = (byte) item.getData();
		return new BridgeItemStack(mat, amount, data, data);
	}

	/**
	 * Creates a SpoutAPI ItemStack from a Bukkit ItemStack
	 *
	 * @param item The Bukkit ItemStack to convert to a SpoutAPI ItemStack
	 * @return The converted SpoutAPI ItemStack
	 */
	public static ItemStack toItemStack(org.bukkit.inventory.ItemStack item) {
		if (item == null){
			return null;
		}
		Material mat = VanillaMaterials.getMaterial((short) item.getTypeId());
		int data = item.getDurability();
		int amount = item.getAmount();
		return new ItemStack(mat, data, amount);
	}

	public static org.bukkit.block.BlockFace toBukkitBlockFace(BlockFace face) {
		switch(face) {
			case TOP: return org.bukkit.block.BlockFace.UP;
			case BOTTOM: return org.bukkit.block.BlockFace.DOWN;
			case NORTH: return org.bukkit.block.BlockFace.NORTH;
			case SOUTH: return org.bukkit.block.BlockFace.SOUTH;
			case EAST: return org.bukkit.block.BlockFace.EAST;
			case WEST: return org.bukkit.block.BlockFace.WEST;
			case THIS: return org.bukkit.block.BlockFace.SELF;
			default: throw new IllegalArgumentException("Unknown face type");
		}
	}

	public static VanillaMaterial getVanillaMaterial(org.bukkit.inventory.ItemStack is) {
		return (VanillaMaterial) VanillaMaterials.getMaterial((short) is.getTypeId());
	}

	public static org.spout.vanilla.material.enchantment.Enchantment getVanillaEnchantment(Enchantment enchant) {
		return Enchantments.getById(enchant.getId());
	}

	public static EnchantmentTarget getEnchantmentTargetFromEnchantment(org.spout.vanilla.material.enchantment.Enchantment enchant) {
		if (enchant instanceof ArmorEnchantment) {
			return EnchantmentTarget.ARMOR;
		} else if (enchant instanceof SwordEnchantment) {
			return EnchantmentTarget.WEAPON;
		} else if (enchant instanceof BowEnchantment) {
			return EnchantmentTarget.BOW;
		} else if (enchant instanceof ToolEnchantment) {
			return EnchantmentTarget.TOOL;
		}

		return EnchantmentTarget.ALL;
	}

	public static BlockState getBlockState(BlockSnapshot blockSnapshot) {
		Block block = fromBlock(blockSnapshot.getBlock());
		int typeID = blockSnapshot.getMaterial().getId();
		BlockState blockState = block.getState();
		blockState.setTypeId(typeID);
		return blockState;
	}

	public static BlockSnapshot getBlockSnapshot(BlockState blockState) {
		org.spout.api.geo.cuboid.Block block = toBlock(blockState.getBlock());
		return new BlockSnapshot(block);
	}
}

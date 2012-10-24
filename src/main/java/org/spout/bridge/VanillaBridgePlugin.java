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

import org.spout.api.Server;
import org.spout.api.Spout;
import org.spout.api.plugin.CommonPlugin;

import org.spout.bridge.bukkit.BridgeServer;
import org.spout.bridge.listener.EntityListener;
import org.spout.bridge.listener.PlayerListener;
import org.spout.bridge.listener.WorldListener;

import org.bukkit.plugin.PluginLoadOrder;

/**
 * Bridge redirects Bukkit method calls to the Spout API, allowing Bukkit plugins to run on Spout
 */
public class VanillaBridgePlugin extends CommonPlugin {
	private static VanillaBridgePlugin instance;
	private WorldListener worldListener;
	private PlayerListener playerListener;
	private EntityListener entityListener;

	public VanillaBridgePlugin() {
		instance = this;
	}

	@Override
	public void onEnable() {
		worldListener = new WorldListener(this);
		playerListener = new PlayerListener(this);
		entityListener = new EntityListener(this);
		BridgeServer server = new BridgeServer((Server)Spout.getEngine(), this);
		server.enablePlugins(PluginLoadOrder.POSTWORLD);
		getLogger().info("Bukkit Bridge Enabling, Version: (" + server.getVersion() + " | Bukkit: " + server.getBukkitVersion() + ")");
	}

	@Override
	public void onDisable() {
	}

	public WorldListener getWorldListener() {
		return worldListener;
	}
	
	public PlayerListener getPlayerListener() {
		return playerListener;
	}

	public EntityListener getEntityListener() {
		return entityListener;
	}

	public static VanillaBridgePlugin getInstance() {
		return instance;
	}
}

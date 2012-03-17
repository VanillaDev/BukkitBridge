/*
 * This file is part of BukkitBridge (http://www.spout.org/).
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
package org.spout.bukkit.block;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Jukebox;

public class BridgeJukebox extends BridgeBlockState implements Jukebox {
	public BridgeJukebox(Block block) {
		super(block);
	}

	@Override
	public Material getPlaying() {
		return null;  //TODO: Adjust for usage with Spout!
	}

	@Override
	public void setPlaying(Material material) {
		//TODO: Adjust for usage with Spout!
	}

	@Override
	public boolean isPlaying() {
		return false;  //TODO: Adjust for usage with Spout!
	}

	@Override
	public boolean eject() {
		return false;  //TODO: Adjust for usage with Spout!
	}
}

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
package org.spout.bridge.bukkit.block;

import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.block.Block;
import org.bukkit.block.NoteBlock;

public class BridgeNoteblock extends BridgeBlockState implements NoteBlock {
	private final org.spout.vanilla.component.substance.material.NoteBlock noteblock;
	public BridgeNoteblock(Block b) {
		super(b);
		this.noteblock = (org.spout.vanilla.component.substance.material.NoteBlock) getHandle();
	}

	@Override
	public Note getNote() {
		throw new UnsupportedOperationException();
	}

	@Override
	public byte getRawNote() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setNote(Note paramNote) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setRawNote(byte paramByte) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean play() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean play(byte paramByte1, byte paramByte2) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean play(Instrument paramInstrument, Note paramNote) {
		throw new UnsupportedOperationException();
	}
}

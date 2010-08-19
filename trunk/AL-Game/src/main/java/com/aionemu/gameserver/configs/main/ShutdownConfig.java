/*
 * This file is part of aion-unique <aion-unique.org>.
 *
 *  aion-unique is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  aion-unique is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with aion-unique.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.aionemu.gameserver.configs.main;

import com.aionemu.commons.configuration.Property;

/**
 * @author lord_rex
 * 
 */
public class ShutdownConfig
{
	@Property(key = "gameserver.shutdown.mode", defaultValue = "1")
	public static int		HOOK_MODE;

	@Property(key = "gameserver.shutdown.delay", defaultValue = "3")
	public static int		HOOK_DELAY;

	@Property(key = "gameserver.shutdown.interval", defaultValue = "1")
	public static int		ANNOUNCE_INTERVAL;

	@Property(key = "gameserver.shutdown.safereboot", defaultValue = "true")
	public static boolean	SAFE_REBOOT;
}

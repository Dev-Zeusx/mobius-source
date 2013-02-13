/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package zones;

import lineage2.gameserver.listener.zone.OnZoneEnterLeaveListener;
import lineage2.gameserver.model.Creature;
import lineage2.gameserver.model.Player;
import lineage2.gameserver.model.Zone;
import lineage2.gameserver.scripts.ScriptFile;
import lineage2.gameserver.utils.Location;
import lineage2.gameserver.utils.ReflectionUtils;

public class OrbisTeleport implements ScriptFile
{
	private static ZoneListener _zoneListener;
	private static final String[] zones =
	{
		"[orbis_to_1st]",
		"[orbis_to_enter]",
		"[orbis_from_1st_to_2nd]",
		"[orbis_from_2nd_to_1st]",
		"[orbis_from_2nd_to_3rd]",
		"[orbis_from_3rd_to_2nd]"
	};
	
	@Override
	public void onLoad()
	{
		_zoneListener = new ZoneListener();
		
		for (String s : zones)
		{
			Zone zone = ReflectionUtils.getZone(s);
			zone.addListener(_zoneListener);
		}
	}
	
	@Override
	public void onReload()
	{
	}
	
	@Override
	public void onShutdown()
	{
	}
	
	public class ZoneListener implements OnZoneEnterLeaveListener
	{
		final Location OrbisTo1Point = new Location(213983, 53250, -8176);
		final Location OrbisToEnterPoint = new Location(197784, 90584, -325);
		final Location OrbisFrom1To2Point = new Location(213799, 53253, -14432);
		final Location OrbisFrom2To1Point = new Location(215056, 50467, -8416);
		final Location OrbisFrom2To3Point = new Location(211641, 115547, -12736);
		final Location OrbisFrom3To2Point = new Location(211137, 50501, -14624);
		
		public ZoneListener()
		{
		}
		
		@Override
		public void onZoneEnter(Zone zone, Creature cha)
		{
			Player player = cha.getPlayer();
			if (player == null)
			{
				return;
			}
			if (zone.isActive())
			{
				if (zone.getName().equalsIgnoreCase("[orbis_to_1st]"))
				{
					player.teleToLocation(OrbisTo1Point);
				}
				else if (zone.getName().equalsIgnoreCase("[orbis_to_enter]"))
				{
					player.teleToLocation(OrbisToEnterPoint);
				}
				else if (zone.getName().equalsIgnoreCase("[orbis_from_1st_to_2nd]"))
				{
					player.teleToLocation(OrbisFrom1To2Point);
				}
				else if (zone.getName().equalsIgnoreCase("[orbis_from_2nd_to_1st]"))
				{
					player.teleToLocation(OrbisFrom2To1Point);
				}
				else if (zone.getName().equalsIgnoreCase("[orbis_from_2nd_to_3rd]"))
				{
					player.teleToLocation(OrbisFrom2To3Point);
				}
				else if (zone.getName().equalsIgnoreCase("[orbis_from_3rd_to_2nd]"))
				{
					player.teleToLocation(OrbisFrom3To2Point);
				}
			}
		}
		
		@Override
		public void onZoneLeave(Zone zone, Creature cha)
		{
		}
	}
}
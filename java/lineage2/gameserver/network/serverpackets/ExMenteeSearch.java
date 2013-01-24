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
package lineage2.gameserver.network.serverpackets;

import java.util.ArrayList;

import lineage2.gameserver.model.Player;
import lineage2.gameserver.model.World;

public class ExMenteeSearch extends L2GameServerPacket
{
	ArrayList<Player> mentees;
	int page, playersInPage;
	
	public ExMenteeSearch(Player activeChar, int _page, int minLevel, int maxLevel)
	{
		mentees = new ArrayList<>();
		page = _page;
		playersInPage = 64;
		for (Player player : World.getAroundPlayers(activeChar))
		{
			if ((player.getLevel() >= minLevel) && (player.getLevel() <= maxLevel))
			{
				mentees.add(player);
			}
		}
	}
	
	@Override
	protected void writeImpl()
	{
		writeEx(0x122);
		writeD(page);
		if (!mentees.isEmpty())
		{
			writeD(mentees.size());
			writeD(mentees.size() % playersInPage);
			int i = 1;
			for (Player player : mentees)
			{
				if ((i <= (playersInPage * page)) && (i > (playersInPage * (page - 1))))
				{
					writeS(player.getName());
					writeD(player.getClassId().getId());
					writeD(player.getLevel());
				}
			}
		}
		else
		{
			writeD(0x00);
			writeD(0x00);
		}
	}
	
	@Override
	public String getType()
	{
		return null;
	}
}

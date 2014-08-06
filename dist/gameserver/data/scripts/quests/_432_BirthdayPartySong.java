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
package quests;

import lineage2.gameserver.model.instances.NpcInstance;
import lineage2.gameserver.model.quest.Quest;
import lineage2.gameserver.model.quest.QuestState;
import lineage2.gameserver.scripts.ScriptFile;

public class _432_BirthdayPartySong extends Quest implements ScriptFile
{
	private static final int MELODY_MAESTRO_OCTAVIA = 31043;
	private static final int ROUGH_HEWN_ROCK_GOLEMS = 21103;
	private static final int RED_CRYSTALS = 7541;
	private static final int BIRTHDAY_ECHO_CRYSTAL = 7061;
	
	@Override
	public void onLoad()
	{
	}
	
	@Override
	public void onReload()
	{
	}
	
	@Override
	public void onShutdown()
	{
	}
	
	public _432_BirthdayPartySong()
	{
		super(false);
		addStartNpc(MELODY_MAESTRO_OCTAVIA);
		addKillId(ROUGH_HEWN_ROCK_GOLEMS);
		addQuestItem(RED_CRYSTALS);
	}
	
	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{
		String htmltext = event;
		
		if (event.equalsIgnoreCase("muzyko_q0432_0104.htm"))
		{
			st.setState(STARTED);
			st.setCond(1);
			st.playSound(SOUND_ACCEPT);
		}
		else if (event.equalsIgnoreCase("muzyko_q0432_0201.htm"))
		{
			if (st.getQuestItemsCount(RED_CRYSTALS) == 50)
			{
				st.takeItems(RED_CRYSTALS, -1);
				st.giveItems(BIRTHDAY_ECHO_CRYSTAL, 25);
				st.playSound(SOUND_FINISH);
				st.exitCurrentQuest(true);
			}
			else
			{
				htmltext = "muzyko_q0432_0202.htm";
			}
		}
		
		return htmltext;
	}
	
	@Override
	public String onTalk(NpcInstance npc, QuestState st)
	{
		String htmltext = "noquest";
		int condition = st.getCond();
		int npcId = npc.getNpcId();
		
		if (npcId == MELODY_MAESTRO_OCTAVIA)
		{
			if (condition == 0)
			{
				if (st.getPlayer().getLevel() >= 31)
				{
					htmltext = "muzyko_q0432_0101.htm";
				}
				else
				{
					htmltext = "muzyko_q0432_0103.htm";
					st.exitCurrentQuest(true);
				}
			}
			else if (condition == 1)
			{
				htmltext = "muzyko_q0432_0106.htm";
			}
			else if ((condition == 2) && (st.getQuestItemsCount(RED_CRYSTALS) == 50))
			{
				htmltext = "muzyko_q0432_0105.htm";
			}
		}
		
		return htmltext;
	}
	
	@Override
	public String onKill(NpcInstance npc, QuestState st)
	{
		if (st.getState() != STARTED)
		{
			return null;
		}
		
		int npcId = npc.getNpcId();
		
		if (npcId == ROUGH_HEWN_ROCK_GOLEMS)
		{
			if ((st.getCond() == 1) && (st.getQuestItemsCount(RED_CRYSTALS) < 50))
			{
				st.giveItems(RED_CRYSTALS, 1);
				
				if (st.getQuestItemsCount(RED_CRYSTALS) == 50)
				{
					st.playSound(SOUND_MIDDLE);
					st.setCond(2);
				}
				else
				{
					st.playSound(SOUND_ITEMGET);
				}
			}
		}
		
		return null;
	}
}

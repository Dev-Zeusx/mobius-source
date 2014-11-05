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

import lineage2.commons.util.Rnd;
import lineage2.gameserver.model.instances.NpcInstance;
import lineage2.gameserver.model.quest.Quest;
import lineage2.gameserver.model.quest.QuestState;
import lineage2.gameserver.scripts.ScriptFile;

public class Q00643_RiseAndFallOfTheElrokiTribe extends Quest implements ScriptFile
{
	private static final int DROP_CHANCE = 75;
	private static final int BONES_OF_A_PLAINS_DINOSAUR = 8776;
	private static final int[] PLAIN_DINOSAURS =
	{
		22208,
		22209,
		22210,
		22211,
		22212,
		22213,
		22221,
		22222,
		22226,
		22227,
		22742,
		22743,
		22744,
		22745
	};
	private static final int[] REWARDS =
	{
		8712,
		8713,
		8714,
		8715,
		8716,
		8717,
		8718,
		8719,
		8720,
		8721,
		8722
	};
	
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
	
	public Q00643_RiseAndFallOfTheElrokiTribe()
	{
		super(true);
		addStartNpc(32106);
		addTalkId(32117);
		
		for (int npc : PLAIN_DINOSAURS)
		{
			addKillId(npc);
		}
		
		addQuestItem(BONES_OF_A_PLAINS_DINOSAUR);
	}
	
	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{
		String htmltext = event;
		long count = st.getQuestItemsCount(BONES_OF_A_PLAINS_DINOSAUR);
		
		if (event.equalsIgnoreCase("singsing_q0643_05.htm"))
		{
			st.setCond(1);
			st.setState(STARTED);
			st.playSound(SOUND_ACCEPT);
		}
		else if (event.equalsIgnoreCase("shaman_caracawe_q0643_06.htm"))
		{
			if (count >= 300)
			{
				st.takeItems(BONES_OF_A_PLAINS_DINOSAUR, 300);
				st.giveItems(REWARDS[Rnd.get(REWARDS.length)], 5, false);
			}
			else
			{
				htmltext = "shaman_caracawe_q0643_05.htm";
			}
		}
		else if (event.equalsIgnoreCase("None"))
		{
			htmltext = null;
		}
		else if (event.equalsIgnoreCase("Quit"))
		{
			htmltext = null;
			st.playSound(SOUND_FINISH);
			st.exitCurrentQuest(true);
		}
		
		return htmltext;
	}
	
	@Override
	public String onTalk(NpcInstance npc, QuestState st)
	{
		String htmltext = "noquest";
		int npcId = npc.getId();
		
		if (st.getCond() == 0)
		{
			if (st.getPlayer().getLevel() >= 75)
			{
				htmltext = "singsing_q0643_01.htm";
			}
			else
			{
				htmltext = "singsing_q0643_04.htm";
				st.exitCurrentQuest(true);
			}
		}
		else if (st.getState() == STARTED)
		{
			if (npcId == 32106)
			{
				long count = st.getQuestItemsCount(BONES_OF_A_PLAINS_DINOSAUR);
				
				if (count == 0)
				{
					htmltext = "singsing_q0643_08.htm";
				}
				else
				{
					htmltext = "singsing_q0643_08.htm";
					st.takeItems(BONES_OF_A_PLAINS_DINOSAUR, -1);
					st.giveItems(ADENA_ID, count * 1374, false);
				}
			}
			else if (npcId == 32117)
			{
				htmltext = "shaman_caracawe_q0643_02.htm";
			}
		}
		
		return htmltext;
	}
	
	@Override
	public String onKill(NpcInstance npc, QuestState st)
	{
		if (st.getCond() == 1)
		{
			st.rollAndGive(BONES_OF_A_PLAINS_DINOSAUR, 1, DROP_CHANCE);
		}
		
		return null;
	}
}
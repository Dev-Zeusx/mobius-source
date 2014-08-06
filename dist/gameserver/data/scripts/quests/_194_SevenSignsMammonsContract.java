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

import lineage2.gameserver.cache.Msg;
import lineage2.gameserver.model.Effect;
import lineage2.gameserver.model.Player;
import lineage2.gameserver.model.instances.NpcInstance;
import lineage2.gameserver.model.quest.Quest;
import lineage2.gameserver.model.quest.QuestState;
import lineage2.gameserver.network.serverpackets.ExStartScenePlayer;
import lineage2.gameserver.scripts.ScriptFile;
import lineage2.gameserver.tables.SkillTable;

public class _194_SevenSignsMammonsContract extends Quest implements ScriptFile
{
	private static final int Colin = 32571;
	private static final int SirGustavAthebaldt = 30760;
	private static final int Frog = 32572;
	private static final int Tess = 32573;
	private static final int Kuta = 32574;
	private static final int ClaudiaAthebaldt = 31001;
	private static final int AthebaldtsIntroduction = 13818;
	private static final int FrogKingsBead = 13820;
	private static final int GrandmaTessCandyPouch = 13821;
	private static final int NativesGlove = 13819;
	
	public _194_SevenSignsMammonsContract()
	{
		super(false);
		addStartNpc(SirGustavAthebaldt);
		addTalkId(Colin, SirGustavAthebaldt, Frog, Tess, Kuta, ClaudiaAthebaldt);
		addQuestItem(AthebaldtsIntroduction, FrogKingsBead, GrandmaTessCandyPouch, NativesGlove);
	}
	
	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{
		Player player = st.getPlayer();
		String htmltext = event;
		
		if (event.equalsIgnoreCase("sirgustavathebaldt_q194_2.htm"))
		{
			st.setCond(1);
			st.setState(STARTED);
			st.playSound(SOUND_ACCEPT);
		}
		else if (event.equalsIgnoreCase("sirgustavathebaldt_q194_2c.htm"))
		{
			st.setCond(2);
			st.playSound(SOUND_MIDDLE);
			player.showQuestMovie(ExStartScenePlayer.SCENE_SSQ_CONTRACT_OF_MAMMON);
			return null;
		}
		else if (event.equalsIgnoreCase("sirgustavathebaldt_q194_3.htm"))
		{
			st.setCond(3);
			st.playSound(SOUND_MIDDLE);
			st.giveItems(AthebaldtsIntroduction, 1);
		}
		else if (event.equalsIgnoreCase("colin_q194_3.htm"))
		{
			st.takeItems(AthebaldtsIntroduction, -1);
			st.setCond(4);
		}
		else if (event.equalsIgnoreCase("colin_q194_3a.htm"))
		{
			if ((player.getTransformation() != 0) || player.isMounted())
			{
				player.sendPacket(Msg.YOU_ALREADY_POLYMORPHED_AND_CANNOT_POLYMORPH_AGAIN);
				return null;
			}
			
			negateSpeedBuffs(player);
			SkillTable.getInstance().getInfo(6201, 1).getEffects(npc, player, false, false);
		}
		else if (event.equalsIgnoreCase("frog_q194_2.htm"))
		{
			st.setCond(5);
			st.playSound(SOUND_MIDDLE);
			st.giveItems(FrogKingsBead, 1);
		}
		else if (event.equalsIgnoreCase("colin_q194_5.htm"))
		{
			st.setCond(6);
			st.takeItems(FrogKingsBead, -1);
			st.playSound(SOUND_MIDDLE);
		}
		else if (event.equalsIgnoreCase("colin_q194_6.htm"))
		{
			if ((player.getTransformation() != 0) || player.isMounted())
			{
				player.sendPacket(Msg.YOU_ALREADY_POLYMORPHED_AND_CANNOT_POLYMORPH_AGAIN);
				return null;
			}
			
			st.setCond(7);
			st.playSound(SOUND_MIDDLE);
			negateSpeedBuffs(player);
			SkillTable.getInstance().getInfo(6202, 1).getEffects(player, player, false, false);
		}
		else if (event.equalsIgnoreCase("tess_q194_2.htm"))
		{
			st.setCond(8);
			st.giveItems(GrandmaTessCandyPouch, 1);
			st.playSound(SOUND_MIDDLE);
		}
		else if (event.equalsIgnoreCase("colin_q194_8.htm"))
		{
			st.setCond(9);
			st.takeItems(GrandmaTessCandyPouch, -1);
			st.playSound(SOUND_MIDDLE);
		}
		else if (event.equalsIgnoreCase("colin_q194_9.htm"))
		{
			if ((player.getTransformation() != 0) || player.isMounted())
			{
				player.sendPacket(Msg.YOU_ALREADY_POLYMORPHED_AND_CANNOT_POLYMORPH_AGAIN);
				return null;
			}
			
			st.setCond(10);
			st.playSound(SOUND_MIDDLE);
			negateSpeedBuffs(player);
			SkillTable.getInstance().getInfo(6203, 1).getEffects(player, player, false, false);
		}
		else if (event.equalsIgnoreCase("kuta_q194_2.htm"))
		{
			st.setCond(11);
			st.giveItems(NativesGlove, 1);
			st.playSound(SOUND_MIDDLE);
		}
		else if (event.equalsIgnoreCase("colin_q194_10a.htm"))
		{
			st.setCond(12);
			st.takeItems(NativesGlove, -1);
			st.playSound(SOUND_MIDDLE);
		}
		else if (event.equalsIgnoreCase("claudiaathebaldt_q194_2.htm"))
		{
			if (player.getBaseClassId() == player.getActiveClassId())
			{
				st.addExpAndSp(10000000, 2500000);
				st.setState(COMPLETED);
				st.exitCurrentQuest(false);
				st.playSound(SOUND_FINISH);
			}
			else
			{
				return "subclass_forbidden.htm";
			}
		}
		else if (event.equalsIgnoreCase("colin_q194_11a.htm"))
		{
			if ((player.getTransformation() != 0) || player.isMounted())
			{
				player.sendPacket(Msg.YOU_ALREADY_POLYMORPHED_AND_CANNOT_POLYMORPH_AGAIN);
				return null;
			}
			
			negateSpeedBuffs(player);
			SkillTable.getInstance().getInfo(6201, 1).getEffects(player, player, false, false);
		}
		else if (event.equalsIgnoreCase("colin_q194_12a.htm"))
		{
			if ((player.getTransformation() != 0) || player.isMounted())
			{
				player.sendPacket(Msg.YOU_ALREADY_POLYMORPHED_AND_CANNOT_POLYMORPH_AGAIN);
				return null;
			}
			
			negateSpeedBuffs(player);
			SkillTable.getInstance().getInfo(6202, 1).getEffects(player, player, false, false);
		}
		else if (event.equalsIgnoreCase("colin_q194_13a.htm"))
		{
			if ((player.getTransformation() != 0) || player.isMounted())
			{
				player.sendPacket(Msg.YOU_ALREADY_POLYMORPHED_AND_CANNOT_POLYMORPH_AGAIN);
				return null;
			}
			
			negateSpeedBuffs(player);
			SkillTable.getInstance().getInfo(6203, 1).getEffects(player, player, false, false);
		}
		else if (event.equalsIgnoreCase("colin_q194_0c.htm"))
		{
			negateTransformations(player);
		}
		
		return htmltext;
	}
	
	@Override
	public String onTalk(NpcInstance npc, QuestState st)
	{
		int npcId = npc.getNpcId();
		int cond = st.getCond();
		Player player = st.getPlayer();
		String htmltext = "noquest";
		
		if (player.getBaseClassId() != player.getActiveClassId())
		{
			return "subclass_forbidden.htm";
		}
		
		if (npcId == SirGustavAthebaldt)
		{
			QuestState qs = player.getQuestState(_193_SevenSignDyingMessage.class);
			
			if ((cond == 0) && (player.getLevel() >= 79) && (qs != null) && qs.isCompleted())
			{
				htmltext = "sirgustavathebaldt_q194_1.htm";
			}
			else if (cond == 1)
			{
				htmltext = "sirgustavathebaldt_q194_2b.htm";
			}
			else if (cond == 2)
			{
				htmltext = "sirgustavathebaldt_q194_2c.htm";
			}
			else if (cond == 3)
			{
				if (st.getQuestItemsCount(AthebaldtsIntroduction) < 1)
				{
					st.giveItems(AthebaldtsIntroduction, 1);
				}
				
				htmltext = "sirgustavathebaldt_q194_4.htm";
			}
			else
			{
				htmltext = "sirgustavathebaldt_q194_0.htm";
				st.exitCurrentQuest(true);
			}
		}
		else if (npcId == Colin)
		{
			if (cond == 3)
			{
				if (st.getQuestItemsCount(AthebaldtsIntroduction) > 0)
				{
					htmltext = "colin_q194_1.htm";
				}
				else
				{
					htmltext = "colin_q194_0b.htm";
				}
			}
			else if (cond == 5)
			{
				htmltext = "colin_q194_4.htm";
			}
			else if (cond == 6)
			{
				htmltext = "colin_q194_5.htm";
			}
			else if (cond == 8)
			{
				htmltext = "colin_q194_7.htm";
			}
			else if (cond == 9)
			{
				htmltext = "colin_q194_8.htm";
			}
			else if (cond == 11)
			{
				htmltext = "colin_q194_10.htm";
			}
			else if (cond == 12)
			{
				htmltext = "colin_q194_14.htm";
			}
			else if ((cond == 4) && (player.getTransformation() == 0))
			{
				htmltext = "colin_q194_11.htm";
			}
			else if ((cond == 7) && (player.getTransformation() == 0))
			{
				htmltext = "colin_q194_12.htm";
			}
			else if ((cond == 10) && (player.getTransformation() == 0))
			{
				htmltext = "colin_q194_13.htm";
			}
			else if (((cond == 4) || (cond == 7) || (cond == 10)) && (player.getTransformation() != 0))
			{
				htmltext = "colin_q194_0a.htm";
			}
		}
		else if (npcId == Frog)
		{
			if ((cond == 4) && (player.getTransformation() == 111))
			{
				htmltext = "frog_q194_1.htm";
			}
			else if ((cond == 5) && (player.getTransformation() == 111))
			{
				htmltext = "frog_q194_4.htm";
			}
			else
			{
				htmltext = "frog_q194_3.htm";
			}
		}
		else if (npcId == Tess)
		{
			if ((cond == 7) && (player.getTransformation() == 112))
			{
				htmltext = "tess_q194_1.htm";
			}
			else if ((cond == 8) && (player.getTransformation() == 112))
			{
				htmltext = "tess_q194_3.htm";
			}
			else
			{
				htmltext = "tess_q194_0.htm";
			}
		}
		else if (npcId == Kuta)
		{
			if ((cond == 10) && (player.getTransformation() == 101))
			{
				htmltext = "kuta_q194_1.htm";
			}
			else if ((cond == 11) && (player.getTransformation() == 101))
			{
				htmltext = "kuta_q194_3.htm";
			}
			else
			{
				htmltext = "kuta_q194_0.htm";
			}
		}
		else if (npcId == ClaudiaAthebaldt)
		{
			if (cond == 12)
			{
				htmltext = "claudiaathebaldt_q194_1.htm";
			}
			else
			{
				htmltext = "claudiaathebaldt_q194_0.htm";
			}
		}
		
		return htmltext;
	}
	
	private void negateSpeedBuffs(Player p)
	{
		for (Effect e : p.getEffectList().getAllEffects())
		{
			if (e.getStackType().contains("SpeedUp") && !e.isOffensive())
			{
				e.exit();
			}
		}
	}
	
	private void negateTransformations(Player p)
	{
		p.setTransformation(0);
	}
	
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
}

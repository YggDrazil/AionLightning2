/*
 * This file is part of aion-unique <aion-unique.org>.
 *
 * aion-unique is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * aion-unique is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with aion-unique.  If not, see <http://www.gnu.org/licenses/>.
 */
package quest.sanctum;

import java.util.Collections;

import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.templates.quest.QuestItems;
import com.aionemu.gameserver.network.aion.serverpackets.SM_DIALOG_WINDOW;
import com.aionemu.gameserver.questEngine.handlers.QuestHandler;
import com.aionemu.gameserver.questEngine.model.QuestEnv;
import com.aionemu.gameserver.questEngine.model.QuestState;
import com.aionemu.gameserver.questEngine.model.QuestStatus;
import com.aionemu.gameserver.services.ItemService;
import com.aionemu.gameserver.utils.PacketSendUtility;

/**
 * @author Rolandas
 * 
 */
public class _3969SexiestManAlive extends QuestHandler
{
	private final static int	questId	= 3969;

	public _3969SexiestManAlive()
	{
		super(questId);
	}

	@Override
	public void register()
	{
		qe.setNpcQuestData(798390).addOnQuestStart(questId); // Palentine
		qe.setNpcQuestData(798391).addOnTalkEvent(questId);  // Andu
		qe.setNpcQuestData(798390).addOnTalkEvent(questId);  // Palentine
	}
	
	@Override
	public boolean onDialogEvent(QuestEnv env)
	{
		final Player player = env.getPlayer();
		int targetId = 0;
		
		QuestState qs2 = player.getQuestStateList().getQuestState(3968);
		if(qs2 == null || qs2.getStatus() != QuestStatus.COMPLETE)
			return false;
		
		if(env.getVisibleObject() instanceof Npc)
			targetId = ((Npc) env.getVisibleObject()).getNpcId();
		QuestState qs = player.getQuestStateList().getQuestState(questId);
		
		if(targetId == 798390) // Palentine
		{
			if(qs == null || qs.getStatus() == QuestStatus.NONE)
			{
				if(env.getDialogId() == -1)
					return sendQuestDialog(player, env.getVisibleObject().getObjectId(), 1011);
				else if(env.getDialogId() == 1002)
				{
					if (ItemService.addItems(player, Collections.singletonList(new QuestItems(182206126, 1))))
					{
						return defaultQuestStartDialog(env);
					}
					return true;
				}
				else
					return defaultQuestStartDialog(env);
			}
		}
		
		if(qs == null)
			return false;
		
		int var = qs.getQuestVarById(0);
		
		if(targetId == 798391) // Andu
		{
			if(qs.getStatus() == QuestStatus.START && var == 0)
			{
				if(env.getDialogId() == -1)
					return sendQuestDialog(player, env.getVisibleObject().getObjectId(), 1352);
				else if(env.getDialogId() == 10000)
				{
					if(player.getInventory().getItemCountByItemId(182206126) > 0)
					{
						player.getInventory().removeFromBagByItemId(182206126, 1);
						qs.setQuestVar(++var);
						qs.setStatus(QuestStatus.REWARD);
						updateQuestStatus(player, qs);
						PacketSendUtility
							.sendPacket(player, new SM_DIALOG_WINDOW(env.getVisibleObject().getObjectId(), 10));
					}
					return true;
				}
				else
					return defaultQuestStartDialog(env);
			}
		}
		else if(targetId == 798390)  // Palentine
		{
			if(qs.getStatus() == QuestStatus.REWARD)
			{
				if(env.getDialogId() == -1)
					return sendQuestDialog(player, env.getVisibleObject().getObjectId(), 2375);
				return defaultQuestEndDialog(env);
			}
		}
		return false;
	}
}
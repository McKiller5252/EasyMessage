package com.killer5252.easymessage.listeners;

import com.killer5252.easymessage.EasyMessage;
import com.killer5252.easymessage.util.ChatMessageComposer;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.ClickEvent.Action;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.*;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FriendAddOrMessageListener {
	
	@SubscribeEvent
	public void onPlayerInteract(EntityInteractEvent event) {
		if (event.target instanceof EntityPlayer) {
			EntityPlayer Player = (EntityPlayer) event.target;
			String name = Player.getName();
			if (Minecraft.getMinecraft().thePlayer.getHeldItem() == null && Minecraft.getMinecraft().thePlayer.isSneaking()) {
				new ChatMessageComposer(EasyMessage.prefix)
				        .appendMessage(new ChatMessageComposer("Message", EnumChatFormatting.GREEN).makeClickable(ClickEvent.Action.SUGGEST_COMMAND, "/msg " + name + " ", new ChatMessageComposer("Click to message " + name + ".", EnumChatFormatting.GRAY))
						.appendMessage(new ChatMessageComposer(" / ", EnumChatFormatting.WHITE).appendMessage(new ChatMessageComposer("Friend", EnumChatFormatting.RED).makeClickable(ClickEvent.Action.RUN_COMMAND, "/friend " + name, new ChatMessageComposer("Click here to add " + name + " as a friend.", EnumChatFormatting.GRAY))))
						.appendMessage(new ChatMessageComposer(" / ", EnumChatFormatting.WHITE).appendMessage(new ChatMessageComposer("Party", EnumChatFormatting.AQUA).makeClickable(ClickEvent.Action.RUN_COMMAND, "/party " + name, new ChatMessageComposer("Click here to party with" + name, EnumChatFormatting.GRAY))))
						.appendMessage(new ChatMessageComposer(" - " + name, EnumChatFormatting.GRAY)))
				        .send(false);
			}
		}
	}

}


//ChatStyle chatStyle = new ChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/f add " + name));
//				          chatStyle = chatStyle.setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new ChatComponentText(EnumChatFormatting.RED + "Click to " + EnumChatFormatting.YELLOW + "add")));
//				          event.entityPlayer.addChatMessage(new ChatComponentText(EasyMessage.prefix + EnumChatFormatting.BLUE + "Click here to add " + EnumChatFormatting.YELLOW + name).setChatStyle(chatStyle));
				          
//			    ChatStyle chatStyle1 = new ChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/tell " + name));
//					      chatStyle1 = chatStyle1.setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText(EnumChatFormatting.RED + "Click to " + EnumChatFormatting.YELLOW + "message")));
//					      event.entityPlayer.addChatMessage(new ChatComponentText(EasyMessage.prefix + EnumChatFormatting.BLUE + "Click here to send a message to " + EnumChatFormatting.YELLOW + name).setChatStyle(chatStyle1));
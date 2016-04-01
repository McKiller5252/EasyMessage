package com.killer5252.easymessage.listeners;

import com.killer5252.easymessage.EasyMessage;
import com.killer5252.easymessage.util.EasyMessageUpdater;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

public class ConnectionHandler {

	private static boolean logged;
	private boolean handling;
	private long ticks;
	private int counter;
	private long ticksM = 100;

	private String ip = "hypixel.net";

	@SubscribeEvent
	public void onLogin(FMLNetworkEvent.ClientConnectedToServerEvent e) {
		if (e.isLocal) {
			return;
		}
		String host = FMLClientHandler.instance().getClient().getCurrentServerData().serverIP;
		if (!logged && host.toLowerCase().endsWith(ip.toLowerCase())) {
			logged = true;
			ticks = 50;
			counter = 0;
			EasyMessage.instance().getLogger().info("Joined Hypixel");
		} else if (logged && !host.toLowerCase().endsWith(ip.toLowerCase())) {
			logged = false;
			handling = true;
			EasyMessage.instance().getLogger().info("Disconnected from Hypixel");
		}
	}

	@SubscribeEvent
	public void onDisconnect(FMLNetworkEvent.ClientDisconnectionFromServerEvent e) {
		if (logged) {
			logged = false;
			handling = true;
			EasyMessage.instance().getLogger().info("Disconnected from Hypixel");
		}
	}
	
	@SubscribeEvent
	public void RenderWorld(RenderWorldLastEvent e) {
		if (logged && !handling) {
			handling = true;
			program();
		}
	}
	
	@SubscribeEvent
	public void onTick(ClientTickEvent e) {
		if (handling) {
			if (ticks > 0) {
				ticks--;
			} else {
				program();
			}
		}
	}
	
	private void program() {
		if (!logged) {
			return;
		}
		if (counter <= 1) {
			ticksM--;
			if (ticksM <= 0) {
				ticksM = 100;
				switch (counter++) {
				case 0:
					addChatMessage(EasyMessage.prefix + EnumChatFormatting.GREEN + "Checking updates...");
					addChatMessage(EasyMessageUpdater.isUpdateAvailable() ? EasyMessage.prefix + EnumChatFormatting.RED + "A new version found: " + EnumChatFormatting.GREEN + EasyMessageUpdater.getVersionString() + EnumChatFormatting.RED + "!" : EasyMessage.prefix + EnumChatFormatting.GREEN + "You're running the latest version.");
			        break;
				}
			}
		}
	}
	
	private void addChatMessage(String message) {
		Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message));
	}
	
	public static boolean isLogged() {
		return logged;
	}
}

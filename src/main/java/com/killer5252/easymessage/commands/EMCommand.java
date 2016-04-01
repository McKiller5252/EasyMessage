package com.killer5252.easymessage.commands;

import com.killer5252.easymessage.EasyMessage;
import com.killer5252.easymessage.util.EasyMessageUpdater;

import net.minecraft.client.*;
import net.minecraft.command.*;
import net.minecraft.util.*;


public class EMCommand extends CommandBase {

	public String getCommandName() {
		return "em";
	}

	public int getRequiredPermissionLevel() {
		return 0;
	}

	public boolean canSenderUseCommand(ICommandSender sender) {
		return true;
	}

	public String getCommandUsage(ICommandSender sender) {
		return "/em";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		try{
			if(args.length == 0){
				addChatMessage(EasyMessage.prefix + EnumChatFormatting.YELLOW + "" + EnumChatFormatting.BOLD + "Commands Help");
				addChatMessage(EnumChatFormatting.RED + "/em checkupdates " + EnumChatFormatting.GREEN + "Check updates");
				return;
			}
			if(args.length == 1){
				String action = args[0];
				if (action.equalsIgnoreCase("checkupdates")) {
					addChatMessage(EasyMessage.prefix + EnumChatFormatting.GREEN + "Checking updates...");
					addChatMessage(EasyMessageUpdater.isUpdateAvailable() ? EasyMessage.prefix + EnumChatFormatting.RED + "A new version found: " + EnumChatFormatting.GREEN + EasyMessageUpdater.getVersionString() + EnumChatFormatting.RED + "!" : EasyMessage.prefix + EnumChatFormatting.GREEN + "You're running the latest version.");
					return;
				}
				addChatMessage(EasyMessage.prefix + EnumChatFormatting.RED + "Usage: " + getCommandUsage(sender));
				return;
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	private void addChatMessage(String message) {
		Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message));
	}
}

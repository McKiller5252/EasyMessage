package com.killer5252.easymessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.killer5252.easymessage.commands.EMCommand;
import com.killer5252.easymessage.listeners.ConnectionHandler;
import com.killer5252.easymessage.listeners.FriendAddOrMessageListener;
import com.killer5252.easymessage.util.EasyMessageUpdater;

import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.*;

@Mod(modid = EasyMessage.MODID, version = EasyMessage.VERSION, name = EasyMessage.NAME)
public class EasyMessage
{
	
    public static final String MODID = "easymessage";
    public static final String NAME = "EasyMessage";
    public static final String VERSION = "1.0.0";
    public static final String prefix = EnumChatFormatting.DARK_GRAY + "[" + EnumChatFormatting.BLUE + "EasyMessage" + EnumChatFormatting.DARK_GRAY + "]: ";
    
    
    private static EasyMessage instance;
	private Logger logger;
	
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		try{
			instance = this;
			logger = LogManager.getLogger("EasyMessage");
		}catch(Exception e) {
			logger.warn("An exception occured in preInit(). Stacktrace below.");
			e.printStackTrace();
		}
	}
    
	@Mod.EventHandler
    public void init(FMLInitializationEvent event) {
		new EasyMessageUpdater();
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(new ConnectionHandler());
		MinecraftForge.EVENT_BUS.register(new FriendAddOrMessageListener());
		ClientCommandHandler.instance.registerCommand(new EMCommand());
    }

	public static EasyMessage instance() {
		return instance;
	}
	
	public Logger getLogger() {
		return logger;
	}

}

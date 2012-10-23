package de.theharofreak;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import de.theharofreak.commands.ExArrowCommand;
import de.theharofreak.listener.ProjectileListener;
import de.theharofreak.util.LoadConfigFile;

public class ExplosiveArrow extends JavaPlugin {
	
	//Strings
	public String prefix = "[ExplosiveArrow] ";
	public String sc = ChatColor.GRAY + "[" + ChatColor.DARK_AQUA + "ExplosiveArrow" + ChatColor.GRAY + "] ";
	
	//Classes
	LoadConfigFile configFile = new LoadConfigFile(this);
	public ProjectileListener listener;
	
	//ArrayLists
	public static ArrayList playerList = new ArrayList();
	
	@Override
	public void onDisable() {
		//PrintStuff
		System.out.println(prefix + "Plugin disabled.");
	}
	
	@Override
	public void onEnable() {
		//Events
		listener = new ProjectileListener(this);
		registerEvents();
		
		//Commands
		getCommand("explosivearrow").setExecutor(new ExArrowCommand(this));
		
		//Config
		configFile.loadConfig();
		
		//PrintStuff
		System.out.println(prefix + "Plugin enabled.");
	}
	
	public void registerEvents() {
		getServer().getPluginManager().registerEvents(listener, this);
	}

}

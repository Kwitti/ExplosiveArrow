package de.theharofreak.util;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;

import de.theharofreak.ExplosiveArrow;

public class LoadConfigFile {
	
	private ExplosiveArrow plugin;
	public LoadConfigFile(ExplosiveArrow plugin) {
		this.plugin = plugin;
	}
	
	public void loadConfig() {
		FileConfiguration config;
		if(new File("plugins/ExplosiveArrow/config.yml").exists()) {
			config = plugin.getConfig();
			System.out.println(plugin.prefix + "Config successfully loaded.");
			
		} else {
			plugin.saveDefaultConfig();
			config = plugin.getConfig();
			System.out.println(plugin.prefix + "No config found. Creating one ...");
			System.out.println(plugin.prefix + "Config successfully created.");
		}
	}

}

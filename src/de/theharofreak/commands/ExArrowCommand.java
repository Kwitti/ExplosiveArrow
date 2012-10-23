package de.theharofreak.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.theharofreak.ExplosiveArrow;

public class ExArrowCommand implements CommandExecutor {
	
	private ExplosiveArrow plugin;
	public ExArrowCommand (ExplosiveArrow plugin) {
		this.plugin = plugin;
	}
	
	//ChatColors
	ChatColor red = ChatColor.RED;
	ChatColor green = ChatColor.GREEN;
	ChatColor yellow = ChatColor.YELLOW;
	ChatColor gold = ChatColor.GOLD;
	ChatColor gray = ChatColor.GRAY;
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof ConsoleCommandSender) {
			sender.sendMessage(plugin.prefix + "This command is only for players!");
			return true;
		}
		
		Player player = (Player) sender;
		
		if(args.length > 1) {
			player.sendMessage(plugin.sc + red + "Too many arguments!");
			return true;
		}
		
		if(args.length == 0) {
			
			if(!plugin.playerList.contains(player.getName())) {
				plugin.playerList.add(player.getName());
				player.sendMessage(plugin.sc + green + "ExplosiveArrows are enabled for you.");
				
				if(!player.getInventory().contains(Material.BOW)) {
					player.getInventory().addItem(new ItemStack(Material.BOW, 1));
				}
				
				if(!player.getInventory().contains(Material.ARROW)) {
					player.getInventory().addItem(new ItemStack(Material.ARROW, 64));
				}
				
				return true;
				
			} else {
				plugin.playerList.remove(player.getName());
				player.sendMessage(plugin.sc + green + "ExplosiveArrows are " + red + "disabled " + green + "for you.");
				
				if(player.getInventory().contains(Material.BOW)) {
					player.getInventory().removeItem(new ItemStack(Material.BOW));
				}
				
				if(player.getInventory().contains(Material.ARROW)) {
					player.getInventory().removeItem(new ItemStack(Material.ARROW, 64));
				}
				return true;
			}
		}
		
		if(args.length == 1) {
			
			try {
				String s = args[0];
				int i = Integer.parseInt(s);
				
				plugin.getConfig().set("ExplosiveArrow.Radius", i);
				player.sendMessage(plugin.sc + green + "Radius successfully set to " + gold + i + green + ".");
				
				if(i >= 50) {
					player.sendMessage(plugin.sc + red + "Attention: " + yellow + "Higher radius may cause laggs!");
					return true;
				}
				
			} catch (NumberFormatException e) {
				player.sendMessage(plugin.sc + gold + args[0] + red + " is not a valid number!");
				return true;
			}
			
			return true;
		}
		
		return false;
	}
}

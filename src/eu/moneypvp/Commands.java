package eu.moneypvp;

import java.util.Comparator;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor, EloUtils{

	@Override
	public boolean onCommand(CommandSender s, Command c, String arg2, String[] args) {		
		
		
		if(!(s instanceof Player)) {
			System.err.println(ChatColor.translateAlternateColorCodes('&', getPrefix() + getisPlayerMessage()));
			return true;
			
		}
		
		Player p = (Player) s;
		if(c.getName().equalsIgnoreCase("elo")) {
			if(args.length == 0) {
			p.sendMessage(StringUtils.replace(ChatColor.translateAlternateColorCodes('&', getPrefix() + getEloMessage())
					,"%elovalue%"
					,Integer.toString(getElo(p))));
					return true;
			}
			if(args.length >= 1) {
				
				
				Player target = Bukkit.getPlayer(args[0]);
				if(target == null) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', getPrefix() + getPlayerExistsMessage()));
					return true;
				}else
				
				if(!target.isOnline()) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', getPrefix() + getPlayerExistsMessage()));
					return true;
				}else {

					p.sendMessage(StringUtils.replace(ChatColor.translateAlternateColorCodes('&', getPrefix() + getCheckEloMessages())
							,"%playername%"
							,target.getName()).replace("%elo%", Integer.toString(fm.getConfig().getInt(target.getName()))));
					return true;
				}
			}
			}
		
		
		if(c.getName().equalsIgnoreCase("topelo")) {
			

            Main.unSortedMap.clear();
            Main.reverseSortedMap.clear();
            fm.reloadConfig();

		        for (String pe : fm.getConfig().getKeys(true))
		        {
		            String getP = pe;
		            int getInt = fm.getConfig().getInt(getP);
		            Main.unSortedMap.put(getP, getInt);
		        }
		     Main.unSortedMap
		     	.entrySet()
		         .stream()
		         .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
		         .limit(Main.instance.getConfig().getInt("EloView"))
		         .forEachOrdered(x -> Main.reverseSortedMap.put(x.getKey(), x.getValue()));
		     	 p.sendMessage(ChatColor.translateAlternateColorCodes('&',fm.getConfigMessages().getString("line1")));
			  	 p.sendMessage(StringUtils.replace(Main.reverseSortedMap.toString(), "{", " §7").replace("}", "").replace(",", "\n§7").replace("=", " §8»§c "));
		     	 p.sendMessage(ChatColor.translateAlternateColorCodes('&',fm.getConfigMessages().getString("line2")));
	
		     	return true;
		}
		
	    
		
		
		
		if(c.getName().equalsIgnoreCase("eloadmin")) {

			if(p.isOp()) {
				if(args.length == 0) {
					p.sendMessage("§7Use §e/eloadmin setelo (player) (value)");
					p.sendMessage("§7Use §e/eloadmin resetelo (player)");
					p.sendMessage("§7Use §e/eloadmin addelo (player) (value)");
					p.sendMessage("§7Use §e/eloadmin removeelo (player) (value)");
					p.sendMessage("§7Use §e/eloadmin reload");
					return true;
				}
				if(args[0].equalsIgnoreCase("reload")) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', getPrefix() + getReloadMessage()));
					Main.instance.reloadConfig();
					fm.reloadConfig();
					return true;
					
				}
				
				
				
				if(args[0].equalsIgnoreCase("addelo")) {
					if(args.length == 1) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', getPrefix()+ getPlayerNameMessage()));
				return true;
				}
			
			Player target = Bukkit.getPlayer(args[1]);
			if(target == null) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', getPrefix() + getPlayerExistsMessage()));
				return true;
			}else
			
			if(!target.isOnline()) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', getPrefix() + getPlayerExistsMessage()));
				return true;
			}else {
				if(args.length == 2) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', getPrefix() + getNumberMessages()));
					return true;
					}
				
				
				if(NumberUtils.isNumber(args[2])) {
					
					addElo(target, Integer.valueOf(args[2]));
					p.sendMessage(StringUtils.replace(ChatColor.translateAlternateColorCodes('&', getPrefix() + addEloMessages())
							,"%playername%"
							,target.getName())
							.replace("%elovalue%",args[2]));					
					return true;
				}else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', getPrefix() + getNumberMessages()));
					return true;
				}
			}
		}
				
				
				
				if(args[0].equalsIgnoreCase("removeelo")) {
					if(args.length == 1) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', getPrefix()+ getPlayerNameMessage()));
				return true;
				}
			
			Player target = Bukkit.getPlayer(args[1]);
			if(target == null) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', getPrefix() + getPlayerExistsMessage()));
				return true;
			}else
			
			if(!target.isOnline()) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', getPrefix() + getPlayerExistsMessage()));
				return true;
			}else {
				if(args.length == 2) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', getPrefix() + getNumberMessages()));
					return true;
					}
				if(NumberUtils.isNumber(args[2])) {
					
					removeElo(target, Integer.valueOf(args[2]));
					p.sendMessage(StringUtils.replace(ChatColor.translateAlternateColorCodes('&', getPrefix() + getRemoveEloMessages())
							,"%playername%"
							,target.getName())
							.replace("%elovalue%",args[2]));					
					return true;
				}else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', getPrefix() + getNumberMessages()));
					return true;
				}
			}
		}
				
					if(args[0].equalsIgnoreCase("setelo")) {
						if(args.length == 1) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', getPrefix()+ getPlayerNameMessage()));
					return true;
					}
				
				Player target = Bukkit.getPlayer(args[1]);
				if(target == null) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', getPrefix() + getPlayerExistsMessage()));
					return true;
				}else
				
				if(!target.isOnline()) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', getPrefix() + getPlayerExistsMessage()));
					return true;
				}else {
				
					if(args.length == 2) {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', getPrefix() + getNumberMessages()));
						return true;
						}
					if(NumberUtils.isNumber(args[2])) {
						
						setElo(target, Integer.valueOf(args[2]));
						p.sendMessage(StringUtils.replace(ChatColor.translateAlternateColorCodes('&', getPrefix() + setEloMessages())
								,"%playername%"
								,target.getName())
								.replace("%elovalue%",args[2]));					
						return true;
					}else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', getPrefix() + getNumberMessages()));
						return true;
					}
				}
			}
			
		
		
		
	
					if(args[0].equalsIgnoreCase("resetelo")) {
						if(args.length == 1) {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', getPrefix() + getPlayerNameMessage()));
							return true;
						}
						
				Player target = Bukkit.getPlayer(args[1]);
				if(target == null) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', getPrefix() + getPlayerExistsMessage()));
					return true;
				}else
				
				if(!target.isOnline()) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', getPrefix() + getPlayerExistsMessage()));
					return true;
				}else {
					resetElo(target);
					p.sendMessage(StringUtils.replace(ChatColor.translateAlternateColorCodes('&', getPrefix() + getResetEloMessages())
							,"%playername%"
							,target.getName()));

					return true;
				}
			}
					
					
						

					
					
			}else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', getPrefix() + getPlayerPermMessages()));
				return true;
			}
			
			}	
			return false;

	
	}
	
	
}




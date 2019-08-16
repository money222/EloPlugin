package eu.moneypvp;

import java.util.Random;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
public class Events implements Listener,EloUtils {

		
	 @EventHandler
	 public void onChat(AsyncPlayerChatEvent e) {
			 e.setFormat(e.getFormat().replace("{elovalue}", Integer.toString(getElo(e.getPlayer()))));
	 }
	 @EventHandler
	 public void onDeath(PlayerDeathEvent e) {
		 if(e.getEntity().getKiller() instanceof Player) {
			 Player victim = e.getEntity();
			 Player killer = e.getEntity().getKiller();
			Random rand = new Random();
		
		int random = rand.nextInt(Main.instance.getConfig().getInt("EloRangeRandom")) + 1;
		if(Integer.valueOf(getElo(victim)) <= 0) {
			setElo(victim, 0);
			victim.sendMessage(ChatColor.translateAlternateColorCodes('&', getPrefix() + getVictimnoeloMessages()));
			addElo(killer, random);
			return;
		}
				
				killer.sendMessage(StringUtils.replace(ChatColor.translateAlternateColorCodes('&', getPrefix() + getKillerMessage())
				,"%victimname%"
				,victim.getName()).replace(
				"%elo%",
				Integer.toString(random)));
		

				victim.sendMessage(StringUtils.replace(ChatColor.translateAlternateColorCodes('&', getPrefix() + getVictimMessage())
				,"%killername%"
				,killer.getName()).replace(
				"%elo%",
				Integer.toString(random)));
				addElo(killer, random);
				removeElo(victim, random);

		
	}
	
}

@EventHandler
public void onJoin(PlayerJoinEvent e) {
	if(fm.getConfig().getString(e.getPlayer().getName()) == null) {
		setElo(e.getPlayer(), Main.instance.getConfig().getInt("startELO"));
		}
	}
}
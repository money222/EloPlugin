package eu.moneypvp;

public interface EloUtils {
	FileManager fm = new FileManager();
	 default Integer getElo(org.bukkit.entity.Player p) {
		return fm.getConfig().getInt(p.getName());
	}
	 default void setElo(org.bukkit.entity.Player p, int value) {
		 if(fm.getConfig().get(p.getPlayer().getName()) == null)
		 {
			 fm.getConfig().set(p.getName(), value);
			 fm.saveConfig(); 
			 return;
		 }
		 if(fm.getConfig().getInt(p.getName()) <= 0) {
			 fm.getConfig().set(p.getName(), 0);
			 fm.saveConfig();
			 return;
		 }
		 fm.getConfig().set(p.getName(), value);
		 fm.saveConfig();
	}
	 default void resetElo(org.bukkit.entity.Player p) {
		 fm.getConfig().set(p.getName(), 1000);
		 fm.saveConfig();
	}
	 default void addElo(org.bukkit.entity.Player p , int value) {
		 fm.getConfig().set(p.getName(), fm.getConfig().getInt(p.getName()) + value);
		 fm.saveConfig();

	}
	 
	 default void removeElo(org.bukkit.entity.Player p , int value) {
		 if(fm.getConfig().getInt(p.getName()) <= 0) {
			 fm.getConfig().set(p.getName(), 0);
			 fm.saveConfig();
			 return;
		 }
		 
		 fm.getConfig().set(p.getName(), fm.getConfig().getInt(p.getName()) - value);
		 fm.saveConfig();

	}
	 
	 default String getPrefix() {
		 return Messages.PREFIX.toString();
	 }
	 default String getPlayerNameMessage() {
		 return Messages.PlayerName.toString();
	 }
	 default String getCheckEloMessages() {
		 return Messages.CheckElo.toString();
	 }
	 default String getPlayerExistsMessage() {
		 return Messages.PlayerExists.toString();
	 }
	 default String getReloadMessage() {
		 return Messages.reload.toString();
	 }
	 default String getisPlayerMessage() {
		 return Messages.isPlayer.toString();
	 }	
	 default String getValueMessage() {
		 return Messages.insertvalue.toString();
	 }
	 default String getEloMessage() {
		 return Messages.elomessage.toString();
	 }
	 default String getNumberMessages() {
		 return Messages.isNumber.toString();
	 }
	 default String getKillerMessage() {
		 return Messages.killermessage.toString();
	 }
	 default String getVictimMessage() {
		 return Messages.victimmessage.toString();
	 }
	 default String getVictimnoeloMessages() {
		 return Messages.victimnoelo.toString();
	 }	
	 default String getRemoveEloMessages() {
		 return Messages.removeelo.toString();
	 }
	 default String getResetEloMessages() {
		 return Messages.resetelo.toString();
	 }
	 default String setEloMessages() {
		 return Messages.setelo.toString();
	 }
	 default String getPlayerPermMessages() {
		 return Messages.PlayerPerm.toString();
	 }
	 default String addEloMessages() {
		 return Messages.addelo.toString();
	 }	
	 default String getLine2Messages() {
		 return Messages.line2.toString();
	 }
	 default String getLine1Messages() {
		 return Messages.line1.toString();
	 }
}

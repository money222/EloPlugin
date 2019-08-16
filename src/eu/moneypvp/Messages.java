package eu.moneypvp;

public enum Messages implements EloUtils{
    PREFIX(fm.getMessagesConfig().getString("prefix")),
	CheckElo(fm.getMessagesConfig().getString("check-elo")),
	PlayerName(fm.getMessagesConfig().getString("insert-playername")),
	PlayerExists(fm.getMessagesConfig().getString("player-exists")),
	reload(fm.getMessagesConfig().getString("reload")),
	PlayerPerm(fm.getMessagesConfig().getString("player-perms")),
	isNumber(fm.getMessagesConfig().getString("isNumber")),
	resetelo(fm.getMessagesConfig().getString("resetelo")),
	setelo(fm.getMessagesConfig().getString("setelo")),
	insertvalue(fm.getMessagesConfig().getString("insert-value")),
	victimnoelo(fm.getMessagesConfig().getString("victim-no-elo")),
	killermessage(fm.getMessagesConfig().getString("killer-message")),
	victimmessage(fm.getMessagesConfig().getString("victim-message")),
	elomessage(fm.getMessagesConfig().getString("elo-message")),
	isPlayer(fm.getMessagesConfig().getString("isPlayer")),
	addelo(fm.getMessagesConfig().getString("addelo")),
	removeelo(fm.getMessagesConfig().getString("removeelo")),
	line1(fm.getMessagesConfig().getString("line1")),
	line2(fm.getMessagesConfig().getString("line2"));

    private String value;
    Messages(String value) {
        this.value = value;
    }
    
    @Override
	public String toString() {
        return value;

    }
}


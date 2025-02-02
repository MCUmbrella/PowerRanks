package nl.svenar.PowerRanks.addons;

import org.bukkit.entity.Player;

import nl.svenar.PowerRanks.PowerRanks;
import nl.svenar.PowerRanks.Data.Users;
import nl.svenar.PowerRanks.api.PowerRanksAPI;

public class PowerRanksPlayer {

	private PowerRanks powerRanks;
	private Player player;
	private Users users;
	private String name;
	private PowerRanksAPI api;

	public PowerRanksPlayer(PowerRanks powerRanks, Player player) {
		this.powerRanks = powerRanks;
		this.player = player;
		this.users = new Users(this.powerRanks);
		if (player != null)
			name = player.getName();
		this.api = new PowerRanksAPI();
	}

	public PowerRanksPlayer(PowerRanks powerRanks, String name) {
		this.powerRanks = powerRanks;
		this.name = name;
		this.users = new Users(this.powerRanks);
		this.api = new PowerRanksAPI();
	}

	public PowerRanks getPowerRanks() {
		return powerRanks;
	}

	public Player getPlayer() {
		return player;
	}

	public String getName() {
		return name;
	}

	// Get the users current rank
	// Arguments: Returns:
	// String (player's rank)
	public String getRank() {
		return users.getPrimaryRank(getPlayer());
	}
	
	public PowerRanksAPI getAPI() {
		return api;
	}

//	// Set the users rank
//	// Arguments: rankname(as registered in powerRanks)
//	// Returns: boolean (true on success & false on fail)
//	public boolean setRank(String rankName) {
//		return users.setGroup(getPlayer(), users.getRankIgnoreCase(rankName));
//	}
//
//	// Get the prefix of the players rank
//	// Arguments:
//	// Returns: String (the current prefix that the player has)
//	public String getPrefix() {
//		return users.getPrefix(getPlayer());
//	}
//
//	// Get the prefix of the players rank
//	// Arguments: rankname(as registered in powerRanks)
//	// Returns: String (the current prefix that the player has)
//	public String getPrefix(String rankName) {
//		return users.getPrefix(users.getRankIgnoreCase(rankName));
//	}
//
//	// Set the prefix of the players rank
//	// Arguments: newPreifx(the new prefix for the player's current rank)
//	// Returns: boolean (true on success & false on fail)
//	public boolean setPrefix(String newPrefix) {
//		return users.setPrefix(getRank(), newPrefix);
//	}
//
//	// Set the prefix of the players rank
//	// Arguments: rankname(as registered in powerRanks), newPreifx(the new prefix for the player's current rank)
//	// Returns: boolean (true on success & false on fail)
//	public boolean setPrefix(String rankName, String newPrefix) {
//		return users.setPrefix(users.getRankIgnoreCase(rankName), newPrefix);
//	}
}

package nl.svenar.PowerRanks.Commands.rank;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import nl.svenar.PowerRanks.PowerRanks;
import nl.svenar.PowerRanks.Commands.PowerCommand;
import nl.svenar.PowerRanks.Data.Messages;
import nl.svenar.PowerRanks.Data.Users;
import nl.svenar.common.structure.PRRank;

public class cmd_delinheritance extends PowerCommand {

	private Users users;

	public cmd_delinheritance(PowerRanks plugin, String command_name, COMMAND_EXECUTOR ce) {
		super(plugin, command_name, ce);
		this.users = new Users(plugin);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String commandName, String[] args) {
		if (sender.hasPermission("powerranks.cmd.delinheritance")) {
			if (args.length == 2) {
				final String rank2 = this.users.getRankIgnoreCase(args[0]);
				final String inheritance = args[1];
				final boolean result = this.users.removeInheritance(rank2, inheritance);
				if (result) {
					Messages.messageCommandInheritanceRemoved(sender, inheritance, rank2);
				} else {
					Messages.messageGroupNotFound(sender, rank2);
				}
			} else {
				Messages.messageCommandUsageRemoveInheritance(sender);
			}
		} else {
			sender.sendMessage(PowerRanks.getLanguageManager().getFormattedMessage("general.no-permission"));
		}

		return false;
	}

	public ArrayList<String> tabCompleteEvent(CommandSender sender, String[] args) {
		ArrayList<String> tabcomplete = new ArrayList<String>();

		if (args.length == 1) {
			for (PRRank rank : this.users.getGroups()) {
				tabcomplete.add(rank.getName());
			}
		}

		if (args.length == 2) {
			for (String inheritance : this.users.getInheritances(this.users.getRankIgnoreCase(args[0]))) {
				tabcomplete.add(inheritance);
			}
		}

		return tabcomplete;
	}
}

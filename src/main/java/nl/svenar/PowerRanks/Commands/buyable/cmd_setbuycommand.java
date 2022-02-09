package nl.svenar.PowerRanks.Commands.buyable;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import nl.svenar.PowerRanks.PowerRanks;
import nl.svenar.PowerRanks.Commands.PowerCommand;
import nl.svenar.PowerRanks.Data.Messages;
import nl.svenar.PowerRanks.Data.Users;
import nl.svenar.common.structure.PRRank;

public class cmd_setbuycommand extends PowerCommand {

	private Users users;

	public cmd_setbuycommand(PowerRanks plugin, String command_name, COMMAND_EXECUTOR ce) {
		super(plugin, command_name, ce);
		this.users = new Users(plugin);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String commandName, String[] args) {
		if (sender.hasPermission("powerranks.cmd.setbuycommand")) {
			if (args.length >= 2) {
				final String rankname = this.users.getRankIgnoreCase(args[0]);
				String buycommand = "";
				for (int i = 1; i < args.length; i++) {
					buycommand = String.valueOf(buycommand) + args[i] + " ";
				}
				buycommand = buycommand.substring(0, buycommand.length() - 1);
				final boolean success2 = this.users.setBuyCommand(rankname, buycommand);
				if (success2) {
					Messages.messageCommandSetbuycommandSuccess(sender, rankname, buycommand);
				} else {
					Messages.messageCommandSetbuycommandError(sender, rankname, buycommand);
				}
			} else {
				Messages.messageCommandUsageSetbuycommand(sender);
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
		
		return tabcomplete;
	}
}

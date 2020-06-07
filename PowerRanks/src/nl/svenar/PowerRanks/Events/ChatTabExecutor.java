package nl.svenar.PowerRanks.Events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachmentInfo;

import nl.svenar.PowerRanks.PowerRanks;
import nl.svenar.PowerRanks.Data.Users;

public class ChatTabExecutor implements TabCompleter {

	private PowerRanks m;

	public ChatTabExecutor(PowerRanks m) {
		this.m = m;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
		if (sender instanceof Player) {
//			Player p = (Player) sender;
			List<String> list = new ArrayList<String>();
			if (args.length == 1) {
				ArrayList<String> commands_list = new ArrayList<String>();
				commands_list.add("help");
				commands_list.add("createrank");
				commands_list.add("deleterank");
				commands_list.add("set");
				commands_list.add("setown");
				commands_list.add("promote");
				commands_list.add("demote");
				commands_list.add("check");
				commands_list.add("addperm");
				commands_list.add("delperm");
				commands_list.add("setprefix");
				commands_list.add("setsuffix");
				commands_list.add("setchatcolor");
				commands_list.add("setnamecolor");
				commands_list.add("addinheritance");
				commands_list.add("delinheritance");
				commands_list.add("enablebuild");
				commands_list.add("disablebuild");
				commands_list.add("renamerank");
				commands_list.add("setdefaultrank");
				commands_list.add("reload");
				commands_list.add("forceupdateconfigversion");
				commands_list.add("gui");
				commands_list.add("rankup");
				commands_list.add("stats");
				
				for (String command : commands_list) {
					if (command.toLowerCase().contains(args[0].toLowerCase())) list.add(command);
				}
			}

			if (args.length == 2) {
				if (args[0].equalsIgnoreCase("setown") || args[0].equalsIgnoreCase("addperm") || args[0].equalsIgnoreCase("delperm") || args[0].equalsIgnoreCase("setprefix") || args[0].equalsIgnoreCase("setsuffix")
						|| args[0].equalsIgnoreCase("setchatcolor") || args[0].equalsIgnoreCase("setnamecolor") || args[0].equalsIgnoreCase("addinheritance") || args[0].equalsIgnoreCase("delinheritance")
						|| args[0].equalsIgnoreCase("enablebuild") || args[0].equalsIgnoreCase("disablebuild") || args[0].equalsIgnoreCase("renamerank") || args[0].equalsIgnoreCase("setdefaultrank")) {
					Users s = new Users(this.m);
					for (String rank : s.getGroups()) {
						if (rank.toLowerCase().contains(args[1].toLowerCase())) list.add(rank);
					}
				}
				
				if (args[0].equalsIgnoreCase("set") || args[0].equalsIgnoreCase("promote") || args[0].equalsIgnoreCase("demote") || args[0].equalsIgnoreCase("check")) {
					for (Player player : Bukkit.getServer().getOnlinePlayers()) {
						if (player.getName().toLowerCase().contains(args[1].toLowerCase())) list.add(player.getName());
					}
				}
			}

			if (args.length == 3) {
				if (args[0].equalsIgnoreCase("set")) {
					Users s = new Users(this.m);
					for (String rank : s.getGroups()) {
						if (rank.toLowerCase().contains(args[2].toLowerCase())) list.add(rank);
					}
				}

				if (args[0].equalsIgnoreCase("addperm")) {
					for (PermissionAttachmentInfo pai : sender.getEffectivePermissions()) {
						String perm = pai.getPermission();
						if (perm.toLowerCase().contains(args[2].toLowerCase())) list.add(perm);
					}
				}

				if (args[0].equalsIgnoreCase("delperm")) {
					Users s = new Users(this.m);
					for (String perm : s.getPermissions(s.getRankIgnoreCase(args[1]))) {
						if (perm.toLowerCase().contains(args[2].toLowerCase())) list.add(perm);
					}
				}

				if (args[0].equalsIgnoreCase("addinheritance")) {
					Users s = new Users(this.m);
					for (String rank : s.getGroups()) {
						if (rank.toLowerCase().contains(args[2].toLowerCase())) list.add(rank);
					}
				}

				if (args[0].equalsIgnoreCase("delinheritance")) {
					Users s = new Users(this.m);
					for (String inheritance : s.getInheritances(s.getRankIgnoreCase(args[1]))) {
						if (inheritance.toLowerCase().contains(args[2].toLowerCase())) list.add(inheritance);
					}
				}
			}

			return list;
		}
		return null;
	}

}

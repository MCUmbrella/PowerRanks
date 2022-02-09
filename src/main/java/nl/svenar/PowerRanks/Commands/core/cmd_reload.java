package nl.svenar.PowerRanks.Commands.core;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import nl.svenar.PowerRanks.PowerRanks;
import nl.svenar.PowerRanks.Cache.CacheManager;
// import nl.svenar.PowerRanks.Cache.CachedConfig;
import nl.svenar.PowerRanks.Commands.PowerCommand;
import nl.svenar.PowerRanks.Data.Messages;
import nl.svenar.PowerRanks.Util.PluginReloader;

public class cmd_reload extends PowerCommand {

	public cmd_reload(PowerRanks plugin, String command_name, COMMAND_EXECUTOR ce) {
		super(plugin, command_name, ce);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String commandName, String[] args) {
		if (sender.hasPermission("powerranks.cmd.reload")) {
			Messages.messageCommandReloadWarning(sender);

			if (args.length != 1) {
				Messages.messageCommandUsageReload(sender);
			} else {
				if (args[0].equalsIgnoreCase("config")) {

					Messages.messageCommandReloadConfig(sender);
					PowerRanks.getConfigManager().reload();
					CacheManager.load(PowerRanks.fileLoc);
					this.plugin.updateAllPlayersTABlist();
					Messages.messageCommandReloadConfigDone(sender);

				} else if (args[0].equalsIgnoreCase("plugin")) {

					Messages.messageCommandReloadPlugin(sender);
					PluginReloader pluginReloader = new PluginReloader();
					pluginReloader.reload(PowerRanks.pdf.getName());
					Messages.messageCommandReloadPluginDone(sender);

				} else if (args[0].equalsIgnoreCase("addons")) {

					Messages.messageCommandReloadAddons(sender);
					PowerRanks.getInstance().addonsManager.disable();
					PowerRanks.getInstance().addonsManager.setup();
					Messages.messageCommandReloadAddonsDone(sender);

				} else if (args[0].equalsIgnoreCase("all")) {

					Messages.messageCommandReloadConfig(sender);
					PowerRanks.getConfigManager().reload();
					Messages.messageCommandReloadConfigDone(sender);

					Messages.messageCommandReloadPlugin(sender);
					PluginReloader pluginReloader = new PluginReloader();
					pluginReloader.reload(PowerRanks.pdf.getName());

					CacheManager.load(PowerRanks.fileLoc);
					this.plugin.updateAllPlayersTABlist();
					Messages.messageCommandReloadPluginDone(sender);

				} else {

					Messages.messageCommandUsageReload(sender);
				}
			}
		} else {
			if (sender instanceof Player) {
				Messages.noPermission((Player) sender);
			}
		}

		return false;
	}

	public ArrayList<String> tabCompleteEvent(CommandSender sender, String[] args) {
		ArrayList<String> tabcomplete = new ArrayList<String>();

		if (args.length == 1) {
			tabcomplete.add("plugin");
			tabcomplete.add("config");
			tabcomplete.add("addons");
			tabcomplete.add("all");
		}

		return tabcomplete;
	}
}

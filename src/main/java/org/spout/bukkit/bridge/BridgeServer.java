/*
 * This file is part of BukkitBridge (http://www.spout.org/).
 *
 * BukkitBridge is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * BukkitBridge is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.spout.bukkit.bridge;

import org.bukkit.GameMode;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.help.HelpMap;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.map.MapView;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.SimpleServicesManager;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.plugin.messaging.StandardMessenger;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;

import com.avaje.ebean.config.ServerConfig;
import org.spout.api.Spout;
import org.spout.bukkit.bridge.entity.BridgePlayer;
import org.spout.vanilla.VanillaPlugin;

public class BridgeServer implements Server {
    private org.spout.api.Server server;
    private final ServicesManager servicesManager = new SimpleServicesManager();
    private final SimpleCommandMap commandMap = new SimpleCommandMap(this);
    private final PluginManager pluginManager = new SimplePluginManager(this, commandMap);
    private final StandardMessenger messenger = new StandardMessenger();

    public void init(org.spout.api.Server server) {
        this.server = server;
    }

    @Override
    public String getName() {
        return server.getName();
    }

    @Override
    public String getVersion() {
        return server.getVersion();
    }

    @Override
    public String getBukkitVersion() {
        return null;  //TODO: Adjust for usage with Spout!
    }

    @Override
    public Player[] getOnlinePlayers() {
        List<Player> players = new ArrayList<Player>();
        for (org.spout.api.player.Player spoutPlayer : server.getOnlinePlayers()) {
            players.add(new BridgePlayer(spoutPlayer));
        }
        return players.toArray(new Player[players.size()]);
    }

    @Override
    public int getMaxPlayers() {
        return Spout.getGame().getMaxPlayers();
    }

    @Override
    public int getPort() {
        return 0;  //TODO: Adjust for usage with Spout!
    }

    @Override
    public int getViewDistance() {
        return 0; //TODO: Adjust for usage with Spout!
    }

    @Override
    public String getIp() {
        return Spout.getGame().getAddress().split(":")[0];
    }

    @Override
    public String getServerName() {
        return Spout.getGame().getName();
    }

    @Override
    public String getServerId() {
        return null;  //TODO: Adjust for usage with Spout!
    }

    @Override
    public boolean getAllowEnd() {
        return false;  //TODO: Adjust for usage with Spout!
    }

    @Override
    public boolean getAllowNether() {
        return false;  //TODO: Adjust for usage with Spout!
    }

    @Override
    public boolean hasWhitelist() {
        return server.isWhitelist();
    }

    @Override
    public void setWhitelist(boolean b) {
        server.setWhitelist(true);
    }

    @Override
    public Set<OfflinePlayer> getWhitelistedPlayers() {
        Set<OfflinePlayer> whitelisted = new HashSet<OfflinePlayer>();
        for (String player : server.getWhitelistedPlayers()) {
            // whitelisted.add(new BridgeOfflinePlayer(somedata));
            //TODO: Construct & add OfflinePlayer objects to Set
        }
        return whitelisted;
    }

    @Override
    public void reloadWhitelist() {
        server.updateWhitelist();
    }

    @Override
    public int broadcastMessage(String message) {
        Spout.getGame().broadcastMessage(message);
        return Spout.getGame().getOnlinePlayers().length;
    }

    @Override
    public String getUpdateFolder() {
        return null;  //TODO: Adjust for usage with Spout!
    }

    @Override
    public File getUpdateFolderFile() {
        return null;  //TODO: Adjust for usage with Spout!
    }

    @Override
    public int getTicksPerAnimalSpawns() {
        return 0;  //TODO: Adjust for usage with Spout!
    }

    @Override
    public int getTicksPerMonsterSpawns() {
        return 0;  //TODO: Adjust for usage with Spout!
    }

    @Override
    public Player getPlayer(String s) {
        return null;  //TODO: Adjust for usage with Spout!
    }

    @Override
    public Player getPlayerExact(String s) {
        return null;  //TODO: Adjust for usage with Spout!
    }

    @Override
    public List<Player> matchPlayer(String s) {
        return null;  //TODO: Adjust for usage with Spout!
    }

    @Override
    public PluginManager getPluginManager() {
        return pluginManager;
    }

    @Override
    public BukkitScheduler getScheduler() {
        return null;  //TODO: Adjust for usage with Spout!
    }

    @Override
    public ServicesManager getServicesManager() {
        return servicesManager;
    }

    @Override
    public List<World> getWorlds() {
        return null;  //TODO: Adjust for usage with Spout!
    }

    @Override
    public World createWorld(WorldCreator worldCreator) {
        return null;  //TODO: Adjust for usage with Spout!
    }

    @Override
    public boolean unloadWorld(String s, boolean b) {
        return false;  //TODO: Adjust for usage with Spout!
    }

    @Override
    public boolean unloadWorld(World world, boolean b) {
        return false;  //TODO: Adjust for usage with Spout!
    }

    @Override
    public World getWorld(String s) {
        return null;  //TODO: Adjust for usage with Spout!
    }

    @Override
    public World getWorld(UUID uuid) {
        return new BridgeWorld(server.getWorld(uuid));
    }

    @Override
    public MapView getMap(short i) {
        return null;  //TODO: Adjust for usage with Spout!
    }

    @Override
    public MapView createMap(World world) {
        return null;  //TODO: Adjust for usage with Spout!
    }

    @Override
    public void reload() {
        //TODO: Adjust for usage with Spout!
    }

    @Override
    public Logger getLogger() {
        return server.getLogger();
    }

    @Override
    public PluginCommand getPluginCommand(String name) {
        Command command = commandMap.getCommand(name);
        if (command instanceof PluginCommand) {
            return (PluginCommand) command;
        } else {
            return null;
        }
    }

    @Override
    public void savePlayers() {
        //TODO: Adjust for usage with Spout!
    }

    @Override
    public boolean dispatchCommand(CommandSender sender, String commandLine) throws CommandException {
        if (commandMap.dispatch(sender, commandLine)) {
            return true;
        }
        sender.sendMessage("Unknown command. Type \"help\" for help.");
        return false;
    }

    @Override
    public void configureDbConfig(ServerConfig serverConfig) {
        //TODO: Adjust for usage with Spout!
    }

    @Override
    public boolean addRecipe(Recipe recipe) {
        return false;  //TODO: Adjust for usage with Spout!
    }

    @Override
    public List<Recipe> getRecipesFor(ItemStack itemStack) {
        return null;  //TODO: Adjust for usage with Spout!
    }

    @Override
    public Iterator<Recipe> recipeIterator() {
        return null;  //TODO: Adjust for usage with Spout!
    }

    @Override
    public void clearRecipes() {
        //TODO: Adjust for usage with Spout!
    }

    @Override
    public void resetRecipes() {
        //TODO: Adjust for usage with Spout!
    }

    @Override
    public Map<String, String[]> getCommandAliases() {
        return null;  //TODO: Adjust for usage with Spout!
    }

    @Override
    public int getSpawnRadius() {
        return 0;  //TODO: Adjust for usage with Spout!
    }

    @Override
    public void setSpawnRadius(int i) {
        //TODO: Adjust for usage with Spout!
    }

    @Override
    public boolean getOnlineMode() {
        return false;  //TODO: Adjust for usage with Spout!
    }

    @Override
    public boolean getAllowFlight() {
        return false;  //TODO: Adjust for usage with Spout!
    }

    @Override
    public boolean useExactLoginLocation() {
        return false;  //TODO: Adjust for usage with Spout!
    }

    @Override
    public void shutdown() {
        server.stop();
    }

    @Override
    public int broadcast(String s, String s1) {
        return 0;  //TODO: Adjust for usage with Spout!
    }

    @Override
    public OfflinePlayer getOfflinePlayer(String s) {
        return null;  //TODO: Adjust for usage with Spout!
    }

    @Override
    public Set<String> getIPBans() {
        return null;  //TODO: Adjust for usage with Spout!
    }

    @Override
    public void banIP(String address) {
        server.ban(address);
    }

    @Override
    public void unbanIP(String address) {
        server.unban(address);
    }

    @Override
    public Set<OfflinePlayer> getBannedPlayers() {
        return null;  //TODO: Adjust for usage with Spout!
    }

    @Override
    public Set<OfflinePlayer> getOperators() {
        Set<OfflinePlayer> ops = new HashSet<OfflinePlayer>();
        return null;  //TODO: Adjust for usage with Spout!
    }

    @Override
    public GameMode getDefaultGameMode() {
        return GameMode.getByValue(VanillaPlugin.defaultGamemode.getId());
    }

    @Override
    public void setDefaultGameMode(GameMode gameMode) {
        //TODO: Vanilla implementation does not allow default gamemode alteration
    }

    @Override
    public ConsoleCommandSender getConsoleSender() {
        return null;  //TODO: Adjust for usage with Spout!
    }

    @Override
    public File getWorldContainer() {
        return null;  //TODO: Adjust for usage with Spout!
    }

    @Override
    public OfflinePlayer[] getOfflinePlayers() {
        return new OfflinePlayer[0];  //TODO: Adjust for usage with Spout!
    }

    @Override
    public Messenger getMessenger() {
        return messenger;
    }

    @Override
    public HelpMap getHelpMap() {
        return null;  //TODO: Adjust for usage with Spout!
    }

    @Override
    public Inventory createInventory(InventoryHolder inventoryHolder, InventoryType inventoryType) {
        return null;  //TODO: Adjust for usage with Spout!
    }

    @Override
    public Inventory createInventory(InventoryHolder inventoryHolder, int i) {
        return null;  //TODO: Adjust for usage with Spout!
    }

    @Override
    public Inventory createInventory(InventoryHolder inventoryHolder, int i, String s) {
        return null;  //TODO: Adjust for usage with Spout!
    }

    @Override
    public void sendPluginMessage(Plugin source, String channel, byte[] message) {
        StandardMessenger.validatePluginMessage(getMessenger(), source, channel, message);

        for (Player player : getOnlinePlayers()) {
            player.sendPluginMessage(source, channel, message);
        }
    }

    @Override
    public Set<String> getListeningPluginChannels() {
        Set<String> result = new HashSet<String>();

        for (Player player : getOnlinePlayers()) {
            result.addAll(player.getListeningPluginChannels());
        }
        return result;
    }
}

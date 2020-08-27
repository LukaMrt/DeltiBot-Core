package fr.lukam.deltibot.core.infrastructure.plugins.repositories;

import fr.lukam.bot.api.bot.Plugin;
import fr.lukam.bot.api.entities.fakes.FakePlugin;
import fr.lukam.bot.api.entities.interfaces.commands.Command;
import fr.lukam.bot.api.entities.interfaces.events.Listener;
import fr.lukam.bot.api.repositories.CommandsRepository;
import fr.lukam.bot.api.repositories.ListenersRepository;
import fr.lukam.bot.api.repositories.PluginsRepository;
import fr.lukam.deltibot.core.infrastructure.utils.YAMLParserUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JarsPluginsRepository implements  PluginsRepository {

    private final List<Plugin> plugins = new ArrayList<>();

    @Override
    public void loadPlugins() {
        try {

            File directory = new File("plugins/");

            if (directory.listFiles() == null) {
                return;
            }

            for (File file : directory.listFiles()) {

                if (file == null) {
                    continue;
                }

                URLClassLoader child = new URLClassLoader(new URL[]{file.toURI().toURL()}, this.getClass().getClassLoader());

                Class<?> aClass = Class.forName(YAMLParserUtils.getParsedYaml(file).get("main"), true, child);

                Plugin plugin = (Plugin) aClass.newInstance();
                this.plugins.add(plugin);

            }

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void startPlugins() {
        this.plugins.forEach(Plugin::onEnable);
    }

    @Override
    public void stopPlugins() {
        this.plugins.forEach(Plugin::onDisable);
    }

    @Override
    public void registerCommands(CommandsRepository commandsRepository) {

        List<Command> commands = this.plugins.stream()
                .map(Plugin::getCommands)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        commandsRepository.registerCommands(commands);
    }

    @Override
    public void registerListeners(ListenersRepository listenersRepository) {

        List<Listener> listeners = this.plugins.stream()
                .map(Plugin::getListeners)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        listenersRepository.registerListeners(listeners);
    }

    @Override
    public List<Plugin> getPlugins() {
        return this.plugins;
    }

    @Override
    public Plugin getPluginByName(String name) {

        return this.plugins.stream()
                .filter(plugin -> plugin.getName().equalsIgnoreCase(name))
                .findAny()
                .orElseGet(FakePlugin::new);
    }

}

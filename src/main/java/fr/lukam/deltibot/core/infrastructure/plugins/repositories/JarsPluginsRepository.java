package fr.lukam.deltibot.core.infrastructure.plugins.repositories;

import fr.lukam.bot.api.bot.Plugin;
import fr.lukam.bot.api.entities.fakes.FakePlugin;
import fr.lukam.deltibot.core.domain.plugins.CommandsRepository;
import fr.lukam.deltibot.core.domain.plugins.ListenersRepository;
import fr.lukam.deltibot.core.domain.plugins.PluginsRepository;
import fr.lukam.deltibot.core.domain.plugins.model.Command;
import fr.lukam.deltibot.core.domain.plugins.model.Listener;
import fr.lukam.deltibot.core.infrastructure.plugins.adapters.CommandAdapter;
import fr.lukam.deltibot.core.infrastructure.plugins.adapters.ListenerAdapter;
import fr.lukam.deltibot.core.infrastructure.utils.YAMLParserUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JarsPluginsRepository implements PluginsRepository, fr.lukam.bot.api.repositories.PluginsRepository {

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
                .map(CommandAdapter::new)
                .collect(Collectors.toList());

        commandsRepository.registerCommands(commands);

    }

    @Override
    public void registerListeners(ListenersRepository listenersRepository) {

        List<Listener> listeners = this.plugins.stream()
                .map(Plugin::getListeners)
                .flatMap(List::stream)
                .map(ListenerAdapter::new)
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

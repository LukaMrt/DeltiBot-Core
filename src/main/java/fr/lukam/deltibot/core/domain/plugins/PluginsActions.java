package fr.lukam.deltibot.core.domain.plugins;

import fr.lukam.bot.api.repositories.CommandsRepository;
import fr.lukam.bot.api.repositories.ListenersRepository;
import fr.lukam.bot.api.repositories.PluginsRepository;

public class PluginsActions implements ManagePlugins {

    private final PluginsRepository pluginsRepository;

    public PluginsActions(PluginsRepository pluginsRepository) {
        this.pluginsRepository = pluginsRepository;
    }

    @Override
    public void loadPlugins() {
        this.pluginsRepository.loadPlugins();
    }

    @Override
    public void startPlugins() {
        this.pluginsRepository.startPlugins();
    }

    @Override
    public void stopPlugins() {
        this.pluginsRepository.stopPlugins();
    }

    @Override
    public void registerCommands(CommandsRepository commandsRepository) {

        this.pluginsRepository.registerCommands(commandsRepository);

    }

    @Override
    public void registerListener(ListenersRepository listenersRepository) {

        this.pluginsRepository.registerListeners(listenersRepository);

    }

}

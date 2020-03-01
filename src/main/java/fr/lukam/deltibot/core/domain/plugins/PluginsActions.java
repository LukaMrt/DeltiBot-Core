package fr.lukam.deltibot.core.domain.plugins;

import fr.lukam.bot_api.bot.Plugin;

public class PluginsActions implements ManagePlugins {

    private final PluginsRepository pluginsRepository;

    public PluginsActions(PluginsRepository pluginsRepository) {
        this.pluginsRepository = pluginsRepository;
    }

    @Override
    public void registerCommands(CommandsRepository commandsRepository) {

        this.pluginsRepository.loadPlugins().stream()
                .map(Plugin::getCommands)
                .forEach(commandsRepository::registerCommands);

    }

    @Override
    public void registerListener(ListenersRepository listenersRepository) {

        this.pluginsRepository.loadPlugins().stream()
                .map(Plugin::getListeners)
                .forEach(listenersRepository::registerListeners);

    }

}

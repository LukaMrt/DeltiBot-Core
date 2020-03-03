package fr.lukam.deltibot.core.domain.plugins;

public interface PluginsRepository {

    void loadPlugins();

    void startPlugins();

    void stopPlugins();

    void registerCommands(CommandsRepository commandsRepository);

    void registerListeners(ListenersRepository listenersRepository);

}

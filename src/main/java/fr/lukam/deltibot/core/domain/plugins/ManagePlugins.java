package fr.lukam.deltibot.core.domain.plugins;

public interface ManagePlugins {

    void loadPlugins();

    void startPlugins();

    void stopPlugins();

    void registerCommands(CommandsRepository simpleCommandsRepository);

    void registerListener(ListenersRepository simpleListenersRepository);

}

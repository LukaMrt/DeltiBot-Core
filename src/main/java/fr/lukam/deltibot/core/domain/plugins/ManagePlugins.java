package fr.lukam.deltibot.core.domain.plugins;

public interface ManagePlugins {

    void registerCommands(CommandsRepository simpleCommandsRepository);

    void registerListener(ListenersRepository simpleListenersRepository);

}

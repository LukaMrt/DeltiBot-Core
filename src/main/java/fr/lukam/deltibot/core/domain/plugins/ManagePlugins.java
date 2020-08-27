package fr.lukam.deltibot.core.domain.plugins;

import fr.lukam.bot.api.repositories.CommandsRepository;
import fr.lukam.bot.api.repositories.ListenersRepository;

public interface ManagePlugins {

    void loadPlugins();

    void startPlugins();

    void stopPlugins();

    void registerCommands(CommandsRepository simpleCommandsRepository);

    void registerListener(ListenersRepository simpleListenersRepository);

}

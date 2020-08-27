package fr.lukam.deltibot.core.main;

import fr.lukam.bot.api.entities.interfaces.events.Listener;
import fr.lukam.bot.api.repositories.CommandsRepository;
import fr.lukam.bot.api.repositories.InfosRepository;
import fr.lukam.bot.api.repositories.ListenersRepository;
import fr.lukam.deltibot.core.domain.infos.SaveInfos;
import fr.lukam.deltibot.core.domain.plugins.ManagePlugins;

public interface ObjectsProvider {

    InfosRepository getInfosRepository();

    CommandsRepository getCommandsRepository();

    ListenersRepository getListenersRepository();

    ManagePlugins getPluginManager();

    SaveInfos getInfosSaver();

    Listener getCommandsListener();

    PluginsRepository getPluginsRepository();

}

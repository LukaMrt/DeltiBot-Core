package fr.lukam.deltibot.core.main;

import fr.lukam.deltibot.core.domain.infos.InfosRepository;
import fr.lukam.deltibot.core.domain.infos.SaveInfos;
import fr.lukam.deltibot.core.domain.plugins.CommandsRepository;
import fr.lukam.deltibot.core.domain.plugins.ListenersRepository;
import fr.lukam.deltibot.core.domain.plugins.ManagePlugins;
import fr.lukam.deltibot.core.domain.plugins.PluginsRepository;
import fr.lukam.deltibot.core.domain.plugins.model.Listener;

public interface ObjectsProvider {

    InfosRepository getInfosRepository();

    CommandsRepository getCommandsRepository();

    ListenersRepository getListenersRepository();

    ManagePlugins getPluginManager();

    SaveInfos getInfosSaver();

    Listener getCommandsListener();

    PluginsRepository getPluginsRepository();

}

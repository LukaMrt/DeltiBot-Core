package fr.lukam.deltibot.core;

import fr.lukam.deltibot.core.infrastructure.CommandsListener;
import fr.lukam.deltibot.core.domain.infos.SaveInfos;
import fr.lukam.deltibot.core.domain.plugins.CommandsRepository;
import fr.lukam.deltibot.core.domain.plugins.ListenersRepository;
import fr.lukam.deltibot.core.domain.plugins.ManagePlugins;

public interface ObjectsProvider {

    CommandsRepository getCommandsRepository();

    ListenersRepository getListenersRepository();

    ManagePlugins getPluginManager();

    SaveInfos getInfosSaver();

    CommandsListener getCommandsListener();

}

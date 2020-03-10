package fr.lukam.deltibot.core.domain.bot;

import fr.lukam.deltibot.core.domain.infos.SaveInfos;
import fr.lukam.deltibot.core.domain.infos.data.ArrayData;
import fr.lukam.deltibot.core.domain.infos.data.SimpleData;
import fr.lukam.deltibot.core.domain.plugins.CommandsRepository;
import fr.lukam.deltibot.core.domain.plugins.ListenersRepository;
import fr.lukam.deltibot.core.domain.plugins.ManagePlugins;
import fr.lukam.deltibot.core.domain.plugins.model.Listener;

import java.util.Collections;

public class DeltiBot implements Bot {

    private final CommandsRepository commandsRepository;
    private final ListenersRepository listenersRepository;

    private final ManagePlugins pluginsManager;
    private final SaveInfos infosSaver;

    public DeltiBot(DeltiBotBuilder builder) {
        this.commandsRepository = builder.commandsRepository;
        this.listenersRepository = builder.listenersRepository;
        this.pluginsManager = builder.managePlugins;
        this.infosSaver = builder.saveInfos;
    }

    @Override
    public void registerInfos(BotInfos botInfos) {

        this.infosSaver.registerData(new SimpleData("prefix", String.valueOf(botInfos.prefix)));
        this.infosSaver.registerData(new SimpleData("owner", botInfos.ownerId));
        this.infosSaver.registerData(new ArrayData("co_owners", botInfos.coOwnersIds));
        this.infosSaver.registerData(new SimpleData("main_server", botInfos.mainServerId));

    }

    @Override
    public void loadPlugins() {

        this.pluginsManager.loadPlugins();
        this.pluginsManager.registerCommands(this.commandsRepository);
        this.pluginsManager.registerListener(this.listenersRepository);
        this.pluginsManager.startPlugins();

    }

    @Override
    public void start(Listener commandsListener) {

        this.listenersRepository.registerListeners(Collections.singletonList(commandsListener));

    }

    @Override
    public void stop() {

        this.pluginsManager.stopPlugins();

    }

}

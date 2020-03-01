package fr.lukam.deltibot.core.domain.bot;

import fr.lukam.deltibot.core.domain.infos.SaveInfos;
import fr.lukam.deltibot.core.domain.infos.data.ArrayData;
import fr.lukam.deltibot.core.domain.infos.data.SimpleData;
import fr.lukam.deltibot.core.domain.plugins.CommandsRepository;
import fr.lukam.deltibot.core.domain.plugins.ListenersRepository;
import fr.lukam.deltibot.core.domain.plugins.ManagePlugins;

import java.util.Arrays;
import java.util.stream.Collectors;

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

        infosSaver.registerData(new SimpleData("prefix", String.valueOf(botInfos.prefix)));
        infosSaver.registerData(new SimpleData("owner", String.valueOf(botInfos.ownerId)));

        String[] coOwners = Arrays.stream(botInfos.coOwnersIds).mapToObj(String::valueOf).collect(Collectors.toList()).toArray(new String[]{});
        infosSaver.registerData(new ArrayData("co_owners", coOwners));

    }

    @Override
    public void loadPlugins() {

        pluginsManager.registerCommands(commandsRepository);
        pluginsManager.registerListener(listenersRepository);

    }

}

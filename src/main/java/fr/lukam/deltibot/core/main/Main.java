package fr.lukam.deltibot.core.main;

import fr.lukam.bot.api.entities.interfaces.events.Listener;
import fr.lukam.deltibot.core.domain.bot.BotInfos;
import fr.lukam.deltibot.core.domain.bot.Bot;

import static fr.lukam.deltibot.core.domain.bot.DeltiBotBuilder.aDeltiBot;

public class Main {

    private final Bot bot;
    private final Listener commandsListener;

    public Main(ObjectsProvider provider) {

        this.commandsListener = provider.getCommandsListener();

        this.bot = aDeltiBot()
                .withCommandsRepository(provider.getCommandsRepository())
                .withListenersRepository(provider.getListenersRepository())
                .withPluginManager(provider.getPluginManager())
                .withInfosSaver(provider.getInfosSaver())
                .build();
    }

    public void start(BotInfos botInfos) {

        bot.loadPlugins();
        bot.registerInfos(botInfos);
        bot.start(commandsListener);

    }

    public void stop() {

        bot.stop();

    }

}

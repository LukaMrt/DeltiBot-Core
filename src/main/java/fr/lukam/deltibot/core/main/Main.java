package fr.lukam.deltibot.core.main;

import fr.lukam.deltibot.core.domain.bot.BotInfos;
import fr.lukam.deltibot.core.domain.bot.Bot;

import static fr.lukam.deltibot.core.domain.bot.DeltiBotBuilder.aDeltiBot;

public class Main {

    private final Bot bot;
    private ObjectsProvider provider;

    public Main(ObjectsProvider provider) {

        System.out.println("From main : " + provider.getCommandsRepository());

        bot = aDeltiBot()
                .withCommandsRepository(provider.getCommandsRepository())
                .withListenersRepository(provider.getListenersRepository())
                .withPluginManager(provider.getPluginManager())
                .withInfosSaver(provider.getInfosSaver())
                .build();

        this.provider = provider;
    }

    public void start(BotInfos botInfos) {

        bot.loadPlugins();
        bot.registerInfos(botInfos);
        bot.start(provider.getCommandsListener());

    }

    public void stop() {

        bot.stop();

    }

}

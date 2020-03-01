package fr.lukam.deltibot.core;

import fr.lukam.deltibot.core.domain.bot.BotInfos;
import fr.lukam.deltibot.core.domain.bot.Bot;

import static fr.lukam.deltibot.core.domain.bot.DeltiBotBuilder.aDeltiBot;

public class Main {

    private final Bot bot;

    public Main(ObjectsProvider provider) {

        bot = aDeltiBot()
                .withCommandsRepository(provider.getCommandsRepository())
                .withListenersRepository(provider.getListenersRepository())
                .withPluginManager(provider.getPluginManager())
                .withInfosSaver(provider.getInfosSaver())
                .build();

    }

    public void run(BotInfos botInfos) {

        bot.loadPlugins();
        bot.registerInfos(botInfos);

    }

}

package fr.lukam.deltibot.core.domain.bot;

import fr.lukam.deltibot.core.infrastructure.CommandsListener;

public interface Bot {

    void registerInfos(BotInfos botInfos);

    void loadPlugins();

    void start(CommandsListener commandsListener);

    void stop();

}

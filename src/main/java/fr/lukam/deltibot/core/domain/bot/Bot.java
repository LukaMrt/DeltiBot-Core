package fr.lukam.deltibot.core.domain.bot;

import fr.lukam.bot.api.entities.interfaces.events.Listener;

public interface Bot {

    void registerInfos(BotInfos botInfos);

    void loadPlugins();

    void start(Listener commandsListener);

    void stop();

}

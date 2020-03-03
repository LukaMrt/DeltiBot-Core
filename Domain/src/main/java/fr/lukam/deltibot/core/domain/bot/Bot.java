package fr.lukam.deltibot.core.domain.bot;

public interface Bot {

    void registerInfos(BotInfos botInfos);

    void loadPlugins();

    void start(CommandsListener commandsListener);

    void stop();

}

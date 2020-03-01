package fr.lukam.deltibot.core.domain.bot;

import fr.lukam.deltibot.core.BotInfos;

public interface Bot {

    void registerInfos(BotInfos botInfos);

    void loadPlugins();

}

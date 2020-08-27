package fr.lukam.deltibot.core.domain.bot;

import fr.lukam.bot.api.repositories.CommandsRepository;
import fr.lukam.bot.api.repositories.ListenersRepository;
import fr.lukam.deltibot.core.domain.infos.SaveInfos;
import fr.lukam.deltibot.core.domain.plugins.ManagePlugins;

public class DeltiBotBuilder {

    public CommandsRepository commandsRepository;
    public ListenersRepository listenersRepository;
    public ManagePlugins managePlugins;
    public SaveInfos saveInfos;

    private DeltiBotBuilder() {
    }

    public static DeltiBotBuilder aDeltiBot() {
        return new DeltiBotBuilder();
    }

    public DeltiBotBuilder withCommandsRepository(CommandsRepository commandsRepository) {
        this.commandsRepository = commandsRepository;
        return this;
    }

    public DeltiBotBuilder withListenersRepository(ListenersRepository listenersRepository) {
        this.listenersRepository = listenersRepository;
        return this;
    }

    public DeltiBotBuilder withPluginManager(ManagePlugins managePlugins) {
        this.managePlugins = managePlugins;
        return this;
    }

    public DeltiBotBuilder withInfosSaver(SaveInfos saveInfos) {
        this.saveInfos = saveInfos;
        return this;
    }

    public DeltiBot build() {
        return new DeltiBot(this);
    }

}

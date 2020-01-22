package fr.lukam.deltibot.model.entities.channels.types;

import fr.lukam.bot_api.entities.interfaces.channels.ChannelType;

public class GuildTextChannel implements ChannelType {

    @Override
    public String getType() {
        return "Guild text";
    }

    @Override
    public boolean isFake() {
        return false;
    }

}

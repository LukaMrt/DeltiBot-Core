package fr.lukam.deltibot.model.entities.channels.types;

import fr.lukam.bot_api.entities.interfaces.channels.ChannelType;

public class GuildVoice implements ChannelType {

    @Override
    public String getType() {
        return "Guild voice";
    }

    @Override
    public boolean isFake() {
        return false;
    }

}

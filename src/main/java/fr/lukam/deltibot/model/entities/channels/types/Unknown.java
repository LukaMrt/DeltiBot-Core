package fr.lukam.deltibot.model.entities.channels.types;

import fr.lukam.bot_api.entities.interfaces.channels.ChannelType;

public class Unknown implements ChannelType {

    @Override
    public String getType() {
        return "Unknown";
    }

    @Override
    public boolean isFake() {
        return false;
    }

}

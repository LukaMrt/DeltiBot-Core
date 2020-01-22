package fr.lukam.deltibot.model.entities.channels.types;

import fr.lukam.bot_api.entities.interfaces.channels.ChannelType;

public class Category implements ChannelType {

    @Override
    public String getType() {
        return "Category";
    }

    @Override
    public boolean isFake() {
        return false;
    }

}

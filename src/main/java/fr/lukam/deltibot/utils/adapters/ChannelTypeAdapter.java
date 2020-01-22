package fr.lukam.deltibot.utils.adapters;

import fr.lukam.bot_api.entities.interfaces.channels.ChannelType;
import fr.lukam.deltibot.model.entities.channels.types.*;

public class ChannelTypeAdapter {

    public static ChannelType fromJDAChannelType(net.dv8tion.jda.api.entities.ChannelType jdaChannelType) {

        switch (jdaChannelType) {

            case TEXT:
                return new GuildTextChannel();

            case GROUP: case PRIVATE:
                return new PrivateChannel();

            case VOICE:
                return new GuildVoice();

            case CATEGORY:
                return new Category();

            case STORE: case UNKNOWN:
                return new Unknown();

            default:
                throw new IllegalStateException("Unexpected value: " + jdaChannelType);

        }

    }

}

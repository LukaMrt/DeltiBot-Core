package fr.lukam.deltibot.model.entities.server.permissions.voice;

import fr.lukam.bot_api.entities.interfaces.server.Permission;

public class VoiceConnect implements Permission {

    @Override
    public String getName() {
        return "Voice connect";
    }

}

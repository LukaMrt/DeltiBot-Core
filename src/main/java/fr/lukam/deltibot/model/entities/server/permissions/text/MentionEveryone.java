package fr.lukam.deltibot.model.entities.server.permissions.text;

import fr.lukam.bot_api.entities.interfaces.server.Permission;

public class MentionEveryone implements Permission {

    @Override
    public String getName() {
        return "Mention everyone";
    }

}

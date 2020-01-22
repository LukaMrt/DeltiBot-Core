package fr.lukam.deltibot.model.entities.server.permissions.members;

import fr.lukam.bot_api.entities.interfaces.server.Permission;

public class ChangeNickname implements Permission {

    @Override
    public String getName() {
        return "Change nickname";
    }

}


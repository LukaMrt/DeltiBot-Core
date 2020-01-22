package fr.lukam.deltibot.model.entities.server.permissions.manage;

import fr.lukam.bot_api.entities.interfaces.server.Permission;

public class ManageNickname implements Permission {

    @Override
    public String getName() {
        return "Manage nicknames";
    }

}

package fr.lukam.deltibot.model.entities.server.permissions.manage;

import fr.lukam.bot_api.entities.interfaces.server.Permission;

public class ManageServer implements Permission {

    @Override
    public String getName() {
        return "Manage Server";
    }

}

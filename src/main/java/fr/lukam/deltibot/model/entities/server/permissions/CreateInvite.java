package fr.lukam.deltibot.model.entities.server.permissions;

import fr.lukam.bot_api.entities.interfaces.server.Permission;

public class CreateInvite implements Permission {

    @Override
    public String getName() {
        return "Create instant invite";
    }

}

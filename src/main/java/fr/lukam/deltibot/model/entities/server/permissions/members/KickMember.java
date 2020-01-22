package fr.lukam.deltibot.model.entities.server.permissions.members;

import fr.lukam.bot_api.entities.interfaces.server.Permission;

public class KickMember implements Permission {

    @Override
    public String getName() {
        return "Kick member";
    }

}

package fr.lukam.deltibot.core.domain.commands;

import fr.lukam.bot_api.entities.interfaces.events.CommandEvent;

public interface ExecuteCommand {

    void execute(CommandEvent commandEvent);

}

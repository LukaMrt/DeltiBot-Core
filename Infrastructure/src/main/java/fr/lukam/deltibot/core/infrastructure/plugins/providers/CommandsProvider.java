package fr.lukam.deltibot.core.infrastructure.plugins.providers;

import fr.lukam.bot_api.entities.interfaces.commands.Command;

import java.util.List;
import java.util.Optional;

public interface CommandsProvider {

    Optional<Command> getCommandByName(String name);

    List<Command> getCommands();

}

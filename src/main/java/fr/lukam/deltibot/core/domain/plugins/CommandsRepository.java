package fr.lukam.deltibot.core.domain.plugins;

import fr.lukam.bot_api.entities.interfaces.commands.Command;

import java.util.List;
import java.util.Optional;

public interface CommandsRepository {

    Optional<Command> getCommandByName(String name);

    List<Command> getCommands();

    void registerCommands(List<Command> commands);

}

package fr.lukam.deltibot.core.domain.plugins;

import fr.lukam.bot_api.entities.interfaces.commands.Command;

import java.util.List;

public interface CommandsRepository {

    void registerCommands(List<Command> commands);

}

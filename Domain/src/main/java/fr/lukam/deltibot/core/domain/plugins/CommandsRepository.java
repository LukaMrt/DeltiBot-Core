package fr.lukam.deltibot.core.domain.plugins;

import fr.lukam.deltibot.core.domain.plugins.model.Command;

import java.util.List;

public interface CommandsRepository {

    void registerCommands(List<Command> commands);

}

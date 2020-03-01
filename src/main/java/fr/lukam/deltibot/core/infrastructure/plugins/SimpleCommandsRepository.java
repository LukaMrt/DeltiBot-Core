package fr.lukam.deltibot.core.infrastructure.plugins;

import fr.lukam.bot_api.entities.interfaces.commands.Command;
import fr.lukam.deltibot.core.domain.plugins.CommandsRepository;

import java.util.ArrayList;
import java.util.List;

public class SimpleCommandsRepository implements CommandsRepository {

    private final List<Command> commands = new ArrayList<>();

    @Override
    public void registerCommands(List<Command> commands) {
        this.commands.addAll(commands);
    }

}

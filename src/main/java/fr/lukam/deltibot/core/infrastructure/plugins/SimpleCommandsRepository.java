package fr.lukam.deltibot.core.infrastructure.plugins;

import fr.lukam.bot_api.entities.interfaces.commands.Command;
import fr.lukam.deltibot.core.domain.plugins.CommandsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SimpleCommandsRepository implements CommandsRepository {

    private final List<Command> commands = new ArrayList<>();

    @Override
    public Optional<Command> getCommandByName(String name) {
        return this.commands.stream()
                .filter(command -> command.isCommand(name))
                .findAny();
    }

    @Override
    public List<Command> getCommands() {
        return this.commands;
    }

    @Override
    public void registerCommands(List<Command> commands) {
        this.commands.addAll(commands);
    }

}

package fr.lukam.deltibot.core.infrastructure.plugins.repositories;

import fr.lukam.deltibot.core.domain.plugins.CommandsRepository;
import fr.lukam.deltibot.core.domain.plugins.model.Command;
import fr.lukam.deltibot.core.infrastructure.plugins.adapters.CommandAdapter;
import fr.lukam.deltibot.core.infrastructure.plugins.providers.CommandsProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SimpleCommandsRepository implements CommandsRepository, CommandsProvider {

    private final List<fr.lukam.bot_api.entities.interfaces.commands.Command> commands = new ArrayList<>();

    @Override
    public Optional<fr.lukam.bot_api.entities.interfaces.commands.Command> getCommandByName(String name) {
        return this.commands.stream()
                .filter(command -> command.isCommand(name))
                .findAny();
    }

    @Override
    public List<fr.lukam.bot_api.entities.interfaces.commands.Command> getCommands() {
        return this.commands;
    }

    @Override
    public void registerCommands(List<Command> commands) {

        commands.stream()
                .map(command -> (CommandAdapter) command)
                .map(CommandAdapter::getAPICommand)
                .forEach(this.commands::add);

    }

}

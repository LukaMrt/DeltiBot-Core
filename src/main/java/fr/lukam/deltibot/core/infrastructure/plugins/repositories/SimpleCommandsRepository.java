package fr.lukam.deltibot.core.infrastructure.plugins.repositories;

import fr.lukam.bot.api.entities.fakes.commands.FakeCommand;
import fr.lukam.deltibot.core.domain.plugins.CommandsRepository;
import fr.lukam.deltibot.core.domain.plugins.model.Command;
import fr.lukam.deltibot.core.infrastructure.plugins.adapters.CommandAdapter;

import java.util.ArrayList;
import java.util.List;

public class SimpleCommandsRepository implements CommandsRepository, fr.lukam.bot.api.repositories.CommandsRepository {

    private final List<fr.lukam.bot.api.entities.interfaces.commands.Command> commands = new ArrayList<>();

    @Override
    public fr.lukam.bot.api.entities.interfaces.commands.Command getCommandByName(String name) {
        return this.commands.stream()
                .filter(command -> command.isCommand(name))
                .findAny()
                .orElseGet(FakeCommand::new);
    }

    @Override
    public List<fr.lukam.bot.api.entities.interfaces.commands.Command> getCommands() {
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

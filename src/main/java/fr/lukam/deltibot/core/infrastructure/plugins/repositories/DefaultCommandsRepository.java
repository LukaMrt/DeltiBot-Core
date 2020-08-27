package fr.lukam.deltibot.core.infrastructure.plugins.repositories;

import fr.lukam.bot.api.entities.fakes.commands.FakeCommand;
import fr.lukam.bot.api.entities.interfaces.commands.Command;
import fr.lukam.bot.api.repositories.CommandsRepository;

import java.util.ArrayList;
import java.util.List;

public class DefaultCommandsRepository implements CommandsRepository {

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

        this.commands.addAll(commands);

    }

}

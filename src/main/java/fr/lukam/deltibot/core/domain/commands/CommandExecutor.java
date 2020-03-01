package fr.lukam.deltibot.core.domain.commands;

import fr.lukam.bot_api.entities.interfaces.events.CommandEvent;
import fr.lukam.deltibot.core.domain.infos.InfosRepository;
import fr.lukam.deltibot.core.domain.plugins.CommandsRepository;

public class CommandExecutor implements ExecuteCommand {

    private final InfosRepository infosRepository;
    private final CommandsRepository commandsRepository;

    public CommandExecutor(InfosRepository infosRepository, CommandsRepository commandsRepository) {
        this.infosRepository = infosRepository;
        this.commandsRepository = commandsRepository;
    }

    @Override
    public void execute(CommandEvent commandEvent) {


    }

}

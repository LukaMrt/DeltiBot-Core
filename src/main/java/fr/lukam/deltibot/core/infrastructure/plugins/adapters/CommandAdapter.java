package fr.lukam.deltibot.core.infrastructure.plugins.adapters;

import fr.lukam.deltibot.core.domain.plugins.model.Command;

public class CommandAdapter implements Command {

    private final fr.lukam.bot_api.entities.interfaces.commands.Command command;

    public CommandAdapter(fr.lukam.bot_api.entities.interfaces.commands.Command command) {
        this.command = command;
    }

    public fr.lukam.bot_api.entities.interfaces.commands.Command getAPICommand() {
        return this.command;
    }

}

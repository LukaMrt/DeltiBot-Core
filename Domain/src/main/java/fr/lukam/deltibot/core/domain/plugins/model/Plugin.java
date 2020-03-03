package fr.lukam.deltibot.core.domain.plugins.model;

import java.util.List;

public interface Plugin {

    List<Command> getCommands();

    List<Listener> getListeners();

}

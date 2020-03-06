package fr.lukam.deltibot.core.infrastructure.plugins.providers;

import fr.lukam.bot_api.entities.interfaces.events.Listener;

import java.util.List;

public interface ListenerProvider {

    List<Listener> getListeners();

}

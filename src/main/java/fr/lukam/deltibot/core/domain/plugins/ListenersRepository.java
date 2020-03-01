package fr.lukam.deltibot.core.domain.plugins;

import fr.lukam.bot_api.entities.interfaces.events.Listener;

import java.util.List;

public interface ListenersRepository {

    List<Listener> getListeners();

    void registerListeners(List<Listener> listeners);

}

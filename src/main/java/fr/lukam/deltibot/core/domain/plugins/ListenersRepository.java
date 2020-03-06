package fr.lukam.deltibot.core.domain.plugins;

import fr.lukam.deltibot.core.domain.plugins.model.Listener;

import java.util.List;

public interface ListenersRepository {

    void registerListeners(List<Listener> listeners);

}

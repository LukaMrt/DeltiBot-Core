package fr.lukam.deltibot.core.infrastructure.plugins;

import fr.lukam.bot_api.entities.interfaces.events.Listener;
import fr.lukam.deltibot.core.domain.plugins.ListenersRepository;

import java.util.ArrayList;
import java.util.List;

public class SimpleListenersRepository implements ListenersRepository {

    private List<Listener> listeners = new ArrayList<>();

    @Override
    public List<Listener> getListeners() {
        return this.listeners;
    }

    @Override
    public void registerListeners(List<Listener> listeners) {
        this.listeners.addAll(listeners);
    }

}

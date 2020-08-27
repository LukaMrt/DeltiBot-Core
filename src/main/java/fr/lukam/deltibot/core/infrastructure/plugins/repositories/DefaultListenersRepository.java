package fr.lukam.deltibot.core.infrastructure.plugins.repositories;

import fr.lukam.bot.api.entities.fakes.events.FakeListener;
import fr.lukam.bot.api.entities.interfaces.events.Listener;
import fr.lukam.bot.api.repositories.ListenersRepository;

import java.util.ArrayList;
import java.util.List;

public class DefaultListenersRepository implements ListenersRepository {

    private final List<Listener> listeners = new ArrayList<>();

    @Override
    public List<Listener> getListeners() {
        return this.listeners;
    }

    @Override
    public Listener getListenerByName(String name) {
        return this.listeners.stream()
                .filter(plugin -> listeners.getClass().getName().equalsIgnoreCase(name))
                .findAny()
                .orElseGet(FakeListener::new);
    }

    @Override
    public void registerListeners(List<Listener> listeners) {
        this.listeners.addAll(listeners);
    }

}

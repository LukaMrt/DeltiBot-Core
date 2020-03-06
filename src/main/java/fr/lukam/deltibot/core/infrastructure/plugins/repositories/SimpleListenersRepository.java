package fr.lukam.deltibot.core.infrastructure.plugins.repositories;

import fr.lukam.bot.api.entities.fakes.events.FakeListener;
import fr.lukam.deltibot.core.domain.plugins.ListenersRepository;
import fr.lukam.deltibot.core.domain.plugins.model.Listener;
import fr.lukam.deltibot.core.infrastructure.plugins.adapters.ListenerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SimpleListenersRepository implements ListenersRepository, fr.lukam.bot.api.repositories.ListenersRepository {

    private List<fr.lukam.bot.api.entities.interfaces.events.Listener> listeners = new ArrayList<>();

    @Override
    public List<fr.lukam.bot.api.entities.interfaces.events.Listener> getListeners() {
        return this.listeners;
    }

    @Override
    public fr.lukam.bot.api.entities.interfaces.events.Listener getListenerByName(String name) {
        return this.listeners.stream()
                .filter(plugin -> listeners.getClass().getName().equalsIgnoreCase(name))
                .findAny()
                .orElseGet(FakeListener::new);
    }

    @Override
    public void registerListeners(List<Listener> listeners) {

        listeners.stream()
                .map(command -> (ListenerAdapter) command)
                .map(ListenerAdapter::getAPIListener)
                .forEach(this.listeners::add);


    }

}

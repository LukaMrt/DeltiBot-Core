package fr.lukam.deltibot.core.infrastructure.plugins.repositories;

import fr.lukam.deltibot.core.domain.plugins.ListenersRepository;
import fr.lukam.deltibot.core.domain.plugins.model.Listener;
import fr.lukam.deltibot.core.infrastructure.plugins.adapters.ListenerAdapter;
import fr.lukam.deltibot.core.infrastructure.plugins.providers.ListenerProvider;

import java.util.ArrayList;
import java.util.List;

public class SimpleListenersRepository implements ListenersRepository, ListenerProvider {

    private List<fr.lukam.bot_api.entities.interfaces.events.Listener> listeners = new ArrayList<>();

    @Override
    public List<fr.lukam.bot_api.entities.interfaces.events.Listener> getListeners() {
        return this.listeners;
    }

    @Override
    public void registerListeners(List<Listener> listeners) {

        listeners.stream()
                .map(command -> (ListenerAdapter) command)
                .map(ListenerAdapter::getAPIListener)
                .forEach(this.listeners::add);


    }

}

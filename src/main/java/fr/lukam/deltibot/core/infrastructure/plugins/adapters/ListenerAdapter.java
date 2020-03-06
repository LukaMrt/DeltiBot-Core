package fr.lukam.deltibot.core.infrastructure.plugins.adapters;

import fr.lukam.deltibot.core.domain.plugins.model.Listener;

public class ListenerAdapter implements Listener {

    private final fr.lukam.bot.api.entities.interfaces.events.Listener listener;

    public ListenerAdapter(fr.lukam.bot.api.entities.interfaces.events.Listener listener) {
        this.listener = listener;
    }

    public fr.lukam.bot.api.entities.interfaces.events.Listener getAPIListener() {
        return this.listener;
    }

}


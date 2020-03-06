package fr.lukam.deltibot.core.infrastructure.plugins.adapters;

import fr.lukam.deltibot.core.domain.plugins.model.Command;
import fr.lukam.deltibot.core.domain.plugins.model.Listener;
import fr.lukam.deltibot.core.domain.plugins.model.Plugin;

import java.util.List;
import java.util.stream.Collectors;

public class PluginAdapter implements Plugin {

    private final fr.lukam.bot.api.bot.Plugin plugin;

    public PluginAdapter(fr.lukam.bot.api.bot.Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<Command> getCommands() {

        return this.plugin.getCommands().stream()
                .map(CommandAdapter::new)
                .collect(Collectors.toList());

    }

    @Override
    public List<Listener> getListeners() {

        return this.plugin.getListeners().stream()
                .map(ListenerAdapter::new)
                .collect(Collectors.toList());

    }

}

package fr.lukam.deltibot.core.infrastructure.plugins;

import fr.lukam.bot_api.bot.Plugin;
import fr.lukam.deltibot.core.domain.plugins.PluginsRepository;

import java.util.ArrayList;
import java.util.List;

public class JarsPluginsRepository implements PluginsRepository {

    @Override
    public List<Plugin> loadPlugins() {
        return new ArrayList<>();
    }

}

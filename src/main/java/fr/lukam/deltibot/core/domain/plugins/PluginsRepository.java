package fr.lukam.deltibot.core.domain.plugins;

import fr.lukam.bot_api.bot.Plugin;

import java.util.List;

public interface PluginsRepository {

    List<Plugin> loadPlugins();

}

package fr.lukam.deltibot.core.domain.plugins;

import fr.lukam.bot_api.bot.Plugin;

import java.util.function.Consumer;

public interface PluginsRepository {

    void loadPlugins();

    void startPlugins();

    void stopPlugins();

    void forEach(Consumer<Plugin> consumer);

}

package fr.lukam.deltibot.core.infrastructure.plugins;

import fr.lukam.bot_api.bot.Plugin;
import fr.lukam.deltibot.core.domain.plugins.PluginsRepository;
import fr.lukam.deltibot.core.utils.YAMLParserUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class JarsPluginsRepository implements PluginsRepository {

    private final List<Plugin> plugins = new ArrayList<>();

    @Override
    public void loadPlugins() {
        try {

            File directory = new File("plugins/");

            if (directory.listFiles() == null) {
                return;
            }

            for (File file : directory.listFiles()) {

                if (file == null) {
                    continue;
                }

                URLClassLoader child = new URLClassLoader(new URL[]{file.toURI().toURL()}, this.getClass().getClassLoader());

                Class<?> aClass = Class.forName(YAMLParserUtils.getParsedYaml(file).get("main"), true, child);

                Plugin plugin = (Plugin) aClass.newInstance();
                this.plugins.add(plugin);

            }

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void startPlugins() {
        this.plugins.forEach(Plugin::onEnable);
    }

    @Override
    public void stopPlugins() {

        this.plugins.forEach(Plugin::onDisable);
    }

    @Override
    public void forEach(Consumer<Plugin> consumer) {
        this.plugins.forEach(consumer);
    }

}

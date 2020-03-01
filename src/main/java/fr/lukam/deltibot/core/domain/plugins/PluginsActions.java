package fr.lukam.deltibot.core.domain.plugins;

public class PluginsActions implements ManagePlugins {

    private final PluginsRepository pluginsRepository;

    public PluginsActions(PluginsRepository pluginsRepository) {
        this.pluginsRepository = pluginsRepository;
    }

    @Override
    public void loadPlugins() {
        this.pluginsRepository.loadPlugins();
    }

    @Override
    public void startPlugins() {
        this.pluginsRepository.startPlugins();
    }

    @Override
    public void stopPlugins() {
        this.pluginsRepository.stopPlugins();
    }

    @Override
    public void registerCommands(CommandsRepository commandsRepository) {

        this.pluginsRepository.forEach(plugin -> commandsRepository.registerCommands(plugin.getCommands()));

    }

    @Override
    public void registerListener(ListenersRepository listenersRepository) {

        this.pluginsRepository.forEach(plugin -> listenersRepository.registerListeners(plugin.getListeners()));

    }

}

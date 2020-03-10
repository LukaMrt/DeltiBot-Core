package fr.lukam.deltibot.core.domain.plugins;

public class PluginsActions implements ManagePlugins {

    private final PluginsRepository pluginsRepository;

    public PluginsActions(PluginsRepository pluginsRepository) {
        this.pluginsRepository = pluginsRepository;
    }

    @Override
    public void loadPlugins() {
        System.out.println("I load plugins");
        this.pluginsRepository.loadPlugins();
    }

    @Override
    public void startPlugins() {
        System.out.println("I start plugins");
        this.pluginsRepository.startPlugins();
    }

    @Override
    public void stopPlugins() {
        this.pluginsRepository.stopPlugins();
    }

    @Override
    public void registerCommands(CommandsRepository commandsRepository) {

        System.out.println("From domain : " + commandsRepository);
        this.pluginsRepository.registerCommands(commandsRepository);

    }

    @Override
    public void registerListener(ListenersRepository listenersRepository) {

        this.pluginsRepository.registerListeners(listenersRepository);

    }

}

package fr.lukam.deltibot.model.bot;

import fr.lukam.bot_api.bot.Bot;
import fr.lukam.bot_api.entities.fakes.server.FakeServer;
import fr.lukam.bot_api.entities.interfaces.server.Server;
import fr.lukam.bot_api.entities.interfaces.user.User;
import fr.lukam.deltibot.model.entities.server.JDAServer;
import fr.lukam.deltibot.model.entities.user.JDAUser;
import net.dv8tion.jda.api.JDA;

import java.util.Optional;

public class JDABot implements Bot {

    private final JDA jda;

    public JDABot(JDA jda) {
        this.jda = jda;
    }

    @Override
    public Server getServer(String serverId) {
        return Optional.ofNullable(jda.getGuildById(serverId))
                .map(guild -> (Server) new JDAServer(guild))
                .orElseGet(FakeServer::new);
    }

    @Override
    public User getSelfUser() {
        return new JDAUser(this.jda.getSelfUser());
    }

    @Override
    public User getUser(String userId) {
        return new JDAUser(this.jda.getUserById(userId));
    }

    @Override
    public boolean isFake() {
        return false;
    }

}

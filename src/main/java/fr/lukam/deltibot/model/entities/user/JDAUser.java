package fr.lukam.deltibot.model.entities.user;

import fr.lukam.bot_api.entities.interfaces.channels.TextChannel;
import fr.lukam.bot_api.entities.interfaces.message.Message;
import fr.lukam.bot_api.entities.interfaces.user.User;
import fr.lukam.deltibot.model.entities.channels.JDATextChannel;
import fr.lukam.deltibot.utils.adapters.EmbedAdapter;
import net.dv8tion.jda.api.MessageBuilder;

import java.time.LocalDateTime;

public class JDAUser implements User {

    private final net.dv8tion.jda.api.entities.User jdaUser;

    public JDAUser(net.dv8tion.jda.api.entities.User jdaUser) {
        this.jdaUser = jdaUser;
    }

    @Override
    public String getAvatarURL() {
        return this.jdaUser.getAvatarUrl();
    }

    @Override
    public String getAsTag() {
        return this.jdaUser.getAsTag();
    }

    @Override
    public TextChannel getPrivateChannel() {
        return new JDATextChannel(this.jdaUser.openPrivateChannel().complete());
    }

    @Override
    public void sendMessage(Message message) {
        net.dv8tion.jda.api.entities.Message jdaMessage = new MessageBuilder()
                .setEmbed(EmbedAdapter.fromAPIEmbed(message.getEmbed()))
                .setContent(message.getContent())
                .build();

        this.jdaUser.openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage(jdaMessage).queue());
    }

    @Override
    public LocalDateTime getCreateAccountTime() {
        return this.jdaUser.getTimeCreated().toLocalDateTime();
    }

    @Override
    public boolean isFake() {
        return false;
    }

    @Override
    public String getId() {
        return this.jdaUser.getId();
    }

    @Override
    public String getAsMention() {
        return this.jdaUser.getAsMention();
    }

    @Override
    public String getName() {
        return this.jdaUser.getName();
    }

}

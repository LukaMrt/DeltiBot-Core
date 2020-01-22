package fr.lukam.deltibot.model.entities.channels;

import fr.lukam.bot_api.entities.interfaces.channels.ServerTextChannel;
import fr.lukam.bot_api.entities.interfaces.message.Message;
import fr.lukam.deltibot.utils.adapters.EmbedAdapter;
import fr.lukam.deltibot.utils.adapters.MessageAdapter;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;
import java.util.stream.Collectors;

public class JDAGuildTextChannel extends JDAGuildChannel implements ServerTextChannel {

    private final TextChannel textChannel;

    public JDAGuildTextChannel(GuildChannel textChannel) {
        super(textChannel);
        this.textChannel = textChannel.getGuild().getTextChannelById(textChannel.getId());
    }

    @Override
    public void sendMessage(Message message) {
        this.textChannel.sendMessage(new MessageBuilder()
                .setContent(message.getContent())
                .setEmbed(EmbedAdapter.fromAPIEmbed(message.getEmbed()))
                .build()).queue();
    }

    @Override
    public Message getMessageById(String messageId) {
        net.dv8tion.jda.api.entities.Message jdaMessage = this.textChannel.retrieveMessageById(messageId).complete();
        return MessageAdapter.fromJDAMessage(jdaMessage); // TODO : catch null
    }

    @Override
    public List<Message> getHistoryBefore(Message message, boolean include, int count) {
        List<net.dv8tion.jda.api.entities.Message> jdaMessages = this.textChannel.getHistoryBefore(message.getId(), count).complete().getRetrievedHistory();
        List<Message> apiMessages = jdaMessages.stream()
                .map(MessageAdapter::fromJDAMessage)
                .collect(Collectors.toList());

        if (include) {
            apiMessages.add(message);
        }

        return apiMessages;
    }

    @Override
    public List<Message> getHistoryAfter(Message message, boolean include, int count) {
        List<net.dv8tion.jda.api.entities.Message> jdaMessages = this.textChannel.getHistoryAfter(message.getId(), count).complete().getRetrievedHistory();
        List<Message> apiMessages = jdaMessages.stream()
                .map(MessageAdapter::fromJDAMessage)
                .collect(Collectors.toList());

        if (include) {
            apiMessages.add(message);
        }

        return apiMessages;
    }

    @Override
    public List<Message> getPinnedMessages() {
        List<net.dv8tion.jda.api.entities.Message> jdaMessages = this.textChannel.retrievePinnedMessages().complete();

        return jdaMessages.stream()
                .map(MessageAdapter::fromJDAMessage)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isFake() {
        return false;
    }

    @Override
    public String getName() {
        return this.textChannel.getName();
    }

}
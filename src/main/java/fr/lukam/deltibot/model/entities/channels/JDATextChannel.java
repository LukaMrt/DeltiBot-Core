package fr.lukam.deltibot.model.entities.channels;

import fr.lukam.bot_api.entities.interfaces.channels.ChannelType;
import fr.lukam.bot_api.entities.interfaces.channels.TextChannel;
import fr.lukam.bot_api.entities.interfaces.message.Message;
import fr.lukam.deltibot.utils.adapters.ChannelTypeAdapter;
import fr.lukam.deltibot.utils.adapters.EmbedAdapter;
import fr.lukam.deltibot.utils.adapters.MessageAdapter;
import net.dv8tion.jda.api.MessageBuilder;

import java.util.List;
import java.util.stream.Collectors;

public class JDATextChannel implements TextChannel {

    private final net.dv8tion.jda.api.entities.MessageChannel jdaChannel;

    public JDATextChannel(net.dv8tion.jda.api.entities.MessageChannel jdaChannel) {
        this.jdaChannel = jdaChannel;
    }

    @Override
    public void sendMessage(Message message) {
        this.jdaChannel.sendMessage(new MessageBuilder()
                .setContent(message.getContent())
                .setEmbed(EmbedAdapter.fromAPIEmbed(message.getEmbed()))
                .build()).queue();
    }

    @Override
    public Message getMessageById(String messageId) {
        net.dv8tion.jda.api.entities.Message jdaMessage = this.jdaChannel.retrieveMessageById(messageId).complete();
        return MessageAdapter.fromJDAMessage(jdaMessage); // TODO : catch null
    }

    @Override
    public List<Message> getHistoryBefore(Message message, boolean include, int count) {
        List<net.dv8tion.jda.api.entities.Message> jdaMessages = this.jdaChannel.getHistoryBefore(message.getId(), count).complete().getRetrievedHistory();
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
        List<net.dv8tion.jda.api.entities.Message> jdaMessages = this.jdaChannel.getHistoryAfter(message.getId(), count).complete().getRetrievedHistory();
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
        List<net.dv8tion.jda.api.entities.Message> jdaMessages = this.jdaChannel.retrievePinnedMessages().complete();

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
        return this.jdaChannel.getName();
    }

    @Override
    public ChannelType getChannelType() {
        return ChannelTypeAdapter.fromJDAChannelType(this.jdaChannel.getType());
    }

}

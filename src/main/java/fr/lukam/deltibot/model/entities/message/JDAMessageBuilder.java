package fr.lukam.deltibot.model.entities.message;

import fr.lukam.bot_api.entities.fakes.message.FakeEmbed;
import fr.lukam.bot_api.entities.interfaces.message.Embed;
import fr.lukam.bot_api.entities.interfaces.message.Message;
import fr.lukam.bot_api.entities.interfaces.message.MessageBuilder;
import fr.lukam.deltibot.utils.adapters.EmbedAdapter;

public class JDAMessageBuilder implements MessageBuilder {

    private String text = ".";
    private Embed embed = new FakeEmbed();

    @Override
    public MessageBuilder aMessage() {
        return new JDAMessageBuilder();
    }

    @Override
    public MessageBuilder setText(String text) {
        this.text = text;
        return this;
    }

    @Override
    public MessageBuilder setEmbed(Embed embed) {
        this.embed = embed;
        return this;
    }

    @Override
    public Message build() {
        return new JDAMessage(new net.dv8tion.jda.api.MessageBuilder()
                .setEmbed(EmbedAdapter.fromAPIEmbed(embed))
                .setContent(text)
                .build());
    }

    @Override
    public boolean isFake() {
        return false;
    }

}

package fr.lukam.deltibot.model.entities.message;

import fr.lukam.bot_api.entities.interfaces.message.Embed;
import fr.lukam.bot_api.entities.interfaces.message.EmbedBuilder;
import fr.lukam.bot_api.entities.interfaces.message.Field;
import fr.lukam.bot_api.entities.interfaces.user.User;

import java.awt.*;
import java.util.Arrays;

public class JDAEmbedBuilder implements EmbedBuilder {

    net.dv8tion.jda.api.EmbedBuilder embedBuilder = new net.dv8tion.jda.api.EmbedBuilder();

    @Override
    public EmbedBuilder anEmbed() {
        return new JDAEmbedBuilder();
    }

    @Override
    public EmbedBuilder setImageURL(String url) {
        embedBuilder.setImage(url);
        return this;
    }

    @Override
    public EmbedBuilder setAuthor(User user) {
        embedBuilder.setAuthor(user.getName(), user.getAvatarURL(), user.getAvatarURL());
        return this;
    }

    @Override
    public EmbedBuilder addField(Field field) {
        embedBuilder.addField(field.getTitle(), field.getContent(), false);
        return this;
    }

    @Override
    public EmbedBuilder setFields(Field... fields) {
        Arrays.stream(fields).forEach(this::addField);
        return this;
    }

    @Override
    public EmbedBuilder clearFields() {
        embedBuilder.clearFields();
        return this;
    }

    @Override
    public EmbedBuilder setDescription(String description) {
        embedBuilder.setDescription(description);
        return this;
    }

    @Override
    public EmbedBuilder setColor(Color color) {
        embedBuilder.setColor(color);
        return this;
    }

    @Override
    public EmbedBuilder setFooter(String footer) {
        embedBuilder.setFooter(footer);
        return this;
    }

    @Override
    public EmbedBuilder setTitle(String title) {
        embedBuilder.setTitle(title);
        return this;
    }

    @Override
    public Embed build() {
        return new JDAEmbed(embedBuilder.build());
    }

    @Override
    public boolean isFake() {
        return false;
    }

}

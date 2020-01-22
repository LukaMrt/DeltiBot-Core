package fr.lukam.deltibot.model.entities.message;

import fr.lukam.bot_api.entities.interfaces.message.Embed;
import fr.lukam.bot_api.entities.interfaces.message.Field;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class JDAEmbed implements Embed {

    private final MessageEmbed embed;

    public JDAEmbed(MessageEmbed embed) {
        this.embed = embed;
    }

    @Override
    public String getAuthorName() {
        MessageEmbed.AuthorInfo author = embed.getAuthor();
        if (author != null) {
            return author.getName();
        }
        return "";
   }

    @Override
    public String getAuthorImageUrl() {
        MessageEmbed.AuthorInfo author = embed.getAuthor();
        if (author != null) {
            return author.getIconUrl();
        }
        return "";
    }

    @Override
    public String getTitle() {
        return this.embed.getTitle();
    }

    @Override
    public String getDescription() {
        return this.embed.getDescription();
    }

    @Override
    public Color getColor() {
        return this.embed.getColor();
    }

    @Override
    public List<Field> getFields() {
        return this.embed.getFields().stream()
                .map(field -> new JDAField(new MessageEmbed.Field(field.getName(), field.getValue(), false)))
                .collect(Collectors.toList());
    }

    @Override
    public String getFooter() {
        if (this.embed.getFooter() != null) {
            return this.embed.getFooter().getText();
        }
        return "";
    }

    @Override
    public String getImageURL() {
        if (this.embed.getImage() != null) {
            return this.embed.getImage().getUrl();
        }
        return "";
    }

    @Override
    public boolean isFake() {
        return false;
    }

}

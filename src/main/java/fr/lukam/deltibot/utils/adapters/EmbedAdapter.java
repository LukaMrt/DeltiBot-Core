package fr.lukam.deltibot.utils.adapters;

import fr.lukam.bot_api.bot.API;
import fr.lukam.bot_api.entities.interfaces.message.Embed;
import fr.lukam.bot_api.entities.interfaces.message.EmbedBuilder;
import fr.lukam.deltibot.model.entities.message.JDAField;
import fr.lukam.deltibot.model.entities.user.JDAUser;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class EmbedAdapter {

    public static MessageEmbed fromAPIEmbed(Embed embed) {

        net.dv8tion.jda.api.EmbedBuilder embedBuilder = new net.dv8tion.jda.api.EmbedBuilder()
                .setAuthor(embed.getAuthorName()/*, embed.getAuthor().getAvatarURL(), embed.getAuthor().getAvatarURL()*/)
                .setDescription(embed.getDescription())
                .setColor(embed.getColor())
                .setImage(embed.getImageURL())
                .setTitle(embed.getTitle())
                .setFooter(embed.getFooter());

        embed.getFields().forEach(field -> embedBuilder.addField(field.getTitle(), field.getContent(), false));

        return embedBuilder.build();
    }

    public static Embed fromJDAEmbed(Message message) {

        MessageEmbed embed = message.getEmbeds().get(0);

        EmbedBuilder embedBuilder = API.createEmbed();
        embedBuilder.setAuthor(new JDAUser(message.getAuthor()));
        embedBuilder.setColor(embed.getColor());
        embedBuilder.setDescription(embed.getDescription());
        embedBuilder.setTitle(embed.getTitle());

        if (embed.getFooter() != null) {
            embedBuilder.setFooter(embed.getFooter().getText());
        }

        if (embed.getImage() != null) {
            embedBuilder.setImageURL(embed.getImage().getUrl());
        }

        embed.getFields().forEach(field -> embedBuilder.addField(new JDAField(field)));
        return embedBuilder.build();
    }

}

package fr.lukam.deltibot.model.entities.message;

import fr.lukam.bot_api.entities.interfaces.message.Field;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class JDAField implements Field {

    private final MessageEmbed.Field field;

    public JDAField(MessageEmbed.Field field) {
        this.field = field;
    }

    @Override
    public String getTitle() {
        return this.field.getName();
    }

    @Override
    public String getContent() {
        return this.field.getValue();
    }

}

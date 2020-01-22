package fr.lukam.deltibot.core;

import fr.lukam.bot_api.bot.API;
import fr.lukam.deltibot.model.bot.JDABot;
import fr.lukam.deltibot.model.entities.message.JDAEmbedBuilder;
import fr.lukam.deltibot.model.entities.message.JDAFieldBuilder;
import fr.lukam.deltibot.model.entities.message.JDAMessageBuilder;

public class Main {

    public static void main(String[] args) {

        API.setBot(new JDABot(null));
        API.setEmbedBuilder(new JDAEmbedBuilder());
        API.setMessageBuilder(new JDAMessageBuilder());
        API.setFieldBuilder(new JDAFieldBuilder());

    }

}

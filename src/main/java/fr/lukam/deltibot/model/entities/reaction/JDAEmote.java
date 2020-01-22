package fr.lukam.deltibot.model.entities.reaction;

import fr.lukam.bot_api.entities.interfaces.reaction.Emote;

public class JDAEmote implements Emote {

    private final boolean serverEmote;
    private final String id;
    private final String name;

    public JDAEmote(net.dv8tion.jda.api.entities.Emote emote) {
        this.serverEmote = true;
        this.id = emote.getId();
        this.name = emote.getName();
    }

    public JDAEmote(String name) {
        this.serverEmote = false;
        this.id = "";
        this.name = name;
    }

    @Override
    public boolean isServerEmote() {
        return serverEmote;
    }

    @Override
    public boolean isFake() {
        return false;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getAsMention() {
        if (this.id.equals("")) {
            return ":" + this.getName() + ":";
        }
        return "<:" + this.getName() + ":" + this.getId() + ">";
    }

    @Override
    public String getName() {
        return this.name;
    }

}

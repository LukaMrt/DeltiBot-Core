package fr.lukam.deltibot.model.entities.message;

import fr.lukam.bot_api.entities.fakes.message.FakeEmbed;
import fr.lukam.bot_api.entities.fakes.reaction.FakeReaction;
import fr.lukam.bot_api.entities.interfaces.channels.ServerTextChannel;
import fr.lukam.bot_api.entities.interfaces.message.Embed;
import fr.lukam.bot_api.entities.interfaces.message.Message;
import fr.lukam.bot_api.entities.interfaces.reaction.Emote;
import fr.lukam.bot_api.entities.interfaces.reaction.Reaction;
import fr.lukam.bot_api.entities.interfaces.server.Role;
import fr.lukam.bot_api.entities.interfaces.user.User;
import fr.lukam.deltibot.utils.adapters.EmbedAdapter;
import fr.lukam.deltibot.utils.adapters.MessageAdapter;
import fr.lukam.deltibot.model.entities.channels.JDAGuildTextChannel;
import fr.lukam.deltibot.model.entities.reaction.JDAReaction;
import fr.lukam.deltibot.model.entities.reaction.JDAEmote;
import fr.lukam.deltibot.model.entities.server.JDARole;
import fr.lukam.deltibot.model.entities.user.JDAUser;
import net.dv8tion.jda.internal.entities.EmoteImpl;
import net.dv8tion.jda.internal.entities.GuildImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class JDAMessage implements Message {

    private final net.dv8tion.jda.api.entities.Message message;

    public JDAMessage(net.dv8tion.jda.api.entities.Message message) {
        this.message = message;
    }

    @Override
    public String getContent() {
        return this.message.getContentDisplay();
    }

    @Override
    public Embed getEmbed() {
        if (this.message.getEmbeds().size() == 0) {
            return new FakeEmbed();
        }
        return EmbedAdapter.fromJDAEmbed(this.message);
    }

    @Override
    public String getLink() {
        return this.message.getJumpUrl();
    }

    @Override
    public LocalDateTime getDate() {
        return this.message.getTimeCreated().toLocalDateTime();
    }

    @Override
    public List<Reaction> getReactions() {
        return this.message.getReactions().stream()
                .map(JDAReaction::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getMentionedUsers() {
        return this.message.getMentionedUsers().stream()
                .map(JDAUser::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServerTextChannel> getMentionedChannels() {
        return this.message.getMentionedChannels().stream()
                .map(JDAGuildTextChannel::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<Role> getMentionedRoles() {
        return this.message.getMentionedRoles().stream()
                .map(JDARole::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<Emote> getEmotes() {
        return this.message.getEmotes().stream()
                .map(JDAEmote::new)
                .collect(Collectors.toList());
    }

    @Override
    public Reaction getReactionById(String emoteId) {
        return this.message.getReactions().stream()
                .filter(reaction -> reaction.getReactionEmote().getId().equalsIgnoreCase(emoteId))
                .map(reaction -> (Reaction) new JDAReaction(reaction))
                .findAny()
                .orElseGet(FakeReaction::new);
    }

    @Override
    public User getAuthor() {
        return new JDAUser(this.message.getAuthor());
    }

    @Override
    public void addReaction(Emote emote) { // TODO : to test
        this.message.addReaction(new EmoteImpl(Long.parseLong(emote.getId()), (GuildImpl) this.message.getGuild())).queue();
    }

    @Override
    public void removeReaction(Emote emote) {
        this.message.removeReaction(new EmoteImpl(Long.parseLong(emote.getId()), (GuildImpl) this.message.getGuild())).queue();
    }

    @Override
    public void pin() {
        this.message.pin().queue();
    }

    @Override
    public void unPin() {
        this.message.unpin().queue();
    }

    @Override
    public void edit(Message newMessage) {
        this.message.editMessage(MessageAdapter.fromAPIMessage(newMessage)).queue();
    }

    @Override
    public void delete() {
        this.message.delete().queue();
    }

    @Override
    public boolean isFake() {
        return false;
    }

    @Override
    public String getId() {
        return this.message.getId();
    }

}

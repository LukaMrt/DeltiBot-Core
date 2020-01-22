package fr.lukam.deltibot.model.entities.reaction;

import fr.lukam.bot_api.entities.interfaces.reaction.Emote;
import fr.lukam.bot_api.entities.interfaces.reaction.Reaction;
import fr.lukam.bot_api.entities.interfaces.user.User;
import fr.lukam.deltibot.model.entities.user.JDAUser;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageReaction;

import java.util.List;
import java.util.stream.Collectors;

public class JDAReaction implements Reaction {

    private final MessageReaction reaction;

    public JDAReaction(MessageReaction reaction) {
        this.reaction = reaction;
    }

    @Override
    public Emote getEmote() {
        MessageReaction.ReactionEmote reactionEmote = this.reaction.getReactionEmote();
        if (reactionEmote.isEmote()) {
            return new JDAEmote(reactionEmote.getEmote());
        }
        return new JDAEmote(reactionEmote.getName());
    }

    @Override
    public int getCount() {
        return this.reaction.getCount();
    }

    @Override
    public List<User> getUsers() {
        return this.reaction.retrieveUsers().complete().stream()
                .map(JDAUser::new)
                .collect(Collectors.toList());
    }

    @Override
    public void removeAll() {
//        this.reaction.retrieveUsers().queue(users ->
//                users.forEach(user ->
//                        this.reaction.removeReaction(user).queue()));
        this.reaction.removeReaction().queue(); // TODO : to test
    }

    @Override
    public void removeFromUser(User user) {

        if (this.reaction.getGuild() == null) {
            return;
        }

        Member member = this.reaction.getGuild().getMemberById(user.getId());

        if (member == null) {
            return;
        }

        this.reaction.removeReaction(member.getUser()).queue();
    }

    @Override
    public boolean isFake() {
        return false;
    }

}

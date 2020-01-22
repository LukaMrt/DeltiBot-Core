package fr.lukam.deltibot.model.entities.server;

import fr.lukam.bot_api.entities.fakes.server.FakeServerMember;
import fr.lukam.bot_api.entities.interfaces.server.Invite;
import fr.lukam.bot_api.entities.interfaces.server.ServerMember;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;

public class JDAInvite implements Invite {

    private final net.dv8tion.jda.api.entities.Invite jdaInvite;

    public JDAInvite(net.dv8tion.jda.api.entities.Invite jdaInvite) {
        this.jdaInvite = jdaInvite;
    }

    @Override
    public String getURL() {
        return this.jdaInvite.getUrl();
    }

    @Override
    public ServerMember getInviter() {

        if (this.jdaInvite.getGuild() == null) {
            return new FakeServerMember();
        }

        Guild guild = this.jdaInvite.getJDA().getGuildById(this.jdaInvite.getGuild().getId());

        if (guild == null || jdaInvite.getInviter() == null) {
            return new FakeServerMember();
        }

        Member member = guild.getMember(jdaInvite.getInviter());
        return new JDAServerMember(member);
    }

    @Override
    public boolean isFake() {
        return false;
    }

}

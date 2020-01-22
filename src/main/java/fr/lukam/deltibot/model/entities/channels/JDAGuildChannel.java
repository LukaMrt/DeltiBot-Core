package fr.lukam.deltibot.model.entities.channels;

import fr.lukam.bot_api.entities.interfaces.channels.ChannelType;
import fr.lukam.bot_api.entities.interfaces.channels.ServerChannel;
import fr.lukam.bot_api.entities.interfaces.server.Invite;
import fr.lukam.deltibot.utils.adapters.ChannelTypeAdapter;
import fr.lukam.deltibot.model.entities.server.JDAInvite;
import net.dv8tion.jda.api.entities.GuildChannel;

public class JDAGuildChannel implements ServerChannel {

    private final GuildChannel guildChannel;

    public JDAGuildChannel(GuildChannel guildChannel) {
        this.guildChannel = guildChannel;
    }

    @Override
    public Invite createInvite() {
        return new JDAInvite(this.guildChannel.createInvite().complete());
    }

    @Override
    public boolean isFake() {
        return false;
    }

    @Override
    public String getId() {
        return this.guildChannel.getId();
    }

    @Override
    public ChannelType getChannelType() {
        return ChannelTypeAdapter.fromJDAChannelType(this.guildChannel.getType());
    }

    @Override
    public String getName() {
        return this.guildChannel.getName();
    }

}

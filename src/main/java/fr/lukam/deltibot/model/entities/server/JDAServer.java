package fr.lukam.deltibot.model.entities.server;

import fr.lukam.bot_api.entities.fakes.channels.FakeServerTextChannel;
import fr.lukam.bot_api.entities.fakes.channels.FakeServerVoiceChannel;
import fr.lukam.bot_api.entities.fakes.reaction.FakeEmote;
import fr.lukam.bot_api.entities.fakes.server.FakeCategory;
import fr.lukam.bot_api.entities.fakes.server.FakeRole;
import fr.lukam.bot_api.entities.fakes.server.FakeServerMember;
import fr.lukam.bot_api.entities.interfaces.channels.ServerChannel;
import fr.lukam.bot_api.entities.interfaces.channels.ServerTextChannel;
import fr.lukam.bot_api.entities.interfaces.channels.ServerVoiceChannel;
import fr.lukam.bot_api.entities.interfaces.reaction.Emote;
import fr.lukam.bot_api.entities.interfaces.server.Category;
import fr.lukam.bot_api.entities.interfaces.server.Role;
import fr.lukam.bot_api.entities.interfaces.server.Server;
import fr.lukam.bot_api.entities.interfaces.server.ServerMember;
import fr.lukam.deltibot.model.entities.channels.JDAGuildTextChannel;
import fr.lukam.deltibot.model.entities.channels.JDAGuildVoiceChannel;
import fr.lukam.deltibot.model.entities.reaction.JDAEmote;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JDAServer implements Server {

    private final Guild jdaServer;

    public JDAServer(Guild jdaServer) {
        this.jdaServer = jdaServer;
    }

    @Override
    public String getDescription() {
        return this.jdaServer.getDescription();
    }

    @Override
    public ServerMember getOwner() {
        return new JDAServerMember(this.jdaServer.getOwner());
    }

    @Override
    public ServerMember getSelfMember() {
        return new JDAServerMember(this.jdaServer.getSelfMember());
    }

    @Override
    public ServerTextChannel getDefaultChannel() {
        return new JDAGuildTextChannel(this.jdaServer.getDefaultChannel());
    }

    @Override
    public Role getDefaultRole() {
        return new JDARole(this.jdaServer.getPublicRole());
    }

    @Override
    public List<ServerMember> getMembers() {
        return this.jdaServer.getMembers().stream()
                .map(JDAServerMember::new)
                .collect(Collectors.toList());
    }

    @Override
    public ServerMember getMemberById(String memberId) {
        return Optional.ofNullable(this.jdaServer.getMemberById(memberId))
                .map(member -> (ServerMember) new JDAServerMember(member))
                .orElseGet(FakeServerMember::new);
    }

    @Override
    public String getIconURLl() {
        return this.jdaServer.getIconUrl();
    }

    @Override
    public List<Role> getRoles() {
        return this.jdaServer.getRoles().stream()
                .map(JDARole::new)
                .collect(Collectors.toList());
    }

    @Override
    public Role getRoleById(String roleId) {
        return Optional.ofNullable(this.jdaServer.getRoleById(roleId))
                .map(role -> (Role) new JDARole(role))
                .orElseGet(FakeRole::new);
    }

    @Override
    public List<Emote> getCustomEmotes() {
        return this.jdaServer.getEmotes().stream()
                .map(JDAEmote::new)
                .collect(Collectors.toList());
    }

    @Override
    public Emote getEmoteById(String emoteId) {
        return Optional.ofNullable(this.jdaServer.getEmoteById(emoteId))
                .map(emote -> (Emote) new JDAEmote(emote))
                .orElseGet(FakeEmote::new);
    }

    @Override
    public List<ServerTextChannel> getTextChannels() {
        return this.jdaServer.getTextChannels().stream()
                .map(JDAGuildTextChannel::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServerVoiceChannel> getVoiceChannels() {
        return this.jdaServer.getVoiceChannels().stream()
                .map(JDAGuildVoiceChannel::new)
                .collect(Collectors.toList());
    }

    @Override
    public ServerTextChannel getTextChannel(String channelId) {
        return Optional.ofNullable(this.jdaServer.getTextChannelById(channelId))
                .map(guildChannel -> (ServerTextChannel) new JDAGuildTextChannel(guildChannel))
                .orElseGet(FakeServerTextChannel::new);
    }

    @Override
    public ServerVoiceChannel getVoiceChannel(String channelId) {
        return Optional.ofNullable(this.jdaServer.getVoiceChannelById(channelId))
                .map(guildChannel -> (ServerVoiceChannel) new JDAGuildVoiceChannel(guildChannel))
                .orElseGet(FakeServerVoiceChannel::new); // TODO : review
    }

    @Override
    public List<Category> getCategories() {
        return this.jdaServer.getCategories().stream()
                .map(JDACategory::new)
                .collect(Collectors.toList());
    }

    @Override
    public Category getCategoryById(String categoryId) {
        return Optional.ofNullable(this.jdaServer.getCategoryById(categoryId))
                .map(category -> (Category) new JDACategory(category))
                .orElseGet(FakeCategory::new);
    }

    @Override
    public void addCategory(Category category) {
        this.jdaServer.createCategory(category.getName()).queue();
    }

    @Override
    public void addTextChannel(ServerTextChannel channel) {
        this.jdaServer.createTextChannel(channel.getName()).queue();
    }

    @Override
    public void addVoiceChannel(ServerVoiceChannel textChannel) { // TODO : review
        this.jdaServer.createVoiceChannel("").queue();
    }

    @Override
    public void removeChannel(ServerChannel channel) {
        GuildChannel jdaChannel = this.jdaServer.getGuildChannelById(channel.getId());

        if (jdaChannel != null) {
            jdaChannel.delete().queue();
        }
    }

    @Override
    public void addRole(Role role) {
        this.jdaServer.createRole().queue(); // TODO : review
    }

    @Override
    public void unBan(String userId) {
        this.jdaServer.unban(userId).queue();
    }

    @Override
    public boolean isFake() {
        return false;
    }

    @Override
    public String getId() {
        return this.jdaServer.getId();
    }

    @Override
    public String getName() {
        return this.jdaServer.getName();
    }

}

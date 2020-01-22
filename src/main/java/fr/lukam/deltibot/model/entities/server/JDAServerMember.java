package fr.lukam.deltibot.model.entities.server;

import fr.lukam.bot_api.entities.interfaces.channels.ServerVoiceChannel;
import fr.lukam.bot_api.entities.interfaces.channels.TextChannel;
import fr.lukam.bot_api.entities.interfaces.message.Message;
import fr.lukam.bot_api.entities.interfaces.server.Permission;
import fr.lukam.bot_api.entities.interfaces.server.Role;
import fr.lukam.bot_api.entities.interfaces.server.ServerMember;
import fr.lukam.bot_api.entities.interfaces.user.Status;
import fr.lukam.deltibot.utils.adapters.EmbedAdapter;
import fr.lukam.deltibot.utils.adapters.StatusAdapter;
import fr.lukam.deltibot.model.entities.channels.JDATextChannel;
import fr.lukam.deltibot.utils.adapters.PermissionsAdapter;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class JDAServerMember implements ServerMember {

    private final Member member;

    public JDAServerMember(Member member) {
        this.member = member;
    }

    @Override
    public String getNickName() {
        return this.member.getNickname();
    }

    @Override
    public void setNickName(String newNickName) {

    }

    @Override
    public Set<Role> getRoles() {
        return this.member.getRoles().stream()
                .map(JDARole::new)
                .collect(Collectors.toSet());
    }

    @Override
    public LocalDateTime getJoinTime() {
        return this.member.getTimeJoined().toLocalDateTime();
    }

    @Override
    public boolean isOwner() {
        return this.member.isOwner();
    }

    @Override
    public boolean hashPermissions(Permission... permissions) {
        return this.member.hasPermission(Arrays.stream(permissions)
                .map(Permission::getName)
                .map(PermissionsAdapter::fromAPIPermission)
                .collect(Collectors.toList()));
    }

    @Override
    public boolean hasRoles(Role... roles) {
        return this.member.getRoles().stream()
                .allMatch(role -> Arrays.stream(roles)
                        .anyMatch(role1 -> role.getName().equalsIgnoreCase(role1.getName())));
    }

    @Override
    public void kick(String reason) {
        this.member.kick(reason).queue();
    }

    @Override
    public void ban(String reason) {
        this.member.ban(0, reason).queue();
    }

    @Override
    public void mute() {
        this.member.getGuild().getRoles().stream()
                .filter(role -> role.getName().equalsIgnoreCase("muted"))
                .findAny()
                .ifPresent(role -> member.getGuild().addRoleToMember(member.getId(), role).queue());
    }

    @Override
    public void addRoles(Role... roles) {

        for (Role role : roles) {

            this.member.getGuild().getRoles().stream()
                    .filter(jdaRole -> jdaRole.getId().equalsIgnoreCase(role.getId()))
                    .findAny()
                    .ifPresent(jdaRole -> member.getGuild().addRoleToMember(member.getId(), jdaRole).queue());
        }

    }

    @Override
    public void removeRoles(Role... roles) {

        for (Role role : roles) {

            this.member.getGuild().getRoles().stream()
                    .filter(jdaRole -> jdaRole.getId().equalsIgnoreCase(role.getId()))
                    .findAny()
                    .ifPresent(jdaRole -> member.getGuild().removeRoleFromMember(member.getId(), jdaRole).queue());
        }
    }

    @Override
    public void connectToVoiceChannel(ServerVoiceChannel channel) {
        VoiceChannel voiceChannel = this.member.getGuild().getVoiceChannelById(channel.getId());
        this.member.getGuild().moveVoiceMember(this.member, voiceChannel).queue();
    }

    @Override
    public String getAvatarURL() {
        return this.member.getUser().getAvatarUrl();
    }

    @Override
    public String getAsTag() {
        return this.member.getUser().getAsTag();
    }

    @Override
    public TextChannel getPrivateChannel() {
        return new JDATextChannel(this.member.getUser().openPrivateChannel().complete());
    }

    @Override
    public void sendMessage(Message message) {
        net.dv8tion.jda.api.entities.Message jdaMessage = new MessageBuilder()
                .setContent(message.getContent())
                .setEmbed(EmbedAdapter.fromAPIEmbed(message.getEmbed()))
                .build();

        this.member.getUser().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage(jdaMessage).queue());
    }

    @Override
    public Status getStatus() {
        return StatusAdapter.fromJDAStatus(this.member.getOnlineStatus());
    }

    @Override
    public LocalDateTime getCreateAccountTime() {
        return this.member.getUser().getTimeCreated().toLocalDateTime();
    }

    @Override
    public boolean isFake() {
        return false;
    }

    @Override
    public String getId() {
        return this.member.getId();
    }

    @Override
    public String getAsMention() {
        return this.member.getAsMention();
    }

    @Override
    public String getName() {
        return this.member.getUser().getName();
    }
}

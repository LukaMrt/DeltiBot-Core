package fr.lukam.deltibot.model.entities.server;

import fr.lukam.bot_api.entities.interfaces.channels.ServerChannel;
import fr.lukam.bot_api.entities.interfaces.channels.ServerTextChannel;
import fr.lukam.bot_api.entities.interfaces.channels.ServerVoiceChannel;
import fr.lukam.bot_api.entities.interfaces.server.Category;
import fr.lukam.bot_api.entities.interfaces.server.Permission;
import fr.lukam.bot_api.entities.interfaces.server.Role;
import fr.lukam.bot_api.entities.interfaces.server.ServerMember;
import fr.lukam.deltibot.model.entities.channels.JDAGuildChannel;
import net.dv8tion.jda.api.entities.GuildChannel;

import java.util.List;
import java.util.stream.Collectors;

public class JDACategory implements Category {

    private final net.dv8tion.jda.api.entities.Category category;

    public JDACategory(net.dv8tion.jda.api.entities.Category category) {
        this.category = category;
    }

    @Override
    public List<ServerChannel> getChannels() {
        return this.category.getChannels().stream()
                .map(JDAGuildChannel::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServerMember> getAuthorizedMembers() {
        return this.category.getMembers().stream()
                .map(JDAServerMember::new)
                .collect(Collectors.toList());
    }

    @Override
    public void addPermissionToRole(Role role, Permission permission) {
        role.addPermission(permission);
        net.dv8tion.jda.api.entities.Role jdaRole = this.category.getGuild().getRoleById(role.getId());

        if (jdaRole != null) {
            this.category.putPermissionOverride(jdaRole).queue();
        }

        role.removePermission(permission);
    }

    @Override
    public void removePermissionFromRole(Role role, Permission permission) {
        role.removePermission(permission);
        net.dv8tion.jda.api.entities.Role jdaRole = this.category.getGuild().getRoleById(role.getId());

        if (jdaRole != null) {
            this.category.putPermissionOverride(jdaRole).queue();
        }

        role.addPermission(permission);
    }

    @Override
    public void addTextChannel(ServerTextChannel serverChannel) {
        this.category.createTextChannel(serverChannel.getName()).queue();
    }

    @Override
    public void addVoiceChannel(ServerVoiceChannel serverChannel) {
        this.category.createVoiceChannel(serverChannel.getName()).queue();
    }

    @Override
    public void removeChannel(ServerChannel serverChannel) {
        GuildChannel jdaChannel = this.category.getGuild().getGuildChannelById(serverChannel.getId());

        if (jdaChannel != null) {
            jdaChannel.delete().queue();
        }
    }

    @Override
    public boolean isFake() {
        return false;
    }

    @Override
    public String getId() {
        return this.category.getId();
    }

    @Override
    public String getName() {
        return this.category.getName();
    }

}

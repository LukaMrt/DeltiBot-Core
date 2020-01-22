package fr.lukam.deltibot.model.entities.server;

import fr.lukam.bot_api.entities.interfaces.server.Permission;
import fr.lukam.bot_api.entities.interfaces.server.Role;
import fr.lukam.deltibot.utils.adapters.PermissionsAdapter;

import java.awt.Color;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class JDARole implements Role {

    private final net.dv8tion.jda.api.entities.Role role;

    public JDARole(net.dv8tion.jda.api.entities.Role role) {
        this.role = role;
    }

    @Override
    public Color getColor() {
        return this.role.getColor();
    }

    @Override
    public boolean isMentionable() {
        return this.role.isMentionable();
    }

    @Override
    public boolean isUpperThan(Role role) {

        if (!(role instanceof JDARole)) {
            return false;
        }

        return this.role.canInteract(((JDARole) role).role);
    }

    @Override
    public void delete() {
        this.role.delete().queue();
    }

    @Override
    public Set<Permission> getPermissions() {
        return this.role.getPermissions().stream()
                .map(PermissionsAdapter::fromJDAPermission)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean hasPermissions(Permission... permissions) {
        return this.role.hasPermission(Arrays.stream(permissions)
                .map(Permission::getName)
                .map(PermissionsAdapter::fromAPIPermission)
                .collect(Collectors.toList()));
    }

    @Override
    public void addPermission(Permission permission) {
        net.dv8tion.jda.api.Permission jdaPermission = PermissionsAdapter.fromAPIPermission(permission.getName());
        this.role.getManager().setPermissions(jdaPermission).queue();
    }

    @Override
    public void removePermission(Permission permission) {
        net.dv8tion.jda.api.Permission jdaPermission = PermissionsAdapter.fromAPIPermission(permission.getName());
        this.role.getManager().revokePermissions(jdaPermission).queue();
    }

    @Override
    public boolean isFake() {
        return false;
    }

    @Override
    public String getId() {
        return this.role.getId();
    }

    @Override
    public String getAsMention() {
        return this.role.getAsMention();
    }

    @Override
    public String getName() {
        return this.role.getName();
    }

}

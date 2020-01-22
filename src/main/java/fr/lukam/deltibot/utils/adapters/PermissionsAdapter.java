package fr.lukam.deltibot.utils.adapters;

import fr.lukam.bot_api.entities.interfaces.server.Permission;
import fr.lukam.deltibot.model.entities.server.permissions.*;
import fr.lukam.deltibot.model.entities.server.permissions.manage.*;
import fr.lukam.deltibot.model.entities.server.permissions.members.BanMember;
import fr.lukam.deltibot.model.entities.server.permissions.members.ChangeNickname;
import fr.lukam.deltibot.model.entities.server.permissions.members.KickMember;
import fr.lukam.deltibot.model.entities.server.permissions.manage.ManageNickname;
import fr.lukam.deltibot.model.entities.server.permissions.text.*;
import fr.lukam.deltibot.model.entities.server.permissions.voice.*;

public class PermissionsAdapter {

    public static Permission fromJDAPermission(net.dv8tion.jda.api.Permission permission) {

        switch (permission) {

            case CREATE_INSTANT_INVITE:
                return new CreateInvite();

            case KICK_MEMBERS:
                return new KickMember();

            case BAN_MEMBERS:
                return new BanMember();

            case ADMINISTRATOR:
                return new Administrator();

            case MANAGE_CHANNEL:
                return new ManageChannel();

            case MANAGE_SERVER:
                return new ManageServer();

            case MESSAGE_ADD_REACTION:
                return new MessageAddReaction();

            case VIEW_AUDIT_LOGS:
                return new ViewLogs();

            case PRIORITY_SPEAKER:
                return null;

            case VIEW_CHANNEL:
                return new ViewChannels();

            case MESSAGE_READ:
                return new ReadMessages();

            case MESSAGE_WRITE:
                return new WriteMessages();

            case MESSAGE_MANAGE:
                return new ManageMessages();

            case MESSAGE_MENTION_EVERYONE:
                return new MentionEveryone();

            case MESSAGE_EXT_EMOJI:
                return new ExternalEmote();

            case VOICE_STREAM:
                return new Stream();

            case VOICE_CONNECT:
                return new VoiceConnect();

            case VOICE_SPEAK:
                return new VoiceSpeak();

            case VOICE_MUTE_OTHERS:
                return new MuteOthers();

            case VOICE_DEAF_OTHERS:
                return new DisconnectOthers();

            case VOICE_MOVE_OTHERS:
                return new MoveOthers();

            case NICKNAME_CHANGE:
                return new ChangeNickname();

            case NICKNAME_MANAGE:
                return new ManageNickname();

            case MANAGE_ROLES:
                return new ManageRoles();

            case MANAGE_PERMISSIONS:
                return new ManagePermissions();

            case MANAGE_EMOTES:
                return new ManageEmotes();

            case MESSAGE_TTS:

            case UNKNOWN:

            case MANAGE_WEBHOOKS:

            case VOICE_USE_VAD:

            case MESSAGE_HISTORY:

            case MESSAGE_ATTACH_FILES:

            case MESSAGE_EMBED_LINKS:
                return new Unknown();

            default:
                throw new IllegalStateException("Unexpected permission: " + permission);
        }

    }

    public static net.dv8tion.jda.api.Permission fromAPIPermission(String permissionName) {

        switch (permissionName) {

            case "Create instant invite":
                return net.dv8tion.jda.api.Permission.CREATE_INSTANT_INVITE;

            case "Kick member":
                return net.dv8tion.jda.api.Permission.KICK_MEMBERS;

            case "Ban member":
                return net.dv8tion.jda.api.Permission.BAN_MEMBERS;

            case "Administrator":
                return net.dv8tion.jda.api.Permission.ADMINISTRATOR;

            case "Manage channels":
                return net.dv8tion.jda.api.Permission.MANAGE_CHANNEL;

            case "Manage server":
                return net.dv8tion.jda.api.Permission.MANAGE_SERVER;

            case "Message add reaction":
                return net.dv8tion.jda.api.Permission.MESSAGE_ADD_REACTION;

            case "View logs":
                return net.dv8tion.jda.api.Permission.VIEW_AUDIT_LOGS;

            case "Priority speaker":
                return net.dv8tion.jda.api.Permission.PRIORITY_SPEAKER;

            case "View channels":
                return net.dv8tion.jda.api.Permission.VIEW_CHANNEL;

            case "Read messages":
                return net.dv8tion.jda.api.Permission.MESSAGE_READ;

            case "Write messages":
                return net.dv8tion.jda.api.Permission.MESSAGE_WRITE;

            case "Send TTS messages":
                return net.dv8tion.jda.api.Permission.MESSAGE_TTS;

            case "Manage messages":
                return net.dv8tion.jda.api.Permission.MESSAGE_MANAGE;

            case "Embed links":
                return net.dv8tion.jda.api.Permission.MESSAGE_EMBED_LINKS;

            case "Attach files":
                return net.dv8tion.jda.api.Permission.MESSAGE_ATTACH_FILES;

            case "Read history":
                return net.dv8tion.jda.api.Permission.MESSAGE_HISTORY;

            case "Mention everyone":
                return net.dv8tion.jda.api.Permission.MESSAGE_MENTION_EVERYONE;

            case "use external emote":
                return net.dv8tion.jda.api.Permission.MESSAGE_EXT_EMOJI;

            case "Stream":
                return net.dv8tion.jda.api.Permission.VOICE_STREAM;

            case "Voice connect":
                return net.dv8tion.jda.api.Permission.VOICE_CONNECT;

            case "Voice speak":
                return net.dv8tion.jda.api.Permission.VOICE_SPEAK;

            case "Mute others":
                return net.dv8tion.jda.api.Permission.VOICE_MUTE_OTHERS;

            case "Disconnect others":
                return net.dv8tion.jda.api.Permission.VOICE_DEAF_OTHERS;

            case "Move others":
                return net.dv8tion.jda.api.Permission.VOICE_MOVE_OTHERS;

            case "Use voice activity":
                return net.dv8tion.jda.api.Permission.VOICE_USE_VAD;

            case "Change nickname":
                return net.dv8tion.jda.api.Permission.NICKNAME_CHANGE;

            case "Manage nicknames":
                return net.dv8tion.jda.api.Permission.NICKNAME_MANAGE;

            case "Manage roles":
                return net.dv8tion.jda.api.Permission.MANAGE_ROLES;

            case "Manage permissions":
                return net.dv8tion.jda.api.Permission.MANAGE_PERMISSIONS;

            case "Manage webhooks":
                return net.dv8tion.jda.api.Permission.MANAGE_WEBHOOKS;

            case "Unknown":
                return net.dv8tion.jda.api.Permission.UNKNOWN;

            default:
                throw new IllegalStateException("Unexpected permission name: " + permissionName);
        }

    }

}

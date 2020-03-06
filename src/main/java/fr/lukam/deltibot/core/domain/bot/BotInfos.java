package fr.lukam.deltibot.core.domain.bot;

public class BotInfos {

    public final char prefix;
    public final String ownerId;
    public final String[] coOwnersIds;
    public final String mainServerId;

    public BotInfos(char prefix, String ownerId, String[] coOwnersIds, String mainServerId) {
        this.prefix = prefix;
        this.ownerId = ownerId;
        this.coOwnersIds = coOwnersIds;
        this.mainServerId = mainServerId;
    }

}

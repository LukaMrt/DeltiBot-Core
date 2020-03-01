package fr.lukam.deltibot.core.domain.bot;

public class BotInfos {

    public final char prefix;
    public final long ownerId;
    public final long[] coOwnersIds;

    public BotInfos(char prefix, long ownerId, long[] coOwnersIds) {
        this.prefix = prefix;
        this.ownerId = ownerId;
        this.coOwnersIds = coOwnersIds;
    }

}

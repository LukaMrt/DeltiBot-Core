package fr.lukam.deltibot.core.infrastructure.infos;

import fr.lukam.deltibot.core.domain.infos.InfosRepository;
import redis.clients.jedis.Jedis;

public class RedisInfosRepository implements InfosRepository, fr.lukam.bot.api.repositories.InfosRepository {

    private final Jedis jedis;

    public RedisInfosRepository() {
        this.jedis = new Jedis("localhost");
    }

    @Override
    public void set(String key, String value) {
        if (value == null) return;
        jedis.set(key, value);
    }

    @Override
    public void set(String key, String[] value) {
        jedis.del(key);
        if (value.length == 0) return;
        jedis.rpush(key, value);
    }

    @Override
    public void set(String key, int value) {
        jedis.set(key, String.valueOf(value));
    }

    @Override
    public String getString(String key) {
        return jedis.get(key);
    }

    @Override
    public String[] getStringArray(String key) {
        return jedis.lrange(key, 0, 100).toArray(new String[]{});
    }

    @Override
    public int getInt(String key) {
        return Integer.parseInt(jedis.get(key));
    }

}

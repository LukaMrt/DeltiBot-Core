package fr.lukam.deltibot.core.infrastructure.infos;

import fr.lukam.deltibot.core.domain.infos.InfosRepository;
import redis.clients.jedis.Jedis;

public class RedisInfosRepository implements InfosRepository {

    public RedisInfosRepository() {

        Jedis jedis = new Jedis("localhost");
        jedis.set("foo", "bar");
        String value = jedis.get("foo");

        System.out.println(value);
    }

    @Override
    public void set(String key, String value) {

    }

    @Override
    public void set(String key, String[] value) {

    }

}

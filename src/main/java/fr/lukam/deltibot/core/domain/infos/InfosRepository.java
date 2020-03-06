package fr.lukam.deltibot.core.domain.infos;

public interface InfosRepository {

    void set(String key, String value);

    void set(String key, String[] value);

}

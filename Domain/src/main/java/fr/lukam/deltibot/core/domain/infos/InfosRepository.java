package fr.lukam.deltibot.core.domain.infos;

public interface InfosRepository {

    void set(String key, String value);

    void set(String key, String[] value);

    void set(String key, int value);

    String getString(String key);

    String[] getStringArray(String key);

    int getInt(String key);

}

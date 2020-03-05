package fr.lukam.deltibot.core.domain.infos.data;

public class SimpleData extends Data {

    public final String value;

    public SimpleData(String key, String value) {
        super(key);
        this.value = value;
    }

}

package fr.lukam.deltibot.core.domain.infos.data;

public class ArrayData extends Data {

    public final String[] value;

    public ArrayData(String key, String[] value) {
        super(key);
        this.value = value;
    }

}

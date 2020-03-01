package fr.lukam.deltibot.core.domain.infos;

import fr.lukam.deltibot.core.domain.infos.data.ArrayData;
import fr.lukam.deltibot.core.domain.infos.data.SimpleData;

public interface SaveInfos {
    
    void registerData(SimpleData simpleData);

    void registerData(ArrayData arrayData);

}

package fr.lukam.deltibot.core.domain.infos;

import fr.lukam.deltibot.core.domain.infos.data.ArrayData;
import fr.lukam.deltibot.core.domain.infos.data.SimpleData;

public class InfosSaver implements SaveInfos {

    private final InfosRepository infosRepository;

    public InfosSaver(InfosRepository infosRepository) {
        this.infosRepository = infosRepository;
    }

    @Override
    public void registerData(SimpleData simpleData) {
        this.infosRepository.set(simpleData.key, simpleData.value);
    }

    @Override
    public void registerData(ArrayData arrayData) {
        this.infosRepository.set(arrayData.key, arrayData.value);
    }

}

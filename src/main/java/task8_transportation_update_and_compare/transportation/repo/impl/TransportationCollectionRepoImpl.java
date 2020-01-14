package  task8_transportation_update_and_compare.transportation.repo.impl;

import  task8_transportation_update_and_compare.storage.IdGenerator;
import  task8_transportation_update_and_compare.transportation.domain.Transportation;
import  task8_transportation_update_and_compare.transportation.repo.TransportationRepo;

import java.util.Objects;

import static  task8_transportation_update_and_compare.storage.Storage.transportationList;

public class TransportationCollectionRepoImpl implements TransportationRepo {

    @Override
    public void add(Transportation transportation) {
        transportation.setId(IdGenerator.generateId());
        transportationList.add(transportation);
    }

    @Override
    public Transportation getById(long id) {
        for (Transportation transportation : transportationList) {
            if (transportation != null && Objects.equals(transportation.getId(), id)) {
                return transportation;
            }
        }

        return null;
    }

    @Override
    public Transportation[] getAll() {
        return transportationList.toArray(new Transportation[0]);
    }

    @Override
    public Transportation update(long id, Transportation transportation) {
        Transportation transportationFromRepo = getById(id);
        if (transportation != null && transportationFromRepo != null) {
            int index = transportationList.indexOf(transportationFromRepo);
            transportation.setId(id);
            transportationList.set(index, transportation);
            return transportation;
        }
        return null;
    }

    @Override
    public boolean deleteById(long id) {
        Transportation transportation = getById(id);

        if (transportation != null) {
            transportationList.remove(transportation);
            return true;
        }

        return false;
    }

}

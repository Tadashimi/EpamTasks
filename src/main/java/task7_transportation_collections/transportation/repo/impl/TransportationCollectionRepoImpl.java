package task7_transportation_collections.transportation.repo.impl;

import task7_transportation_collections.storage.IdGenerator;
import task7_transportation_collections.transportation.domain.Transportation;
import task7_transportation_collections.transportation.repo.TransportationRepo;

import java.util.Objects;

import static task7_transportation_collections.storage.Storage.transportationList;

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
    public boolean deleteById(long id) {
        Transportation transportation = getById(id);

        if (transportation != null) {
            transportationList.remove(transportation);
            return true;
        }

        return false;
    }

}

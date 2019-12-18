package Task7_TransportationWithCollections.transportation.repo.impl;

import Task7_TransportationWithCollections.storage.IdGenerator;
import Task7_TransportationWithCollections.transportation.domain.Transportation;
import Task7_TransportationWithCollections.transportation.repo.TransportationRepo;

import java.util.Objects;

import static Task7_TransportationWithCollections.storage.Storage.transportationList;

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

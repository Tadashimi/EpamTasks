package  task10_transportation_generics.transportation.repo.impl;


import  task10_transportation_generics.storage.IdGenerator;
import  task10_transportation_generics.transportation.domain.Transportation;
import  task10_transportation_generics.transportation.repo.TransportationRepo;

import java.util.Iterator;
import java.util.List;

import static  task10_transportation_generics.storage.Storage.transportationCollection;

public class TransportationCollectionRepoImpl implements TransportationRepo {

    @Override
    public void add(Transportation transportation) {
        transportation.setId(IdGenerator.generateId());
        transportationCollection.add(transportation);
    }

    @Override
    public Transportation getById(long id) {
        for (Transportation transportation : transportationCollection) {
            if (Long.valueOf(id).equals(transportation.getId())) {
                return transportation;
            }
        }

        return null;
    }

    @Override
    public List<Transportation> getAll() {
        return transportationCollection;
    }

    @Override
    public void update(Transportation transportation) {

    }

    @Override
    public boolean deleteById(long id) {
        boolean deleted = false;

        Iterator<Transportation> iter = transportationCollection.iterator();
        while (iter.hasNext()) {
            if (Long.valueOf(id).equals(iter.next().getId())) {
                iter.remove();
                deleted = true;
                break;
            }
        }
        return deleted;
    }
}

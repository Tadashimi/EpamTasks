package main.java.Task10_TransportationWithGenerics.transportation.repo.impl;


import main.java.Task10_TransportationWithGenerics.storage.IdGenerator;
import main.java.Task10_TransportationWithGenerics.transportation.domain.Transportation;
import main.java.Task10_TransportationWithGenerics.transportation.repo.TransportationRepo;

import java.util.Iterator;
import java.util.List;

import static main.java.Task10_TransportationWithGenerics.storage.Storage.transportationCollection;

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

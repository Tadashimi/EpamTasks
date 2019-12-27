package Task11_TransportationWithFileReader.transportation.repo.impl;


import Task11_TransportationWithFileReader.storage.IdGenerator;
import Task11_TransportationWithFileReader.transportation.domain.Transportation;
import Task11_TransportationWithFileReader.transportation.repo.TransportationRepo;

import java.util.Iterator;
import java.util.List;

import static Task11_TransportationWithFileReader.storage.Storage.transportationCollection;

public class TransportationCollectionRepoImpl implements TransportationRepo {

    @Override
    public void add(Transportation transportation) {
        transportation.setId(IdGenerator.generateId());
        transportationCollection.add(transportation);
    }

    @Override
    public Transportation getById(Long id) {
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
    public void addAll(List<Transportation> transportations) {
        if (transportations != null) {
            transportationCollection.addAll(transportations);
        }
    }

    @Override
    public boolean deleteById(Long id) {
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

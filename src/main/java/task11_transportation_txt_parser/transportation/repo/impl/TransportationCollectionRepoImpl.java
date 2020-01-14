package task11_transportation_txt_parser.transportation.repo.impl;


import task11_transportation_txt_parser.storage.IdGenerator;
import task11_transportation_txt_parser.transportation.domain.Transportation;
import task11_transportation_txt_parser.transportation.repo.TransportationRepo;

import java.util.Iterator;
import java.util.List;

import static task11_transportation_txt_parser.storage.Storage.transportationCollection;

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

package task12_transportation_XML_paser_and_export_to_file.transportation.repo.impl;


import task12_transportation_XML_paser_and_export_to_file.storage.IdGenerator;
import task12_transportation_XML_paser_and_export_to_file.transportation.domain.Transportation;
import task12_transportation_XML_paser_and_export_to_file.transportation.repo.TransportationRepo;

import java.util.Iterator;
import java.util.List;

import static task12_transportation_XML_paser_and_export_to_file.storage.Storage.transportationCollection;

public class TransportationCollectionRepoImpl implements TransportationRepo {

    @Override
    public void save(Transportation transportation) {
        transportation.setId(IdGenerator.generateId());
        transportationCollection.add(transportation);
    }

    @Override
    public Transportation findById(Long id) {
        for (Transportation transportation : transportationCollection) {
            if (transportation.getId().equals(id)) {
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
    public boolean update(Transportation transportation) {
        return true;
    }

    @Override
    public boolean deleteById(Long id) {
        boolean deleted = false;

        Iterator<Transportation> iter = transportationCollection.iterator();
        while (iter.hasNext()) {
            if (iter.next().getId().equals(id)) {
                iter.remove();
                deleted = true;
                break;
            }
        }
        return deleted;
    }

    @Override
    public int countAll() {
        return transportationCollection.size();
    }
}

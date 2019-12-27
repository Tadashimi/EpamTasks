package main.java.Task7_TransportationWithCollections.transportation.repo.impl;

import main.java.Task7_TransportationWithCollections.common.solutions.utils.ArrayUtils;
import main.java.Task7_TransportationWithCollections.storage.IdGenerator;
import main.java.Task7_TransportationWithCollections.storage.Storage;
import main.java.Task7_TransportationWithCollections.transportation.domain.Transportation;
import main.java.Task7_TransportationWithCollections.transportation.repo.TransportationRepo;

import static main.java.Task7_TransportationWithCollections.storage.Storage.transportationIndex;

public class TransportationArrayRepoImpl implements TransportationRepo {

    @Override
    public void add(Transportation transportation) {
        if (transportationIndex == Storage.transportations.length) {
            Transportation[] newTransportations =
                    new Transportation[Storage.transportations.length * 2];
            ArrayUtils.copyArray(Storage.transportations, newTransportations);
            Storage.transportations = newTransportations;
        }

        transportation.setId(IdGenerator.generateId());
        Storage.transportations[transportationIndex] = transportation;
        transportationIndex++;
    }

    @Override
    public Transportation getById(long id) {
        for (Transportation transportation : Storage.transportations) {
            if (transportation != null && Long.valueOf(id).equals(transportation.getId())) {
                return transportation;
            }
        }

        return null;
    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }

}

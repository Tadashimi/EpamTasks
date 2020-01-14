package task7_transportation_collections.transportation.repo.impl;

import task7_transportation_collections.common.solutions.utils.ArrayUtils;
import task7_transportation_collections.storage.IdGenerator;
import task7_transportation_collections.storage.Storage;
import task7_transportation_collections.transportation.domain.Transportation;
import task7_transportation_collections.transportation.repo.TransportationRepo;

import static task7_transportation_collections.storage.Storage.transportationIndex;

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

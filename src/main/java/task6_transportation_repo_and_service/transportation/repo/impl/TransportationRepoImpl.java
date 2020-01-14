package task6_transportation_repo_and_service.transportation.repo.impl;

import task6_transportation_repo_and_service.common.utils.ArrayUtils;
import task6_transportation_repo_and_service.storage.IdGenerator;
import task6_transportation_repo_and_service.transportation.domain.Transportation;
import task6_transportation_repo_and_service.transportation.repo.TransportationRepo;

import static task6_transportation_repo_and_service.storage.Storage.transportationIndex;
import static task6_transportation_repo_and_service.storage.Storage.transportations;

public class TransportationRepoImpl implements TransportationRepo {

    @Override
    public void add(Transportation transportation) {
        if (transportationIndex == transportations.length) {
            Transportation[] newTransportations = new Transportation[transportations.length * 2];
            ArrayUtils.copyArray(transportations, newTransportations);
            transportations = newTransportations;
        }

        transportation.setId(IdGenerator.generateId());
        transportations[transportationIndex] = transportation;
        transportationIndex++;
    }

    @Override
    public Transportation getById(long id) {
        for (Transportation transportation : transportations) {
            if (transportation != null && Long.valueOf(id).equals(transportation.getId())) {
                return transportation;
            }
        }

        return null;
    }

    @Override
    public boolean deleteById(long id) {
        int curIndex = 0;
        while (curIndex < transportations.length) {
            Transportation transportation = transportations[curIndex];
            if (transportation != null && Long.valueOf(id).equals(transportation.getId())) {
                ArrayUtils.removeElementFromArray(transportations, curIndex);
                return true;
            }
            curIndex++;
        }
        return false;
    }
}

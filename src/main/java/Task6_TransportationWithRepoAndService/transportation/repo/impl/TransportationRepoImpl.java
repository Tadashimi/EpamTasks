package main.java.Task6_TransportationWithRepoAndService.transportation.repo.impl;

import main.java.Task6_TransportationWithRepoAndService.common.utils.ArrayUtils;
import main.java.Task6_TransportationWithRepoAndService.storage.IdGenerator;
import main.java.Task6_TransportationWithRepoAndService.transportation.domain.Transportation;
import main.java.Task6_TransportationWithRepoAndService.transportation.repo.TransportationRepo;

import static main.java.Task6_TransportationWithRepoAndService.storage.Storage.transportationIndex;
import static main.java.Task6_TransportationWithRepoAndService.storage.Storage.transportations;

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

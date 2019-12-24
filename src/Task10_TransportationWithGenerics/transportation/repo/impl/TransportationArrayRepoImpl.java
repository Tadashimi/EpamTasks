package Task10_TransportationWithGenerics.transportation.repo.impl;

import Task10_TransportationWithGenerics.common.solutions.utils.ArrayUtils;
import Task10_TransportationWithGenerics.storage.IdGenerator;
import Task10_TransportationWithGenerics.transportation.domain.Transportation;
import Task10_TransportationWithGenerics.transportation.repo.TransportationRepo;

import java.util.Arrays;
import java.util.List;

import static Task10_TransportationWithGenerics.common.business.repo.CommonRepoHelper.findEntityIndexInArrayStorageById;
import static Task10_TransportationWithGenerics.storage.Storage.transportationArray;
import static Task10_TransportationWithGenerics.storage.Storage.transportationIndex;

public class TransportationArrayRepoImpl implements TransportationRepo {

    @Override
    public void add(Transportation transportation) {
        if (transportationIndex == transportationArray.length) {
            Transportation[] newTransportations =
                    new Transportation[transportationArray.length * 2];
            ArrayUtils.copyArray(transportationArray, newTransportations);
            transportationArray = newTransportations;
        }

        transportation.setId(IdGenerator.generateId());
        transportationArray[transportationIndex] = transportation;
        transportationIndex++;
    }

    @Override
    public Transportation getById(long id) {
        for (Transportation transportation : transportationArray) {
            if (transportation != null && Long.valueOf(id).equals(transportation.getId())) {
                return transportation;
            }
        }

        return null;
    }

    @Override
    public List<Transportation> getAll() {
        return Arrays.asList(transportationArray);
    }

    @Override
    public void update(Transportation transportation) {

    }

    @Override
    public boolean deleteById(long id) {
        Integer indexToDelete = findEntityIndexInArrayStorageById(transportationArray, id);

        if (indexToDelete == null) {
            return false;
        } else {
            ArrayUtils.removeElement(transportationArray, indexToDelete);
            return true;
        }
    }
}

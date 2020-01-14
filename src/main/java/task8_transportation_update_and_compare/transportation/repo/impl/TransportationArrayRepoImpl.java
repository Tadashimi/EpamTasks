package  task8_transportation_update_and_compare.transportation.repo.impl;

import  task8_transportation_update_and_compare.common.solutions.utils.ArrayUtils;
import  task8_transportation_update_and_compare.storage.IdGenerator;
import  task8_transportation_update_and_compare.storage.Storage;
import  task8_transportation_update_and_compare.transportation.domain.Transportation;
import  task8_transportation_update_and_compare.transportation.repo.TransportationRepo;

import static  task8_transportation_update_and_compare.common.business.repo.CommonRepoHelper.findEntityIndexInArrayStorageById;
import static  task8_transportation_update_and_compare.storage.Storage.transportationIndex;
import static  task8_transportation_update_and_compare.storage.Storage.transportations;

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
    public Transportation[] getAll() {
        return excludeNullableElementsFromArray(transportations);
    }

    @Override
    public Transportation update(long id, Transportation transportation) {
        Transportation transportationFromRepo = getById(id);
        Integer indexToUpdate = findEntityIndexInArrayStorageById(transportations, id);

        boolean transportationWasFoundInArrayAndRepo = transportationFromRepo != null && indexToUpdate != null;
        if (transportation != null && transportationWasFoundInArrayAndRepo) {
            transportation.setId(transportationFromRepo.getId());
            transportations[indexToUpdate] = transportation;
            return transportation;
        }
        return null;
    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }

    private Transportation[] excludeNullableElementsFromArray(Transportation[] transportations) {
        int sizeOfArrWithNotNullElems = 0;

        for (Transportation transportation : transportations) {
            if (transportation != null) {
                sizeOfArrWithNotNullElems++;
            }
        }

        if (sizeOfArrWithNotNullElems == 0) {
            return new Transportation[0];
        } else {
            Transportation[] result = new Transportation[sizeOfArrWithNotNullElems];
            int index = 0;
            for (Transportation transportation : transportations) {
                if (transportation != null) {
                    result[index++] = transportation;
                }
            }

            return result;
        }
    }

}

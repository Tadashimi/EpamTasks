package task8_transportation_update_and_compare.carrier.repo.impl;

import task8_transportation_update_and_compare.carrier.domain.Carrier;
import task8_transportation_update_and_compare.carrier.repo.CarrierRepo;
import task8_transportation_update_and_compare.common.solutions.utils.ArrayUtils;
import task8_transportation_update_and_compare.storage.IdGenerator;

import java.util.Objects;

import static task8_transportation_update_and_compare.common.business.repo.CommonRepoHelper.findEntityIndexInArrayStorageById;
import static task8_transportation_update_and_compare.storage.Storage.carrierIndex;
import static task8_transportation_update_and_compare.storage.Storage.carriers;

public class CarrierArrayRepoImpl implements CarrierRepo {

    private static final Carrier[] EMPTY_CARRIER_ARRAY = new Carrier[0];

    @Override
    public void add(Carrier carrier) {
        if (carrierIndex == carriers.length) {
            Carrier[] newCarriers = new Carrier[carriers.length * 2];
            ArrayUtils.copyArray(carriers, newCarriers);
            carriers = newCarriers;
        }

        carrier.setId(IdGenerator.generateId());
        carriers[carrierIndex] = carrier;
        carrierIndex++;
    }

    @Override
    public Carrier getById(long id) {
        for (Carrier carrier : carriers) {
            if (carrier != null && Long.valueOf(id).equals(carrier.getId())) {
                return carrier;
            }
        }

        return null;
    }

    @Override
    public Carrier[] getByName(String name) {
        Carrier[] searchResultWithNullableElems = getByNameIncludingNullElements(name);
        if (searchResultWithNullableElems == null || searchResultWithNullableElems.length == 0) {
            return EMPTY_CARRIER_ARRAY;
        } else {
            return excludeNullableElementsFromArray(searchResultWithNullableElems);
        }
    }

    @Override
    public Carrier[] getAll() {
        return excludeNullableElementsFromArray(carriers);
    }

    @Override
    public Carrier update(long id, Carrier carrier) {
        Carrier carrierFromRepo = getById(id);
        Integer indexToUpdate = findEntityIndexInArrayStorageById(carriers, id);

        boolean carrierWasFoundInArrayAndRepo = carrierFromRepo != null && indexToUpdate != null;
        if (carrier != null && carrierWasFoundInArrayAndRepo) {
            carrier.setId(carrierFromRepo.getId());
            carriers[indexToUpdate] = carrier;
            return carrier;
        }
        return null;
    }

    private Carrier[] getByNameIncludingNullElements(String name) {
        Carrier[] result = new Carrier[carriers.length];

        int curIndex = 0;
        for (Carrier carrier : carriers) {
            if (carrier != null && Objects.equals(carrier.getName(), name)) {
                result[curIndex++] = carrier;
            }
        }

        return result;
    }


    private Carrier[] excludeNullableElementsFromArray(Carrier[] carriers) {
        int sizeOfArrWithNotNullElems = 0;

        for (Carrier carrier : carriers) {
            if (carrier != null) {
                sizeOfArrWithNotNullElems++;
            }
        }

        if (sizeOfArrWithNotNullElems == 0) {
            return EMPTY_CARRIER_ARRAY;
        } else {
            Carrier[] result = new Carrier[sizeOfArrWithNotNullElems];
            int index = 0;
            for (Carrier carrier : carriers) {
                if (carrier != null) {
                    result[index++] = carrier;
                }
            }

            return result;
        }
    }

    @Override
    public boolean deleteById(long id) {
        Integer indexToDelete = findEntityIndexInArrayStorageById(carriers, id);

        if (indexToDelete == null) {
            return false;
        } else {
            ArrayUtils.removeElement(carriers, indexToDelete);
            return true;
        }
    }

}

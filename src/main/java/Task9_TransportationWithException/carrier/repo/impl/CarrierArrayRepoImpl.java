package  Task9_TransportationWithException.carrier.repo.impl;


import  Task9_TransportationWithException.transportation.domain.Transportation;
import  Task9_TransportationWithException.carrier.domain.Carrier;
import  Task9_TransportationWithException.carrier.repo.CarrierRepo;
import  Task9_TransportationWithException.common.solutions.utils.ArrayUtils;
import  Task9_TransportationWithException.storage.IdGenerator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import static  Task9_TransportationWithException.common.business.repo.CommonRepoHelper.findEntityIndexInArrayStorageById;
import static  Task9_TransportationWithException.storage.Storage.carrierArray;
import static  Task9_TransportationWithException.storage.Storage.carrierIndex;

public class CarrierArrayRepoImpl implements CarrierRepo {

    private static final Carrier[] EMPTY_CARRIER_ARRAY = new Carrier[0];

    @Override
    public void add(Carrier carrier) {
        if (carrierIndex == carrierArray.length) {
            Carrier[] newCarriers = new Carrier[carrierArray.length * 2];
            ArrayUtils.copyArray(carrierArray, newCarriers);
            carrierArray = newCarriers;
        }

        carrier.setId(IdGenerator.generateId());
        carrierArray[carrierIndex] = carrier;
        carrierIndex++;
    }

    @Override
    public Carrier getById(long id) {
        for (Carrier carrier : carrierArray) {
            if (carrier != null && Long.valueOf(id).equals(carrier.getId())) {
                return carrier;
            }
        }

        return null;
    }

    @Override
    public Carrier[] findByName(String name) {
        Carrier[] searchResultWithNullableElems = getByNameIncludingNullElements(name);
        if (searchResultWithNullableElems == null || searchResultWithNullableElems.length == 0) {
            return EMPTY_CARRIER_ARRAY;
        } else {
            return excludeNullableElementsFromArray(searchResultWithNullableElems);
        }
    }

    private Carrier[] getByNameIncludingNullElements(String name) {
        Carrier[] result = new Carrier[carrierArray.length];

        int curIndex = 0;
        for (Carrier carrier : carrierArray) {
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
        Integer indexToDelete = findEntityIndexInArrayStorageById(carrierArray, id);

        if (indexToDelete == null) {
            return false;
        } else {
            ArrayUtils.removeElement(carrierArray, indexToDelete);
            return true;
        }
    }

    @Override
    public List<Carrier> getAll() {
        List<Carrier> carriers = Arrays.asList(carrierArray);
        Iterator<Carrier> iter = carriers.iterator();

        while (iter.hasNext()) {
            if (iter.next() == null) {
                iter.remove();
            }
        }

        return carriers;
    }

    @Override
    public void update(Carrier carrier) {

    }

}

package task17_transportation_optional.carrier.repo.impl;


import task17_transportation_optional.carrier.domain.Carrier;
import task17_transportation_optional.carrier.repo.CarrierRepo;
import task17_transportation_optional.common.solutions.utils.ArrayUtils;
import task17_transportation_optional.storage.IdGenerator;

import java.util.*;

import static task17_transportation_optional.common.business.repo.CommonRepoHelper.findEntityIndexInArrayStorageById;
import static task17_transportation_optional.storage.Storage.carrierArray;
import static task17_transportation_optional.storage.Storage.carrierIndex;

public class CarrierArrayRepoImpl implements CarrierRepo {

    private static final Carrier[] EMPTY_CARRIER_ARRAY = new Carrier[0];

    @Override
    public void save(Carrier carrier) {
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
    public boolean update(Carrier carrier) {
        return true;
    }

    @Override
    public Optional<Carrier> getByIdFetchingTransportations(long id) {
        return findById(id);
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
    public List<Carrier> getAll() {
        Carrier[] carriers = excludeNullableElementsFromArray(carrierArray);
        return carriers.length == 0 ? Collections.emptyList() : Arrays.asList(carrierArray);
    }

    @Override
    public int countAll() {
        return this.getAll().size();
    }

    @Override
    public Optional<Carrier> findById(Long id) {
        for (Carrier carrier : carrierArray) {
            if (carrier != null && carrier.getId().equals(id)) {
                return Optional.of(carrier);
            }
        }

        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        Integer indexToDelete = findEntityIndexInArrayStorageById(carrierArray, id);

        if (indexToDelete == null) {
            return false;
        } else {
            ArrayUtils.removeElement(carrierArray, indexToDelete);
            return true;
        }
    }
}

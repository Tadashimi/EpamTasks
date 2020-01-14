package task6_transportation_repo_and_service.carrier.repo.impl;

import task6_transportation_repo_and_service.carrier.domain.Carrier;
import task6_transportation_repo_and_service.carrier.repo.CarrierRepo;
import task6_transportation_repo_and_service.common.utils.ArrayUtils;
import task6_transportation_repo_and_service.storage.IdGenerator;

import java.util.Objects;

import static task6_transportation_repo_and_service.storage.Storage.carrierIndex;
import static task6_transportation_repo_and_service.storage.Storage.carriers;

public class CarrierRepoImpl implements CarrierRepo {

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
        Carrier[] result = new Carrier[carriers.length];

        int curIndex = 0;
        for (Carrier carrier : carriers) {
            if (carrier != null && Objects.equals(carrier.getName(), name)) {
                result[curIndex++] = carrier;
            }
        }
        ArrayUtils.trimArray(result);
        return result;
    }

    @Override
    public boolean deleteById(long id) {
        int curIndex = 0;
        while (curIndex < carriers.length) {
            Carrier carrier = carriers[curIndex];
            if (carrier != null && Long.valueOf(id).equals(carrier.getId())) {
                ArrayUtils.removeElementFromArray(carriers, curIndex);
                return true;
            }
            curIndex++;
        }
        return false;
    }
}

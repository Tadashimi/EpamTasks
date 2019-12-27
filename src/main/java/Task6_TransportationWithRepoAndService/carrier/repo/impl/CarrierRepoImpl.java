package main.java.Task6_TransportationWithRepoAndService.carrier.repo.impl;

import main.java.Task6_TransportationWithRepoAndService.carrier.domain.Carrier;
import main.java.Task6_TransportationWithRepoAndService.carrier.repo.CarrierRepo;
import main.java.Task6_TransportationWithRepoAndService.common.utils.ArrayUtils;
import main.java.Task6_TransportationWithRepoAndService.storage.IdGenerator;

import java.util.Objects;

import static main.java.Task6_TransportationWithRepoAndService.storage.Storage.carrierIndex;
import static main.java.Task6_TransportationWithRepoAndService.storage.Storage.carriers;

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

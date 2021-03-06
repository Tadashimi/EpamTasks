package task7_transportation_collections.carrier.repo.impl;

import task7_transportation_collections.carrier.domain.Carrier;
import task7_transportation_collections.carrier.repo.CarrierRepo;
import task7_transportation_collections.storage.IdGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static task7_transportation_collections.storage.Storage.carrierList;

public class CarrierCollectionRepoImpl implements CarrierRepo {

    @Override
    public void add(Carrier carrier) {
        carrier.setId(IdGenerator.generateId());
        carrierList.add(carrier);
    }

    @Override
    public Carrier getById(long id) {
        for (Carrier carrier : carrierList) {
            if (carrier != null && Objects.equals(carrier.getId(), id)) {
                return carrier;
            }
        }

        return null;
    }

    @Override
    public Carrier[] getByName(String name) {
        List<Carrier> result = new ArrayList<>();
        for (Carrier carrier : carrierList) {
            if (carrier != null && Objects.equals(carrier.getName(), name)) {
                result.add(carrier);
            }
        }
        return result.toArray(new Carrier[0]);
    }

    @Override
    public boolean deleteById(long id) {
        Carrier carrier = getById(id);

        if (carrier != null) {
            carrierList.remove(carrier);
            return true;
        }

        return false;
    }

}

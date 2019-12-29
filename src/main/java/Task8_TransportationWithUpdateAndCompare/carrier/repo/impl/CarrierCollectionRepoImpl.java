package Task8_TransportationWithUpdateAndCompare.carrier.repo.impl;

import Task8_TransportationWithUpdateAndCompare.carrier.domain.Carrier;
import Task8_TransportationWithUpdateAndCompare.carrier.repo.CarrierRepo;
import Task8_TransportationWithUpdateAndCompare.storage.IdGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static Task8_TransportationWithUpdateAndCompare.storage.Storage.carrierList;

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
    public Carrier[] getAll() {
        return carrierList.toArray(new Carrier[0]);
    }

    @Override
    public Carrier update(long id, Carrier carrier) {
        Carrier carrierFromRepo = getById(id);
        if (carrier != null && carrierFromRepo != null) {
            int index = carrierList.indexOf(carrierFromRepo);
            carrier.setId(id);
            carrierList.set(index, carrier);
            return carrier;
        }
        return null;
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

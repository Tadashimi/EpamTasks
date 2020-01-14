package  task9_transportation_exceptions.carrier.repo.impl;


import  task9_transportation_exceptions.carrier.domain.Carrier;
import  task9_transportation_exceptions.carrier.repo.CarrierRepo;
import  task9_transportation_exceptions.storage.IdGenerator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import static  task9_transportation_exceptions.storage.Storage.carrierCollection;

public class CarrierCollectionRepoImpl implements CarrierRepo {

    @Override
    public void add(Carrier carrier) {
        carrier.setId(IdGenerator.generateId());
        carrierCollection.add(carrier);
    }

    @Override
    public Carrier getById(long id) {
        for (Carrier carrier : carrierCollection) {
            if (Long.valueOf(id).equals(carrier.getId())) {
                return carrier;
            }
        }

        return null;
    }

    @Override
    public Carrier[] findByName(String name) {
        List<Carrier> result = new ArrayList<>();

        for (Carrier carrier : carrierCollection) {
            if (Objects.equals(carrier.getName(), name)) {
                result.add(carrier);
            }
        }

        return result.toArray(new Carrier[0]);
    }

    @Override
    public boolean deleteById(long id) {
        Iterator<Carrier> iter = carrierCollection.iterator();

        boolean removed = false;
        while (iter.hasNext()) {
            if (Long.valueOf(id).equals(iter.next().getId())) {
                iter.remove();
                removed = true;
                break;
            }
        }

        return removed;
    }

    @Override
    public List<Carrier> getAll() {
        return carrierCollection;
    }

    @Override
    public void update(Carrier carrier) {
    }

}

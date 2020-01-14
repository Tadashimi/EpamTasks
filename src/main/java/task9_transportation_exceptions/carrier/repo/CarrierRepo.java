package  task9_transportation_exceptions.carrier.repo;

import  task9_transportation_exceptions.carrier.domain.Carrier;
import  task9_transportation_exceptions.common.business.repo.CommonRepo;
import  task9_transportation_exceptions.transportation.domain.Transportation;

import java.util.List;

public interface CarrierRepo extends CommonRepo {

    void add(Carrier carrier);

    Carrier getById(long id);

    Carrier[] findByName(String name);

    List<Carrier> getAll();

    void update(Carrier carrier);

    default void removeTransportation(Carrier carrier, Transportation transportation) {
        if (carrier !=  null) {
            carrier.removeTransportation(transportation);
        }
    }
}

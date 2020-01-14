package task8_transportation_update_and_compare.carrier.repo;

import task8_transportation_update_and_compare.carrier.domain.Carrier;
import task8_transportation_update_and_compare.common.business.repo.CommonRepo;

public interface CarrierRepo extends CommonRepo {

    void add(Carrier carrier);

    Carrier getById(long id);

    Carrier[] getByName(String name);

    Carrier[] getAll();

    Carrier update(long id, Carrier carrier);
}

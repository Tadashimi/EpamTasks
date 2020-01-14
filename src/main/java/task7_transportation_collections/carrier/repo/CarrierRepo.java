package task7_transportation_collections.carrier.repo;

import task7_transportation_collections.carrier.domain.Carrier;
import task7_transportation_collections.common.business.repo.CommonRepo;

public interface CarrierRepo extends CommonRepo {

    void add(Carrier carrier);

    Carrier getById(long id);

    Carrier[] getByName(String name);

}

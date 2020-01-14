package  task10_transportation_generics.carrier.repo;

import  task10_transportation_generics.carrier.domain.Carrier;
import  task10_transportation_generics.common.business.repo.CommonRepo;

public interface CarrierRepo extends CommonRepo<Carrier> {

    Carrier getByIdFetchingTransportations(long id);

    Carrier[] findByName(String name);

}

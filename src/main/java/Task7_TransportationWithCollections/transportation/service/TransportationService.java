package Task7_TransportationWithCollections.transportation.service;

import Task7_TransportationWithCollections.common.business.service.CommonService;
import Task7_TransportationWithCollections.transportation.domain.Transportation;

public interface TransportationService extends CommonService {

    void add(Transportation transportation);

    Transportation getById(Long id);

}

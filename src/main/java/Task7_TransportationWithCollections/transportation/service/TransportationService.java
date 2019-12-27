package main.java.Task7_TransportationWithCollections.transportation.service;

import main.java.Task7_TransportationWithCollections.common.business.service.CommonService;
import main.java.Task7_TransportationWithCollections.transportation.domain.Transportation;

public interface TransportationService extends CommonService {

    void add(Transportation transportation);

    Transportation getById(Long id);

}

package main.java.Task8_TransportationWithUpdateAndCompare.transportation.service;

import main.java.Task8_TransportationWithUpdateAndCompare.common.business.service.CommonService;
import main.java.Task8_TransportationWithUpdateAndCompare.transportation.domain.Transportation;

public interface TransportationService extends CommonService {

    void add(Transportation transportation);

    Transportation getById(Long id);

    Transportation update(Long id, Transportation transportation);

}

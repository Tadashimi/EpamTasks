package main.java.Task9_TransportationWithException.common.business.service;

import main.java.Task9_TransportationWithException.common.solutions.exception.TransportationException;

public interface CommonService {

    boolean deleteById(Long id) throws TransportationException;

    void printAll();

}

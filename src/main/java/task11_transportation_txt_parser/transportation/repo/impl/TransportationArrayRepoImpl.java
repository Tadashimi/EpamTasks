package task11_transportation_txt_parser.transportation.repo.impl;

import task11_transportation_txt_parser.common.solutions.utils.ArrayUtils;
import task11_transportation_txt_parser.storage.IdGenerator;
import task11_transportation_txt_parser.transportation.domain.Transportation;
import task11_transportation_txt_parser.transportation.repo.TransportationRepo;

import java.util.Arrays;
import java.util.List;

import static task11_transportation_txt_parser.common.business.repo.CommonRepoHelper.findEntityIndexInArrayStorageById;
import static task11_transportation_txt_parser.storage.Storage.transportationArray;
import static task11_transportation_txt_parser.storage.Storage.transportationIndex;

public class TransportationArrayRepoImpl implements TransportationRepo {

    @Override
    public void add(Transportation transportation) {
        if (transportationIndex == transportationArray.length) {
            Transportation[] newTransportations =
                    new Transportation[transportationArray.length * 2];
            ArrayUtils.copyArray(transportationArray, newTransportations);
            transportationArray = newTransportations;
        }

        transportation.setId(IdGenerator.generateId());
        transportationArray[transportationIndex] = transportation;
        transportationIndex++;
    }

    @Override
    public Transportation getById(Long id) {
        for (Transportation transportation : transportationArray) {
            if (transportation != null && Long.valueOf(id).equals(transportation.getId())) {
                return transportation;
            }
        }

        return null;
    }

    @Override
    public List<Transportation> getAll() {
        return Arrays.asList(transportationArray);
    }

    @Override
    public void update(Transportation transportation) {

    }

    @Override
    public void addAll(List<Transportation> transportations) {
        if (transportations != null) {
            for (Transportation transportation : transportations) {
                if (transportation != null) {
                    add(transportation);
                }
            }
        }
    }

    @Override
    public boolean deleteById(Long id) {
        Integer indexToDelete = findEntityIndexInArrayStorageById(transportationArray, id);

        if (indexToDelete == null) {
            return false;
        } else {
            ArrayUtils.removeElement(transportationArray, indexToDelete);
            return true;
        }
    }
}

package task18_transportation_local_date_and_streams.transportation.repo.impl;


import task18_transportation_local_date_and_streams.common.solutions.utils.ArrayUtils;
import task18_transportation_local_date_and_streams.storage.IdGenerator;
import task18_transportation_local_date_and_streams.transportation.domain.Transportation;
import task18_transportation_local_date_and_streams.transportation.repo.TransportationRepo;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static task18_transportation_local_date_and_streams.common.business.repo.CommonRepoHelper.findEntityIndexInArrayStorageById;
import static task18_transportation_local_date_and_streams.storage.Storage.transportationArray;
import static task18_transportation_local_date_and_streams.storage.Storage.transportationIndex;

public class TransportationArrayRepoImpl implements TransportationRepo {

    @Override
    public void save(Transportation transportation) {
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
    public Optional<Transportation> findById(Long id) {
        for (Transportation transportation : transportationArray) {
            if (transportation != null && transportation.getId().equals(id)) {
                return Optional.of(transportation);
            }
        }

        return Optional.empty();
    }

    @Override
    public List<Transportation> getAll() {
        return Arrays.stream(transportationArray)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public int countAll() {
        return getAll().size();
    }

    @Override
    public boolean update(Transportation transportation) {
        return true;
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

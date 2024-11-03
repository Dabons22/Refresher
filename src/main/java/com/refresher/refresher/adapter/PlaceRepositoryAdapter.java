package com.refresher.refresher.adapter;

import com.refresher.refresher.entities.Places;
import com.refresher.refresher.repository.PlaceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Slf4j
public class PlaceRepositoryAdapter implements PlaceRepo {

    private final PlaceRepository placeRepository;

    @Override
    public List<Places> getAllPlace() {
        return placeRepository.findAll();
    }

    @Override
    public Places updatePlace(Long id, Places updatedPlaces) {
        return placeRepository.findById(id).map(places -> {
            places.setDescription(updatedPlaces.getDescription());
            places.setNameOfplace(updatedPlaces.getNameOfplace());
            places.setWeatherDescription(updatedPlaces.getWeatherDescription());
            return placeRepository.save(places);
        }).orElseThrow(() -> new NullPointerException("Id not found"));

    }

    @Override
    public void deletePlaceById(Long id) {

        placeRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Data not found"));
        placeRepository.deleteById(id);
        log.info("Successful deleted value of : {}",id);
    }

    @Override
    public Places savePlace(Places places) {
        if(Objects.nonNull(places.getId())){
            throw new IllegalArgumentException("Id must have a value");
        }
            return placeRepository.save(places);
    }

    @Override
    public Places getPlaceById(Long id) {
       return placeRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Id not found"));
    }
}

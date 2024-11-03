package com.refresher.refresher.service;

import com.refresher.refresher.adapter.PlaceRepositoryAdapter;
import com.refresher.refresher.entities.Places;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class PlacesServiceImpl {

    private final PlaceRepositoryAdapter placesService;

    public Places save(Places places) {
        log.info("Saving places:= {}", places.toString());
        return placesService.savePlace(places);
    }

    public void deleteById(Long id) {
        log.info("Deleting places:= {}", id);

        placesService.deletePlaceById(id);
    }

    public List<Places> getAllPlace() {
        List<Places> result = new ArrayList<>();
        result = placesService.getAllPlace();
        log.info("Fetching all places: {}", result);
        return result;
    }

    public Places findById(Long id) {
        log.info("Place found : {}", id);
        return placesService.getPlaceById(id);
    }

    public Places updatePlaces(Long id, Places places) {
        log.info("Updating places id: {}, place is : {}",id ,places);
        return placesService.updatePlace(id, places);
    }

}

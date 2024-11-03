package com.refresher.refresher.adapter;

import com.refresher.refresher.entities.Places;

import java.util.List;

public interface PlaceRepo {

    List<Places> getAllPlace();
    Places updatePlace(Long id,Places places);
    void deletePlaceById (Long id);
    Places savePlace (Places places);
    Places getPlaceById(Long id);
}

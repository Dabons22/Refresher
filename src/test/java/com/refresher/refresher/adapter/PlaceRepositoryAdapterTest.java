package com.refresher.refresher.adapter;

import com.refresher.refresher.entities.Places;
import com.refresher.refresher.repository.PlaceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PlaceRepositoryAdapterTest {

    private PlaceRepository placeRepository; // Mocking the PlaceRepository
    private PlaceRepositoryAdapter placeRepositoryAdapter; // Class under test

    @BeforeEach
    void setUp() {
        placeRepository = mock(PlaceRepository.class);
        placeRepositoryAdapter = new PlaceRepositoryAdapter(placeRepository);
    }

    @Test
    void getAllPlace() {
        Places places = new Places();
        places.setId(1L);
        places.setDescription("TestDescription");
        places.setWeatherDescription("Sunny");
        places.setNameOfplace("Rizal");

        when(placeRepository.findAll()).thenReturn(Collections.singletonList(places));

        // Act
        List<Places> data = placeRepositoryAdapter.getAllPlace();

        // Assert
        assertNotNull(data);
        assertEquals(1, data.size()); // Ensure we have one item
        Places retrievedPlace = data.get(0); // Get the first (and only) item
        assertEquals("TestDescription", retrievedPlace.getDescription());
        assertEquals("Rizal", retrievedPlace.getNameOfplace());
        assertEquals("Sunny", retrievedPlace.getWeatherDescription());

        // Verify that findAll was called
        verify(placeRepository, times(1)).findAll();
    }

    @Test
    void updatePlace() {
        Places existingData = new Places();
        existingData.setId(1L);
        existingData.setDescription("Long description");
        existingData.setWeatherDescription("Sunny");
        existingData.setNameOfplace("rizal");

        when(placeRepository.findById(existingData.getId())).thenReturn(Optional.of(existingData));
        when(placeRepository.save(existingData)).thenReturn(existingData);
        Places newData = new Places();
        newData.setId(1L);
        newData.setDescription("Long description");
        newData.setWeatherDescription("rainy");
        newData.setNameOfplace("new rizal");

        Places data = placeRepositoryAdapter.updatePlace(existingData.getId(), newData);

        assertNotNull(data);
        assertEquals("new rizal", data.getNameOfplace());
        assertEquals("Long description", data.getDescription());
        assertEquals("rainy", data.getWeatherDescription());

        verify(placeRepository,times(1)).findById(existingData.getId());
        verify(placeRepository,times(1)).save(existingData);

    }

    @Test
    void deletePlaceById_NotFound() {
        Long id  = 1L;

        when(placeRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class ,()->{
            placeRepositoryAdapter.deletePlaceById(id);
        });
        assertEquals("Data not found", exception.getMessage());
        verify(placeRepository,times(1)).findById(id);

    }
    @Test
    void deletePlaceById() {
        Long id  = 1L;

        Places existingPlace = new Places();
        existingPlace.setId(id);

        when(placeRepository.findById(id)).thenReturn(Optional.of(existingPlace));
        placeRepositoryAdapter.deletePlaceById(id);

        verify(placeRepository,times(1)).deleteById(id);

    }

    @Test
    void savePlace() {
        Places places = new Places();
        places.setId(1L);
        places.setDescription("TestDescription");
        places.setWeatherDescription("Sunny");
        places.setNameOfplace("Rizal");

        when(placeRepository.save(any(Places.class))).thenReturn(places);

        Places data = placeRepositoryAdapter.savePlace(places);

        assertNotNull(data);
        assertEquals("TestDescription", data.getDescription());
        assertEquals("Rizal", data.getNameOfplace());
        assertEquals("Sunny", data.getWeatherDescription());
        verify(placeRepository, times(1)).save(places);

    }

    @Test
    void savePlaceContainsInvalidID() {
        Places places = new Places();
        places.setId(1L);
        places.setDescription("TestDescription");
        places.setWeatherDescription("Sunny");
        places.setNameOfplace("Rizal");

        when(placeRepository.save(any(Places.class))).thenReturn(places);

        Exception exception = assertThrows(IllegalArgumentException.class,()->
                    placeRepositoryAdapter.savePlace(places),"Id must have value");
        assertEquals("Id must have a value",exception.getMessage());
        verify(placeRepository, never()).save(places);

    }

    @Test
    void getPlaceById() {
        Places places = new Places();
        places.setId(1L);
        places.setDescription("TestDescription");
        places.setWeatherDescription("Sunny");
        places.setNameOfplace("Rizal");

        when(placeRepository.findById(places.getId())).thenReturn(Optional.of(places));

        Places data = placeRepositoryAdapter.getPlaceById(places.getId());

        assertNotNull(data);
        assertEquals("TestDescription", data.getDescription());
        assertEquals("Rizal", data.getNameOfplace());
        assertEquals("Sunny", data.getWeatherDescription());
        verify(placeRepository, times(1)).findById(data.getId());
    }
}
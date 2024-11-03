package com.refresher.refresher.service;

import com.refresher.refresher.adapter.PlaceRepositoryAdapter;
import com.refresher.refresher.entities.Places;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlacesServiceImplTest {


    private PlacesServiceImpl placesService;
    private PlaceRepositoryAdapter placesServiceAdapter;

    @BeforeEach
    void setUp() {
    placesServiceAdapter = mock(PlaceRepositoryAdapter.class);
    placesService = new PlacesServiceImpl(placesServiceAdapter);
    }

    @Test
    void save_ShouldSavePlace() {
        // Arrange
        Places place = new Places(); // Assuming Places has a default constructor
        place.setId(1L);
        when(placesServiceAdapter.savePlace(any(Places.class))).thenReturn(place);

        // Act
        Places savedPlace = placesService.save(place);

        // Assert
        assertNotNull(savedPlace);
        assertEquals(1L, savedPlace.getId());
        verify(placesServiceAdapter, times(1)).savePlace(place);
    }

    @Test
    void givenById_whenIdIsMatch_thenDeleteDataById() {
        //Arrange
        Places places = new Places();
        places.setId(1L);
        //Act
        placesService.deleteById(places.getId());
        //Assert
        verify(placesServiceAdapter,times(1)).deletePlaceById(places.getId());
 }
    @Test
    void givenById_whenIdMatchToRecord_thenReturnPlace(){
        Long id = 1L;
        Places places = new Places();
        places.setId(id);

        when(placesServiceAdapter.getPlaceById(places.getId())).thenReturn(places);
        Places foundPlace = placesService.findById(id);

        assertNotNull(foundPlace);
        assertEquals(places,foundPlace);
        assertEquals(places.getId(),foundPlace.getId());
        verify(placesServiceAdapter,times(1)).getPlaceById(id);

    }
    @Test
    void givenPlaceWithNullId_whenSaving_thenThrowIllegalArgumentException() {
        // Arrange
        Places place = new Places();
        place.setId(null);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> placesService.save(place));
        assertEquals("Id must have a value", exception.getMessage());

        // Verify that savePlace is never called
        verify(placesServiceAdapter, never()).savePlace(any(Places.class));
    }

    @Test
    void givenValidId_whenAccessingUpdate_thenReturnNewRecord(){
        Long Id = 1L;
        Places places  = new Places();
        places.setId(Id);

        when(placesServiceAdapter.updatePlace(eq(Id),any(Places.class))).thenReturn(places);

        Places result = placesService.updatePlaces(Id,places);
        assertNotNull(result);
        assertEquals(Id,result.getId());
        verify(placesServiceAdapter,times(1)).updatePlace(eq(Id),any(Places.class));
    }
}

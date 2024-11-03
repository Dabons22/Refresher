package com.refresher.refresher.controller;

import com.refresher.refresher.entities.Places;
import com.refresher.refresher.service.PlacesServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/places")
@RequiredArgsConstructor
public class PlaceController {

    private final PlacesServiceImpl placesService;

    @PostMapping
    public ResponseEntity<Places> savePlace(@RequestBody Places places){
            Places savePlaces = placesService.save(places);
            return ResponseEntity.status(200).body(savePlaces);
    }
    @GetMapping("/allPlaces")
    public ResponseEntity<List<Places>> getAllPlaces(){
        List<Places> allPlaces = placesService.getAllPlace();
        return ResponseEntity.ok(allPlaces);
    }
    @GetMapping("/getPlace/{id}")
    public ResponseEntity<Places> getPlacesId(@PathVariable Long id){
        Places place = placesService.findById(id);
        return ResponseEntity.status(200).body(place);
    }
    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<Places> deleteByID(@PathVariable Long id){
        placesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public  ResponseEntity<Places> updateById(@PathVariable Long id, @PathVariable Places places){
        Places result =  placesService.updatePlaces(id,places);
        return ResponseEntity.status(201).body(result);
    }

}

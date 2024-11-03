package com.refresher.refresher.config;

import com.refresher.refresher.adapter.PlaceRepositoryAdapter;
import com.refresher.refresher.repository.PlaceRepository;
import com.refresher.refresher.service.PlacesServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlacesConfig {

    @Bean
    public PlacesServiceImpl placesService (PlaceRepositoryAdapter placesService){
            return new PlacesServiceImpl(placesService);
    }
    @Bean
    public PlaceRepositoryAdapter placeRepositoryAdapter( PlaceRepository placeRepository){
        return new PlaceRepositoryAdapter(placeRepository);
    }
}

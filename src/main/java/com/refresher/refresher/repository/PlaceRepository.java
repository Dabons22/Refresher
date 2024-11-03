package com.refresher.refresher.repository;

import com.refresher.refresher.entities.Places;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PlaceRepository  extends JpaRepository<Places,Long> {

}

package hr.tvz.napredna.java.repository;

import hr.tvz.napredna.java.model.Projekt;
import org.springframework.data.repository.CrudRepository;

//TODO promjeniti CrudRepository u JpaRepository

public interface ProjektRepository extends CrudRepository<Projekt, Integer> {


}

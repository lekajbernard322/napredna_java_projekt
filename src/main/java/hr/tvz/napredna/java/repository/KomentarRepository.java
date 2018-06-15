package hr.tvz.napredna.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.tvz.napredna.java.model.Komentar;

public interface KomentarRepository extends JpaRepository<Komentar, Integer> {
}
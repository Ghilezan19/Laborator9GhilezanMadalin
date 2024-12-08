package org.example.laborator9ghilezanmadalin.Masini;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface MasinaRepository extends JpaRepository<Masina, Integer> {

    Optional<Masina> gaseste_nr_inmatriculare(String numarInmatriculare);

    void stergere_dupa_nr(String numarInmatriculare);
    List<Masina> gaseste_dupa_marca(String marca);

    List<Masina> km_mai_putini(Integer numarKm);

    List<Masina> an_fab_mai_mare(Integer anul);

}

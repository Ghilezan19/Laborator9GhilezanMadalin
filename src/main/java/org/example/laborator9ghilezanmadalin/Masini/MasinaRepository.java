package org.example.laborator9ghilezanmadalin.Masini;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface MasinaRepository extends JpaRepository<Masina, Integer> {

    Optional<Masina> findByNumarInmatriculare(String numarInmatriculare);

    void deleteByNumarInmatriculare(String numarInmatriculare);
    List<Masina> findByMarca(String marca);

    List<Masina> findByNumarKmLessThan(Integer numarKm);

    List<Masina> findByAnulFabricatieiGreaterThan(Integer anul);

}

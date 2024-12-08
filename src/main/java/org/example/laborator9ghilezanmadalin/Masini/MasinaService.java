package org.example.laborator9ghilezanmadalin.Masini;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MasinaService {

    @Autowired
    private MasinaRepository masinaRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void incarcaDateDinSQL() {
        try {
            String sql = Files.lines(Paths.get("src/main/resources/data.sql"))
                    .collect(Collectors.joining("\n"));

            jdbcTemplate.execute(sql);
            System.out.println("Datele au fost incarcate in sql.");
        } catch (Exception e) {
            System.err.println("Datele nu s au incarcat in sql: " + e.getMessage());
        }
    }
    public Masina adaugaMasina(Masina masina) {
        return masinaRepository.save(masina);
    }

    public void stergeMasina(String numarInmatriculare) {
        Optional<Masina> masinaOptional = masinaRepository.gaseste_nr_inmatriculare(numarInmatriculare);

        if (masinaOptional.isPresent()) {
            Masina masina = masinaOptional.get();
            masinaRepository.delete(masina);
            System.out.println("Masina cu numarul de inmatriculare " + numarInmatriculare + " a fost stearsa.");
        } else {
            System.out.println("Nu a fost gasita nici o masina cu acest numar de inmatriculare.");
        }
    }

    public Optional<Masina> cautaMasina(String numarInmatriculare) {
        return masinaRepository.gaseste_nr_inmatriculare(numarInmatriculare);
    }

    public List<Masina> listeazaMasini() {
        return masinaRepository.findAll();
    }

    public long numarMasiniCuMarca(String marca) {
        return masinaRepository.gaseste_dupa_marca(marca).size();
    }

    public long numarMasiniSub100k() {
        return masinaRepository.km_mai_putini(100000).size();
    }

    public List<Masina> masiniMaiNoiDe5Ani() {
        int anulActual = 2024;
        return masinaRepository.an_fab_mai_mare(anulActual - 5);
    }

}

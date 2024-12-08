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
            System.out.println("Datele au fost încărcate cu succes din data.sql.");
        } catch (Exception e) {
            System.err.println("A apărut o eroare la încărcarea datelor: " + e.getMessage());
        }
    }
    public Masina adaugaMasina(Masina masina) {
        return masinaRepository.save(masina);
    }

    public void stergeMasina(String numarInmatriculare) {
        Optional<Masina> masinaOptional = masinaRepository.findByNumarInmatriculare(numarInmatriculare);

        if (masinaOptional.isPresent()) {
            Masina masina = masinaOptional.get(); // Extragem valoarea din Optional
            masinaRepository.delete(masina); // Ștergem mașina
            System.out.println("Mașina cu numărul de înmatriculare " + numarInmatriculare + " a fost ștearsă.");
        } else {
            System.out.println("Nu a fost găsită nicio mașină cu acest număr de înmatriculare.");
        }
    }

    public Optional<Masina> cautaMasina(String numarInmatriculare) {
        return masinaRepository.findByNumarInmatriculare(numarInmatriculare);
    }

    public List<Masina> listeazaMasini() {
        return masinaRepository.findAll();
    }

    public long numarMasiniCuMarca(String marca) {
        return masinaRepository.findByMarca(marca).size();
    }

    public long numarMasiniSub100k() {
        return masinaRepository.findByNumarKmLessThan(100000).size();
    }

    public List<Masina> masiniMaiNoiDe5Ani() {
        int anulActual = 2024;
        return masinaRepository.findByAnulFabricatieiGreaterThan(anulActual - 5);
    }

}

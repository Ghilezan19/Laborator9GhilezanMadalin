package org.example.laborator9ghilezanmadalin.Masini;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/masini")
public class MasinaController {

    @Autowired
    private MasinaService masinaService;

    @PostMapping("/adauga")
    public Masina adaugaMasina(@RequestBody Masina masina) {
        return masinaService.adaugaMasina(masina);
    }

    @DeleteMapping("/sterge/{numarInmatriculare}")
    public void stergeMasina(@PathVariable String numarInmatriculare) {
        masinaService.stergeMasina(numarInmatriculare);
    }

    @GetMapping("/cauta/{numarInmatriculare}")
    public Optional<Masina> cautaMasina(@PathVariable String numarInmatriculare) {
        return masinaService.cautaMasina(numarInmatriculare);
    }

    @GetMapping("/toate")
    public List<Masina> listeazaMasini() {
        return masinaService.listeazaMasini();
    }

    @GetMapping("/numar_masini_cu_marca/{marca}")
    public long numarMasiniCuMarca(@PathVariable String marca) {
        return masinaService.numarMasiniCuMarca(marca);
    }

    @GetMapping("/numar_masini_sub_100k")
    public long numarMasiniSub100k() {
        return masinaService.numarMasiniSub100k();
    }

    @GetMapping("/masini_mai_noi_5_ani")
    public List<Masina> masiniMaiNoiDe5Ani() {
        return masinaService.masiniMaiNoiDe5Ani();
    }
}

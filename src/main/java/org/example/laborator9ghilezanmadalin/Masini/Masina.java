package org.example.laborator9ghilezanmadalin.Masini;

import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;

@Entity
public class Masina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marca;
    private int anulFabricatiei;
    private String culoare;
    private String numarInmatriculare;
    private int numarKm;

    public Masina(String marca, int anulFabricatiei, String culoare, String numarInmatriculare, int numarKm) {
        this.marca = marca;
        this.anulFabricatiei = anulFabricatiei;
        this.culoare = culoare;
        this.numarInmatriculare = numarInmatriculare;
        this.numarKm = numarKm;
    }

    public Masina() {
    }
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAnulFabricatiei() {
        return anulFabricatiei;
    }

    public void setAnulFabricatiei(int anulFabricatiei) {
        this.anulFabricatiei = anulFabricatiei;
    }

    public String getCuloare() {
        return culoare;
    }

    public void setCuloare(String culoare) {
        this.culoare = culoare;
    }

    public String getNumarInmatriculare() {
        return numarInmatriculare;
    }

    public void setNumarInmatriculare(String numarInmatriculare) {
        this.numarInmatriculare = numarInmatriculare;
    }

    public int getNumarKm() {
        return numarKm;
    }

    public void setNumarKm(int numarKm) {
        this.numarKm = numarKm;
    }
    public interface MasinaRepository extends JpaRepository<Masina, Long> {
        void deleteByNumarInmatriculare(String numarInmatriculare);
    }
}

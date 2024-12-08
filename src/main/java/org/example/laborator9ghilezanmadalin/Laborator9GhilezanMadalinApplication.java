package org.example.laborator9ghilezanmadalin;

import org.example.laborator9ghilezanmadalin.Masini.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication(scanBasePackages = "org.example.laborator9ghilezanmadalin")
public class Laborator9GhilezanMadalinApplication implements CommandLineRunner {

    @Autowired
    private MasinaService masinaService;

    public static void main(String[] args) {
        SpringApplication.run(Laborator9GhilezanMadalinApplication.class, args);
    }
    private static final Logger logger = LoggerFactory.getLogger(Laborator9GhilezanMadalinApplication.class);
    Scanner scanner = new Scanner(System.in);
    @Override
    public void run(String... args) {
        logger.info("Start...");
        int optiune;

        do {
            System.out.println("\n---------Meniu----------");
            System.out.println("1.Adauga o masina");
            System.out.println("2.Sterge o masina dupa numarul de inmatriculare");
            System.out.println("3.Cauta o masina dupa numarul de inmatriculare");
            System.out.println("4.Listeaza toate masinile");
            System.out.println("5.Numarul de masini cu o anumita marca");
            System.out.println("6.Numarul de masini cu sub 100.000 km");
            System.out.println("7.Listeaza masinile mai noi de 5 ani");
            System.out.println("8.Adauga Masinile in Baza de date");
            System.out.println("0. Iesire");
            System.out.print("Alegere: ");

            optiune = scanner.nextInt();
            scanner.nextLine(); // Consumăm linia rămasă

            switch (optiune) {
                case 1:
                    System.out.print("Numărul de înmatriculare: ");
                    String numarInmatriculare = scanner.nextLine();
                    System.out.print("Marca: ");
                    String marca = scanner.nextLine();
                    System.out.print("Anul fabricației: ");
                    int anulFabricatiei = scanner.nextInt();
                    scanner.nextLine(); // Consumăm linia rămasă
                    System.out.print("Culoare: ");
                    String culoare = scanner.nextLine();
                    System.out.print("Număr km: ");
                    int numarKm = scanner.nextInt();
                    scanner.nextLine();

                    Masina masina = new Masina(marca,anulFabricatiei,culoare,numarInmatriculare,numarKm);
                    masinaService.adaugaMasina(masina);
                    System.out.println("Mașina a fost adăugată.");
                    break;
                case 2:
                    System.out.print("Numărul de înmatriculare al mașinii de șters: ");
                    String numarStergere = scanner.nextLine();
                    masinaService.stergeMasina(numarStergere);
                    System.out.println("Mașina a fost ștearsă.");
                    break;
                case 3:
                    System.out.print("Numărul de înmatriculare pentru căutare: ");
                    String numarCautare = scanner.nextLine();
                    masinaService.cautaMasina(numarCautare).ifPresentOrElse(
                            m -> System.out.println("Mașina găsită: " + m.getMarca() + " " + m.getAnulFabricatiei()),
                            () -> System.out.println("Mașina nu a fost găsită.")
                    );
                    break;
                case 4:
                    List<Masina> masini = masinaService.listeazaMasini();
                    System.out.println("Lista mașinilor din baza de date:");
                    masini.forEach(m -> System.out.println(m.getNumarInmatriculare() + " - " + m.getMarca()+" - "+m.getAnulFabricatiei()+" - "+m.getCuloare()+" - "+m.getNumarKm()));
                    break;
                case 5:
                    System.out.print("Introdu marca: ");
                    String marcaCautata = scanner.nextLine();
                    System.out.println("Numărul de mașini cu această marcă: " + masinaService.numarMasiniCuMarca(marcaCautata));
                    break;
                case 6:
                    System.out.println("Numărul de mașini cu sub 100.000 km: " + masinaService.numarMasiniSub100k());
                    break;
                case 7:
                    List<Masina> masiniNoi = masinaService.masiniMaiNoiDe5Ani();
                    System.out.println("Lista mașinilor mai noi de 5 ani:");
                    masiniNoi.forEach(m -> System.out.println(m.getNumarInmatriculare() + " - " + m.getMarca()));
                    break;
                case 8:
                    masinaService.incarcaDateDinSQL(); // Încarcă datele din data.sql
                    break;
                case 0:
                    System.out.println("La revedere!");
                    break;
                default:
                    System.out.println("Opțiune invalidă. Încearcă din nou.");
            }
        } while (optiune != 0);

        scanner.close();
    }
}

package testsUnitaires;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import personnel.*;

class testLigue 
{
	GestionPersonnel gestionPersonnel = GestionPersonnel.getGestionPersonnel();
	
	@Test
	void createLigue() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		assertEquals("Fléchettes", ligue.getNom());
	}

	@Test
	void addEmploye() throws SauvegardeImpossible, DateInvalide
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty", null, null); 
		assertEquals(employe, ligue.getEmployes().first());
		
	}
	
	@Test
	void exceptionArriveeDansLeFutur() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		LocalDate futur = LocalDate.now().plusDays(1);
		assertThrows(DateInvalide.class, () -> ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty", futur, null));
	}

	@Test
	void exceptionSetterDateDepart() throws SauvegardeImpossible, DateInvalide
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		LocalDate arrivee = LocalDate.of(2024, 5, 10);
		LocalDate depart = LocalDate.of(2024, 5, 20);
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty", arrivee, depart);
		assertThrows(DateInvalide.class, () -> employe.setDateDepart(LocalDate.of(2024, 5, 1)));
		assertEquals(depart, employe.getDateDepart());
	}
	
}



package commandLine;

import static commandLineMenus.rendering.examples.util.InOut.getString;

import commandLineMenus.ListOption;
import commandLineMenus.Menu;
import commandLineMenus.Option;
import personnel.Employe;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import personnel.DateInvalide;


public class EmployeConsole 
{
	//j'ai enlevé le private devant la methode afficher pour que LigueConsole puisse l'utiliser
	 Option afficher(final Employe employe)
	{
		return new Option("Afficher l'employé", "l", () -> {System.out.println(employe);});
	}

	ListOption<Employe> editerEmploye()
	{
		return (employe) -> editerEmploye(employe);		
	}

	Option editerEmploye(Employe employe)
	{
			Menu menu = new Menu("Gérer le compte " + employe.getNom(), "c");
			menu.add(afficher(employe));
			menu.add(changerNom(employe));
			menu.add(changerPrenom(employe));
			menu.add(changerMail(employe));
			menu.add(changerPassword(employe));
			menu.add(changerDateArrivee(employe));
			menu.add(changerDateDepart(employe));
			menu.addBack("q");
			return menu;
	}

	private Option changerNom(final Employe employe)
	{
		return new Option("Changer le nom", "n", 
				() -> {employe.setNom(getString("Nouveau nom : "));}
			);
	}
	
	private Option changerPrenom(final Employe employe)
	{
		return new Option("Changer le prénom", "p", () -> {employe.setPrenom(getString("Nouveau prénom : "));});
	}
	
	private Option changerMail(final Employe employe)
	{
		return new Option("Changer le mail", "e", () -> {employe.setMail(getString("Nouveau mail : "));});
	}
	
	private Option changerPassword(final Employe employe)
	{
		return new Option("Changer le password", "x", () -> {employe.setPassword(getString("Nouveau password : "));});
	}
	
	private Option changerDateArrivee(final Employe employe)
	{
		return new Option("Changer la date d'arrivée", "y", () -> {
			try
			{
				employe.setDateArrivee(lireDate("Nouvelle date d'arrivée (aaaa-mm-jj, vide = aucune) : "));
			}
			catch (DateInvalide e)
			{
				System.err.println(e.getMessage());
			}
		});
	}

    	private Option changerDateDepart(final Employe employe)
	{
		return new Option("Changer la date de départ", "z", () -> {
			try
			{
				employe.setDateDepart(lireDate("Nouvelle date de départ (aaaa-mm-jj, vide = aucune) : "));
			}
			catch (DateInvalide e)
			{
				System.err.println(e.getMessage());
			}
		});
	}
    	
    	LocalDate lireDate(String question)
    	{
    		while (true)
    		{
    			String saisie = getString(question).trim();
    			if (saisie.isEmpty())
    				return null;
    			try
    			{
    				return LocalDate.parse(saisie);
    			}
    			catch (DateTimeParseException e)
    			{
    				System.err.println("Format invalide, utilisez aaaa-mm-jj.");
    			}
    		}
    	}
	
}

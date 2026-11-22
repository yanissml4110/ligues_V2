package commandLine;

import static commandLineMenus.rendering.examples.util.InOut.getString;
import java.util.ArrayList;
import commandLineMenus.List;
import commandLineMenus.Menu;
import commandLineMenus.Option;



import personnel.*;

public class LigueConsole 
{
	private GestionPersonnel gestionPersonnel;
	private EmployeConsole employeConsole;

	public LigueConsole(GestionPersonnel gestionPersonnel, EmployeConsole employeConsole)
	{
		this.gestionPersonnel = gestionPersonnel;
		this.employeConsole = employeConsole;
	}

	Menu menuLigues()
	{
		Menu menu = new Menu("Gérer les ligues", "l");
		menu.add(afficherLigues());
		menu.add(ajouterLigue());
		menu.add(selectionnerLigue());
		menu.addBack("q");
		return menu;
	}

	private Option afficherLigues()
	{
		return new Option("Afficher les ligues", "l", () -> {System.out.println(gestionPersonnel.getLigues());});
	}

	private Option afficher(final Ligue ligue)
	{
		return new Option("Afficher la ligue", "l", 
				() -> 
				{
					System.out.println(ligue);
					System.out.println("administrée par " + ligue.getAdministrateur());
				}
		);
	}
	private Option afficherEmployes(final Ligue ligue)
	{
		return new Option("Afficher les employes", "l", () -> {System.out.println(ligue.getEmployes());});
	}

	private Option ajouterLigue()
	{
		return new Option("Ajouter une ligue", "a", () -> 
		{
			try
			{
				gestionPersonnel.addLigue(getString("nom : "));
			}
			catch(SauvegardeImpossible exception)
			{
				System.err.println("Impossible de sauvegarder cette ligue");
			}
		});
	}
	
	private Menu editerLigue(Ligue ligue)
	{
		Menu menu = new Menu("Editer " + ligue.getNom());
		menu.add(afficher(ligue));
		menu.add(gererEmployes(ligue));
		menu.add(changerAdministrateur(ligue));
		menu.add(changerNom(ligue));
		menu.add(supprimer(ligue));
		menu.addBack("q");
		return menu;
	}

	private Option changerNom(final Ligue ligue)
	{
		return new Option("Renommer", "r", 
				() -> {ligue.setNom(getString("Nouveau nom : "));});
	}

	private List<Ligue> selectionnerLigue()
	{
		return new List<Ligue>("Sélectionner une ligue", "e", 
				() -> new ArrayList<>(gestionPersonnel.getLigues()),
				(element) -> editerLigue(element)
				);
	}
	/** itération 2 (selectionner un employé)
	 Option de sélection d'un employé de la ligue. L'employé choisi
	 * ouvre un sous-menu (afficher, modifier, supprimer).
	 */
	
	private List<Employe> selectionnerEmploye(final Ligue ligue)
	{
		return new List<>("Sélectionner un employé", "s",
				() -> new ArrayList<>(ligue.getEmployes()),
				(employe) -> menuEmploye(employe)
				);
	}
	
	/**
	 * Sous-menu d'un employé déjà sélectionné : afficher, modifier ou supprimer.
	 */

	private Menu menuEmploye(final Employe employe)
	{
		Menu menu = new Menu("Employé : " + employe.getNom());
		menu.add(employeConsole.afficher(employe));
		menu.add(employeConsole.editerEmploye(employe));
		menu.add(supprimerEmploye(employe));
		menu.addBack("q");
		return menu;
	}
	
	/**
	 * Option qui supprime l'employé sélectionné.
	 */
	private Option supprimerEmploye(final Employe employe)
	{
		return new Option("Supprimer l'employé", "d", () -> {employe.remove();});
	}
	
	
	private Option ajouterEmploye(final Ligue ligue)
	{
		return new Option("ajouter un employé", "a",
				() -> 
				{
					try 
					{
						ligue.addEmploye(getString("nom : "), 
								getString("prenom : "), 
								getString("mail : "), 
								getString("password : "),
								employeConsole.lireDate("date d'arrivée (aaaa-mm-jj, vide = aucune) : "),
								employeConsole.lireDate("date de départ (aaaa-mm-jj, vide = aucune) : "));
					}
					catch (DateInvalide | SauvegardeImpossible exception)
					{
						System.err.println(exception.getMessage());
					}
				}
		);
	}
	
	private Menu gererEmployes(Ligue ligue)
	{
		Menu menu = new Menu("Gérer les employés de " + ligue.getNom(), "e");
		menu.add(afficherEmployes(ligue));
		menu.add(ajouterEmploye(ligue));
		menu.add(selectionnerEmploye(ligue)); // j'ai remplacer le modifier par selectionner
		menu.addBack("q");
		return menu;
	}
	/**
	 * Option qui permet de choisir, parmi les employés de la ligue,
	 * le nouvel administrateur.
	 */
	private List<Employe> changerAdministrateur(final Ligue ligue)
	{
		return new List<>("Changer d'administrateur", "c",
				() -> new ArrayList<>(ligue.getEmployes()),
				(index, employe) -> 
				{
					try
					{
						ligue.setAdministrateur(employe);
						System.out.println(employe.getNom() + " est maintenant administrateur de " + ligue.getNom());
					}
					catch (DroitsInsuffisants e)
					{
						System.err.println("Cet employé ne peut pas être administrateur de cette ligue.");
					}
				}
				);
	}

	
	private Option supprimer(Ligue ligue)
	{
		return new Option("Supprimer", "d", () -> {ligue.remove();});
	}
	
}

package personnel;

import java.io.Serializable;
import java.time.LocalDate; // l'importation pour les dates

/**
 * Employé d'une ligue hébergée par la M2L. Certains peuvent 
 * être administrateurs des employés de leur ligue.
 * Un seul employé, rattaché à aucune ligue, est le root.
 * Il est impossible d'instancier directement un employé, 
 * il faut passer la méthode {@link Ligue#addEmploye addEmploye}.
 */

public class Employe implements Serializable, Comparable<Employe>
{
	private static final long serialVersionUID = 4795721718037994734L;
	private String nom, prenom, password, mail;
	private Ligue ligue;
	private GestionPersonnel gestionPersonnel;
	private LocalDate dateArrivee, dateDepart;
	Employe(GestionPersonnel gestionPersonnel, Ligue ligue, String nom, String prenom, String mail, String password, LocalDate dateArrivee, LocalDate dateDepart)
	throws DateInvalide, SauvegardeImpossible
	{
		this.gestionPersonnel = gestionPersonnel;
		this.nom = nom;
		this.prenom = prenom;
		this.password = password;
		this.mail = mail;
		this.ligue = ligue;
		validateDateArrivee(dateArrivee);
		validateDateDepart(dateArrivee, dateDepart);
		this.dateArrivee = dateArrivee;
		this.dateDepart = dateDepart;
	}
	
	/**
	 * Regle metier: la date d'arrivee ne peut pas etre dans le futur.
	 */
	
	private void validateDateArrivee(LocalDate dateArrivee) throws DateInvalide
	{
		if (dateArrivee != null && dateArrivee.isAfter(LocalDate.now()))
			throw new DateInvalide("La date d'arrivee ne peut pas etre superieure a la date actuelle.");
	}
	
	/**
	 * Regle metier: la date de depart ne peut pas preceder la date d'arrivee.
	 */
	private void validateDateDepart(LocalDate dateArrivee, LocalDate dateDepart) throws DateInvalide
	{
		if (dateDepart != null && dateArrivee != null && dateDepart.isBefore(dateArrivee))
			throw new DateInvalide("La date de depart ne peut pas etre avant la date d'arrivee.");
	}
	/**
	 * Retourne vrai ssi l'employé est administrateur de la ligue 
	 * passée en paramètre.
	 * @return vrai ssi l'employé est administrateur de la ligue 
	 * passée en paramètre.
	 * @param ligue la ligue pour laquelle on souhaite vérifier si this 
	 * est l'admininstrateur.
	 */
	
	public boolean estAdmin(Ligue ligue)
	{
		return ligue.getAdministrateur() == this;
	}
	
	/**
	 * Retourne vrai ssi l'employé est le root.
	 * @return vrai ssi l'employé est le root.
	 */
	
	public boolean estRoot()
	{
		return gestionPersonnel.getRoot() == this;
	}
	
	/**
	 * Retourne le nom de l'employé.
	 * @return le nom de l'employé. 
	 */
	
	public String getNom()
	{
		return nom;
	}

	/**
	 * Change le nom de l'employé.
	 * @param nom le nouveau nom.
	 */
	
	public void setNom(String nom)
	{
		this.nom = nom;
	}

	/**
	 * Retourne le prénom de l'employé.
	 * @return le prénom de l'employé.
	 */
	
	public String getPrenom()
	{
		return prenom;
	}
	
	/**
	 * Change le prénom de l'employé.
	 * @param prenom le nouveau prénom de l'employé. 
	 */

	public void setPrenom(String prenom)
	{
		this.prenom = prenom;
	}

	/**
	 * Retourne le mail de l'employé.
	 * @return le mail de l'employé.
	 */
	
	public String getMail()
	{
		return mail;
	}
	
	/**
	 * Retourne la date d'arrivée de l'employé.
	 * @return la date d'arrivée de l'employé.
	 */
	public LocalDate getDateArrivee()
	{
		return dateArrivee;
	}

	/**
	 * Change la date d'arrivée de l'employé.
	 * @param dateArrivee la nouvelle date d'arrivée de l'employé.
	 * @throws DateInvalide si la date est dans le futur ou postérieure à la date de départ.
	 */
	public void setDateArrivee(LocalDate dateArrivee) throws DateInvalide
	{
		validateDateArrivee(dateArrivee);
		validateDateDepart(dateArrivee, this.dateDepart);
		this.dateArrivee = dateArrivee;
	}

	/**
	 * Retourne la date de départ de l'employé.
	 * @return la date de départ de l'employé.
	 */
	public LocalDate getDateDepart()
	{
		return dateDepart;
	}

	/**
	 * Change la date de départ de l'employé.
	 * @param dateDepart la nouvelle date de départ de l'employé.
	 * @throws DateInvalide si la date est antérieure à la date d'arrivée.
	 */
	public void setDateDepart(LocalDate dateDepart) throws DateInvalide
	{
		validateDateDepart(this.dateArrivee, dateDepart);
		this.dateDepart = dateDepart;
	}
	
	/**
	 * Change le mail de l'employé.
	 * @param mail le nouveau mail de l'employé.
	 */

	public void setMail(String mail)
	{
		this.mail = mail;
	}

	/**
	 * Retourne vrai ssi le password passé en paramètre est bien celui
	 * de l'employé.
	 * @return vrai ssi le password passé en paramètre est bien celui
	 * de l'employé.
	 * @param password le password auquel comparer celui de l'employé.
	 */
	
	public boolean checkPassword(String password)
	{
		return this.password.equals(password);
	}

	/**
	 * Change le password de l'employé.
	 * @param password le nouveau password de l'employé. 
	 */
	
	public void setPassword(String password)
	{
		this.password= password;
	}

	/**
	 * Retourne la ligue à laquelle l'employé est affecté.
	 * @return la ligue à laquelle l'employé est affecté.
	 */
	
	public Ligue getLigue()
	{
		return ligue;
	}

	/**
	 * Supprime l'employé. Si celui-ci est un administrateur, le root
	 * récupère les droits d'administration sur sa ligue.
	 */
	
	public void remove()
	{
		Employe root = gestionPersonnel.getRoot();
		if (this != root)
		{
			if (estAdmin(getLigue()))
				getLigue().setAdministrateur(root);
			getLigue().remove(this);
		}
		else
			throw new ImpossibleDeSupprimerRoot();
	}

	@Override
	public int compareTo(Employe autre)
	{
		int cmp = getNom().compareTo(autre.getNom());
		if (cmp != 0)
			return cmp;
		return getPrenom().compareTo(autre.getPrenom());
	}
	
	@Override
	public String toString()
	{
		String res = nom + " " + prenom + " " + mail + " (";
		if (estRoot())
			res += "super-utilisateur";
		else
			res += ligue.toString();
		return res + ")";
	}
}

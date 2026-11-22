package personnel;

/**
 * Exception levée lorsque les dates d'un employé sont incohérentes.
 */
public class DateInvalide extends Exception
{
	private static final long serialVersionUID = 1L;

	public DateInvalide(String message)
	{
		super(message);
	}
}
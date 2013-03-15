/**
 * Spanish verb conjugation data. Stores a verb in its infinitive form,
 * if it is irregular, and if so what does the stem change to.
 * 
 * @author Spenser Pothier
 *
 */
public class VerbData {

	private String infinitive;
	@SuppressWarnings("unused")
	private String stemChange;
	private boolean irregular;

	/**
	 * Default constructor, preferably to be called by a method that loads
	 * the necessary verbs from a text file.
	 * @param intInf Verb in its infinitive form
	 * @param intIrr Irregularity 
	 * @param intStem Stem change in verb
	 */
	public VerbData(String intInf, boolean intIrr, String intStem)
	{
		infinitive = intInf;
		stemChange = intStem;
		irregular = intIrr;
	}
	
	/**
	 * Method to conjugate this verb
	 * @param Tense the tense in which to conjugate the verb
	 * @param Subject the subject with which the verb is to be conjugated
	 * @return A conjugated verb 
	 */
	public String conjugate(int tense, int subject)
	{
		if (tense < Tense.PRESENT || tense > Tense.IMPERFECT)
				throw new IllegalArgumentException();
		
		String output = infinitive.substring(0,infinitive.length() - 2);
		
		if (!irregular)
			System.out.println("Fix me");
		if ( infinitive.substring(infinitive.length() - 2).equalsIgnoreCase("ar"))
			output = output + Endings.ENDS[subject][tense][Endings.AR];		
		
		if (infinitive.substring(infinitive.length() - 2).equalsIgnoreCase("er"))
			output = output + Endings.ENDS[subject][tense][Endings.ER];
		
		if (infinitive.substring(infinitive.length() - 2).equalsIgnoreCase("ir"))
			output = output + Endings.ENDS[subject][tense][Endings.IR];
		
		System.out.println(output);
		return output;
	}
	
	/**
	 * Query method
	 * @return Infinitive form of the verb
	 */
	public String getInfinitive()
	{
		return infinitive;
	}
	
	/**
	 * Query function 
	 * @return true if the verb is irregular.
	 */
	public boolean isIrregular()
	{
		return irregular;
	}

	
	static class Endings
	{	
		
		public static final int AR = 0; 
		public static final int ER = 1;
		public static final int IR = 2;
		
		public static final int YO = 0;
		public static final int TU = 1;
		public static final int EL_USTED = 2;
		public static final int NOSOTROS = 3;
		public static final int ELLOS_USTEDS = 4;
		
		// Subjects - Yo, Tœ, ƒl/Usted, Nosotros, Ellos/Ustedes
		// Tenses - Present, Preterite, Imperfect
		// Endings - AR, ER, IR
		
		// [Subject][tense][ending]
		public static final String ENDS[][][] = {
												 {{"o","o","o"}, {"Ž","’","’"}, {"aba","’a","’a"}},
			 									  
												 {{"as","es","is"}, {"aste","iste","iste"},{"abas","’as","’as"}},
												  
												 {{"a","e","e"}, {"—","i—","i—"}, {"aba", "’a","’a"}},
												   
												 {{"amos","emos","imos"}, {"amos","imos","imos"},{"abamos","’amos","’amos"}},
												   
												 {{"an","en","en"}, {"aron","ieron","ieron"},{"aban","’an","’an"}}
												};
		public static final String subPronouns[] = {"Yo","Tœ","ƒl/Usted","Nosotros","Ellos/Ustedes"};
		public static final String tenses[] = {"Present","Preterite", "Imperfect"};
	}
	static class Tense
	{
		public static final int PRESENT = 0;
		public static final int PRETERITE = 1;
		public static final int IMPERFECT = 2;
	}
}

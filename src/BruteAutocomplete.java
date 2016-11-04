import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Denise Doyle BruteAutocomplete class Initiates Term ArrayList, parses
 *         data for BufferedReader, methods for weightOf, bestMatch and matches
 */

public class BruteAutocomplete implements Autocomplete {

	String bestMatch;
	String terms1;
	double minWeight = -1.0;
	Term term;
	double termWeight = 0.0;

	List<Term> terms = new ArrayList<Term>();

	public BruteAutocomplete(String file) throws Exception {
		BufferedReader readIn = null;

		try {
			readIn = new BufferedReader(new FileReader(file));

			String line = "";
			int count = 0;
			while ((line = readIn.readLine()) != null) {
				/*
				 * count to skip first line
				 * http://stackoverflow.com/questions/23236000/bufferedreader-to
				 * -skip-first-line
				 */
				if (count == 0) {
					count++;
					continue;
				}

				// split the lines at tab
				String parts[] = line.split("\t");
				// remove spaces from weight
				parts[0] = parts[0].replaceAll(" ", "");
				// resolve Term to new parsed data with weight-spaces and query
				// in lower case
				Term term = new Term(Long.parseLong(parts[0]), parts[1].toLowerCase());

				// Caused a lot of trouble when returning matches, constantly
				// returned null because of duplication?
				for (Term termObject : terms)
					if (termObject.query == parts[1])
						throw new IllegalArgumentException();
				terms.add(term);
			}
		} finally {
			if (readIn != null)
				// leaking resource without close
				readIn.close();
		}

	}
	
	/* bestMatch
	 * Check to make sure prefix is valid
	 * If weight is valid and prefix matches existing term, return term with highest weight
	 * 
	 */

	@Override
	public String bestMatch(String prefix) {
		if (prefix == "" || prefix == null)
			throw new NullPointerException();

		for (Term term : terms)
			if (term.getWeight() > minWeight && term.getTerm().toLowerCase().startsWith(prefix)) {
				minWeight = term.getWeight();
				bestMatch = term.getTerm();
			}

		return bestMatch;
	}
	
	/* weightOf
	 * Check to make sure term is valid
	 * For all terms, check current term weight and return
	 * 
	 */

	@Override
	public double weightOf(String term) {
		if (term == "" || term == null)
			throw new NullPointerException();

		term = term.toLowerCase();
		for (Term termObject : terms)
			if (termObject.query.equals(term)) 
				return termObject.weight;


		return 0.0;
	}
	
	/* Iterable<String>matches
	 * Check to see if k and prefix are not null
	 * Creates an ArrayList of matches that contains all matches whose weight is valid and contain the correct prefix
	 * Bugged for high values.. only returns smaller values, maybe reverting to zero when number of matches is less than asked for??
	 */

	@Override
	public Iterable<String> matches(String prefix, int k) {
		if (k < 0)
			throw new IllegalArgumentException();

		if (prefix == null || prefix == "" || k == 0)
			throw new NullPointerException();

		prefix = prefix.toLowerCase();

		int count = 0;
		ArrayList<String> matches = new ArrayList<String>();
		for (Term t : terms) {
			if (count <= k - 1) {
				if (t.getWeight() > minWeight && t.getTerm().toLowerCase().startsWith(prefix)) {
					terms1 = t.getTerm();
					matches.add(terms1);
					count++;
				}
			}
		}
		return matches;
	}

}
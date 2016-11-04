/**
 * @author Denise Doyle 
 * Term class that checks to make sure if query and weight are within valid limits, otherwise; throws exceptions
 */

public class Term {
	
	double weight;
	String query;
	
	public Term(double weight, String query){
		
		if(weight < 0){
			throw new IllegalArgumentException();
		}

		if(query == "" || query == null){
			throw new NullPointerException();
		}
		
		this.weight = weight;
		this.query = query;
	}

	public double getWeight() {
		return weight;
	}

	public String getTerm() {
		return query;
	}

}

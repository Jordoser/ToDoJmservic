package ualberta.cs.ca.todojmservic;

public class Todos {
	protected String todotask;
	
	public Todos (String todotask){
		this.todotask = todotask;
	}
	
	public String getTodoText() {
		return todotask;
		
	}
	public String toString(){
		return getTodoText();
	}

}

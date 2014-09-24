package ualberta.cs.ca.todojmservic;

import java.io.Serializable;
import java.util.ArrayList;

public class Todos implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8112131533333600701L;
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

	public String done() {
		return (todotask + "\u2713");
		
	}

	public String notdone() {
		return todotask.replace("\u2713", "");

	}



}

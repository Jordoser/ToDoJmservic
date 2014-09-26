//Main class that holds list of todos/emails and allows for manipulation of those lists via add, remove as well as keeping track of listeners for the observerpattern
package ualberta.cs.ca.todojmservic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import android.R.string;

public class TodoList implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3949756761262991636L;
	protected ArrayList<Todos> todolist;
	protected transient ArrayList<Listener> listeners;

	
	
	public TodoList(){
		todolist = new ArrayList<Todos>();
		listeners = new ArrayList<Listener>();
		
	}
	
	
	private ArrayList<Listener> getListeners() {
		if (listeners == null ) {
			listeners = new ArrayList<Listener>();
		}
		return listeners;
	}
	
	public Collection<Todos> getTodos(){
		return todolist;

	}
	
	public void addTodo(Todos todo){
		todolist.add(todo);
		notifyeveryone();
	}
	
	private void notifyeveryone() {
		for(Listener listener: listeners){
			listener.update();
		}
		
	}
	
	public void check(Todos todotask) {
		if (todotask.toString().toLowerCase().contains("\u2713")){
			int index1 = todolist.indexOf(todotask);
			todolist.remove(todotask);
			Todos todotask2 = new Todos (todotask.notdone());
			todolist.add(index1,todotask2);
			notifyeveryone();
		}
		else{
			int index1 = todolist.indexOf(todotask);
			todolist.remove(todotask);
			Todos todotask2 = new Todos (todotask.done());
			todolist.add(index1,todotask2);
			notifyeveryone();
		}
		
	}

	public void removeTodo(Todos todo){
		todolist.remove(todo);
		notifyeveryone();
	}
	
	public void addListener(Listener l){
		getListeners().add(l);
	}
	public void removeListener(Listener l){
		getListeners().remove(l);
	}
	
	public String Count(){
		int count = 0;
		String scount = "";
		for (Todos TodoList : todolist) {
			if (TodoList.toString().toLowerCase().contains("\u2713")){
				count = count + 1;
			}
		}
		scount = new Integer(count).toString();
		return scount;
		
	}
	
	public String UnCount(){
		int count = todolist.size() - Integer.parseInt(Count());
		String scount = "";
		scount = new Integer(count).toString();
		return scount;
	}

	public CharSequence TotalCount() {
		int count  = todolist.size();
		String scount = new Integer(count).toString(); 
		return scount;
	}

	public void clear(){
		todolist = new ArrayList<Todos>();
		notifyeveryone();
	}


	public void set(ArrayList<Todos> emails) {
		todolist = emails;
		notifyeveryone();
		
		
	}

}

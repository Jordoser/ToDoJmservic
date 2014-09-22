package ualberta.cs.ca.todojmservic;

import java.util.ArrayList;
import java.util.Collection;

public class TodoList {
	
	protected ArrayList<Todos> todolist;
	
	public TodoList(){
		todolist = new ArrayList<Todos>();
	}
	
	public Collection<Todos> getTodos(){
		return todolist;

	}
	
	public void addTodo(Todos todo){
		todolist.add(todo);
	}
	
	public void removeTodo(Todos todo){
		todolist.remove(todo);
	}
}

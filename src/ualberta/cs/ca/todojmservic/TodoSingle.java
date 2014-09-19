package ualberta.cs.ca.todojmservic;

public class TodoSingle {
	private static TodoList todolist = null;
	static public TodoList getTodoList(){
		if (todolist == null){
			todolist = new TodoList();
		}
		return todolist;
	}
	public void addTodo(Todos todos) {
		getTodoList().addTodo(todos);
		
	}
	
}

package ualberta.cs.ca.todojmservic;

public class ArchiveSingle {
	
	private static ArchiveList archive = null;
	
	static public TodoList getArchive(){
		if (archive == null){
			archive = new ArchiveList();
		}
		return archive;
	}
	public void addArchive(Todos todos) {
		getArchive().addTodo(todos);
		
	}
}

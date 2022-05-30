package bstorm.akimts.jdbc.exceptions;

public class EntityNotFoundException extends RuntimeException {
	
	private final Object searchBy;
	private final Class<?> searchByType;
	private final Class<?> searched;
	
	public EntityNotFoundException(Object searchBy, Class<?> searchByType, Class<?> searched) {
		super("L'entité ("+ searched.getSimpleName() +") recherchée grâce à la valeur {"+searchBy+"("+ searchByType.getSimpleName() + ")} n'a pas été trouvée" );
		this.searchBy = searchBy;
		this.searchByType = searchByType;
		this.searched = searched;
	}
	
	public Object getSearchBy() {
		return searchBy;
	}
	public Class<?> getSearchByType() {
		return searchByType;
	}
	public Class<?> getSearched() {
		return searched;
	}
	
	
	
}

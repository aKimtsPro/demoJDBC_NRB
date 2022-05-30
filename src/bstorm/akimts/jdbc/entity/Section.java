package bstorm.akimts.jdbc.entity;

public class Section {
	
	private long id;
	private String nom;
	private Student delegate;
	
	public Section() {}
	
	public Section(long id, String nom, Student delegate) {
		super();
		this.id = id;
		this.nom = nom;
		this.delegate = delegate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Student getDelegate() {
		return delegate;
	}

	public void setDelegate(Student delegate) {
		this.delegate = delegate;
	}

}

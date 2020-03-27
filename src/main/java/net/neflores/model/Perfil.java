package net.neflores.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Perfiles")
public class Perfil {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idPerfiles;
	private String perfil;
	
	
	
	public Integer getId() {
		return idPerfiles;
	}
	public void setId(Integer id) {
		this.idPerfiles = id;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	@Override
	public String toString() {
		return "Perfiles [idPerfiles=" + idPerfiles + ", perfil=" + perfil + "]";
	}
	
	

}

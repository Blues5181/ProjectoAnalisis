package com.Analisis2.entity;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;



@Entity
public class Cliente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2745562832644004920L;

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator= "native")
	@GenericGenerator(name="native",strategy="native")
	private long id;
	
	
	@Column
	@NotNull
	private long nit;
	
	@Column
	@NotBlank
	@Size(min=3,message="debe de ingrear un nombre valido")
	private String nombre;
	
	@Column
	@NotBlank
	@Size(min=4,message="Ingrese una direccion valida")
	private String dirreccion;
	
	public Cliente() {}
	
	public Cliente(Long id) {
		
		this.id=id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getNit() {
		return nit;
	}

	public void setNit(long nit) {
		this.nit = nit;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDirreccion() {
		return dirreccion;
	}

	public void setDirreccion(String dirreccion) {
		this.dirreccion = dirreccion;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nit=" + nit + ", nombre=" + nombre + ", dirreccion=" + dirreccion + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dirreccion == null) ? 0 : dirreccion.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (nit ^ (nit >>> 32));
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (dirreccion == null) {
			if (other.dirreccion != null)
				return false;
		} else if (!dirreccion.equals(other.dirreccion))
			return false;
		if (id != other.id)
			return false;
		if (nit != other.nit)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	

}

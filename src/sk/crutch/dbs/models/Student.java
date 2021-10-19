package sk.crutch.dbs.models;

import java.util.Set;

public class Student {
 	private Integer id;


	private String name;
	

	private Double vsp;
	
	private Set<Lunch> lunches;
	
	
	public Set<Lunch> getLunches() {
		return lunches;
	}
	public void setLunches(Set<Lunch> lunches) {
		this.lunches = lunches;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getVsp() {
		return vsp;
	}
	public void setVsp(Double vsp) {
		this.vsp = vsp;
	}
	
	public Student(){
		
	}
	
	public Student(String name, Double vsp){
		setName(name);
		setVsp(vsp);
	}
}

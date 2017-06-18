package com.asa.simple_factory;

public class Apple implements Fruit{

	private Integer treeAge;
	
	@Override
	public void grow() {
		log("Apple is growing...");
	}

	@Override
	public void harvest() {
		log("Apple has been havested");
	}

	@Override
	public void plant() {
		log("Apple has been planted");
	}
	
	public static void log(String msg){
		System.out.println(msg);
	}

	public Integer getTreeAge() {
		return treeAge;
	}

	public void setTreeAge(Integer treeAge) {
		this.treeAge = treeAge;
	}

}

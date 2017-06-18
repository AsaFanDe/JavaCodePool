package com.asa.simple_factory;


public class Grape implements Fruit{

	/**
	 * 有无仔
	 */
	private boolean seedless;
	
	@Override
	public void grow() {
		log("Grape is gowing");
	}

	@Override
	public void harvest() {
		log("Grape has been harvested");
	}

	@Override
	public void plant() {
		log("Grape has been planted");
	}

	public static void log(String msg){
		System.out.println(msg);
	}

	public boolean isSeedless() {
		return seedless;
	}

	public void setSeedless(boolean seedless) {
		this.seedless = seedless;
	}
	
	
}

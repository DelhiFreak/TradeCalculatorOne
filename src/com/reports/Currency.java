package com.reports;

public enum Currency {
	
	SAR ("SAR",  0.40, 1),
	AED ("AED",  0.22, 1),
	SGP ("SGP",  0.50, 2),
	;

	private String name;
	private double agreedFx;
	private int typeHoliday;
	
	private Currency(String name, double agreedFx, int typeHoliday){
		this.name = name;
		this.agreedFx = agreedFx;
		this.typeHoliday = typeHoliday;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the agreedFx
	 */
	public double getAgreedFx() {
		return agreedFx;
	}
	
	public int getTypeHoliday() {
		return typeHoliday;
	}
	
}

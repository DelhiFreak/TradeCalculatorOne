package com.reports;

import java.util.Date;
import java.util.Calendar;

public class Entity {

	private String name;
	private String type;
	private Currency currency;
	private Date instructionDate;
	private Date settlementDate;
	private Date calSettlementDate;
	private int units;
	private double price;
	private double totalAmountUSD;
	
	
	
	public Entity(String name, String type, Currency currency, Date instructionDate, Date settlementDate, int units, double price) {
		super();
		this.name = name;
		this.type = type;
		this.currency = currency;
		this.instructionDate = instructionDate;
		this.settlementDate = settlementDate;
		this.units = units;
		this.price = price;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the currency
	 */
	public Currency getCurrency() {
		return currency;
	}
	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	/**
	 * @return the instructionDate
	 */
	public Date getInstructionDate() {
		return instructionDate;
	}
	/**
	 * @param instructionDate the instructionDate to set
	 */
	public void setInstructionDate(Date instructionDate) {
		this.instructionDate = instructionDate;
	}
	/**
	 * @return the settlementDate
	 */
	public Date getSettlementDate() {
		return settlementDate;
	}
	/**
	 * @param settlementDate the settlementDate to set
	 */
	public void setSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
	}
	/**
	 * @return the units
	 */
	public int getUnits() {
		return units;
	}
	/**
	 * @param units the units to set
	 */
	public void setUnits(int units) {
		this.units = units;
	}
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	/**
	 * @return the totalAmountUSD
	 */
	public Double getTotalAmountUSD() {
		return new Double(totalAmountUSD);
	}
	/**
	 * @param totalAmountUSD the totalAmountUSD to set
	 */
	public void calculateSettlment() {
		this.totalAmountUSD = currency.getAgreedFx()*units*price;
		Calendar cal = Calendar.getInstance();
		cal.setTime(settlementDate);
		if(currency.getTypeHoliday() == 1){
			
			if(cal.get(Calendar.DAY_OF_WEEK) > Calendar.THURSDAY){
				cal.add(Calendar.WEEK_OF_MONTH,1);
				cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
				calSettlementDate = cal.getTime();
			}else{
				calSettlementDate = settlementDate;
			}
			
		}else{
			
			if(cal.get(Calendar.DAY_OF_WEEK) > Calendar.FRIDAY){
				cal.add(Calendar.WEEK_OF_MONTH,1);
				cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
				calSettlementDate = cal.getTime();
			}else{
				calSettlementDate = settlementDate;
			}
		}
	}
	
	
	
	/**
	 * @return the calSettlementDate
	 */
	public Date getCalSettlementDate() {
		return calSettlementDate;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "name=" + name + "| type=" + type + "| currency=" + currency.getName() +"| AgreedFx=" + currency.getAgreedFx()+ "| instructionDate=" + instructionDate + "| settlementDate="
				+ settlementDate + "| units=" + units + "| price=" + price + "| totalAmountUSD=" + totalAmountUSD;
	}
	
	
	
	
	
	
}

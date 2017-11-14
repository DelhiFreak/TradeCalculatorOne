package com.reports;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Runner {
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
	
	public static void main(String[] args) throws ParseException {
		Runner runner = new Runner();
		List< Entity> inputs = runner.generateInputs();
		runner.generateReportForIncoming(inputs.stream().filter(entity -> "B".equals(entity.getType())).collect(Collectors.toList()));
		runner.generateReportForOutgoing(inputs.stream().filter(entity -> "S".equals(entity.getType())).collect(Collectors.toList()));
		
	}

	private void generateReportForOutgoing(List<Entity> collect) {
		
		setUSDAndSorting(collect, "Outgoing");
	}

	private void setUSDAndSorting(List<Entity> collect, String type) {
		
		System.out.println("------------------------"+type+" report------------------------");
		
		collect.forEach(entity -> entity.calculateSettlment());
		
		Map<Date, Double> sum = collect.stream().collect(Collectors.groupingBy(Entity::getCalSettlementDate, Collectors.summingDouble(Entity::getTotalAmountUSD)));

		System.out.println("-------Day wise-------");
		for(Entry<Date, Double> d : sum.entrySet()){
			System.out.println("Date : "+dateFormat.format(d.getKey())+ ", Total(USD) : "+d.getValue());
		}
		
		Collections.sort(collect, (o1, o2) -> {return o2.getTotalAmountUSD().compareTo(o1.getTotalAmountUSD());});
		
		System.out.println("-------Ranking-------");
		for(int i = 0, size= collect.size(); i < size; i++){
			System.out.println((i+1)+ " | "+collect.get(i).getName());
		}
	}

	private void generateReportForIncoming(List<Entity> inputs) {
		setUSDAndSorting(inputs, "Incoming");
	}

	private List<Entity> generateInputs() throws ParseException {
		List<Entity> inputs = new ArrayList<>();
		inputs.add(new Entity("foo", "B", Currency.SGP, dateFormat.parse("01 Jan 2016"), dateFormat.parse("02 Jan 2016"), 200, 100.25));
		inputs.add(new Entity("fo1", "S", Currency.SGP, dateFormat.parse("18 Jan 2016"), dateFormat.parse("19 Jan 2016"), 105, 100.25));
		inputs.add(new Entity("bar", "B", Currency.AED, dateFormat.parse("05 Jan 2016"), dateFormat.parse("06 Jan 2016"), 500, 150.5));
		inputs.add(new Entity("ba1", "S", Currency.AED, dateFormat.parse("05 Jan 2016"), dateFormat.parse("08 Jan 2016"), 450, 150.5));
		inputs.add(new Entity("xyz", "B", Currency.SAR, dateFormat.parse("01 Jan 2016"), dateFormat.parse("02 Jan 2016"), 200, 100.25));
		return inputs;
	}

}

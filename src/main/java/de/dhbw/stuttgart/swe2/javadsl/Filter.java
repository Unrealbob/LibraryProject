package de.dhbw.stuttgart.swe2.javadsl;

public interface Filter<Output> {
	
//	ManyService<Input, Output> filter(Filter<Output> filter);
	boolean get(Output output);
	

}

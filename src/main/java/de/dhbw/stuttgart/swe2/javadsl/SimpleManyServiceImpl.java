package de.dhbw.stuttgart.swe2.javadsl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.annotations.Filters;

public class SimpleManyServiceImpl<Input, Output> implements ManyService<Input, Output> {

	public static <Input, Output> ManyService<Input, Output> from(ToMany<Input, Output> toMany) {
		return new SimpleManyServiceImpl<Input, Output>(toMany);
	}

	private ToMany<Input, Output> toMany;
	private List<Filter<Output>> filters = new ArrayList<Filter<Output>>();

	SimpleManyServiceImpl(ToMany<Input, Output> toMany) {
		this.toMany = toMany;
	}

	
	public List<Output> getUnfiltered(Input input) {
		return toMany.get(input);
	}

	@Override
	public <NextOutput> ManyService<Input, NextOutput> join(ManyService<Output, NextOutput> toMany) {
		return new BridgingManyServiceImpl<Input, Output, NextOutput>(this, toMany);
	}

	@Override
	public <NextOutput> ManyService<Input, NextOutput> join(ToMany<Output, NextOutput> toMany) {
		return new BridgingManyServiceImpl<Input, Output, NextOutput>(this, toMany);
	}

	@Override
	public ManyService<Input, Output> filter(Filter<Output> filter) {
		// TODO Auto-generated method stub
		filters.add(filter);
		return this;
	}
	
	@Override
	public List<Output> get(Input input) {
		List<Output> unfiltered = getUnfiltered(input);
		List<Output> filtered = new ArrayList<Output>();
		for(Iterator<Output> iterator = unfiltered.iterator(); iterator.hasNext();) {
			Output output = iterator.next();
			Boolean match = true;
			for(Iterator<Filter<Output>> filterIterator = filters.iterator(); filterIterator.hasNext() && match;) {
				match = filterIterator.next().get(output);
			}
			if (match)
				filtered.add(output);
		}
		return filtered;
	}

}

package com.ninepublishing.fairfax.helpers;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class TagCollector implements Collector<Set<String>,Set<String>,Set<String>> {
    @Override
    public Supplier<Set<String>> supplier() {
        return HashSet::new;
    }

    @Override
    public BiConsumer<Set<String>, Set<String>> accumulator() {
        return (resultSet, suppliedSet)-> resultSet.addAll(suppliedSet);

    }

    @Override
    public BinaryOperator<Set<String>> combiner() {
        return (resultSet, suppliedSet)->{
            resultSet.addAll(suppliedSet);
            return resultSet;
        };
    }

    @Override
    public Function<Set<String>, Set<String>> finisher() {
        return resultString -> resultString;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return EnumSet.of(Characteristics.CONCURRENT);
    }
}

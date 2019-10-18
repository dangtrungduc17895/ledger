package com.pet.ledger.utils;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ModelMapperUtils {

    private static ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    private ModelMapperUtils() {}

    public static <T> T transferObject(Object object, Class<T> clazz){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(object, clazz);
    }

    public static <D, T> List<D> transferListObject(final Collection<T> entityList, Class<D> clazz){
        return entityList.stream().map(entity -> transferObject(entity, clazz)).collect(Collectors.toList());
    }

    private static boolean as(int x) {
        switch (x) {
            case 'a': case 'e': case 'i': case 'o': case 'u': return true;
        }
        return false;
    }

    public static void main(String[] args) {
    }

}

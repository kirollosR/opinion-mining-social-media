package com.example.opinionminingsocialmedia.controllers;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;

import java.util.List;
import java.util.Set;

public class Filter {
//    public MappingJacksonValue filter(List list, Set<String> array, String filterName) {
//        SimpleBeanPropertyFilter simpleBeanPropertyFilter =
//                SimpleBeanPropertyFilter.serializeAllExcept(array);
//
//        FilterProvider filterProvider = new SimpleFilterProvider()
//                .addFilter(filterName, simpleBeanPropertyFilter);
//
//        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
//        mappingJacksonValue.setFilters(filterProvider);
//        return mappingJacksonValue;
//    }

    public MappingJacksonValue filter(List<?> list, Set<String> excludeProperties, String filterName) {
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAllExcept(excludeProperties);

        FilterProvider filters = new SimpleFilterProvider()
                .addFilter(filterName, filter);

        MappingJacksonValue mapping = new MappingJacksonValue(list);
        mapping.setFilters(filters);

        return mapping;
    }

}

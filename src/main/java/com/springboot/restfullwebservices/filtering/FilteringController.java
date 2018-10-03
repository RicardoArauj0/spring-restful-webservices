package com.springboot.restfullwebservices.filtering;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public SomeBean getSomeBean() {
        return new SomeBean("Field1", "Field2", "Field3");
    }

    @GetMapping("/filtering-list")
    public List<SomeBean> getSomeBeansList() {
        return Arrays.asList(new SomeBean("Field1", "Field2", "Field3"), new SomeBean("Field11", "Field22", "Field33"));
    }
}

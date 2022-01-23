package com.nishant.mongodb.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    private String _id;
    private Name name;
    private int rollNumber;
    private Address address;
    private List<String> hobbies;
}

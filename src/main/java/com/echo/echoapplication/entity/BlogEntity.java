package com.echo.echoapplication.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "blogs")
@Data
@NoArgsConstructor
public class BlogEntity {

    @Id
    private String id;

    @NonNull
    private String title;
    private String description;

}

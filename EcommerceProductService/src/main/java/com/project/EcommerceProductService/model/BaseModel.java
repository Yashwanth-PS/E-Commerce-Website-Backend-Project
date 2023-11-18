package com.project.EcommerceProductService.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;
@Getter
@Setter
@MappedSuperclass
public abstract class BaseModel {
    @Id // Tells JPA that the attribute is the Primary Key of the table
    @GeneratedValue(generator = "uuidGenerator") // Tells JPA how the values should be Generated
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid2") // Creating a Random Generator
    @Column(name = "id", columnDefinition = "binary(16)", nullable = false, updatable = false) // We cannot update the ID and ID cannot be null
    private UUID uuid;
}

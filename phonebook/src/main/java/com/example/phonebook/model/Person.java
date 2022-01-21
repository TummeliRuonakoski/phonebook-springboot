package com.example.phonebook.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.AbstractPersistable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person extends AbstractPersistable<Long>{
    
    @NotEmpty
    @Size(min = 4, max = 64)
    private String name;
    private String phonenumber;
    @CreationTimestamp
    private LocalDateTime created;
    @UpdateTimestamp
    private LocalDateTime lastUpdate;
}

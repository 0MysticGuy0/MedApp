package com.example.taskServer01.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@Table(name="users")
public class User {
    private String fio;
    //private Date birthDate;
    @NonNull // поле-обязательное
    @Id
    private String email;
    private String password;
}

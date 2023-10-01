package com.tokmakov.fuzzing_pantry.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "stacktrace")
@RequiredArgsConstructor
@NoArgsConstructor
public class Stacktrace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NonNull
    @Column(name = "trace")
    private String trace;

    @NonNull
    @Column(name = "label")
    private String label;
}
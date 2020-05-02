package com.mamatha.kafkademo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@EqualsAndHashCode
@Entity
@Table
public class BrandManufacturer {

    @Id
    @GeneratedValue
    private int Id;

    private String manufacturer_name;

}

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
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table
public class Brand {
    @Id
    @GeneratedValue
    private int id;

    private String manufacturer_id;
    private String brand_code;
    private String brand_name;


}

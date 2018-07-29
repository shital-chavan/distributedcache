package com.example.distributedcache.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "cache")
@NoArgsConstructor
public class Cache {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "key")
  private String key;

  @Column(name = "value")
  private String value;

  public Cache(String key, String value){
      this.key = key;
      this.value = value;
  }

}

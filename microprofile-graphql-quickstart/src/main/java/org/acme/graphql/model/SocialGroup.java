package org.acme.graphql.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SocialGroup {

  @Id
  @GeneratedValue
  @EqualsAndHashCode.Include
  private Long id;
  private String name;


  @ManyToOne(fetch = FetchType.LAZY)
  private User user;



}
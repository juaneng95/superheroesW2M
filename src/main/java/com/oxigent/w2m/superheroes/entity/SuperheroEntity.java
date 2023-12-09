package com.oxigent.w2m.superheroes.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serial;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SUPERHERO", schema = "SUPERHEROES_SCHEMA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class SuperheroEntity extends AuditAbstractEntity {

  @Serial private static final long serialVersionUID = -3429376358735326048L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name", nullable = false, length = 50)
  private String name;

  @Column(name = "vigor", nullable = false)
  private Integer vigor;

  @Column(name = "mind", nullable = false)
  private Integer mind;

  @Column(name = "endurance", nullable = false)
  private Integer endurance;

  @Column(name = "strength", nullable = false)
  private Integer strength;

  @Column(name = "dexterity", nullable = false)
  private Integer dexterity;

  @Column(name = "intelligence", nullable = false)
  private Integer intelligence;

  @Column(name = "speed", nullable = false)
  private Integer speed;

  @Column(name = "is_active", nullable = false)
  private Boolean isActive;
}

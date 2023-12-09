package com.oxigent.w2m.superheroes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditAbstractEntity implements Serializable {

  @Serial private static final long serialVersionUID = -6529877347003746845L;

  @Column(name = "created_at", nullable = false, updatable = false)
  @JsonIgnore
  @CreatedDate
  private Date createdAt;

  @Column(name = "modified_at", nullable = false)
  @JsonIgnore
  @LastModifiedDate
  private Date modifiedAt;
}

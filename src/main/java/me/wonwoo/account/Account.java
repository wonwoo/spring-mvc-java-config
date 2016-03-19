package me.wonwoo.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by wonwoo on 2016. 3. 19..
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

  @Id
  @GeneratedValue
  private Long Id;

  private String name;

}

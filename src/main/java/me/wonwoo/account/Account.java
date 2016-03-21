package me.wonwoo.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

  @OneToOne(fetch = FetchType.EAGER,cascade={CascadeType.ALL})
  private Product order;

}

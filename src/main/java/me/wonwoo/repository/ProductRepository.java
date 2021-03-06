package me.wonwoo.repository;

import me.wonwoo.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wonwoo on 2016. 3. 20..
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

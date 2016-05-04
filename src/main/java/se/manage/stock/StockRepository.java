package se.manage.stock;

import org.springframework.data.repository.CrudRepository;
import se.manage.user.User;

import java.util.List;

public interface StockRepository extends CrudRepository<Stock, Long> {
    List<Stock> findByCode(String code);
}

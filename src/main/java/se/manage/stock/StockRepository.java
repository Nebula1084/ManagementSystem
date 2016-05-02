package se.manage.stock;

import org.springframework.data.repository.CrudRepository;
import se.manage.stock.Stock;

public interface StockRepository extends CrudRepository<Stock, Long> {
}

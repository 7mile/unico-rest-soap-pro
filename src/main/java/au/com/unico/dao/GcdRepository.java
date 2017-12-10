package au.com.unico.dao;

import au.com.unico.domain.Gcd;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by xiaofei on 2017/12/10.
 */
@Repository
public interface GcdRepository extends CrudRepository<Gcd, Long> {
}

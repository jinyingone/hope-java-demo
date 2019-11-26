package fun.jinying.mongo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @description:
 * @author: sjy
 * @create: 2019-11-25 18:06
 **/
public interface UserRepository extends MongoRepository<User, String> {
}

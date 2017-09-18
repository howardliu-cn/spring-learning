package cn.howardliu.springLearning.simple.dao;

import cn.howardliu.springLearning.simple.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <br>created at 17-9-14
 *
 * @author liuxh
 * @since 1.0.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

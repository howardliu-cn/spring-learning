package cn.howardliu.sl.sb2.swagger.dao;

import cn.howardliu.sl.sb2.swagger.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <br>created at 18-6-21
 *
 * @author liuxh
 * @since 0.1.0
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}

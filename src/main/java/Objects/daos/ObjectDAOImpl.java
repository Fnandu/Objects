package Objects.daos;

import Objects.model.Bucket;
import Objects.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ObjectDAOImpl implements ObjectDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void NewBucket(Bucket bucket) {
        jdbcTemplate.update("insert into buckets values(?,?,?)", bucket.getUri(), bucket.getUser(), bucket.getDate());
    }

    @Override
    public List<Bucket> ListOfBuckets(String username) {
        List<Bucket> buckets = jdbcTemplate.query("SELECT * FROM buckets WHERE username=?",
                new BeanPropertyRowMapper<Bucket>(Bucket.class),
                username);
        if (buckets.get(0) == null) return null;

        return buckets;
    }
}

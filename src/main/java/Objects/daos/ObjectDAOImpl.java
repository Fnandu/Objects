package Objects.daos;

import Objects.model.Bucket;
import Objects.model.Objects;
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

        return buckets;
    }

    @Override
    public void NewObject(Objects objects) {
        jdbcTemplate.update("insert into object values(?,?,?,?,?,?,?,?)", objects.getFileId(),
                objects.getFileName(),objects.getFileType(), objects.getFileData(),
                objects.getFileSize(), objects.getFileDate(), objects.getFileUri(),
                objects.getFileUsername());
    }
}

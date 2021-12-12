package Objects.daos;

import Objects.model.Bucket;
import Objects.model.Objects_Versions;
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
    public void NewObject(Objects_Versions objectsVersions) {
        jdbcTemplate.update("insert into object values(?,?,?,?,?,?,?,?)", objectsVersions.getFileId(),
                objectsVersions.getFileName(), objectsVersions.getFileType(), objectsVersions.getFileData(),
                objectsVersions.getFileSize(), objectsVersions.getFileDate(), objectsVersions.getFileUri(),
                objectsVersions.getFileUsername());
    }

    @Override
    public List<Objects_Versions> ListOfObjects(String bucket, String username) {
        List<Objects_Versions> objects = jdbcTemplate.query("SELECT * FROM object WHERE fileuri=? and fileusername=?",
                new BeanPropertyRowMapper<Objects_Versions>(Objects_Versions.class),
                bucket,username);

        return objects;
    }


}

package Objects.daos;

import Objects.model.Bucket;
import Objects.model.Objects;
import Objects.model.Version;
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
    public void NewObject(Objects objectsVersions) {
        jdbcTemplate.update("insert into object values(?,?,?,?,?,?,?,?)", objectsVersions.getFileId(),
                objectsVersions.getFileName(), objectsVersions.getFileType(), objectsVersions.getFileData(),
                objectsVersions.getFileSize(), objectsVersions.getFileDate(), objectsVersions.getFileUri(),
                objectsVersions.getFileUsername());
    }

    @Override
    public List<Objects> ListOfObjects(String bucket, String username) {
        List<Objects> objects = jdbcTemplate.query("SELECT * FROM object WHERE fileuri=? and fileusername=?",
                new BeanPropertyRowMapper<Objects>(Objects.class),
                bucket,username);

        return objects;
    }

    @Override
    public Objects GetIdFromObject(String bucket, String username, String filename) {
        List<Objects> objects = jdbcTemplate.query("select * from object where filename=? and fileuri=? and fileusername=?",
                new BeanPropertyRowMapper<Objects>(Objects.class),
                filename,bucket,username);

        if (objects.size() == 0) return null;

        return objects.get(0);
    }

    @Override
    public void NewVersion(Version version) {
        jdbcTemplate.update("insert into versions values(?,?,?,?,?,?)", version.getVersionId(),version.getVersionName(), version.getFileData(),
                version.getVersionSize(), version.getVersionDate(), version.getFileid());
    }

    @Override
    public List<Version> ListOfVersions(int fileid) {
        return null;
    }


}
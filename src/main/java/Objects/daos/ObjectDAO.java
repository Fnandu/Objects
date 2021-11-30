package Objects.daos;

import Objects.model.Bucket;

import java.util.List;

public interface ObjectDAO {
    public void NewBucket(Bucket bucket);
    public List<Bucket> ListOfBuckets(String username);
}

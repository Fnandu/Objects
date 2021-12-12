package Objects.daos;

import Objects.model.Bucket;
import Objects.model.Objects_Versions;

import java.util.List;

public interface ObjectDAO {
    public void NewBucket(Bucket bucket);
    public List<Bucket> ListOfBuckets(String username);
    public void NewObject(Objects_Versions objectsVersions);
    public List<Objects_Versions> ListOfObjects(String bucket, String username);
}

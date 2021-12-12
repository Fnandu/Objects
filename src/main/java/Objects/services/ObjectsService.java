package Objects.services;

import Objects.model.Bucket;
import Objects.model.Objects_Versions;

import java.util.List;

public interface ObjectsService {
    public void CreateBucket(Bucket bucket_name);
    public List<Bucket> BucketList(String username);
    public void CreateObject(Objects_Versions objectsVersions);
    public List<Objects_Versions> ObjectList(String bucket, String username);
}

package Objects.services;

import Objects.model.Bucket;
import Objects.model.Objects;

import java.util.List;

public interface ObjectsService {
    public void CreateBucket(Bucket bucket_name);
    public List<Bucket> BucketList(String username);
    public void CreateObject(Objects objects);
    public List<Objects> ObjectList(String bucket, String username);
}

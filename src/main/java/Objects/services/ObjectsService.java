package Objects.services;

import Objects.model.Bucket;

import java.util.List;

public interface ObjectsService {
    public void CreateBucket(Bucket bucket_name);
    public List<Bucket> BucketList(String username);
}

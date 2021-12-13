package Objects.services;

import Objects.model.Bucket;
import Objects.model.Objects;
import Objects.model.Version;

import java.util.List;

public interface ObjectsService {
    public void CreateBucket(Bucket bucket_name);
    public List<Bucket> BucketList(String username);
    public void CreateObject(Objects objects);
    public List<Objects> ObjectList(String bucket, String username);
    public Objects GetId(String bucket, String username, String filename);
    public void CreateVersion(Version version);
    public List<Version> VersionList(int fileid);
}
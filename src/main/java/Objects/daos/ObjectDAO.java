package Objects.daos;

import Objects.model.Bucket;
import Objects.model.Objects;
import Objects.model.Version;

import java.util.List;

public interface ObjectDAO {
    public void NewBucket(Bucket bucket);
    public List<Bucket> ListOfBuckets(String username);
    public void NewObject(Objects objects);
    public List<Objects> ListOfObjects(String bucket, String username);
    public Objects GetIdFromObject(String bucket, String username, String filename);
    public void NewVersion(Version version);
    public List<Version> ListOfVersions(int fileid);
    public Version DownloadbyId(int versionId);
    public void DeleteBucket(String bucket, String username);
    public void DeleteObject(int fileid);
}
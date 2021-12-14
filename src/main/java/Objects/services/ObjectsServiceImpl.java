package Objects.services;

import Objects.daos.ObjectDAO;
import Objects.model.Bucket;
import Objects.model.Objects;
import Objects.model.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjectsServiceImpl implements ObjectsService{
    @Autowired
    ObjectDAO objectDAO;

    @Override
    public void CreateBucket(Bucket bucket) {
        objectDAO.NewBucket(bucket);
    }

    @Override
    public List<Bucket> BucketList(String user) {
        return objectDAO.ListOfBuckets(user);
    }

    @Override
    public void CreateObject(Objects objects) {
        objectDAO.NewObject(objects);
    }

    @Override
    public List<Objects> ObjectList(String bucket, String username) {
        return objectDAO.ListOfObjects(bucket,username);
    }

    @Override
    public Objects GetId(String bucket, String username, String filename) {
        return objectDAO.GetIdFromObject(bucket,username,filename);
    }

    @Override
    public void CreateVersion(Version version) {
        objectDAO.NewVersion(version);
    }

    @Override
    public List<Version> VersionList(int fileid) {
        return objectDAO.ListOfVersions(fileid);
    }

    @Override
    public Version downloadFile(int versionId) {
        List<Version> downloadVersion = objectDAO.ListOfVersions(versionId);
        return downloadVersion.get(0);
    }

    @Override
    public void DeleteBucket(String uri, String username) {
        objectDAO.DeleteBucket(uri,username);
    }

    @Override
    public void DeleteObject(int fileid) {
        objectDAO.DeleteObject(fileid);
    }
}
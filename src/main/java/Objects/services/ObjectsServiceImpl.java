package Objects.services;

import Objects.daos.ObjectDAO;
import Objects.model.Bucket;
import Objects.model.Objects_Versions;
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
    public void CreateObject(Objects_Versions objectsVersions) {
        objectDAO.NewObject(objectsVersions);
    }

    @Override
    public List<Objects_Versions> ObjectList(String bucket, String username) {
        return objectDAO.ListOfObjects(bucket,username);
    }


}

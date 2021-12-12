package Objects.services;

import Objects.daos.ObjectDAO;
import Objects.model.Bucket;
import Objects.model.Objects;
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


}

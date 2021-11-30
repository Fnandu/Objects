package Objects.services;

import Objects.daos.ObjectDAO;
import Objects.model.Bucket;
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
    public List<Bucket> BucketList(String username) {
        return objectDAO.ListOfBuckets(username);
    }
}

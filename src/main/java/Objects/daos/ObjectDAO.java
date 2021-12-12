package Objects.daos;

import Objects.model.Bucket;
import Objects.model.Objects;

import java.util.List;

public interface ObjectDAO {
    public void NewBucket(Bucket bucket);
    public List<Bucket> ListOfBuckets(String username);
    public void NewObject(Objects objects);
    public List<Objects> ListOfObjects(String bucket,String username);
}

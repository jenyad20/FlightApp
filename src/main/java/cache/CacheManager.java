package cache;

/**
 * Created by jenya on 11/30/18.
 */
public class CacheManager {

    private static CacheManager instance = null;

    public static CacheManager getInstance() {
        synchronized(CacheManager.class){
            if (instance == null) {
                instance = new CacheManager();
            }
        }
        return instance;
    }
}

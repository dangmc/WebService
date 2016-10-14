package VCC_ASS3.webService;

/**
 * Hello world!
 *
 */
import spark.Spark;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class App 
{
    public static void main( String[] args ) {
    	LoadingCache<Integer, BigInteger> cache =  CacheBuilder.newBuilder()
		  .maximumSize(10)
		  .expireAfterAccess(10, TimeUnit.SECONDS) 
		  .build(new CacheLoader<Integer, BigInteger>(){
			   @Override
			   public BigInteger load(Integer N) throws Exception {
			       BigInteger res = new BigInteger("1");
			       System.out.println("OK");
			       for (int i = 1; i <= N; i++)
			       	  res = res.multiply(new BigInteger(Integer.toString(i)));
			          return res;
			       } 
			   });
		  
    	Spark.setPort(8899);
    	Spark.get("/TinhGiaThua", (request, response)->{
    		  
    		  String id = request.queryParams("n");
    		  int n = Integer.parseInt(id);
    		  return cache.get(n);
    	});
    }
    
}

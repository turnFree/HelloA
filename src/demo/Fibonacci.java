/**
 * 
 */
package demo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
* @ClassName: Fibonacci
* @Description: TODO(这里用一句话描述这个类的作用)
* @author zhixuan.xue
* @date 2017年5月24日 上午11:48:16
*
*/
public class Fibonacci {

	private Map<Integer, Integer> cache = new ConcurrentHashMap<Integer, Integer>();  
    
    public int fib(int n) {  
        if (n == 0 || n == 1) return n;  
  
        return (Integer)cache.computeIfAbsent(n, (key) -> {  
            System.out.println("calculating fib(" + n + ")");  
            return fib(n - 2) + fib(n - 1);  
        });
    }  
    
    public static void main(String[] args) {
    	Fibonacci ff = new Fibonacci();
    	ff.fib(10);
	}

}

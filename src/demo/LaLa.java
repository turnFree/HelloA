/**
 * 
 */
package demo;

import java.math.BigDecimal;

/**
* @ClassName: LaLa
* @Description: TODO(这里用一句话描述这个类的作用)
* @author zhixuan.xue
* @date 2017年5月18日 下午2:57:03
*
*/
public class LaLa {

	public BigDecimal func(int n){
		
		if(n == 0){
			return new BigDecimal(1);
		}
		if(n > 0){
			return new BigDecimal(2*func(n - 1).doubleValue());
		}
		return new BigDecimal(1);
	}
	
	public static void main(String[] args) {
		LaLa lala = new LaLa();
		BigDecimal result = lala.func(10);
		System.out.println(result.doubleValue());
	}
}

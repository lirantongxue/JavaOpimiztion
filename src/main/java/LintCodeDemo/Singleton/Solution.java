package LintCodeDemo.Singleton;

/**
 * 双锁单利模式
 *
 * @author v-ranli
 * @create 2016-11-09-11:15
 */

public class Solution {

    private volatile static Solution solution ;

    private Solution (){};


    /**
     * @return: The same instance of this class every time
     */
    public synchronized static Solution getInstance() {
        if (solution == null) {
            synchronized (Solution.class) {
                if (solution == null) {
                    solution = new Solution();
                }
            }
        }

        return solution;
    }
}

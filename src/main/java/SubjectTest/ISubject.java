package SubjectTest;

/**
 * 被观者:
 *
 * @auther liran
 * @create 2018-09-03-下午6:25
 */
public interface ISubject {

    //添加观察
    public void attach(IObserver observer);

    public void inform(int state);


}

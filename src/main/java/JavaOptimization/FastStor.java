package JavaOptimization;

/**
 * 快排:
 *
 * @auther liran
 * @create 2020-11-03-下午7:12
 */
public class FastStor {

    /**
     * @description:核心思想分治发， 选定一个基准值。保证小于基准值的放左边，大于基准值的放右边
     * @author: liran
     * @date: 下午7:25
     * @Param: [arr, startIndex, endIndex]
     * @return: void
     */
    public static void quickSort(int[] arr, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }
        int index= comparevalue(arr, startIndex, endIndex);
        quickSort(arr,startIndex,index-1);
        quickSort(arr,index+1,endIndex);

    }

    private static int comparevalue(int[] arr, int startIndex, int endIndex) {
        int pivot = arr[0];//获取基准参照值,可以默认第一个或数据中随机位置一个
        int left = startIndex;
        int right = endIndex;
        while (left != right) {
            //右指针移动 当右指针的值 小于 基准值 右指针移动
            if (left < right && arr[right] > pivot) {
                right--;
            }
            //左指针移动 当左指针的值 大于 基准值 左指针移动 因为 pivot = arr[0] 基准值等于 left 初始值故而 <=pivot
            if (left < right && arr[left] <= pivot) {
                left++;
            }

            if (left < right) {
                int p = arr[right];
                arr[right] = arr[left];
                arr[left] = p;
            }


        }

        if( arr[left]==arr[right]){
            arr[left]=pivot;
        }
        return left;
    }
}

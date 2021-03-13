package C2;

public class E26 {
    public static int[] arr = {3, 4, 3, 4, 3, 4, 3, 4, 4};

    public static MajorRes majorityNumber(MajorList nums) {
        MajorRes candidate;
        if (nums.count == 1 || (nums.count == 2 && nums.a[0] == nums.a[1])) {
            candidate = new MajorRes();
            candidate.value = nums.a[0];
            return candidate;
        }
        if (nums.count == 0 || nums.count == 2 && nums.a[0] != nums.a[1]) {
            candidate = new MajorRes();
            candidate.has = false;
            return candidate;
        }

        MajorList temp = new MajorList(arr);
        for(int i = 0; i < nums.count - 1; i += 2) {
            if (nums.a[i] == nums.a[i + 1]) {
                temp.a[temp.count] = nums.a[i];
                temp.count++;
            }
        }

        candidate = majorityNumber(temp);

        if (nums.count % 2 == 1 && !candidate.has) {
            candidate = new MajorRes();
            candidate.value = nums.a[nums.count - 1];
        }
        
        return candidate;
    }
    

    public static void main(String[] args) throws Exception {
        MajorList arrList = new MajorList(arr);
        arrList.a = arr;
        arrList.count = arr.length;
        MajorRes res = majorityNumber(arrList);
        boolean flag = false;
        if (res.has) {
            int count = 0;
            for(int i: arr) {
                if (i == res.value) {
                    count++;
                }
            }
            
            if(count > arr.length/2) {
                flag = true;
            }
        }

        if (flag) {
            System.out.println(res.value);
        } else {
            System.out.println("No value");
        }
    }
}

class MajorList {
    int[] a;
    int count = 0;

    MajorList(int[] arr) {
        this.a = new int[arr.length];
    }
}

class MajorRes {
    int value;
    boolean has = true;
}

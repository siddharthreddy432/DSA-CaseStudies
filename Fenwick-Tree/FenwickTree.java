public class FenwickTree {

    int[] bit;
    int n;

    FenwickTree(int n) {
        this.n = n;
        bit = new int[n + 1];
    }

    void update(int i, int delta) {

        while (i <= n) {

            bit[i] += delta;
            i += i & (-i);
        }
    }

    int prefixSum(int i) {

        int sum = 0;

        while (i > 0) {

            sum += bit[i];
            i -= i & (-i);
        }

        return sum;
    }

    int rangeSum(int l, int r) {

        return prefixSum(r) - prefixSum(l - 1);
    }

    public static void main(String[] args) {

        FenwickTree ft = new FenwickTree(15);

        int[] spend = {
            1200,800,0,2400,1500,
            600,0,0,3500,0,
            1100,950,700,0,0
        };

        for (int i = 0; i < spend.length; i++) {

            ft.update(i + 1, spend[i]);
        }

        System.out.println(
            "Total Spend Jan 5 to Jan 12 = "
            + ft.rangeSum(5,12)
        );
    }
}
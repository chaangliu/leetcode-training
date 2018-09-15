package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by DrunkPiano on 09/07/2017.
 * Input: [2,5], [[3,0,5],[1,2,10]], [3,2]
 */

public class ShoppingOffers {
	int min;
	public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
		int kinds = price.size();
		if (kinds == 0) return 0;
		for (int i = 0; i < kinds; i++) {
			min += needs.get(i) * price.get(i);
		}
		dfs(0, price, special, new int[special.size()], needs);
		return min;
	}

	private boolean dfs(int start, List<Integer> price, List<List<Integer>> special, int[] specialAmount, List<Integer> needs) {
		for (int i = 0; i < needs.size(); i++) {
			if (needs.get(i) < 0) {
				//对比一次
				return true;
			}
		}
		for (int i = start; i < special.size(); i++) {
			List<Integer> pack = special.get(i);
			specialAmount[i]++;
			for (int j = 0; j < needs.size(); j++) {
				needs.set(j, needs.get(j) - pack.get(j));
			}
			if (dfs(start, price, special, specialAmount, needs)) {
				specialAmount[i]--;
				int total = 0;
				for (int k = 0; k < needs.size(); k++) {
					needs.set(k, needs.get(k) + pack.get(k));
					//pack以外需要的钱
					total += needs.get(k) * price.get(k);
				}
				//pack需要的钱
				for (int m = 0; m < special.size(); m++) {
					total += specialAmount[m] * special.get(m).get(pack.size() - 1);
				}
				min = Math.min(total, min);
			} else {
				specialAmount[i]--;
				for (int k = 0; k < needs.size(); k++) {
					needs.set(k, needs.get(k) + pack.get(k));
				}
			}
		}
		return false;
	}

	public static void main(String args[]) {
		Integer priceArr[] = {2, 5};
		Integer sp1[] = {3, 0, 5};
		Integer sp2[] = {1, 2, 10};
		Integer needsArr[] = {3, 2};

		List<Integer> price = Arrays.asList(priceArr);

		List<List<Integer>> special = new ArrayList<>();
		List<Integer> special1 = Arrays.asList(sp1);
		List<Integer> special2 = Arrays.asList(sp2);
		special.add(special1);
		special.add(special2);


		List<Integer> needs = Arrays.asList(needsArr);
		new ShoppingOffers().shoppingOffers(price, special, needs);
	}
}

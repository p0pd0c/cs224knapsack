import java.util.ArrayList;

public class Main {

	public static int[] weights = {5, 3, 7, 3, 4, 12, 9, 4, 5, 2, 6, 7, 1};
	public static int[] values = {2, 1, 1, 8, 1, 5, 4, 5, 4, 3, 4, 2, 6};
	public static int limit = 15;
	
	public static void main(String[] args) {
		optimize(weights, values, limit);
	}
	
	public static void optimize(int[] weights, int[] values, int limit) {
		// int to track highest value seen
		int maxValue = 0;
		int weightOfMaxValue = 0;
		
		// Create array to store a subset
		boolean[] choose = new boolean[weights.length];
		
		// Create array to store the best subset
		ArrayList<Integer> bestChoice = new ArrayList<>(); 
		
		// Array to hold current choice
		// List of indexes
		ArrayList<Integer> currentChoice = new ArrayList<>(); 
		
		// 0 to 2^N-1
		for(int index = 0; index < Math.pow(2, weights.length) - 1; index++) {
			
			// Falsify choose array
			for(int j = 0; j < weights.length - 1; j++) {
				choose[j] = false;
			}
			
			// bit hack
			for(int j = 0; j < weights.length - 1; j++) {
				int val = (int) Math.pow(2, j);
				if((index & val) > 0) {
					choose[j] = true;
				}
			}
			
			// build subset
			for(int j = 0; j < weights.length - 1; j++) {
				if(choose[j]) {
					currentChoice.add(j);
				}
			}
			
			// compute total weight and total value
			int totalWeight = 0;
			int totalValue = 0;
			for(int j = 0; j < currentChoice.size() - 1; j++) {
				totalWeight += weights[currentChoice.get(j)];
				totalValue += values[currentChoice.get(j)];
			}
			
			// if valid weight, then proceed
			if(totalWeight <= limit) {
				// check if this subset is better than the best choice
				// update the max value and best choice index array
				if(totalValue > maxValue) {
					maxValue = totalValue;
					weightOfMaxValue = totalWeight;
					bestChoice = new ArrayList<Integer>(currentChoice);
				}
			}
			
			// reset the choice list
			currentChoice = new ArrayList<Integer>();
		}
		
		// report the best subset 
		System.out.println("Found the optimal subset to satisfy the problem.");
		System.out.println("Total Weight: " + weightOfMaxValue + " out of " + limit);
		System.out.println("Total Value: " + maxValue);
		System.out.println("Elements in the subset: ");
		for(int j = 0; j < bestChoice.size() - 1; j++) {
			System.out.println(bestChoice.get(j) + ": Weight is " + weights[bestChoice.get(j)] + ", Value is " + values[bestChoice.get(j)]);
		}
	}
}

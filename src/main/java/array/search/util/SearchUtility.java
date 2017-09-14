package array.search.util;

public class SearchUtility {
	
	public static boolean  find_logic1(int[][] input, int startRow, int startColumn, int endRow, int endColumn, int target) {
		boolean result = false;		
		int rows = 0, columns = 0;
		int middlerow = 0, middlecol = 0;
		
		rows = endRow - startRow + 1;
		if (rows == 0) return result; 
		columns = endColumn - startColumn + 1;
		
		if(rows == 1 && columns == 1) {
			if(input[startRow][startColumn] == target) {
				result = true;
			}
			else {
				result = false;
			}
			return result;
		}
		
		middlerow = startRow + (rows/2) + (rows %2) -1;
		middlecol = startColumn + (columns/2) + (columns %2) -1;
				
		if(input[middlerow][middlecol] == target) {
			result = true;
			return result;
		} else if(input[middlerow][middlecol] > target) {			
			result = find_logic1(input, 0, 0, middlerow, middlecol, target);
			if (result) return result;
		} else {
			if(middlecol+1 < input[0].length && input[middlerow][endColumn] >= target) {
				result = find_logic1(input, startRow, middlecol+1, middlerow, endColumn, target);
				if (result) return result;
			}
			
			if(middlerow+1 < input.length && input[endRow][middlecol] >= target) {
				result = find_logic1(input, middlerow+1, startColumn, endRow, middlecol, target);
				if (result) return result;
			}
			
			if(middlerow+1 < input.length && middlecol+1 < input[0].length && input[endRow][endColumn] >= target) {
				result = find_logic1(input, middlerow+1, middlecol+1, endRow, endColumn, target);
				if (result) return result;			
			}
		}
		
		return result;
	}
	
	public static boolean  find_logic2(int[][] input, int target) {
		boolean result = false;		
		int rows = 0, columns = 0;		
		
		rows = input.length;
		if (rows == 0) return result; 
		columns = input[0].length;
		
		int row = 0;
		int col = columns -1;
		
		while(row < rows && col >= 0) {
			if(input[row][col] == target) {
				result = true;
				break;
			} else if(input[row][col] > target) {
				col--;
			} else if (input[row][col] < target) {
				row++;
			}
		}
		return result;
	}

}

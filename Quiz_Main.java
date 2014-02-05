import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class Quiz_Main{

	public static void main(String[] args) {
		Quiz q = new Quiz();
		String directory = q.selectQuizDirectory();

		q.loadItems(directory+"questions.txt",directory+"answers.txt");
		
		List<Item> items = new ArrayList<Item>();
		items = q.getItems();

		int counter = 0;
		int totalItems = items.size();

		List<Integer> itemNumbers = new ArrayList<Integer>();
		for(int i = 0 ; i < totalItems ; i++) itemNumbers.add(i);
		long seed = System.nanoTime();
		Collections.shuffle(itemNumbers, new Random(seed));

		while(counter < totalItems){
			Item item = items.get(itemNumbers.get(counter));
			System.out.println();
			
			System.out.println("QUESTION "+itemNumbers.get(counter)+": "+item.getQuestion());
			System.out.print("ANSWER: ");
			Scanner sc = new Scanner(System.in);
			String answer = sc.nextLine().toLowerCase();

			if(answer.equals(item.getAnswer())){
				System.out.println("CORRECT!");
				q.setScore(q.getScore()+1);
			}else{
				System.out.println("WRONG! The answer is "+item.getAnswer());
			}
			System.out.println();
			counter++;
		}
		System.out.println("Your score is "+q.getScore()+" out of "+totalItems+".");
	}
}
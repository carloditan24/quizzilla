import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
import java.util.Arrays;

public class Quiz_Main{

	public static void main(String[] args) {
		Quiz q = new Quiz();
		String directory = q.selectQuizDirectory();

		q.loadItems(directory+"/questions.txt",directory+"/answers.txt");
		List<Item> items = new ArrayList<Item>();
		items = q.getItems();

		int counter = 0;
		int totalItems = items.size();
		int totalPoints = totalItems;
		List<Integer> itemNumbers = new ArrayList<Integer>();
		for(int i = 0 ; i < totalItems ; i++) itemNumbers.add(i);
		long seed = System.nanoTime();
		Collections.shuffle(itemNumbers, new Random(seed));

		while(counter < totalItems){
			Item item = items.get(itemNumbers.get(counter));
			System.out.println();
			
			System.out.println("QUESTION "+itemNumbers.get(counter)+": "+item.getQuestion());
			System.out.print("ANSWER: ");
			
			String answer = item.getAnswer();

			if(answer.indexOf('#') == -1){
				Scanner sc = new Scanner(System.in);
				String attempt = sc.nextLine().toLowerCase();

				if(attempt.equals(answer)){
					System.out.println("CORRECT!");
					q.setScore(q.getScore()+1);
				}else{
					System.out.println("WRONG! The answer is "+answer);
				}
				System.out.println();
			}else{
				String[] answers = answer.split("#");
				String[] correctAttempts = new String[answers.length];
				int i = 0;
				totalPoints += answers.length-1;
				System.out.println();
				for(String a: answers){
					Scanner sc = new Scanner(System.in);
					System.out.print("$: ");
					String attempt = sc.nextLine().toLowerCase();

					if(Arrays.asList(correctAttempts).contains(attempt)){
						System.out.println("\tDUPLICATE ANSWER!");
					}else if(Arrays.asList(answers).contains(attempt)){
						System.out.println("\tCORRECT!");
						correctAttempts[i] = attempt;
						i++;
						q.setScore(q.getScore()+1);
					}else{
						System.out.println("\tWRONG!");
					}
					System.out.println();
				}
				i=0;
				System.out.println("ANSWERS:");
				for(String a: answers){
					System.out.println("\t$ "+a);
				}
			}
			counter++;
		}
		System.out.println("Your score is "+q.getScore()+" out of "+totalPoints+".");
	}
}
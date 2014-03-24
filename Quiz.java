import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Quiz{

	private List<Item> items = new ArrayList<Item>();
	private int score = 0;

	public Quiz(){

	}

	public void setItems(List<Item> items){
		this.items = items;
	}

	public List<Item> getItems(){
		return this.items;
	}

	public void setScore(int score){
		this.score = score;
	}

	public int getScore(){
		return this.score;
	}

	public void loadItems(String questionFile, String answerFile){
		try{
			BufferedReader questionReader = new BufferedReader(new FileReader(questionFile));
			BufferedReader answerReader = new BufferedReader(new FileReader(answerFile));

			String questionLine = null;
			String answerLine = null;

			try{
				while((questionLine = questionReader.readLine()) != null && (answerLine = answerReader.readLine()) != null){
					if(questionLine.indexOf('#') == -1){
						Item i = new Item();
						i.setQuestion(questionLine);
						i.setAnswer(answerLine);
						this.items.add(i);
					}
				}
			}catch(IOException ioe){
				System.out.println(ioe.getMessage());
			}
		}catch(FileNotFoundException fnfe){
			System.out.println(fnfe.getMessage());
		}
	}

	public static String selectQuizDirectory(){
		int item = 1;
		List<String> directories = new ArrayList<String>();
		System.out.println("SELECT TOPIC");
		try{
			BufferedReader reader = new BufferedReader(new FileReader("sources.txt"));
			String line = null;
			try{
				while((line = reader.readLine()) != null){
					String[] parts = line.split("#");
					System.out.println("("+item+") "+parts[0]);
					directories.add(parts[1].replaceAll("\\s+",""));
					item++;
				}
			}catch(IOException ioe){
				System.out.println(ioe.getMessage());
			}
		}catch(FileNotFoundException fnfe){
			System.out.println(fnfe.getMessage());
		}
		Scanner sc = new Scanner(System.in);
		System.out.print("Option: ");
		int option = Integer.parseInt(sc.nextLine())-1;

		return directories.get(option);
	}
}
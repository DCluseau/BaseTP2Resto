/**
 * 
 */
package BaseTP2Resto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 
 */
public class BaseTP2Resto {
	/**
	 * Atributes
	 * menu : list of dishes, organized by types (Entrées, Plats, Accompagnements, Boissons, Desserts)
	 * messages : list of the displayed messages
	 * commands : list of orders in the form of :
	 * 		[0]["dish1"] --> order n°1, 1st dish
	 * 		[0]["dish2"] --> order n°1, 2nd dish
	 * 		[1]["another dish in another order"] --> order n°2, 1st dish
	 */
	HashMap<String, ArrayList<String>> menu = new HashMap<String, ArrayList<String>>();
	HashMap<String, String> messages = new HashMap<String, String>();
	HashMap<Integer, HashMap<String, Integer>> orders = new HashMap<Integer, HashMap<String, Integer>>();
	
	/**
	 * Constructor
	 */
	BaseTP2Resto(){
		/**
		 * Fill the messages list
		 */
		this.messages.put("welcome", "Bonjour, combien de menus souhaitez-vous ?");
		this.messages.put("nbOrder", "Commande numéro ");
		this.messages.put("dishesChoice", "Choix ");
		this.messages.put("starterChoice", "entrée ");
		this.messages.put("mainCourseChoice", "plats");
		this.messages.put("sideDishChoice", "accompagnements");
		this.messages.put("drinkChoice", "boissons");
		this.messages.put("desertChoice", "desserts");
		this.messages.put("askChoice", "Que souhaitez-vous comme %s ? [saisir le chiffre correspondant]");
		this.messages.put("recap", "Résumé de la commande ");
		/**
		 * Fill the lists of dishes by type
		 */
		ArrayList<String> dishes = new ArrayList<String>();
		dishes.add("1 - SALADE ");
		dishes.add("2 - SOUPE ");
		dishes.add("3 - QUICHE ");
		dishes.add("4 - AUCUNE ");
		this.menu.put("ENTREES", dishes);
		dishes.clear();
		dishes.add("1 - POULET ");
		dishes.add("2 - BOEUF ");
		dishes.add("3 - POISSON ");
		dishes.add("4 - VEGETARIEN ");
		dishes.add("5 - VEGAN ");
		dishes.add("6 - AUCUN ");
		this.menu.put("PLATS", dishes);
		dishes.clear();
		dishes.add("1 - RIZ ");
		dishes.add("2 - PATES ");
		dishes.add("3 - FRITES ");
		dishes.add("4 - LEGUMES ");
		dishes.add("5 - AUCUN ");
		this.menu.put("ACCOMPAGNEMENTS", dishes);
		dishes.clear();
		dishes.add("1 - EAU PLATE ");
		dishes.add("2 - EAU GAZEUSE ");
		dishes.add("3 - SODA ");
		dishes.add("4 - VIN ");
		dishes.add("5 - AUCUNE ");
		this.menu.put("BOISSONS", dishes);
		dishes.clear();
		dishes.add("1 - TARTE MAISON ");
		dishes.add("2 - MOUSSE AU CHOCOLAT (du patron) ");
		dishes.add("3 - TIRAMISU ");
		dishes.add("4 - AUCUN ");
		this.menu.put("DESSERTS", dishes);
	}
	
	/**
	 * 
	 * @param msg : id(string) of the message to display (from the ArrayList messages)
	 */
	public void displayMsg(ArrayList<String> msg) {
		String finalMsg = "";
		for(int i = 0; i < msg.size(); i++){
			if(!(this.messages.get(msg.get(i)) == null)){
				finalMsg +=	this.messages.get(msg.get(i));
			}else {
				finalMsg +=	msg.get(i);
			}
		}
		System.out.println(finalMsg);
	}
	
	/**
	 * Function begin a new order. The user has to enter a number of command to command
	 * @return number of orders to order
	 */
	public int beginNewOrd() {
		int nbOrd = 0;
		Scanner scan = new Scanner(System.in);
		ArrayList<String> msgToDisplay = new ArrayList<String>();
		msgToDisplay.add("welcome");
		this.displayMsg(msgToDisplay);
		nbOrd = scan.nextInt();
		scan.close();
		return nbOrd;
	}
	public void makeCommand(int numOrder) {
		ArrayList<String> msgToDisplay = new ArrayList<String>();
		HashMap<String, Integer> addDish = new HashMap<String, Integer>();
		int chosenDish = 0;
		msgToDisplay.add("nbOrder");
		msgToDisplay.add(String.valueOf(numOrder));
		this.displayMsg(msgToDisplay);
		msgToDisplay.clear();
		chosenDish = this.choiceDish("ENTREES");
		addDish.put("ENTREES", chosenDish);
		this.orders.put(1, addDish);
	}
	
	public Integer choiceDish(String typeDish) {
		ArrayList<String> msgToDisplay = new ArrayList<String>();
		int numDish = 0;
		Scanner scan = new Scanner(System.in);
		String dishMsg = this.messages.get("askChoice");
		dishMsg = String.format(dishMsg, this.messages.get("starterChoice"));
		msgToDisplay.add("dishesChoice");
		msgToDisplay.add(typeDish);
		this.displayMsg(msgToDisplay);
		msgToDisplay.clear();
		msgToDisplay.add("[ ");
		for(String dish : this.menu.get(typeDish)) {
			msgToDisplay.add(dish);
		}
		msgToDisplay.add("]");
		this.displayMsg(msgToDisplay);
		msgToDisplay.clear();
		System.out.println(dishMsg);
		// TODO Scanner doesn't wait for an input
		if(scan.hasNextInt()){
			numDish = scan.nextInt();
		}
		scan.close();
		return numDish;
	}
	
	/**
	 * 
	 * Display an order
	 * @param numOrder : number of the order to display
	 */
	public void displayOrder(int numOrder) {
		String dispDish = "";
		for(int i = 0; i < this.orders.get(numOrder).size(); i++) {
			dispDish += this.orders.get(numOrder).toString();
		}
		System.out.println(dispDish);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BaseTP2Resto newOrd = new BaseTP2Resto();
		int numOrd = 0;
		numOrd = newOrd.beginNewOrd();
		newOrd.makeCommand(numOrd);
		newOrd.displayOrder(numOrd);
	}

}

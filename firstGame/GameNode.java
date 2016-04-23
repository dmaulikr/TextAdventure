public class GameNode {	
	public String name; // NAME
	public String text; // The text of the panel
	public String image; // The image of the panel 
	public String aText; // Text of first button
	public String bText; // Text of second button
	public int choiceA; // First button option
	public int choiceB; // Second button option

	/** Constructs new ListNode
	 * @param newData data element for new ListNode
	 * @param nextA pointer to ListNode of first chocie
	 * @param nextB pointer to Listnode of second choice
	 */

	public GameNode (String name, String newText, String newImage, String textOne, String textTwo, int nextA, int nextB) {
		text = newText;
		image = newImage; 
		aText = textOne;
		bText = textTwo;
		choiceA = nextA;
		choiceB = nextB;
	}

	/** Constructs new ListNode with null for next
	 * @param newData data element for new ListNode
	 */

	public GameNode (String newText) {
		text = newText;
		image = null;
		aText = "Option A";
		bText = "Option B";
		choiceA = 0;
		choiceB = 0;
	}

	/** Constructs new ListNode with null for data and image, and both choices (ALL CLEAR)
	 */

	public GameNode () {
		text = null;
		image = null;
		aText = "Option A";
		bText = "Option B";
		choiceA = 0;
		choiceB = 0;
	}


}
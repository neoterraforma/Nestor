package hsahay;

import ks.common.games.Solitaire;
import ks.common.model.BuildablePile;
import ks.common.model.Card;
import ks.common.model.Column;
import ks.common.model.Deck;
import ks.common.view.BuildablePileView;
import ks.common.view.CardImages;
import ks.common.view.ColumnView;
import ks.common.view.IntegerView;
import ks.launcher.Main;

public class Nestor extends Solitaire {

	Deck deck;
	Column[] column = new Column[8];
	BuildablePile reserve;
	
//	DeckView deckView;
	ColumnView[] columnView = new ColumnView[8];
	BuildablePileView reserveView;
	IntegerView scoreView;

	
	@Override
	public String getName() {
		return "hsahay-Nestor";
	}

	@Override
	public boolean hasWon() {
		return false;
	}

	@Override
	public void initialize() {
		// initialize model
		initializeModel(getSeed());
		initializeView();
		initializeControllers();

		/*prepare the game by dealing cards to all columns, 
		 * with one face up card with no overlap on it
		 */
		for (int colNum=0; colNum <=7; colNum++) {
			//5 inner cards per column
			for(int i=1; i<=5; i++)
			{
				Card c = deck.get();
				c.setFaceUp (true);
				column[colNum].add (c);
			}											
			// This one is the top card
			column[colNum].add (deck.get());
			}
				
		// Reserve cards
				for (int i=0; i<1; i++)
				{
					Card c = deck.get();
					
					c.setFaceUp(false);
					reserve.add (deck.get());
				}
				// this card is faceup in initially
				reserve.add (deck.get());
					
	
	}

	private void initializeControllers() {
		// TODO Auto-generated method stub
		
	}

	private void initializeView() {
		// TODO Auto-generated method stub
		CardImages ci = getCardImages();
		
//		deckView = new DeckView (deck);
//		deckView.setBounds (20,0, ci.getWidth(), ci.getHeight());
//		container.addWidget (deckView);

		// create ColumnViews, one after the other with 6 cards in each column
		for (int colNum = 1; colNum <=8; colNum++) {
			columnView[colNum-1] = new ColumnView (column[colNum-1]);
			columnView[colNum-1].setBounds (20+(colNum-1)*10+(colNum-1)*ci.getWidth(), 0, ci.getWidth(), 5*ci.getOverlap()+ci.getHeight());
			container.addWidget (columnView[colNum-1]);
		}
		// create BuildablePileViews, one after the other 
		reserveView = new BuildablePileView(reserve);
		reserveView.setBounds (20, 30+(5*ci.getOverlap())+ci.getHeight(), ci.getWidth(), ci.getOverlap()*4+ci.getHeight());
		container.addWidget (reserveView);
		
		scoreView = new IntegerView (getScore());
		scoreView.setFontSize(16);
		scoreView.setColor(java.awt.Color.white);
		scoreView.setBounds (680,0,100,60);
		container.addWidget (scoreView);
		
		updateScore(0);
				
	}

	private void initializeModel(int seed) {
		deck = new Deck("deck");
		deck.create(seed);
		model.addElement(deck);   // add to our model (as defined within our superclass).
		
		reserve = new BuildablePile("reserve");
		model.addElement(reserve);
		
		// each of the columns appears here
		for (int i = 0; i<8; i++) {
			column[i] = new Column("column" + i);
			model.addElement (column[i]);
		} 
		
	}

	/** Code to launch solitaire variation. */
	public static void main (String []args) {
		// Seed is to ensure we get the same initial cards every time.
		// Here the seed is to "order by suit."
		Main.generateWindow(new Nestor(), Deck.OrderBySuit);
	}

}

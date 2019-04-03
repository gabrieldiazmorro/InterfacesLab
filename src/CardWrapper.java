import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class CardWrapper { 

	public static interface Dealable {
		public  Card dealCard();
		public void shuffleDeck();
	}
	
	/**
	 * Card Collection Class
	 */
	public static class CardDeck implements Cloneable,Collection<Card>, Dealable{

		private Card[] deck;
		private int cardCount;
 
		
		/**
		 * Bare Constructor
		 * By giving a size it will reserve that space
		 * in the array and initialize all that is necessary.
		 * @param size
		 */
		public CardDeck(int size) {
			deck = new Card[size];
			cardCount = 0;
		}
		@Override
		public CardDeck clone() {
			CardDeck cloneDeck = new CardDeck(deck.length);
			for (int i = 0; i < cardCount; i++) {
				cloneDeck.add(deck[i].clone());
			}
			return cloneDeck;
		}

		/**
		 * Array Constructor
		 * Give it a Card array and initialize the class 
		 * with that information.
		 * Assumption: The given deck will be full of cards.
		 * @param deck
		 */
		public CardDeck(Card[] deck) {
			this.deck = deck;
			cardCount = deck.length;
		}

		/**
		 * Allows you to add a card to the bottom of the deck.
		 * @param arg0
		 */
		public boolean add(Card arg0) {
			if(arg0 == null) { return false; }
			if(cardCount >= deck.length) {
				int size = deck.length;
				if(size == 0) {	size = 1; }
				Card[] newList = new Card[size * 2];
				for(int i = 0; i < deck.length; i++) {
					newList[i] = deck[i];
				}
				deck = newList;
			}
			deck[cardCount++] = arg0;
			return true;
		}

		/**
		 * Lets you get the item in the specified index
		 * @param index
		 */
		public Card getCard(int index) {
			if(index < 0 || index >= cardCount) {
				return null;
			}
			return deck[index];
		}

		/**
		 * Let's you get the inner array.
		 * @return
		 */
		public Card[] getDeck() {
			return deck;
		}
		
		/**
		 * Returns the current Card Count.
		 * @return
		 */
		public int getCardCount() {
			return cardCount;
		}
		
		/**
		 * Check if the given argument is equal 
		 * to the instance object.
		 * @param arg0
		 */
		@Override
		public boolean equals(Object arg0) {
			if(arg0 == null || !(arg0 instanceof CardDeck)){ 
				return false; 
			}
			CardDeck cd = (CardDeck) arg0;
			if(this == cd) { return true; };
			if(cardCount != cd.cardCount) { return false; }
			for(int idx = 0; idx < cardCount; idx++) {
				if(!deck[idx].equals(cd.deck[idx])) { return false; }
			}
			return true;
		}
		@Override
		public Iterator<Card> iterator() {
			// TODO Auto-generated method stub
		
			return new DeckIterator(this);
		}
		@Override
		public int size() {
			int count =0;
			for (Card card : deck) {
				if(card!=null) {
					count++;
				}
			}
			return count;
		}
		@Override
		public boolean isEmpty() {
			int count =0;
			for (Card card : deck) {
				if(card!=null) {
					count++;
				}
			}
			if(count == 0) {
				return true;
			}
			return false;
		}
		@Override
		public boolean contains(Object o) {
			int count =0;
			for (Card card : deck) {
				if(card==o) {
					return true;
				}
			}
			return false;
		}
		@Override
		public Object[] toArray() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public <T> T[] toArray(T[] a) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public boolean remove(Object o) {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public boolean containsAll(Collection<?> c) {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public boolean addAll(Collection<? extends Card> c) {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public boolean removeAll(Collection<?> c) {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public boolean retainAll(Collection<?> c) {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public void clear() {
			// TODO Auto-generated method stub
			
		}
		@Override
		public Card dealCard() {
			Card first = deck[0];
			for (int i = 0; i < deck.length-1; i++) {
				deck[i]= deck[i+1];
			
			}
			deck[deck.length-1] =null;
			this.cardCount = this.cardCount-1;
			return first;
		}
		@Override
		public void shuffleDeck() {
			// TODO Auto-generated method stub
			Random rand = new Random();
			Card firstcard = deck[0];
			int randpos =rand.nextInt(cardCount);
			deck[0]= deck[randpos];
			deck[randpos]= firstcard;
		}

		/*  vvvvvvv Not in Use vvvvvvvv [Addd a front slash at the end * 

		@Override
		public boolean remove(Object arg0) {
			return false;
		}

		@Override
		public boolean removeAll(Collection<?> arg0) {
			return false;
		}

		@Override
		public boolean retainAll(Collection<?> arg0) {
			return false;
		}

		@Override
		public boolean addAll(Collection<? extends Card> arg0) {
			return false;
		}

		@Override
		public void clear() {
		}

		@Override
		public boolean containsAll(Collection<?> arg0) {
			return false;
		}

		@Override
		public Object[] toArray() {
			return null;
		}

		@Override
		public <T> T[] toArray(T[] arg0) {
			return null;
		}

		/*  ^^^^^^^ Not in Use ^^^^^^^^ */
		
	}

	/**
	 * An iterator object class to enable to iterate
	 * through the CardDeck collection
	 *
	 */
	public static class DeckIterator implements Iterator<Card> {
		private int currentPos;
		private CardDeck deck;
		private Card[] arrayForm;

		/**
		 * Iterator constructor
		 * @param toIterate
		 */
		public DeckIterator (CardDeck toIterate) {
			this.currentPos = 0;
			this.deck = toIterate;
			this.arrayForm = toIterate.getDeck();
		}

		/**
		 * Check is there is at least one item left to iterate to.
		 */
		@Override
		public boolean hasNext() {
			return currentPos < deck.getCardCount();
		}

		/**
		 * Iterates to the next available item.
		 */
		@Override
		public Card next() {
			if(this.hasNext()) {
				return arrayForm[currentPos++];
			} else {
				throw new NoSuchElementException("No more elements to iterate over.");
			}
		}

	}

	/**
	 * Enum types for the card variables
	 * - Suit
	 * - Rank
	 */
	enum Suit { CLUBS, DIAMONDS, HEARTS, SPADES }
	enum Rank { A, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHTH,
		NINE, TEN, J, Q, K }

	/**
	 * Card Class
	 */
	public static class Card implements Cloneable,Comparable<Card>{
		Suit suit;
		Rank rank;

		/**
		 * Main card class
		 * @param s
		 * @param r
		 */
		public Card(Suit s, Rank r) {
			suit = s;
			rank = r;
		}

		/**
		 * Gives a new instance with the same parameters of
		 * the card.
		 */
		@Override
		public Card clone() {
			return new Card(suit, rank);
		}
		
		public Suit getSuit() { return this.suit; }
		public Rank getRank() { return this.rank; }

		/**
		 * Checks if two cards are the same.
		 * @param arg0
		 */
		@Override
		public boolean equals(Object arg0) {
			if(!(arg0 instanceof Card)) { return false; }
			Card c = (Card) arg0;
			return suit == c.suit && rank == c.rank;
		}

		@Override
		public int compareTo(Card o) {
			if(this.rank.compareTo(o.rank) ==0) {
				if(this.suit.compareTo(o.suit) ==0) {
					return 0;
				}
				return this.suit.compareTo(o.suit);
			}
			return this.rank.compareTo(o.rank);
		}
	}

	
}

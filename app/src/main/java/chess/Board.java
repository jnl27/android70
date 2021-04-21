package chess;
import pieces.Rook;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Queen;

/**
 * The Board class has the entire layout of the chessBoard with the pieces in their designated Spots, effectively a grid of Spot instances
 *
 * @author Param Thakker
 * @author Jonathan Lu
 *
 */
 
public class Board {
	/**
	 * the 2D grid by which Spots are filled, representing the entire Chess Board
	 */
	public Spot[][] grid;

	/**
	 * Constructor
	 */
	public Board() {
		grid=new Spot[8][8];
		makeStartBoard();
	}
	
	/**
	 *  makeStartBoard method is used to place the pieces in their right spots at the beginning of the game.
	 *  It takes no parameters and does not have a return type.
	 */
	public void makeStartBoard() {
		//TODO set starting positions of all pieces
		//black pieces
		grid[0][0] = new Spot(0, 0, new Rook(1));
		grid[0][1] = new Spot(0, 1, new Pawn(1));
		grid[1][0] = new Spot(1, 0, new Knight(1));
		grid[1][1] = new Spot(1, 1, new Pawn(1));
		grid[2][0] = new Spot(2, 0, new Bishop(1));
		grid[2][1] = new Spot(2, 1, new Pawn(1));
		grid[3][0] = new Spot(3, 0, new Queen(1));
		grid[3][1] = new Spot(3, 1, new Pawn(1));
		grid[4][0] = new Spot(4, 0, new King(1));
		grid[4][1] = new Spot(4, 1, new Pawn(1));
		grid[5][0] = new Spot(5, 0, new Bishop(1));
		grid[5][1] = new Spot(5, 1, new Pawn(1));
		grid[6][0] = new Spot(6, 0, new Knight(1));
		grid[6][1] = new Spot(6, 1, new Pawn(1));
		grid[7][0] = new Spot(7, 0, new Rook(1));
		grid[7][1] = new Spot(7, 1, new Pawn(1));

		//white pieces
		grid[0][6] = new Spot(0, 6, new Pawn(0));
		grid[0][7] = new Spot(0, 7, new Rook(0));
		grid[1][6] = new Spot(1, 6, new Pawn(0));
		grid[1][7] = new Spot(1, 7, new Knight(0));
		grid[2][6] = new Spot(2, 6, new Pawn(0));
		grid[2][7] = new Spot(2, 7, new Bishop(0));
		grid[3][6] = new Spot(3, 6, new Pawn(0));
		grid[3][7] = new Spot(3, 7, new Queen(0));
		grid[4][6] = new Spot(4, 6, new Pawn(0));
		grid[4][7] = new Spot(4, 7, new King(0));
		grid[5][6] = new Spot(5, 6, new Pawn(0));
		grid[5][7] = new Spot(5, 7, new Bishop(0));
		grid[6][6] = new Spot(6, 6, new Pawn(0));
		grid[6][7] = new Spot(6, 7, new Knight(0));
		grid[7][6] = new Spot(7, 6, new Pawn(0));
		grid[7][7] = new Spot(7, 7, new Rook(0));

		//set rest of the Spots to null pieces
		for (int a=0;a<8;a++) {
			for (int b=2;b<6;b++) {
				grid[a][b] = new Spot(a, b, null);
			}
		}
	/*	 *  0   1  2  3  4  5  6  7  
		 *|bR|bN|bB|bQ|bK|bB|bN|bR| 0  j
		 *|bP|bP|bP|bP|bP|bP|bP|bP| 1
		 *|  |##|  |##|  |##|  |##| 2
		 *|##|  |##|  |##|  |##|  | 3
		 *|  |##|  |##|  |##|  |##| 4
		 *|##|  |##|  |##|  |##|  | 5
		 *|wP|wP|wP|wP|wP|wP|wP|wP| 6
		 *|wR|wN|wB|wQ|wK|wB|wN|wR| 7  */
		
	}
	
	
	/**
	 * drawBoard method is used to draw the chessBoard for the user to see with piece abbreviations
	 * It takes no parameters and does not have a return type. //UPDATE FOR GUI
	 */
	public void drawBoard() {
		String[][] sboard = new String[8][8];

		int color = 0;
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				if (color == 0){
					sboard[i][j] = "   ";
					color = 1;
				}else{
					sboard[i][j] = "## ";
					color = 0;
				}
			}
			color = color == 0 ? 1 : 0;
		}
		for (int a = 0; a < 8; a++){
			for (int b = 0; b < 8; b++){
				if (this.grid[a][b].getPiece() != null){
					sboard[a][b] = this.grid[a][b].getPiece().getPieceName() + " " ;
				}
			}
		}


		System.out.println();
		for (int a = 0; a < 8; a++){
			for (int b = 0; b < 8; b++){
				System.out.print(sboard[b][a]);
			}
			System.out.print(8-a);
			System.out.println();
		}
		System.out.println(" a  b  c  d  e  f  g  h");
	}
	
	/**
	 * isPathEmpty method is used to check if the path is clear for a particular move to be executed
	 * @param startPosition    The spot where the piece is currently located
	 * @param endPosition      The spot where the piece is attempting to move
	 * @return                 True if the path is clear for the move to be made, false otherwise
	 */
	public boolean isPathEmpty(Spot startPosition, Spot endPosition) {
				
		
		String piece=startPosition.getPiece().getPieceName();
		if (piece.equals("wK") || piece.equals("bK") || piece.equals("bN") || piece.equals("wN")) {
			return true;
		}
				int yChange= endPosition.getYCoordinate()-startPosition.getYCoordinate();
				int xChange=endPosition.getXCoordinate()-startPosition.getXCoordinate();
				
				int startBoxesX = startPosition.getXCoordinate();
				int startBoxesY = startPosition.getYCoordinate();
				
				int yDirection=yChange>0?1:yChange<0?-1:0;
				int xDirection=xChange>0?1:xChange<0?-1:0;

				
				
				if (yChange == 0) { // no change in Y direction, i.e look for only boxes in X direction
					startBoxesX = startBoxesX + xDirection;
					int numberofBoxes=Math.abs(xChange)-1;
					int i=0;
					while (i<numberofBoxes) {
						if (grid[startBoxesX][startBoxesY].getPiece()!=null) {
							return false;
						}
						startBoxesX=startBoxesX+xDirection;
						i++;
					}
					
				}
				else if (xChange == 0) { // no change in X direction, i.e look for only boxes in Y direction
				
					startBoxesY = startBoxesY + yDirection;
					int numberofBoxes=Math.abs(yChange)-1;
					int i=0;
					while (i<numberofBoxes) {
						if (grid[startBoxesX][startBoxesY].getPiece()!=null) {
							return false;
						}
						startBoxesY=startBoxesY+yDirection;
						i++;
					}
					
				}
				
				else { // look for boxes in both Y and X directions
					startBoxesY = startBoxesY + yDirection;
					startBoxesX = startBoxesX + xDirection;
					int numberofBoxes=Math.abs(yChange)-1;
					int i=0;
					while (i<numberofBoxes) {
						if (grid[startBoxesX][startBoxesY].getPiece()!=null) {
							return false;
						}
						startBoxesY = startBoxesY + yDirection;
						startBoxesX=startBoxesX+xDirection;
						i++;
					}
				}
				
				return true;
	}
	

}
